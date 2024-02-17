package spring.jpa.mappings.one.to.many.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "REVIEW_COMMENT")
	private String reviewComment;

	public Review() {}
	
	public Review(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewComment=" + reviewComment + "]";
	}
}
