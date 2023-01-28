package tn.esprithub.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprithub.Entities.Options;

import tn.esprithub.Entities.User;
import tn.esprithub.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	  private  BCryptPasswordEncoder bCryptPasswordEncoder;
	  @Autowired
	   private  OptionService optionService;
	    
	@Autowired
	private UserRepository repository;
	
	private final LocalDateTime now= LocalDateTime.now();
	  private final LocalDateTime yesterday= LocalDateTime.now().minusDays(1);
	public User findByUserName(String username) {
		return repository.findByUsername(username);
	}
	
	
	public User addUser(User user) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		return repository.save(user);
	}
	
	public User updataUser(User user) {
		return repository.save(user);
	}
	public boolean verrifymail(String email) {
		User user=repository.findByEmail(email);
		return user != null;
		
	}
	
	public boolean verrifyUsername(String username) {
		User user=repository.findByUsername(username);
		return user != null;
	}
	
	 
	    public User update(User user, Long id) {
	    	
	        if (repository.findById(id).get() != null) {
	            User u = repository.findById(id).get();
	            u.setFirstName(user.getFirstName());
	            u.setLastName(user.getLastName());
	            u.setEmail(user.getEmail());
	            u.setGender(user.getGender());
	            u.setNiveau(user.getNiveau());
	            u.setAddress(user.getAddress());
	            Options option = optionService.findById(user.getOption().getIdOption());
	           
	            u.setOption(option);
	            u.setAboutMe(user.getAboutMe());
	            return repository.save(u);

	        }
	        return null;


	    }

	    
	    public void delete(long id) {
	    	repository.deleteById(id);
	    }

	    
	    public List<User> findAll() {
	  
	        return (List<User>) repository.findAll();
	    }

	    
	    public User findById(Long id) {
	        return repository.findById(id).get();
	    }

	    
	    public String getRole(Long id) {
	        User user = repository.findById(id).get();
	        return user.getRole().name();
	        
	    }

	    
	    public User changeROle(User user, Long id) {
	        if (repository.findById(id).isPresent()) {
	            User u = repository.findById(id).get();
	            u.setRole(user.getRole());
	            repository.save(u);
	            return u;
	        }
	        return null;
	    }

	    
	    public User finduserbytoken(String email) {
	        return repository.findByEmail(email);
	    }

	    
	    public String changePassword(String oldpassword, Long id) {
	        if (repository.findById(id).isPresent()) {
	            User u = repository.findById(id).get();
	            if(bCryptPasswordEncoder.matches(oldpassword, u.getPassword())){
	                return "true";
	            }else {
	                return oldpassword;
	            }


	        }
	        return "false";    }
	    
	    
	    public Map<String, String> changePassword(String newPassword, String oldpassword, Long id) {
	        HashMap<String, String> map = new HashMap<>();

	        if (repository.findById(id).isPresent()) {
	            User u = repository.findById(id).get();
	            if (bCryptPasswordEncoder.matches(oldpassword, u.getPassword())) {
	                u.setPassword(bCryptPasswordEncoder.encode(newPassword));
	                repository.save(u);
	                map.put("response", "password changed Successfully");

	            }else {
	                map.put("response", "wrong password");
	            }


	    }
	        return map;
	    }
	    public Long countUsers(){
	        return repository.count();
	    }

	    
	    public Long usersByday() {
	       return repository.findAllActiveUsersNative(yesterday,now).stream().count();
	    }

	    
	    public Long listuserByrRoleUser() {
	        return repository.findUserRole("user").stream().count();
	    }

	    
	    public Long listuserByrRoleTeacher() {
	        return repository.findUserRole("teacher").stream().count();
	    }

	    
	    public List getUserPerMonth() {
	        List<User> users = (List<User>) repository.findAll();
	        int jan = 0 ;
	        int feb = 0 ;
	        int mar = 0 ;
	        int apr = 0 ;
	        int may = 0 ;
	        int jun = 0 ;
	        int july = 0 ;
	        int aug = 0 ;
	        int sep = 0 ;
	        int oct = 0 ;
	        int nov = 0 ;
	        int dec = 0 ;

	        for (User user : users) {
	            if( user.getCreatedat().getMonth().name().equals("JANUARY")){
	                jan=jan+1;
	            }
	            if( user.getCreatedat().getMonth().name().equals("FEBRUARY")){
	                feb=feb+1;
	            } 
	            if( user.getCreatedat().getMonth().name().equals("MARCH")){
	                mar=mar+1;
	            } 
	            if( user.getCreatedat().getMonth().name().equals("APRIL")){
	                apr=apr+1;
	            }
	            if( user.getCreatedat().getMonth().name().equals("MAY")){
	                may=may+1;
	            }
	            if( user.getCreatedat().getMonth().name().equals("JUNE")){
	                jun=jun+1;
	            }
	            if( user.getCreatedat().getMonth().name().equals("JULY")){
	                july=july+1;
	            }
	            if( user.getCreatedat().getMonth().name().equals("AUGUST")){
	                aug=aug+1;
	            }
	            if( user.getCreatedat().getMonth().name().equals("SEPTEMBER")){
	                sep=sep+1;
	            }
	            if( user.getCreatedat().getMonth().name().equals("OCTOBER")){
	                oct=oct+1;
	            }
	            if( user.getCreatedat().getMonth().name().equals("NOVEMBER")){
	                nov=nov+1;
	            }
	            if( user.getCreatedat().getMonth().name().equals("DECEMBER")){
	                dec=dec+1;
	            }
	        }
	        List usersPermonth = new ArrayList();
	        usersPermonth.add(jan);
	        usersPermonth.add(feb);
	        usersPermonth.add(mar);
	        usersPermonth.add(apr);
	        usersPermonth.add(may);
	        usersPermonth.add(jun);
	        usersPermonth.add(july);
	        usersPermonth.add(aug);
	        usersPermonth.add(sep);
	        usersPermonth.add(oct);
	        usersPermonth.add(nov);
	        usersPermonth.add(dec);
	        return usersPermonth;
	    }


}
