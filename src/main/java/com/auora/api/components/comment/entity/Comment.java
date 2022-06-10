package com.auora.api.components.comment.entity;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.question.entity.Question;
import com.auora.api.components.thread.entity.Thread;
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
@Table(name = Constants.DB_ACCOUNT)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

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
}