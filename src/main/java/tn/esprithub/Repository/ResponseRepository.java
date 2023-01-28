package tn.esprithub.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprithub.Entities.Response;


@Repository
public interface ResponseRepository extends CrudRepository<Response, Long> {
	
	List<Response> findByIdUser(Long id);

}
