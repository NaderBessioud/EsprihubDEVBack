package tn.esprithub.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprithub.Entities.Tag;


@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
	
	Tag findByTitle(String title);

}
