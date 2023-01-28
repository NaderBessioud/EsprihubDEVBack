package tn.esprithub.Controller;


import lombok.AllArgsConstructor;
import tn.esprithub.Entities.Options;
import tn.esprithub.Services.OptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/options/")
@AllArgsConstructor
public class OptionsController {
	@Autowired
    private  OptionService optionService ;
    @PutMapping("update/{id}")
    public Options update(@RequestBody Options o, @PathVariable("id") Long id) {
        return optionService.update(o, id);
        
    }
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") long id) throws Exception {
        optionService.delete(id);
    }
    @GetMapping("findById/{id}")
    public Options findById(@PathVariable("id") Long id) {
        return optionService.findById(id);
    }
    @GetMapping("list")
    public List<Options> findAll() {
        return optionService.findAll();
    }
    @PostMapping ( "save")
    public Options saveOption(@RequestBody Options o ){
       return    optionService.save(o);
    }
    
    @GetMapping("countOption")
    public Long countOption() {
        return optionService.countOptions();
    }
 
}
