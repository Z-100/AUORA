package com.auora.api.components.question.entity;

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
@Table(name = Constants.DB_ACCOUNT)
public class Question {

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

	@OneToMany(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "fkThreadId")
	@JsonBackReference
	private List<Comment> comments;
}