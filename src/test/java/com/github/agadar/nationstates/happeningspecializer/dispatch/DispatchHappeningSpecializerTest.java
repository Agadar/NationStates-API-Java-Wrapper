package com.github.agadar.nationstates.happeningspecializer.dispatch;

import com.github.agadar.nationstates.domain.common.happening.DispatchHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.DispatchCategory;
import com.github.agadar.nationstates.enumerator.DispatchSubCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DispatchHappeningSpecializerTest {

	@Test
	public void isOfSpecializedType_true() {
		System.out.println("isOfSpecializedType_true");

		// Arrange
		var specializer = new DispatchHappeningSpecializer();
		var happening = new Happening(193257689L, 1520092742L,
				"@@euro-slavia@@ published \"People's Union of Indochina\" (Factbook: Overview).");

		// Act
		boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

		// Assert
		assertTrue(isOfSpecializedType);
	}

	@Test
	public void isOfSpecializedType_false() {
		System.out.println("isOfSpecializedType_false");

		// Arrange
		var specializer = new DispatchHappeningSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@euro-slavia@@ published \"<a href=\"page=dispatch/id=1165453\">People's Union of Indochina</a>\" (Factbook: Overview).");

		// Act
		boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

		// Assert
		assertFalse(isOfSpecializedType);
	}

	@Test
	public void toSpecializedType() {
		System.out.println("toSpecializedType");

		// Arrange
		var specializer = new DispatchHappeningSpecializer();
		var happening = new Happening(193257689L, 1520092742L,
				"@@euro-slavia@@ published \"People's Union of Indochina\" (Factbook: Overview).");

		// Act
		final DispatchHappening lawHappening = specializer.toSpecializedType(happening);

		// Assert
		assertEquals(happening.getId(), lawHappening.getId());
		assertEquals(happening.getTimestamp(), lawHappening.getTimestamp());
		assertEquals(happening.getDescription(), lawHappening.getDescription());
		assertEquals("euro-slavia", lawHappening.getNation());
		assertEquals(0L, lawHappening.getDispatchId());
		assertEquals("People's Union of Indochina", lawHappening.getDispatchName());
		assertEquals(DispatchCategory.FACTBOOK, lawHappening.getDispatchCategory());
		assertEquals(DispatchSubCategory.OVERVIEW, lawHappening.getDispatchSubCategory());
	}

}
