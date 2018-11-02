package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	Collection<Comment> findByReview(Review review);

	Collection<Comment> findByReviewId(long reviewId);

}
