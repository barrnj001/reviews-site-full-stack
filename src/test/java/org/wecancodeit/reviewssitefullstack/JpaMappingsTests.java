package org.wecancodeit.reviewssitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JpaMappingsTests {

	@Resource
	EntityManager entityManager;
	
	@Resource
	ReviewRepository reviewRepo;
	
	@Resource
	CategoryRepository categoryRepo;
	
	@Resource
	TagRepository tagRepo;

	@Resource
	CommentRepository commentRepo;
	
	@Test
	public void shouldSaveAndLoadReview() {
		Review review = reviewRepo.save(new Review("review", "content", null));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		Review reviewResult = result.get();
		assertThat(reviewResult.getTitle(), is("review"));
	}
	
	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review("review", "content", null));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(reviewId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		Category categoryResult = result.get();
		assertThat(categoryResult.getName(), is("category"));
		
	}
	
	@Test
	public void shouldEstablishCategoryToReviewsRelationship() {
		
		Category category = categoryRepo.save(new Category("category"));
		/*reviewRepo.save(review);
		reviewRepo.save(anotherReview);*/
		Review review = reviewRepo.save(new Review("review", "content", category));
		Review anotherReview = reviewRepo.save(new Review("anotherReview", "content", category));
		
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		Category categoryResult = result.get();
		assertThat(categoryResult.getReviews(), containsInAnyOrder(review, anotherReview));
	}

	@Test
	public void shouldSaveAndLoadTag() {
		Tag tag = tagRepo.save(new Tag("tag"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		Tag tagResult = result.get();
		assertThat(tagResult.getName(), is("tag"));
	}
	
	
	@Test
	public void shouldEstablishTagsToReviewsRelationships() {
		
		Review review = reviewRepo.save(new Review("review", "content", null));
		Review anotherReview = reviewRepo.save(new Review("anotherReview", "content", null));
		
		
		Tag tag = new Tag("tag", review, anotherReview);
		tag = tagRepo.save(tag);
		Tag tag2 = new Tag("tag2", review);
		tag2 = tagRepo.save(tag2);
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		Tag tagResult = result.get();
		assertThat(tagResult.getReviews(), containsInAnyOrder(review, anotherReview));
	}

	@Test
	public void shouldFindTagsPerReview() {
		Review review = reviewRepo.save(new Review("review", "content", null));
		Review anotherReview = reviewRepo.save(new Review("anotherReview", "content", null));
		
		Tag tag = new Tag("tag", review);
		tag = tagRepo.save(tag);
		Tag tag2 = new Tag("tag2", review, anotherReview);
		tag2 = tagRepo.save(tag2);
		
		entityManager.flush();
		entityManager.clear();
				
		Collection<Tag> result = tagRepo.findByReviewsContains(review);
		assertThat(result, containsInAnyOrder(tag, tag2));
	}
	
	@Test
	public void shouldFindTagsForReviewsId() {
		Review review = reviewRepo.save(new Review("review", "content", null));
		Review anotherReview = reviewRepo.save(new Review("anotherReview", "content", null));
		
		Tag tag = new Tag("tag", review);
		tag = tagRepo.save(tag);
		Tag tag2 = new Tag("tag2", review, anotherReview);
		tag2 = tagRepo.save(tag2);
		long reviewsId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Tag> result = tagRepo.findByReviewsId(reviewsId);
		assertThat(result, containsInAnyOrder(tag, tag2));
	}
	
	@Test
	public void shouldFindReviewsPerCategory() {
		Category category = new Category("category");
		category = categoryRepo.save(category);
		Tag tag = new Tag("tag");
		tag = tagRepo.save(tag);
		Tag tag2 = new Tag("tag2");
		tag2 = tagRepo.save(tag2);
		Review review = reviewRepo.save(new Review("review", "content", category));
		Review anotherReview = reviewRepo.save(new Review("anotherReview", "content", category));
		
		entityManager.flush();
		entityManager.clear();
				
		Collection<Review> result = reviewRepo.findByCategory(category);	
		assertThat(result, containsInAnyOrder(review, anotherReview));
	}
	
	@Test
	public void shouldFindReviewsForCategoryId() {
		Category category = new Category("category");
		category = categoryRepo.save(category);
		
		Tag tag = new Tag("tag");
		tag = tagRepo.save(tag);
		Tag tag2 = new Tag("tag2");
		tag2 = tagRepo.save(tag2);
		
		Review review = reviewRepo.save(new Review("review", "content", category));
		Review anotherReview = reviewRepo.save(new Review("anotherReview", "content", category));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> result = reviewRepo.findByCategoryId(categoryId);
		assertThat(result, containsInAnyOrder(review, anotherReview));
	}
	
	@Test
	public void shouldSaveAndLoadComment() {
		Comment comment = commentRepo.save(new Comment("userName", "comment", null));
		long commentId = comment.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Comment> result = commentRepo.findById(commentId);
		Comment commentResult = result.get();
		assertThat(commentResult.getContent(), is("comment"));
	}
	
	@Test
	public void shouldEstablishCommentsToReviewRelationship() {
		Review review = reviewRepo.save(new Review("review", "content", null));
		Comment comment = commentRepo.save(new Comment("userName", "content", review));
		Comment anotherComment = commentRepo.save(new Comment("userName", "content", review));
		
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		Review reviewResult = result.get();
		assertThat(reviewResult.getComments(), containsInAnyOrder(comment, anotherComment));
	}
	
	@Test
	public void shouldFindCommentsPerReview() {
		Review review = reviewRepo.save(new Review("review", "content", null));
		Comment comment = commentRepo.save(new Comment("userName", "content", review));
		Comment anotherComment = commentRepo.save(new Comment("userName", "content", review));
		
		
		entityManager.flush();
		entityManager.clear();
				
		Collection<Comment> result = commentRepo.findByReview(review);	
		assertThat(result, containsInAnyOrder(comment, anotherComment));
	}
	
	@Test
	public void shouldFindCommentsForReviewId() {
		Review review = reviewRepo.save(new Review("review", "content", null));
		Comment comment = commentRepo.save(new Comment("userName", "content", review));
		Comment anotherComment = commentRepo.save(new Comment("userName", "content", review));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Comment> result = commentRepo.findByReviewId(reviewId);
		assertThat(result, containsInAnyOrder(comment, anotherComment));
	}
	
	
}
	


