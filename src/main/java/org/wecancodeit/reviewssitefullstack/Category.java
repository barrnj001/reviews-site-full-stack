package org.wecancodeit.reviewssitefullstack;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue
	long id;
	String name;

	@OneToMany(mappedBy = "category")
	private Collection<Review> reviews;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

}
