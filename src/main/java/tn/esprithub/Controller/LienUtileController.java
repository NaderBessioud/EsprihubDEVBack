package tn.esprithub.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import tn.esprithub.Entities.LienUtile;
import tn.esprithub.Exception.RessourceNotFoundException;
import tn.esprithub.Repository.LienUtileRepository;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("lienUtile")

public class LienUtileController {
	@Autowired
	private LienUtileRepository lienUtileRepository;
	
	 @GetMapping("/liensUtiles")
	    public List <LienUtile> getAllLiens(){
	        return lienUtileRepository.findAll();
	    }
	 @PostMapping("/lienUtile")
	    public LienUtile createLien( @Validated @RequestBody LienUtile lu) {
	    	return lienUtileRepository.save(lu);
	    }
	 
	  @GetMapping("/lienUtile/{id}")
	    public ResponseEntity<LienUtile> getUEById( @PathVariable(value="id") Long id) 
	    		throws RessourceNotFoundException {
		  LienUtile lu= lienUtileRepository.findById(id)
	    		.orElseThrow(()-> new RessourceNotFoundException("LienUtile Not Found"+id));
	    return ResponseEntity.ok().body(lu);
	    }
	  
	   @PutMapping("/lienUtile/{id}")
		public ResponseEntity<LienUtile> updateLien(@PathVariable(value = "id") Long Id,
				@Validated @RequestBody LienUtile luDetails) 
						throws RessourceNotFoundException {
		   LienUtile lu = lienUtileRepository.findById(Id)
					.orElseThrow(() -> new RessourceNotFoundException("lien Utile not found for this id :: " + Id));

		   lu.setLibelle(luDetails.getLibelle());
			final LienUtile updatedLu = lienUtileRepository.save(lu);
			return ResponseEntity.ok(updatedLu);
		}
	   
	   @DeleteMapping("/lienUtile/{id}")
		public Map<String, Boolean> deleteLien(@PathVariable(value = "id") Long Id)
				throws RessourceNotFoundException {
		   LienUtile lu = lienUtileRepository.findById(Id)
					.orElseThrow(() -> new RessourceNotFoundException("UE not found for this id :: " + Id));

		   lienUtileRepository.delete(lu);
			Map<String, Boolean> response = new HashMap<>();
			response.put("lien Utile has been deleted:", Boolean.TRUE);
			return response;
		}
}
