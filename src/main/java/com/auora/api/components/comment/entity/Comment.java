package com.auora.api.components.comment.entity;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.question.entity.Question;
import com.auora.api.components.thread.entity.Thread;
import com.auora.api.other.Constants;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = Constants.DB_COMMENT)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "votes")
	private Long votes = 1L; //+/- possible

	@ManyToOne
	@JoinColumn(name = "fk_account_id")
	@JsonManagedReference
	private Account fkAccountId;

	@ManyToOne
	@JoinColumn(name = "fk_question_id")
	@JsonManagedReference
	private Question fkQuestionId;

	@ManyToOne
	@JoinColumn(name = "fk_thread_id")
	@JsonManagedReference
	private Thread fkThreadId;

	public void addUpvote() {
		votes++;
	}

	public void addDownVote() {
		votes--;
	}
}
