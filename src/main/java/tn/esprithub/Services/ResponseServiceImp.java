package tn.esprithub.Services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprithub.Entities.Question;
import tn.esprithub.Entities.Response;
import tn.esprithub.Entities.TypeBadge;
import tn.esprithub.Entities.User;
import tn.esprithub.Entities.UserQuestion;
import tn.esprithub.Repository.QuestionRepository;
import tn.esprithub.Repository.ResponseRepository;
import tn.esprithub.Repository.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;



@Service
public class ResponseServiceImp implements IResponseService {
	@Autowired
	private ResponseRepository responseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	

	@Override
	public Response addResponse(Response r) {
		r.setDatepub(new Date());
		r.setApproved(0);
	return	responseRepository.save(r);
	}

	@Override
	public List<Response> retrieveResponses() {
		return (List<Response>) responseRepository.findAll();
	}

	@Override
	public void deleteResponse(Long id) {
		responseRepository.deleteById(id);
		
	}

	@Override
	public Response updateResponse(Response u) {
		return responseRepository.save(u);
	}

	@Override
	public Response retrieveResponse(Long id) {
		return responseRepository.findById(id).orElse(null);
	}
	
	public List<UserQuestion> getQuestionAnswers(Long id) throws IOException{
		List<UserQuestion> result=new ArrayList<>();
		Question question=questionRepository.findById(id).get();
		
		
		
		for (Response response : question.getQuestionresponse()) {
			User user=userRepository.findById(response.getIdUser()).get();
			String nom=user.getFirstName()+" "+user.getLastName();
			if(response.getApproved()==1 || response.getApproved()==2) {
			result.add(new UserQuestion(response.getIdResponse(),nom, response.getContent(), response.getDatepub(),"", 0, user.getRole().toString(),AffectBadge(response.getIdUser()),this.downloadImage(user.getImage())));
			
			}
			
		}
		
		
		
	
		return result;
	}
	
	
	
	public List<UserQuestion> getQuestionAnswersNotApproved(Long id) throws IOException{
		List<UserQuestion> result=new ArrayList<>();
		Question question=questionRepository.findById(id).get();
		
		
		
		for (Response response : question.getQuestionresponse()) {
			User user=userRepository.findById(response.getIdUser()).get();
			String nom=user.getFirstName()+" "+user.getLastName();
			if(response.getApproved()==0)
				result.add(new UserQuestion(response.getIdResponse(), nom, response.getContent(), response.getDatepub(),"", 0, user.getRole().toString(),AffectBadge(response.getIdUser()),this.downloadImage(user.getImage())));

		}
		
		
		
	
		return result;
	}
	
	public void ApproveAnswer(Long id) {
		Response response=responseRepository.findById(id).get();
		response.setApproved(1);
		responseRepository.save(response);
	}
	
	@Transactional
	public void CancelAnswer(Long id,Long idu) {
		User user=userRepository.findById(idu).get();
		List<Response> responses=new ArrayList<>();
		Response response=responseRepository.findById(id).get();
		response.setApproved(3);
		responseRepository.save(response);
		
		for (Response res : responseRepository.findByIdUser(idu)) {
			if(res.getApproved()==3)
				responses.add(res);
		}
		
		for(int i=0;i<responses.size();i++) {
			int nb=0;
			for(int j=0;j<responses.size();j++) {
				
				LocalDate dateBefore = responses.get(i).getDatepub().toInstant()
					      .atZone(ZoneId.systemDefault())
					      .toLocalDate();
				LocalDate dateafter = responses.get(j).getDatepub().toInstant()
					      .atZone(ZoneId.systemDefault())
					      .toLocalDate();
				long diff=ChronoUnit.DAYS.between(dateBefore,dateafter);
				if(diff==7 || diff==-7)
					nb++;
			}
			
			if(nb>=5) {
				Date date=new Date();
				Calendar c = Calendar.getInstance();
		        c.setTime(date);
		        c.add(Calendar.DATE, 7);
				user.setBanned(true);
				user.setDatebanned(c.getTime());
			}
		}
		
		
	}
	
	public void CommentAnswer(Long id) {
		Response response=responseRepository.findById(id).get();
		response.setApproved(2);
		responseRepository.save(response);
	}
	
	public float getRightAnswerPerCent(Long id) {
		List<Response> responses=responseRepository.findByIdUser(id);
		int nbrRight=0;
		int nbTotal=0;
		for (Response response : responses) {
			if(response.getApproved()==1)
				nbrRight++;
			if(response.getApproved()==1 || response.getApproved()==2)
				nbTotal++;
		}
		if(nbTotal == 0)
			return 0;
		else
		
			return (nbrRight/nbTotal)*100;
		
	}
	
	public TypeBadge AffectBadge(Long id) {
		float p=getRightAnswerPerCent(id);
		if(p >= 70)
			return TypeBadge.gold;
		else if(p >=50)
			return TypeBadge.silver;
		else 
			return TypeBadge.bronze;
		
	}
	 public String downloadImage( String name) throws IOException {
		 FTPClient ftpClient = new FTPClient();
		 String encodidImage="";
		 byte[] data;
		  try {
      		
       	   ftpClient.connect("172.19.0.3", 21);
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
	 
	 public Response addResponseWithQuestion(Response r,long idq) {
			r.setDatepub(new Date());
			r.setApproved(0);
			r.setResponses(questionRepository.findById(idq).get());
		return	responseRepository.save(r);
		}
	 
	

}
