package tn.esprithub.Services;

import tn.esprithub.Entities.LienUtile;

public interface ICourseService {
	public void affecterLienCours(Long  idCours, Long idLink);
	public void addLinkAndAssignCourse(LienUtile link, Long idC);

}
