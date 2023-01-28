package tn.esprithub.Entities;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity

public class Options {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOption")
    private Long idOption;
	 @Column(name = "libelle")
	 private String libelle;
	 
	 @JsonIgnore
	 @OneToMany(mappedBy = "option")
	 private Set<User> useroption;
	 
	 @Column(name = "discription")
		private String discription;


		
		
		public  Options( Long idOption ){
			this.idOption=idOption;
		}




		public Options() {
			super();
			// TODO Auto-generated constructor stub
		}




		public Long getIdOption() {
			return idOption;
		}




		public void setIdOption(Long idOption) {
			this.idOption = idOption;
		}




		public String getLibelle() {
			return libelle;
		}




		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}



		@JsonIgnore
		public Set<User> getUseroption() {
			return useroption;
		}



		@JsonIgnore
		public void setUseroption(Set<User> useroption) {
			this.useroption = useroption;
		}




		public String getDiscription() {
			return discription;
		}




		public void setDiscription(String discription) {
			this.discription = discription;
		}
		
		
		
		
		
}
