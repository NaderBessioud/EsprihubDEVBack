package tn.esprithub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprithub.Entities.Cours;
import tn.esprithub.Entities.LienUtile;
import tn.esprithub.Exception.RessourceNotFoundException;
import tn.esprithub.Repository.CoursRepository;
import tn.esprithub.Services.CourseService;

import java.util.Map;
import java.util.HashMap;



import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("course")
public class CoursController {
    @Autowired
    private CoursRepository coursRepository;
    @Autowired
	   CourseService courseService;
    @GetMapping("/courses")
    public List <Cours> getAllCourses(){
        return coursRepository.findAll();
    }

    @PostMapping("/course")
    public Cours createCours( @Validated @RequestBody Cours c) {
    	return coursRepository.save(c);
    }
    
    @GetMapping("/course/{id}")
    public ResponseEntity<Cours> getCoursById( @PathVariable(value="id") Long id) 
    		throws RessourceNotFoundException {
    Cours cours= coursRepository.findById(id)
    		.orElseThrow(()-> new RessourceNotFoundException("course Not Found"+id));
    return ResponseEntity.ok().body(cours);
    }
    
    @PutMapping("/course/{id}")
	public ResponseEntity<Cours> updateCourse(@PathVariable(value = "id") Long Id,
			@Validated @RequestBody Cours courseDetails) 
					throws RessourceNotFoundException {
		Cours c = coursRepository.findById(Id)
				.orElseThrow(() -> new RessourceNotFoundException("course not found for this id :: " + Id));

		c.setLibelle(courseDetails.getLibelle());
		final Cours updatedCourse = coursRepository.save(c);
		return ResponseEntity.ok(updatedCourse);
	}
    @DeleteMapping("/course/{id}")
	public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long Id)
			throws RessourceNotFoundException {
		Cours c = coursRepository.findById(Id)
				.orElseThrow(() -> new RessourceNotFoundException("course not found for this id :: " + Id));

		coursRepository.delete(c);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
    
    
	 
	   @PostMapping("/affecterCourseLu/{idLu}")
	   @ResponseBody
	   public String affecterCourseUE(@PathVariable("idLu")Long idC,@RequestBody LienUtile lien ) {
		   courseService.addLinkAndAssignCourse(lien, idC);
		   return "Done";
		   
	   }
	   
	
	   @PutMapping("/affecterCourseLu/{idCourse}/{idLu}")
	   @ResponseBody
	   public String affecterCourseUE(@PathVariable("idLu")Long idLu,@PathVariable("idCourse")Long idCourse ) {
		   courseService.affecterLienCours(idCourse, idLu);
		   return "Done";
		   
	   }
    
    
    
    
    
    
    
    
    }