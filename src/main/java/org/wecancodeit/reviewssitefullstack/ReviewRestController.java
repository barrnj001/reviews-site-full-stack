package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewRestController {
	
	@Resource
	ReviewRepository reviewRepo;
	
	@Resource
	TagRepository tagRepo;
	
	@GetMapping("/api/reviews/{id}/tags")
	public Iterable<Tag> getAllTags(@PathVariable Long id) {
		Optional<Review> result = reviewRepo.findById(id);
		Review reviewResult = result.get();
		Collection<Tag> tags = reviewResult.getTags();
		return tags;
	}
	
	@GetMapping("/api/reviews/{id}")
	public Review getReview(@PathVariable Long id) {
		return reviewRepo.findById(id).get();
	}
	

}
