package com.github.agadar.nationstates.happeningspecializer.ejected;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.agadar.nationstates.domain.common.happening.Happening;

public class XWasEjectedFromYByZHappeningSpecializerTest {

	@Test
	public void isOfSpecializedType_true() {
		System.out.println("isOfSpecializedType_true");

		// Arrange
		var specializer = new XWasEjectedFromYByZHappeningSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@devils_hand@@ was ejected from %%the_facist_nations_of_songs_of_salem%% by @@devils_feet@@.");

		// Act
		boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

		// Assert
		assertTrue(isOfSpecializedType);
	}

	@Test
	public void isOfSpecializedType_false() {
		System.out.println("isOfSpecializedType_false");

		// Arrange
		var specializer = new XWasEjectedFromYByZHappeningSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@devils_hand@@ was ejected and banned from %%the_facist_nations_of_songs_of_salem%% by @@devils_feet@@.");

		// Act
		boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

		// Assert
		assertFalse(isOfSpecializedType);
	}

	@Test
	public void toSpecializedType() {
		System.out.println("toSpecializedType");

		// Arrange
		var specializer = new XWasEjectedFromYByZHappeningSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@devils_hand@@ was ejected from %%the_facist_nations_of_songs_of_salem%% by @@devils_feet@@.");

		// Act
		var specialized = specializer.toSpecializedType(happening);

		// Assert
		assertEquals(happening.getDescription(), specialized.getDescription());
		assertEquals("devils_feet", specialized.getEjectingNation());
		assertEquals("devils_hand", specialized.getEjectedNation());
		assertEquals("the_facist_nations_of_songs_of_salem", specialized.getFromRegion());
		assertEquals(happening.getId(), specialized.getId());
		assertEquals(happening.getTimestamp(), specialized.getTimestamp());
		assertFalse(specialized.isBanned());
	}
}
