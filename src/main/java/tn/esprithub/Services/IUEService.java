package tn.esprithub.Services;

import tn.esprithub.Entities.Cours;

public interface IUEService {

	public void affecterCoursUE(Long  idCours, Long idUE);
	public void addCourseAndAssignUE(Cours cours, Long idUE);
}
