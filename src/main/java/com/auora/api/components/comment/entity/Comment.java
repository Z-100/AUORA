package com.auora.api.components.comment.entity;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.question.entity.Question;
import com.auora.api.components.thread.entity.Thread;
import com.auora.api.other.Constants;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.relational.core.mapping.Table;
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
	private Long votes; //+/- possible

	@ManyToOne
	@JoinColumn(name = "fkAccountId")
	@JsonManagedReference
	private Account fkAccountId;

	@ManyToOne
	@JoinColumn(name = "fkQuestionId")
	@JsonManagedReference
	private Question fkQuestionId;

	@ManyToOne
	@JoinColumn(name = "fkThreadId")
	@JsonManagedReference
	private Thread fkThreadId;

	public void addUpvote() {
		votes++;
	}

	public void addDownVote() {
		votes--;
	}
}
