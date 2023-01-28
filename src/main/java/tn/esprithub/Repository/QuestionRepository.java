package tn.esprithub.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprithub.Entities.Question;


@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
