package com.auora.api.service;

import java.util.Collection;

 /**
 * @author Z-100
 * A generic solution for a null & empty checker with many choices as parameters.
 *
 * @param <T> A reference to the to-be-checked object
 */
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
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

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
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

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
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

			for (String s : messages)
				sb.append(s).append(" ");

			throw new NullPointerException(sb.toString());
		}

		if (t.length == 0) {
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

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
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

			for (String s : messages)
				sb.append(s).append(" ");

			throw new NullPointerException(String.format(sb.toString(), values));
		}

		if (t.length == 0) {
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

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
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

			for (String s : messages)
				sb.append(s).append(" ");

			throw new NullPointerException(sb.toString());
		}

		if (t.isEmpty()) {
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

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
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

			for (String s : messages)
				sb.append(s).append(" ");

			throw new NullPointerException(String.format(sb.toString(), values));
		}

		if (t.isEmpty()) {
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(String.format(sb.toString(), values));
		}
		return t;
	}

	public static <T, E> void equals(final T t, final E e) {
		if (t == null || e == null) {
			throw new IllegalArgumentException();
		}

		if (!t.equals(e)) {
			throw new IllegalArgumentException();
		}
	}

	public static <T, E> void equals(final T t, final E e, String message) {
		if (t == null || e == null) {
			throw new IllegalArgumentException(message);
		}

		if (!t.equals(e)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static <T, E> void equals(final T t, final E e, String[] messages) {
		if (t == null || e == null) {
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(sb.toString());
		}

		if (!t.equals(e)) {
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(sb.toString());
		}
	}

	public static <T, E> void equals(final T t, final E e, String message, Object... values) {
		if (t == null || e == null) {
			throw new IllegalArgumentException(String.format(message, values));
		}

		if (!t.equals(e)) {
			throw new IllegalArgumentException(String.format(message, values));
		}
	}

	public static <T, E> void equals(final T t, final E e, String[] messages, Object... values) {
		if (t == null || e == null) {
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(String.format(sb.toString(), values));
		}

		if (!t.equals(e)) {
			StringBuilder sb = EntityFactory.getInstance(StringBuilder.class);

			for (String s : messages)
				sb.append(s).append(" ");

			throw new IllegalArgumentException(String.format(sb.toString(), values));
		}
	}
}
