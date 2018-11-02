package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
	
	Collection<Tag> findByReviewsContains(Review review);

	Collection<Tag> findByReviewsId(long id);
	
	Optional<Tag> findByName(String name);
	
	

}
