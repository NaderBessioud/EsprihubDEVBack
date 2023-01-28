package tn.esprithub.Services;



import java.util.List;

import tn.esprithub.Entities.Options;

public interface OptionService {
    Options update(Options option, Long id);
    void delete(long id);
    List<Options> findAll();
    Options findById(Long id);
    Options save (Options o);
    Long countOptions();
}
