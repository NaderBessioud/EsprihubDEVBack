package tn.esprithub.Controller;


import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;

import java.util.List;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprithub.Entities.Question;

import tn.esprithub.Entities.Ressource;
import tn.esprithub.Entities.UserQuestion;
import tn.esprithub.Services.QuestionServiceImp;
import tn.esprithub.Services.ResponseServiceImp;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/question")

public class QuestionController {
	
	@Autowired
	private QuestionServiceImp questionservice;
	
	@Autowired
	private ResponseServiceImp responseServiceImp;
	
	
	
	
	@PostMapping("/addQuestion")
	@ResponseBody
	public Question addQuestion(@RequestBody Question question,@RequestParam("ress") Long ress,@RequestParam("idu") long idu,@RequestParam("idm") long idm) {
		return questionservice.addQuestion(question,ress,idu,idm);
	}
	
	@PostMapping("/addQuestionWRessource")
	@ResponseBody
	public Question addQuestionWithoutRessource(@RequestBody Question question,@RequestParam("idu") long idu,@RequestParam("idm") long idm) {
		 return questionservice.addQuestionWithoutRessource(question,idu,idm);
	}
	
	
	@PutMapping("/updateQuestion")
	@ResponseBody
	public Question updateQuestion(@RequestBody Question question) {
		return questionservice.updateProduit(question);
	}
	
	@GetMapping("/Question")
	@ResponseBody
	public Question retrieveQuestion(@RequestParam("id") Long id) {
		return questionservice.retrieveQuestion(id);
	}
	
	@GetMapping("/Questions")
	@ResponseBody
	public List<Question> retrieveQuestions(){
		return questionservice.retrieveQuestions();
	}
	
	@GetMapping("/Userquestions")
	@ResponseBody
	public List<UserQuestion> getAllUserQuestions() throws IOException, SQLException{
		return questionservice.getAllUserQuestions();
	}
	
	@GetMapping("/Userquestion")
	@ResponseBody
	public UserQuestion getUserQuestion(@RequestParam("id") Long id) throws IOException,  SQLException {
		return questionservice.getQuestion(id);
	}
	
	@PutMapping("/ResponseNumber")
	public void updateAnswersNumber(@RequestBody Question question) {
		questionservice.updateAnswersNumber(question.getIdQuestion());
	}
	
	@PutMapping("/LikeQuestion")
	public void updateLike(@RequestBody Question question,@RequestParam("idu") Long idu) {
		questionservice.LikeQuestion(question.getIdQuestion(),idu);
	}
	
	@PutMapping("/DislikeQuestion")
	public void updateDislike(@RequestBody Question question,@RequestParam("idu") Long idu) {
		questionservice.DislikeQuestion(question.getIdQuestion(),idu);
	}
	
	@PutMapping("/CloseQuestion")
	public void closeQuestion(@RequestBody Question question) {
		
		questionservice.closeQuestion(question.getIdQuestion());
	}
	
	
	@PostMapping("/UploadFile")
	@ResponseBody
	public String uploadfile(@RequestParam("file") MultipartFile file) {
		return questionservice.uploadfile(file);
	}
	
	@GetMapping("/downloadFile")
	@ResponseBody
	public String downloadFile(@RequestParam("name") String name) throws  SQLException {
		return questionservice.downloadFile(name);
	}
	
	@GetMapping("/downloadImage")
	@ResponseBody
	public String downloadImage(@RequestParam("name") String name) throws IOException  {
		return questionservice.downloadImage(name);
	}
	
	 @PostMapping("/upload")
	 @ResponseBody
	 public String uploadImage(@RequestParam(value = "imageFile", required = true) MultipartFile  uploadFile) {
		   FTPClient ftpClient = new FTPClient();
		 
		     try {
		    	
		    	 
		         ftpClient.connect("192.168.2.179", 21);
		         ftpClient.login("ftp-user", "ftpuser");
		         ftpClient.enterLocalPassiveMode();

		         ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		         // APPROACH #1: uploads first file using an InputStream
		        

		     
		         InputStream inputStream = uploadFile.getInputStream();
		         System.out.println("Start uploading first file");
		         
		         boolean done = ftpClient.storeFile(uploadFile.getOriginalFilename(), inputStream);
		         inputStream.close();
		         if (done) {
		             System.out.println("The first file is uploaded successfully.");
		         }

		         
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
		     return uploadFile.getOriginalFilename();

		}
	
	@PostMapping("/addRessource")
	@ResponseBody
	public Long addRessource(@RequestBody Ressource ressource) {
		return questionservice.addRessource(ressource);
	}
	
	@GetMapping("/TeachersQuestion")
	@ResponseBody
	public List<UserQuestion> getTeachersQuestions() throws IOException,  SQLException{
		return questionservice.getTeachersQuestion();
	}
	
	@GetMapping("/StudentsQuestion")
	@ResponseBody
	public List<UserQuestion> getStudentsQuestions() throws IOException,  SQLException{
		return questionservice.getStudentsQuestion();
	}
	
	@GetMapping("/countQuestions")
	@ResponseBody
	public Long countQuestions(){
		return questionservice.countQuestions();
	}
	
	@GetMapping("/QuestionByTag")
	@ResponseBody
	public List<UserQuestion> getQuestionByTag(@RequestParam("tag") String tag) throws  IOException, SQLException{
		return questionservice.getQuestionByTag(tag);
	}
	
	@GetMapping("/QuestionByUE")
	@ResponseBody
	public List<UserQuestion> getQuestionByUE(@RequestParam("libelle") String libelle) throws  IOException, SQLException{
		return questionservice.getQuestionByUE(libelle);
	}
	
	
	@GetMapping("/QuestionByTagAndUE")
	@ResponseBody
	public List<UserQuestion> getQuestionByTagAndUe(@RequestParam("tag") String tag,@RequestParam("libelle") String libelle) throws  IOException, SQLException{
		return questionservice.getQuestionByTagAndUE(tag, libelle);
	}

	
	
	

}
