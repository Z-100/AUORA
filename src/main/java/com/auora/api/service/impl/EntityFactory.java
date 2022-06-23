package com.auora.api.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	public static <T> List<T> getListInstance(Class<T> t) {
		try {
			return new ArrayList<T>();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public static <T extends Collection<?>, E> T getInstance(Class<T> t, Class<E> args) {
		try {
			return t.getDeclaredConstructor(args).newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
}
