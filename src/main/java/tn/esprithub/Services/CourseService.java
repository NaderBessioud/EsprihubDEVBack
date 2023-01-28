package tn.esprithub.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprithub.Entities.Cours;
import tn.esprithub.Entities.LienUtile;
import tn.esprithub.Repository.CoursRepository;
import tn.esprithub.Repository.LienUtileRepository;

@Service

public class CourseService implements ICourseService {

	
	@Autowired
	LienUtileRepository luRepo;
	@Autowired
	CoursRepository courseRepo;
	
	@Override
	@Transactional	
	public void affecterLienCours(Long  idCours, Long idLink)
	{
		LienUtile lu= luRepo.findById(idLink).get();
		Cours course= courseRepo.findById(idCours).get();
		lu.setCourslien(course);
		luRepo.save(lu);
	}
	
	
	@Override
	@Transactional	
	public void addLinkAndAssignCourse(LienUtile link, Long idC)
	{
		Cours c=courseRepo.findById(idC).orElse(null);
		link.setCourslien(c);
		luRepo.save(link);

	
		
	}

}
