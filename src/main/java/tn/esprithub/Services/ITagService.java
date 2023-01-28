package tn.esprithub.Services;

import java.util.List;

import tn.esprithub.Entities.Tag;





public interface ITagService {
	Tag addTag (Tag q);
	List<Tag> retrieveTags();

	

	void deleteTag(Long id);

	Tag updateTag(Tag u);

	Tag retrieveTag(Long id);

}
