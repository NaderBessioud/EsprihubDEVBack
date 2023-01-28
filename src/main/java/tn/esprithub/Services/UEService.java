package tn.esprithub.Services;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprithub.Entities.Cours;
import tn.esprithub.Entities.UE;

import tn.esprithub.Repository.CoursRepository;
import tn.esprithub.Repository.UeRepository;



@Service
public class UEService implements IUEService{

	@Autowired
	UeRepository ueRepo;
	@Autowired
	CoursRepository courseRepo;
	
		@Override
		@Transactional
		public void affecterCoursUE(Long idCours, Long idUE) {
			UE ue= ueRepo.findById(idUE).get();
			Cours course= courseRepo.findById(idCours).get();
			course.setUecours(ue);
			courseRepo.save(course);
		}
		@Override
		@Transactional
		public void addCourseAndAssignUE(Cours cours, Long idUE)
 {
			UE ue= ueRepo.findById(idUE).orElse(null);
			cours.setUecours(ue);
			courseRepo.save(cours);
		}
		
		
		
		
}
