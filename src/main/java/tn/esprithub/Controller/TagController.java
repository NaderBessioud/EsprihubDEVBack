package tn.esprithub.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

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

import tn.esprithub.Entities.Tag;
import tn.esprithub.Entities.UserQuestion;
import tn.esprithub.Services.TagService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tag")

public class TagController {

	@Autowired
	private TagService tagservice;
	
	@PostMapping("/addTag")
	@ResponseBody
	public Tag addTag(@RequestBody Tag tag) {
		return tagservice.addTag(tag);
	}
	
	@PutMapping("/updateTag")
	@ResponseBody
	public Tag updateTag(@RequestBody Tag tag) {
		return tagservice.updateTag(tag);
	}
	
	@GetMapping("/Tag")
	@ResponseBody
	public Tag getTag(@RequestParam("id") Long id ) {
		return tagservice.retrieveTag(id);
	}
	
	@GetMapping("/Tags")
	@ResponseBody
	public List<Tag> getTags(){
		return tagservice.retrieveTags();
	}
	
	
	@GetMapping("/addTagAndAffectQuestion")
	public void addTagAndAffectQuestion(@RequestParam("list") String tags,@RequestParam("id") Long id) {
		tagservice.addTagAndAffectQuestion(tags, id);
	}
	
	@GetMapping("/QuestionByTag")
	@ResponseBody
	public List<UserQuestion> getQuestionByTag(@RequestParam("tag") String title) throws IOException,  SQLException{
		return tagservice.getQuestionByTag(title);
	}
	
	@GetMapping("/TeacherQuestionByTag")
	@ResponseBody
	public List<UserQuestion> getTeachersQuestionByTag(@RequestParam("tag") String title) throws IOException,  SQLException{
		return tagservice.getTeachersQuestionsByTag(title);
	}
	
	@GetMapping("/SimilarQuestionByTags")
	@ResponseBody
	public List<UserQuestion> getSimilarQuestionByTags(@RequestParam("tags") String tags) throws IOException,  SQLException{
		return tagservice.getSimilarQuestionByTags(tags);
	}
}
