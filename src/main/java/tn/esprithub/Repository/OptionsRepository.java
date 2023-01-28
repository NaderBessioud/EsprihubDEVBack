package tn.esprithub.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprithub.Entities.Options;

@Repository
@Transactional(readOnly = true)
public interface OptionsRepository extends JpaRepository<Options, Long> {
}
