package org.wecancodeit.reviewssitefullstack;


import java.util.Collection;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	private String title;

	@Lob
	private String content;


	@JsonIgnore
	@ManyToOne
	private Category category;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "reviews")
	private Collection<Tag> tags;
	
	@JsonIgnore
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	public Category getCategory() {
		return category;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	
	public Collection<Tag> getTags() {
		return tags;
	}
	
	public Collection<Comment> getComments() {
		return comments;
	}

	public Review() {

	}

	public Review(String title, String content, Category category) {
		this.title = title;
		this.content = content;
		this.category = category;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}



	
}
