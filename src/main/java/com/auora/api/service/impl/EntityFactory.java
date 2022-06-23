package com.auora.api.service.impl;

import java.util.Collection;

/**
 * @author Z-100
 * A generic solution for a Factory. Can generate all kinds of classes with the parameters T & E
 *
 * @param <T> A reference to the to-be-instanced class
 * @param <E> Any parameters, which are needed by the classes constructor
 */
public class EntityFactory <T, E> {

	public static <T> T getInstance(Class<T> obj) {
		try {
			return obj.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@SafeVarargs
	public static <T, E> T getInstance(Class<T> obj, E... args) {
		try {
			return obj.getDeclaredConstructor(obj).newInstance((Object[]) args);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public static <T extends Collection<?>> T getInstance(Class<T> obj, String... s) {
		try {
			return obj.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
