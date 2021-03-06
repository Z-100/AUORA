package com.auora.api.components.thread.entity;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.comment.entity.Comment;
import com.auora.api.other.Constants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = Constants.DB_THREAD)
public class Thread {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "votes")
	private Long votes = 1L;

	@OneToMany(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "fkThreadId")
	@JsonBackReference
	private List<Comment> comments;

	@ManyToOne
	@JoinColumn(name = "fk_account_id")
	@JsonManagedReference
	private Account fkAccountId;

	public void addUpvote() {
		votes++;
	}

	public void addDownVote() {
		votes--;
	}
}
