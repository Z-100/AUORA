package com.auora.api.components.account.entity;

import com.auora.api.components.comment.entity.Comment;
import com.auora.api.components.question.entity.Question;
import com.auora.api.components.thread.entity.Thread;
import com.auora.api.other.Constants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = Constants.DB_ACCOUNT)
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "validationsentence")
	private String validationSentence;

	@OneToMany(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "fkAccountId")
	@JsonBackReference
	private List<Question> questions;

	@OneToMany(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "fkAccountId")
	@JsonBackReference
	private List<Thread> threads;

	@OneToMany(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "fkAccountId")
	@JsonBackReference
	private List<Comment> comments;
}
