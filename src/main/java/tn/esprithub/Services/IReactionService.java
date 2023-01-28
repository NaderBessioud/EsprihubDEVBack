package tn.esprithub.Services;

import java.util.List;

import tn.esprithub.Entities.Reaction;



public interface IReactionService {
	
	Reaction addReaction (Reaction r);
	List<Reaction> retrieveReactions();

	

	void deleteReaction(Long id);

	Reaction updateReaction(Reaction r);

	Reaction retrieveReaction(Long id);

}
