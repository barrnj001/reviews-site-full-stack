package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagRestController {
	
	@Resource
	TagRepository tagRepo;
	
	@Resource
	ReviewRepository reviewRepo;
	
	@GetMapping("/api/tags")
	public Iterable<Tag> getAllTags() {
		Collection<Tag> tags =(Collection<Tag>) tagRepo.findAll();
		return tags;
	}
	
	@GetMapping("/api/tags/{id}")
	public Tag getTag(@PathVariable Long id) {
		return tagRepo.findById(id).get();
	}
	
	@PutMapping("/api/reviews/{reviewId}/tags/{tagName}")
	public Tag createTag(@PathVariable long reviewId, @PathVariable String tagName) throws ReviewNotFoundException {
		//Get tag from repository
		Optional<Tag> tagResult = tagRepo.findByName(tagName);
		Tag newTag;
		//if tag not present, create it
		if (tagResult.isPresent()) {
			newTag = tagResult.get();
		} else {
			newTag = tagRepo.save(new Tag(tagName));
		}
		//get the review from repository
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		
		//if review not present, throw review not found exception
		if (!reviewResult.isPresent()) {
			throw new ReviewNotFoundException();
		}
		Review review = reviewResult.get();
		//if review.tags contains tag
		Collection<Review> tagReviews = newTag.getReviews();
		if(tagReviews.contains(review)) {
			return newTag;
		} else {
			//else review.tags  add tag
			tagReviews.add(review);
		}
		  
	      //save review to repo
			tagRepo.save(newTag);
		 //return tag
		return newTag;

		
	/*	Collection<Review> newTagReviews = newTag.getReviews();
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		newTagReviews.add(review);*/
		//return tagRepo.save(newTag);
	}
	
	
}
