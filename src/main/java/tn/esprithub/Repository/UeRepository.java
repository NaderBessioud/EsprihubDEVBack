package tn.esprithub.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprithub.Entities.UE;




public interface UeRepository extends JpaRepository<UE,Long> {
	List<UE> findByLibelle(String libelle);
}
