package tn.esprithub.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprithub.Entities.Cours;
import tn.esprithub.Entities.LienUtile;
import tn.esprithub.Repository.CoursRepository;
import tn.esprithub.Repository.LienUtileRepository;



public class LienUtileService implements ILienUtileService {
	@Autowired
	LienUtileRepository luRepo;
	@Autowired
	CoursRepository courseRepo;
	

			@Override
			@Transactional
			public void affecterLienCours(Long  idCours, Long idLien) {
				LienUtile lu= luRepo.findById(idLien).get();
				Cours course= courseRepo.findById(idCours).get();
				lu.setCourslien(course);
				luRepo.save(lu);
			}
}
