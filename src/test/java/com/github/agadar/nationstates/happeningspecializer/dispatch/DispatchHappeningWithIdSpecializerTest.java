package com.github.agadar.nationstates.happeningspecializer.dispatch;

import org.junit.Assert;
import org.junit.Test;

import com.github.agadar.nationstates.domain.common.happening.DispatchHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.DispatchCategory;
import com.github.agadar.nationstates.enumerator.DispatchSubCategory;

public class DispatchHappeningWithIdSpecializerTest {

	@Test
	public void isOfSpecializedType_true() {
		System.out.println("isOfSpecializedType_true");

		// Arrange
		var specializer = new DispatchHappeningWithIdSpecializer();
		var happening = new Happening(193257689L, 1520092742L,
				"@@euro-slavia@@ published \"<a href=\"page=dispatch/id=1165453\">People's Union of Indochina</a>\" (Factbook: Overview).");

		// Act
		boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

		// Assert
		Assert.assertTrue(isOfSpecializedType);
	}

	@Test
	public void isOfSpecializedType_false() {
		System.out.println("isOfSpecializedType_false");

		// Arrange
		var specializer = new DispatchHappeningWithIdSpecializer();
		var happening = new Happening(193260412L, 1520094314L,
				"@@euro-slavia@@ published \"People's Union of Indochina\" (Factbook: Overview).");

		// Act
		boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

		// Assert
		Assert.assertFalse(isOfSpecializedType);
	}

	@Test
	public void toSpecializedType() {
		System.out.println("toSpecializedType");

		// Arrange
		var specializer = new DispatchHappeningWithIdSpecializer();
		var happening = new Happening(193257689L, 1520092742L,
				"@@euro-slavia@@ published \"<a href=\"page=dispatch/id=1165453\">People's Union of Indochina</a>\" (Factbook: Overview).");

		// Act
		final DispatchHappening lawHappening = specializer.toSpecializedType(happening);

		// Assert
		Assert.assertEquals(happening.getId(), lawHappening.getId());
		Assert.assertEquals(happening.getTimestamp(), lawHappening.getTimestamp());
		Assert.assertEquals(happening.getDescription(), lawHappening.getDescription());
		Assert.assertEquals("euro-slavia", lawHappening.getNation());
		Assert.assertEquals(1165453L, lawHappening.getDispatchId());
		Assert.assertEquals("People's Union of Indochina", lawHappening.getDispatchName());
		Assert.assertEquals(DispatchCategory.FACTBOOK, lawHappening.getDispatchCategory());
		Assert.assertEquals(DispatchSubCategory.OVERVIEW, lawHappening.getDispatchSubCategory());
	}

}
