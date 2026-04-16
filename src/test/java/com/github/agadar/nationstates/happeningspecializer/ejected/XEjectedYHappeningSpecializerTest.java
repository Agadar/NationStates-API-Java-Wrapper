package com.github.agadar.nationstates.happeningspecializer.ejected;

import com.github.agadar.nationstates.domain.common.happening.Happening;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class XEjectedYHappeningSpecializerTest {

	@Test
	public void isOfSpecializedType_true() {
		System.out.println("isOfSpecializedType_true");

		// Arrange
		var specializer = new XEjectedYHappeningSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@devils_hand@@ ejected @@devils_feet@@ from the region.");

		// Act
		boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

		// Assert
		assertTrue(isOfSpecializedType);
	}

	@Test
	public void isOfSpecializedType_false() {
		System.out.println("isOfSpecializedType_false");

		// Arrange
		var specializer = new XEjectedYHappeningSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@devils_hand@@ ejected and banned @@devils_feet@@ from the region.");

		// Act
		boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

		// Assert
		assertFalse(isOfSpecializedType);
	}

	@Test
	public void toSpecializedType() {
		System.out.println("toSpecializedType");

		// Arrange
		var specializer = new XEjectedYHappeningSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@devils_hand@@ ejected @@devils_feet@@ from the region.");

		// Act
		var specialized = specializer.toSpecializedType(happening);

		// Assert
		assertEquals(happening.getDescription(), specialized.getDescription());
		assertEquals("devils_hand", specialized.getEjectingNation());
		assertEquals("devils_feet", specialized.getEjectedNation());
		assertEquals("", specialized.getFromRegion());
		assertEquals(happening.getId(), specialized.getId());
		assertEquals(happening.getTimestamp(), specialized.getTimestamp());
		assertFalse(specialized.isBanned());
	}
}
