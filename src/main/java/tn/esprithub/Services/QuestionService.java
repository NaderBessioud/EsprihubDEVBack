package tn.esprithub.Services;

import java.util.List;

import tn.esprithub.Entities.Question;




public interface QuestionService {

	
	List<Question> retrieveQuestions();

	

	void deleteQuestion(Long id);

	Question updateProduit(Question u);

	Question retrieveQuestion(Long id);
	Long countQuestions();
}
