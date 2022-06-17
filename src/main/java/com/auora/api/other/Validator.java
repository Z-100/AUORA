package com.auora.api.other;

import java.util.Collection;

public class Validator<T> {

	public static <T> T notNull(final T t) {
		if (t == null) {
			throw new IllegalArgumentException();
		}
		return t;
	}

	public static <T> T notNull(final T t, final String message) {
		if (t == null) {
			throw new IllegalArgumentException(message);
		}
		return t;
	}

	public static <T> T notNull(final T t, final String[] messages) {
		if (t == null) {
			StringBuilder sb = new StringBuilder();

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(sb.toString());
		}
		return t;
	}

	public static <T> T notNull(final T t, final String message, final Object... values) {
		if (t == null) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return t;
	}

	public static <T> T notNull(final T t, final String[] messages, final Object... values) {
		if (t == null) {
			StringBuilder sb = new StringBuilder();

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(String.format(sb.toString(), values));
		}
		return t;
	}

	public static <T> T[] notEmpty(final T[] t) {
		if (t == null) {
			throw new NullPointerException();
		}

		if (t.length == 0) {
			throw new IllegalArgumentException();
		}
		return t;
	}

	public static <T> T[] notEmpty(final T[] t, final String message) {
		if (t == null) {
			throw new NullPointerException(message);
		}

		if (t.length == 0) {
			throw new IllegalArgumentException(message);
		}
		return t;
	}

	public static <T> T[] notNull(final T[] t, final String[] messages) {
		if (t == null) {
			StringBuilder sb = new StringBuilder();

			for (String s : messages)
				sb.append(s).append(" ");

			throw new NullPointerException(sb.toString());
		}

		if (t.length == 0) {
			StringBuilder sb = new StringBuilder();

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(sb.toString());
		}
		return t;
	}

	public static <T> T[] notEmpty(final T[] t, final String message, final Object... values) {
		if (t == null) {
			throw new NullPointerException(String.format(message, values));
		}

		if (t.length == 0) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return t;
	}

	public static <T> T[] notNull(final T[] t, final String[] messages, final Object... values) {
		if (t == null) {
			StringBuilder sb = new StringBuilder();

			for (String s : messages)
				sb.append(s).append(" ");

			throw new NullPointerException(String.format(sb.toString(), values));
		}

		if (t.length == 0) {
			StringBuilder sb = new StringBuilder();

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(String.format(sb.toString(), values));
		}
		return t;
	}

	public static <T extends Collection<?>> T notEmpty(final T t) {
		if (t == null) {
			throw new NullPointerException();
		}

		if (t.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return t;
	}

	public static <T extends Collection<?>> T notEmpty(final T t, final String message) {
		if (t == null) {
			throw new NullPointerException(message);
		}

		if (t.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
		return t;
	}

	public static <T extends Collection<?>> T notEmpty(final T t, final String[] messages) {
		if (t == null) {
			StringBuilder sb = new StringBuilder();

			for (String s : messages)
				sb.append(s).append(" ");

			throw new NullPointerException(sb.toString());
		}

		if (t.isEmpty()) {
			StringBuilder sb = new StringBuilder();

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(sb.toString());
		}
		return t;
	}

	public static <T extends Collection<?>> T notEmpty(final T t, final String message, final Object... values) {
		if (t == null) {
			throw new NullPointerException(String.format(message, values));
		}

		if (t.isEmpty()) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return t;
	}

	public static <T extends Collection<?>> T notEmpty(final T t, final String[] messages, final Object... values) {
		if (t == null) {
			StringBuilder sb = new StringBuilder();

			for (String s : messages)
				sb.append(s).append(" ");

			throw new NullPointerException(String.format(sb.toString(), values));
		}

		if (t.isEmpty()) {
			StringBuilder sb = new StringBuilder();

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(String.format(sb.toString(), values));
		}
		return t;
	}
}
