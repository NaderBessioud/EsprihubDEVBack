package tn.esprithub.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprithub.Entities.Response;
import tn.esprithub.Entities.UserQuestion;
import tn.esprithub.Services.ResponseServiceImp;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/response")
public class ResponseController {

	@Autowired
	private ResponseServiceImp responseServiceImp;
	
	@PostMapping("/addAnswer")
	@ResponseBody
	public Response addResponse(@RequestBody Response response,@RequestParam("idq") long idq) {
		
		return responseServiceImp.addResponseWithQuestion(response,idq);
	}
	
	
	@PutMapping("/updateResponse")
	@ResponseBody
	public Response updateResponse(@RequestBody Response response) {
		return responseServiceImp.updateResponse(response);
	}
	
	@GetMapping("/Response")
	@ResponseBody
	public Response getResponse(@RequestParam("id") Long id) {
		return responseServiceImp.retrieveResponse(id);
	}
	
	@GetMapping("/Responses")
	@ResponseBody
	public List<Response> getResponses(){
		return responseServiceImp.retrieveResponses();
	}
	
	@GetMapping("/QuestionAnswers")
	@ResponseBody
	public List<UserQuestion> getQuestionAnswers(@RequestParam("id") Long id) throws IOException{
		
		return responseServiceImp.getQuestionAnswers(id);
	}
	
	@GetMapping("/QuestionAnswersNotApproved")
	@ResponseBody
	public List<UserQuestion> getQuestionAnswersNotApproved(@RequestParam("id") Long id) throws IOException{
		
		return responseServiceImp.getQuestionAnswersNotApproved(id);
	}
	
	@PutMapping("/ApproveAnswer")
	
	public void ApproveAnswer(@RequestBody Response response ) {
		responseServiceImp.ApproveAnswer(response.getIdResponse());
	}
	
	
	@DeleteMapping("/CancelAnswer")
	public void CancelAnswer(@RequestParam("id") Long id) {
		responseServiceImp.deleteResponse(id);
	}
	
	@PutMapping("/CommentAnswer")
	public void CommentAnswer(@RequestBody Response response ) {
		responseServiceImp.CommentAnswer(response.getIdResponse());
	}
	
}
