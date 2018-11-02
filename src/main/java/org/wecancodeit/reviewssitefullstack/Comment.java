package org.wecancodeit.reviewssitefullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Lob
	private String content;
	
	private String userName;
	
	@ManyToOne
	private Review review;
	
	
	public long getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getContent() {
		return content;
	}
	
	public Review getReview() {
		return review;
	}
	
	public Comment() {
	}

	public Comment(String userName, String content, Review review) {
		this.userName = userName;
		this.content = content;
		this.review = review;
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
