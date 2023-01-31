package tn.esprithub.Controller;


import lombok.AllArgsConstructor;
import tn.esprithub.Entities.User;
import tn.esprithub.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/user/")
@AllArgsConstructor
public class UserController {
	@Autowired
    private  UserService userService;
	
    @PutMapping("update/{id}")
    public User update(@RequestBody User user, @PathVariable("id") Long id) {
        return userService.update(user, id);
       
    }
    @PutMapping("managment/changeRole/{id}")
    public User ChangeRole(@RequestBody User user, @PathVariable("id") Long id) {
      return userService.changeROle(user, id);
      
    }


    @DeleteMapping("managment/delete/{id}")
    public void delete(@PathVariable("id") long id)  {
        userService.delete(id);
    }
    @GetMapping("managment/findById/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }
    @GetMapping("list")
    public List<User> findAll() {
        return userService.findAll();
    }
    @GetMapping("managment/getrole/{id}")
    public String getrole(@PathVariable("id") Long id) {
        return userService.getRole(id);
    }

    @GetMapping("managment/getbyemail/{id}")
    public String getuserbyEmail(@PathVariable("id") Long id) {
        return userService.getRole(id);
    }


    @GetMapping("findByToken")
    public User findUserByToken() {
        return userService.finduserbytoken(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PostMapping("insertImage")
    public String updateImage(@RequestParam("File") String file,@RequestBody User user)  {

        
        
    	user.setImage(file);
    	
        userService.updataUser(user);
        
        return "The File Uploaded Successfully";
    }
   
    
    @PutMapping ( "changePassowrd/{id}")
   public Map<String, String> changePassword(@RequestParam("oldpassword") String oldpassword, @RequestParam("newPassword") String newPassword , @PathVariable("id") Long id){
       return userService.changePassword(newPassword,oldpassword,id);

   }
   @GetMapping ("countUser")
       public Long countusers (){
       return userService.countUsers();
   }
   @GetMapping ("usersDay")
   public Long getauserenabled (){
       return userService.usersByday();
   }
   //get user with User role
   @GetMapping ("getRoleUser")
   public Long getRoleUser (){
       return userService.listuserByrRoleUser();
   }    //get user with Teacher role

   @GetMapping ("getRoleTeacher")
   public Long getRoleTeacher (){
       return userService.listuserByrRoleTeacher();
   }
   @GetMapping ("getUserPerMonth")
   public List getUserPerMonth (){
       return userService.getUserPerMonth();
   }
}
