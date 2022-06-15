package com.auora.api.other;

import java.util.Collection;

public class Validate<T> {

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

	public static <T> T notNull(final T t, final String message, final Object... values) {
		if (t == null) {
			throw new IllegalArgumentException(String.format(message, values));
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

	public static <T> T[] notEmpty(final T[] t, final String message, final Object... values) {
		if (t == null) {
			throw new NullPointerException(String.format(message, values));
		}

		if (t.length == 0) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return t;
	}

	public static <T extends Collection<?>> T notEmpty(final T collection) {
		if (collection == null) {
			throw new NullPointerException();
		}
		if (collection.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return collection;
	}

	public static <T extends Collection<?>> T notEmpty(final T collection, final String message) {
		if (collection == null) {
			throw new NullPointerException(message);
		}
		if (collection.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
		return collection;
	}

	public static <T extends Collection<?>> T notEmpty(final T collection, final String message, final Object... values) {
		if (collection == null) {
			throw new NullPointerException(String.format(message, values));
		}
		if (collection.isEmpty()) {
			throw new IllegalArgumentException(String.format(message, values));
		}
		return collection;
	}
}
