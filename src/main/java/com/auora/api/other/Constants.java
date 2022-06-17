package com.auora.api.other;

public class Constants {

	/* URL Links for Controllers */

		/* General */
		public static final String URL_GET = "/get";
		public static final String URL_ALL = "-all";
		public static final String URL_ADD = "/add";
		public static final String URL_DELETE = "/delete";
		public static final String URL_UPDATE = "/update";

		public static final String URL_UPVOTE = "-upvote";
		public static final String URL_DOWNVOTE = "-downvote";
		public static final String URL_ADD_COMMENT = "-comment";

		/* Account specific */
		public static final String URL_ACCOUNT = "/account";
		public static final String URL_LOGIN = "/login";
		public static final String URL_REGISTER = "/register";

		/* Comment specific */
		public static final String URL_COMMENT = "/comment";

		/* Question specific */
		public static final String URL_QUESTION = "/question";

		/* Thread specific */
		public static final String URL_THREAD = "/thread";


	/* Database information */
	public static final String DB_ACCOUNT = "account";
	public static final String DB_QUESTION = "question";
	public static final String DB_COMMENT = "comment";
	public static final String DB_THREAD = "thread";

	/* API Response */
	public static final String SUCCESS = "Success!";
	public static final String SOMETHING_WRONG = "Oops! Something went wrong!";
	public static final String INVALID_PASSWORD = "Entered E-Mail and/or Password invalid";
	public static final String ALREADY_EXISTS = "The entered e-mail address already exists!";

	public static final String EMAIL_NOT_NULL = "E-Mail can not be null!";
	public static final String PASSWORD_NOT_NULL = "Password can not be null!";
	public static final String TITLE_NOT_NULL = "E-Mail and/or description can not be null!";
	public static final String NOT_EXISTS = "The requested entity/entities is null.";
}