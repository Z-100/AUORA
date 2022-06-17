package com.auora.api.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonStringTest {

	String token = "token";

	JsonString jsonString = new JsonString(token);

	@Test
	void testCorrectToken() {
		assertEquals(token, jsonString.token());
	}

	@Test
	void testIncorrectToken() {
		assertNotEquals("", jsonString.token());
	}
}