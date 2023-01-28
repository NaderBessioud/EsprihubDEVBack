package tn.esprithub.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import tn.esprithub.Entities.Cours;

@Repository
public interface CoursRepository extends JpaRepository<Cours,Long>{

}
