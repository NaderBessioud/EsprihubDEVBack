package tn.esprithub.Services;



import java.io.File;

import java.io.IOException;
import java.io.InputStream;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;



import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprithub.Entities.Question;
import tn.esprithub.Entities.Reaction;

import tn.esprithub.Entities.Ressource;
import tn.esprithub.Entities.Role;
import tn.esprithub.Entities.Tag;
import tn.esprithub.Entities.TypeReaction;
import tn.esprithub.Entities.TypeRessource;
import tn.esprithub.Entities.UE;
import tn.esprithub.Entities.User;
import tn.esprithub.Entities.UserQuestion;
import tn.esprithub.Repository.QuestionRepository;
import tn.esprithub.Repository.ReactionRepository;
import tn.esprithub.Repository.RessourceRepository;
import tn.esprithub.Repository.TagRepository;
import tn.esprithub.Repository.UeRepository;
import tn.esprithub.Repository.UserRepository;




@Service
public class QuestionServiceImp implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TagRepository repository;
	@Autowired
	private ReactionRepository reactionRepository;
	
	@Autowired
	private ResponseServiceImp responseServiceImp;
	
	@Autowired
	private RessourceRepository repository2;
	
	@Autowired
	private UeRepository ueRepository;
	
	
	
	@Transactional
	public Question addQuestionWithoutRessource(Question q,long idu,long idm) {
		User user=userRepository.findById(idu).get();
		UE ue=ueRepository.findById(idm).get();
		

		
		
	q.setClosed(false);
	q.setDatepub(new Date());
	q.setUserquestions(user);
	q.setUe(ue);
	
	Question question= questionRepository.save(q);
	user.getQuestions().add(q);
	userRepository.save(user);
	ue.getUequestions().add(question);
	ueRepository.save(ue);
	return question;

	}

	@Transactional
	public Question addQuestion(Question q,Long idr,long idu,long idm) {
			
			User user=userRepository.findById(idu).get();
			Ressource ressource=repository2.findById(idr).get();
			UE ue=ueRepository.findById(idm).get();

			
			
		q.setClosed(false);
		q.setDatepub(new Date());
		q.setUserquestions(user);
		q.setUe(ue);
		
		Question question= questionRepository.save(q);
		user.getQuestions().add(q);
		userRepository.save(user);
		ressource.setRessources(q);
		repository2.save(ressource);
		ue.getUequestions().add(question);
		ueRepository.save(ue);
		return question;
	}

	@Override
	public List<Question> retrieveQuestions() {
		return (List<Question>) questionRepository.findAll();
	}

	@Override
	public void deleteQuestion(Long id) {
		questionRepository.deleteById(id);
	}

	@Override
	public Question updateProduit(Question u) {
		return questionRepository.save(u);
	}

	@Override
	public Question retrieveQuestion(Long id) {
		return questionRepository.findById(id).orElse(null);
	}
	
	public List<UserQuestion> getAllUserQuestions() throws IOException,  SQLException{
		List<Question> questions =this.retrieveQuestions();
		List<UserQuestion> result=new ArrayList<>();
	
		
		for (Question q : questions) {
			User user=userRepository.findById(q.getUserquestions().getId()).get();
			String nom=user.getFirstName()+" "+user.getLastName();
			List<String> tags=new ArrayList<>();
			for (Tag tag : q.getTags()) {
				tags.add(tag.getTitle());	
			}
			
			Ressource ressource=q.getQuestionressources().stream().findFirst().orElse(null);
			if(ressource != null) {
			if(ressource.getType() ==TypeRessource.Image){
				
				result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(),tags,q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()),this.downloadImage(q.getUserquestions().getImage()),this.downloadImage(ressource.getLibelle())));
		
			}
				else
			
					result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(),tags,q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()),this.downloadImage(q.getUserquestions().getImage()),this.downloadFile(ressource.getLibelle())));
			
			}
			else {
				result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(),tags,q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()),this.downloadImage(q.getUserquestions().getImage()),""));

			}
			
			
			
		}
		
		return result;
	}
	
	public UserQuestion getQuestion(Long id) throws IOException,  SQLException {
		Question question=questionRepository.findById(id).get();
		String nom=question.getUserquestions().getFirstName()+" "+question.getUserquestions().getLastName();
		List<String> tags=new ArrayList<>();
		for (Tag tag : question.getTags()) {
			tags.add(tag.getTitle());
			
		}
		Ressource ressource=question.getQuestionressources().stream().findFirst().orElse(null);
		if(ressource != null) {
		if(ressource.getType() ==TypeRessource.Image){
			return new UserQuestion(question.getIdQuestion(), nom, question.getContent(), question.getDatepub(), question.getTitle(), 0, tags, question.getUserquestions().getRole().toString(),responseServiceImp.AffectBadge(question.getUserquestions().getId()),question.isClosed(),this.downloadImage(question.getUserquestions().getImage()),this.downloadImage(ressource.getLibelle()));
	
		}
			else
		
				return new UserQuestion(question.getIdQuestion(), nom, question.getContent(), question.getDatepub(), question.getTitle(), 0, tags, question.getUserquestions().getRole().toString(),responseServiceImp.AffectBadge(question.getUserquestions().getId()),question.isClosed(),this.downloadImage(question.getUserquestions().getImage()),this.downloadFile(ressource.getLibelle()));
		
		}
		else {
			return new UserQuestion(question.getIdQuestion(), nom, question.getContent(), question.getDatepub(), question.getTitle(), 0, tags, question.getUserquestions().getRole().toString(),responseServiceImp.AffectBadge(question.getUserquestions().getId()),question.isClosed(),this.downloadImage(question.getUserquestions().getImage()),"");

		}
		}
	
	@Transactional
	public void addQuestionAndAffectTag(Question question,Long id) {
		
		
		Tag tag=repository.findById(id).get();
		
		question.getTags().add(tag);
		questionRepository.save(question);
		tag.getQuestiontag().add(question);
		repository.save(tag);
		
	}
	
	public void updateAnswersNumber(Long id) {
		Question question=questionRepository.findById(id).get();
		question.setNbresp(question.getNbresp()+1);
		questionRepository.save(question);
	}
	
	@Transactional
	public void LikeQuestion(Long id,Long idu) {
		Reaction reaction=new Reaction();
		reaction.setDate(new Date());
		reaction.setIdUser(idu);
		reaction.setTypereaction(TypeReaction.Like);
		Question question=questionRepository.findById(id).get();
		question.setLikes(question.getLikes()+1);
		reaction.setQuestionReaction(question);
		reactionRepository.save(reaction);
		question.getReactions().add(reaction);
		questionRepository.save(question);
		
		
	}
	
	@Transactional
	public void DislikeQuestion(Long id,Long idu) {
		Reaction reaction=new Reaction();
		reaction.setDate(new Date());
		reaction.setIdUser(idu);
		reaction.setTypereaction(TypeReaction.Dislike);
		Question question=questionRepository.findById(id).get();
		question.setDislike(question.getDislike()+1);
		
		reaction.setQuestionReaction(question);
		reactionRepository.save(reaction);
		question.getReactions().add(reaction);
		questionRepository.save(question);
	}
	
	public void closeQuestion(Long id) {
		Question question=questionRepository.findById(id).get();
		question.setClosed(true);
		questionRepository.save(question);
		
	}
	
	
	
	 public String uploadfile(MultipartFile uploadFile) {
		   FTPClient ftpClient = new FTPClient();
		     try {
		    	
		    	 
		         ftpClient.connect("192.168.1.17", 21);
		         ftpClient.login("ftp-user", "ftpuser");
		         ftpClient.enterLocalPassiveMode();

		         ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		         // APPROACH #1: uploads first file using an InputStream
		        

		      
		         InputStream inputStream = uploadFile.getInputStream();
		         String extension = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
		         System.out.println("Start uploading first file");
		    
		         boolean done = ftpClient.storeFile(uploadFile.getOriginalFilename(), inputStream);
		         inputStream.close();
		         if (done) {
		             System.out.println("The first file is uploaded successfully.");
		         }

		         /*
		         // APPROACH #2: uploads second file using an OutputStream
		         File secondLocalFile = new File("E:/Test/Report.doc");
		         String secondRemoteFile = "test/Report.doc";
		         inputStream = new FileInputStream(secondLocalFile);

		         System.out.println("Start uploading second file");
		         OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
		         byte[] bytesIn = new byte[4096];
		         int read = 0;

		         while ((read = inputStream.read(bytesIn)) != -1) {
		             outputStream.write(bytesIn, 0, read);
		         }
		         inputStream.close();
		         outputStream.close();

		         boolean completed = ftpClient.completePendingCommand();
		         if (completed) {
		             System.out.println("The second file is uploaded successfully.");
		         }*/
		         return uploadFile.getOriginalFilename();

		     } catch (IOException ex) {
		         ex.printStackTrace();
		     } finally {
		         try {
		             if (ftpClient.isConnected()) {
		                 ftpClient.logout();
		                 ftpClient.disconnect();
		             }
		         } catch (IOException ex) {
		             ex.printStackTrace();
		         }
		     }
		     return null;

		}
	 
	 public String downloadFile(String name) throws  SQLException {
		 FTPClient ftpClient = new FTPClient();
		
		 String src="";
	        try {
	        		
	        	   ftpClient.connect("192.168.1.17", 21);
			         ftpClient.login("ftp-user", "ftpuser");
	            ftpClient.enterLocalPassiveMode();
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	            // APPROACH #2: using InputStream retrieveFileStream(String)
	            File file=new File(name);
	            String remoteFile2 = name;
	  
	            InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
	            
	            byte[] bytesArray = IOUtils.toByteArray(inputStream);
	            src="data:application/pdf;base64,"+Base64.getEncoder().encodeToString(bytesArray);
	            
	            FileUtils.writeByteArrayToFile(file,bytesArray );
	          
	            boolean success = ftpClient.completePendingCommand();
	            if (success) {
	                System.out.println("File #2 has been downloaded successfully.");
	            }
	        
	            inputStream.close();
	           
	            
	           
	            
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	        
	        return src;
	 }
	 
	 public long addRessource(Ressource ressource) {
		
		return repository2.save(ressource).getIdRessource();
		 
	 }
	 
	 public List<UserQuestion> getTeachersQuestion() throws IOException,  SQLException{
		 List<Question> list=new ArrayList<>(); 
		 List<Question> questions =this.retrieveQuestions();
			List<UserQuestion> result=new ArrayList<>();
		for (Question question :questions) {
			if(question.getUserquestions().getRole()==Role.teacher)
				list.add(question);
		}
			
			for (Question q : list) {
				User user=userRepository.findById(q.getUserquestions().getId()).get();
				String nom=user.getFirstName()+" "+user.getLastName();
				List<String> tags=new ArrayList<>();
				for (Tag tag : q.getTags()) {
					tags.add(tag.getTitle());	
				}
			
				
				Ressource ressource=q.getQuestionressources().stream().findFirst().orElse(null);
				if(ressource != null) {
				if(ressource.getType() ==TypeRessource.Image){
					result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(),tags,q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()),this.downloadImage(q.getUserquestions().getImage()),this.downloadImage(ressource.getLibelle())));
			
				}
					else
				
						result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(),tags,q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()),this.downloadImage(q.getUserquestions().getImage()),this.downloadFile(ressource.getLibelle())));
				
				}
				else {
					result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(),tags,q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()),this.downloadImage(q.getUserquestions().getImage()),""));

				}
				
			}
			
			return result;
		 
	 }
	 
	 public List<UserQuestion> getStudentsQuestion() throws IOException,  SQLException{
		 List<Question> list=new ArrayList<>(); 
		 List<Question> questions =this.retrieveQuestions();
			List<UserQuestion> result=new ArrayList<>();
		for (Question question :questions) {
			if(question.getUserquestions().getRole()==Role.user)
				list.add(question);
		}
			
			for (Question q : list) {
				User user=userRepository.findById(q.getUserquestions().getId()).get();
				String nom=user.getFirstName()+" "+user.getLastName();
				List<String> tags=new ArrayList<>();
				for (Tag tag : q.getTags()) {
					tags.add(tag.getTitle());	
				}
			
				
				Ressource ressource=q.getQuestionressources().stream().findFirst().orElse(null);
				if(ressource != null) {
				if(ressource.getType() ==TypeRessource.Image){
					result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(),tags,q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()),this.downloadImage(q.getUserquestions().getImage()),this.downloadImage(ressource.getLibelle())));
			
				}
					else
				
						result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(),tags,q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()),this.downloadImage(q.getUserquestions().getImage()),this.downloadFile(ressource.getLibelle())));
				
				}
				else {
					
						
					result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(),tags,q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()),this.downloadImage(q.getUserquestions().getImage()),""));

				}
				
			}
			
			return result;
		 
	 }
	 
	 public String downloadImage( String name) throws IOException {
		 FTPClient ftpClient = new FTPClient();
		 String encodidImage="";
		 byte[] data;
		  try {
      		
       	   ftpClient.connect("192.168.1.17", 21);
		         ftpClient.login("ftp-user", "ftpuser");
           ftpClient.enterLocalPassiveMode();
           ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

           
           // APPROACH #2: using InputStream retrieveFileStream(String)
           String remoteFile2 = name;
           
         
           InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
         
          
     
       
           data = IOUtils.toByteArray(inputStream);
          
         

           boolean success = ftpClient.completePendingCommand();
           if (success) {
               System.out.println("File #2 has been downloaded successfully.");
           }
       
           inputStream.close();
          
           
           
            encodidImage=Base64.getEncoder().encodeToString(data);
           encodidImage="data:image/png;base64,"+encodidImage;
        

           


       } catch (IOException ex) {
           
           ex.printStackTrace();
          
       }
		  
		  finally {
           try {
               if (ftpClient.isConnected()) {
                   ftpClient.logout();
                   ftpClient.disconnect();
                   
               }
           } catch (IOException ex) {
               ex.printStackTrace();
           }
           
           return encodidImage; 
          
       }
	 }
	 
	 @Override
		public Long countQuestions() {
			return questionRepository.count();
		}
	 
	 public List<UserQuestion> getQuestionByUE(String libelle) throws  IOException, SQLException{
		 List<UE> ues=ueRepository.findByLibelle(libelle);
		 List<Question> questions=new ArrayList<>();
		 List<UserQuestion> userQuestions=new ArrayList<>();
		 for (UE ue : ues) {
			questions.addAll(ue.getUequestions());
		}
		 try {
		 for (Question q : questions) {
			 List<String> tags=new ArrayList<>();
			 for (Tag t : q.getTags()) {
				tags.add(t.getTitle());
			}
			 String nom=q.getUserquestions().getFirstName()+" "+q.getUserquestions().getLastName();
			 Ressource ressource=q.getQuestionressources().stream().findFirst().orElse(null);
				if(ressource != null) {
					if(ressource.getType()==TypeRessource.Image)
						userQuestions.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(), tags, q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()), this.downloadImage(q.getUserquestions().getImage()),this.downloadImage(ressource.getLibelle())));

				
				else {
					userQuestions.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(), tags, q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()), this.downloadImage(q.getUserquestions().getImage()),this.downloadFile(ressource.getLibelle())));

				}
				}
				else {
					userQuestions.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(), tags, q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()), this.downloadImage(q.getUserquestions().getImage()),""));

				}
			
		}
		 
	 }

	 catch(NullPointerException ex) {
	 	ex.printStackTrace();
	 }
	 		
	 		
	 	
	 	
		return userQuestions;
			
		}
	 
	 
		public List<UserQuestion> getQuestionByTag(String title) throws IOException,  SQLException{
			Tag tag=repository.findByTitle(title);
			List<UserQuestion> result=new ArrayList<>();
	try {
			for (Question q : tag.getQuestiontag()) {
				List<String> tags=new ArrayList<>();
				for (Tag tagg : q.getTags()) {
					tags.add(tagg.getTitle());
				}
				
				String nom=q.getUserquestions().getFirstName()+" "+q.getUserquestions().getLastName();
				
				Ressource ressource=q.getQuestionressources().stream().findFirst().orElse(null);
				if(ressource != null) {
					if(ressource.getType()==TypeRessource.Image)
						result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(), tags, q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()), this.downloadImage(q.getUserquestions().getImage()),this.downloadImage(ressource.getLibelle())));

				
				else {
					result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(), tags, q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()), this.downloadImage(q.getUserquestions().getImage()),this.downloadFile(ressource.getLibelle())));

				}
				}
				else {
					result.add(new UserQuestion(q.getIdQuestion(), nom, q.getContent(), q.getDatepub(), q.getTitle(), q.getNbresp(), tags, q.getUserquestions().getRole().toString(),responseServiceImp.getQuestionAnswersNotApproved(q.getIdQuestion()).size(),responseServiceImp.AffectBadge(q.getUserquestions().getId()), this.downloadImage(q.getUserquestions().getImage()),""));

				}

			}
			}
	catch(NullPointerException ex) {
		ex.printStackTrace();
		
	}
			return result;
			
		}
		
		
		public List<UserQuestion> getQuestionByTagAndUE(String tag,String libelle) throws  IOException, SQLException{
			List<UserQuestion> tagq=new ArrayList<>();
			List<UserQuestion> ueq=new ArrayList<>();
			List<UserQuestion> result=new ArrayList<>();
			tagq=this.getQuestionByTag(tag);
			ueq=this.getQuestionByUE(libelle);
			result.addAll(tagq);
			for (UserQuestion userQuestion : ueq) {
				if( tagq.contains(userQuestion))
					result.add(userQuestion);
			}
			return result;
		}
	

}
