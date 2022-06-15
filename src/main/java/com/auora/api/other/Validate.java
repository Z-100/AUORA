package com.auora.api.other;

public class Validate<T> {

	public static <T> T notNull(T t) {
		if (t == null) {
			throw new IllegalArgumentException("Object is null");
		}
		return t;
	}
}
