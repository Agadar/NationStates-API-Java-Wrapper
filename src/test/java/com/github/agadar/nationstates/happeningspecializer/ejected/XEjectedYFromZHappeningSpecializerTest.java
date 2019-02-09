package com.github.agadar.nationstates.happeningspecializer.ejected;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.agadar.nationstates.domain.common.happening.Happening;

public class XEjectedYFromZHappeningSpecializerTest {

	@Test
	public void isOfSpecializedType_true() {
		System.out.println("isOfSpecializedType_true");

		// Arrange
		var specializer = new XEjectedYFromZHappeningSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@devils_hand@@ ejected @@devils_feet@@ from %%the_facist_nations_of_songs_of_salem%%.");

		// Act
		boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

		// Assert
		assertTrue(isOfSpecializedType);
	}

	@Test
	public void isOfSpecializedType_false() {
		System.out.println("isOfSpecializedType_false");

		// Arrange
		var specializer = new XEjectedYFromZHappeningSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@devils_hand@@ ejected and banned @@devils_feet@@ from %%the_facist_nations_of_songs_of_salem%%.");

		// Act
		boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

		// Assert
		assertFalse(isOfSpecializedType);
	}

	@Test
	public void toSpecializedType() {
		System.out.println("toSpecializedType");

		// Arrange
		var specializer = new XEjectedYFromZHappeningSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@devils_hand@@ ejected @@devils_feet@@ from %%the_facist_nations_of_songs_of_salem%%.");

		// Act
		var specialized = specializer.toSpecializedType(happening);

		// Assert
		assertEquals(happening.getDescription(), specialized.getDescription());
		assertEquals("devils_hand", specialized.getEjectingNation());
		assertEquals("devils_feet", specialized.getEjectedNation());
		assertEquals("the_facist_nations_of_songs_of_salem", specialized.getFromRegion());
		assertEquals(happening.getId(), specialized.getId());
		assertEquals(happening.getTimestamp(), specialized.getTimestamp());
		assertFalse(specialized.isBanned());
	}
}
