package com.github.agadar.nationstates.happeningspecializer;

import org.junit.Assert;
import org.junit.Test;

import com.github.agadar.nationstates.domain.common.happening.EjectedHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;

public class EjectedHappeningSpecializerTest {

    @Test
    public void isOfSpecializedType_true_1() {
	System.out.println("isOfSpecializedType_true_1");

	// Arrange
	final EjectedHappeningSpecializer specializer = new EjectedHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@demorlan_goricky@@ was ejected from %%canterbury%% by @@new_legland@@.");

	// Act
	final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

	// Assert
	Assert.assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_2() {
	System.out.println("isOfSpecializedType_true_2");

	// Arrange
	final EjectedHappeningSpecializer specializer = new EjectedHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@national_socalist_workers_party@@ was ejected and banned from %%the_western_isles%% by @@vancouvia@@.");

	// Act
	final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

	// Assert
	Assert.assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_3() {
	System.out.println("isOfSpecializedType_true_3");

	// Arrange
	final EjectedHappeningSpecializer specializer = new EjectedHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@bran_astor@@ banned @@les_arbes@@ from %%the_west_pacific%%.");

	// Act
	final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

	// Assert
	Assert.assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_false() {
	System.out.println("isOfSpecializedType_false");

	// Arrange
	final EjectedHappeningSpecializer specializer = new EjectedHappeningSpecializer();
	final Happening happening = new Happening(193260412L, 1520094314L,
		"@@d-community@@ changed its national motto to \"Abrir todas las jaulas\".");

	// Act
	final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

	// Assert
	Assert.assertFalse(isOfSpecializedType);
    }

    @Test
    public void toSpecializedType_1() {
	System.out.println("toSpecializedType_1");

	// Arrange
	final EjectedHappeningSpecializer specializer = new EjectedHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@demorlan_goricky@@ was ejected from %%canterbury%% by @@new_legland@@.");

	// Act
	final EjectedHappening ejectedHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, ejectedHappening.id);
	Assert.assertEquals(happening.timestamp, ejectedHappening.timestamp);
	Assert.assertEquals(happening.description, ejectedHappening.description);
	Assert.assertEquals("demorlan_goricky", ejectedHappening.ejectedNation);
	Assert.assertEquals("new_legland", ejectedHappening.ejectingNation);
	Assert.assertEquals("canterbury", ejectedHappening.fromRegion);
	Assert.assertFalse(ejectedHappening.banned);
    }

    @Test
    public void toSpecializedType_2() {
	System.out.println("toSpecializedType_2");

	// Arrange
	final EjectedHappeningSpecializer specializer = new EjectedHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@national_socalist_workers_party@@ was ejected and banned from %%the_western_isles%% by @@vancouvia@@.");

	// Act
	final EjectedHappening ejectedHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, ejectedHappening.id);
	Assert.assertEquals(happening.timestamp, ejectedHappening.timestamp);
	Assert.assertEquals(happening.description, ejectedHappening.description);
	Assert.assertEquals("national_socalist_workers_party", ejectedHappening.ejectedNation);
	Assert.assertEquals("vancouvia", ejectedHappening.ejectingNation);
	Assert.assertEquals("the_western_isles", ejectedHappening.fromRegion);
	Assert.assertTrue(ejectedHappening.banned);
    }

    @Test
    public void toSpecializedType_3() {
	System.out.println("toSpecializedType_3");

	// Arrange
	final EjectedHappeningSpecializer specializer = new EjectedHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@bran_astor@@ banned @@les_arbes@@ from %%the_west_pacific%%.");

	// Act
	final EjectedHappening ejectedHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, ejectedHappening.id);
	Assert.assertEquals(happening.timestamp, ejectedHappening.timestamp);
	Assert.assertEquals(happening.description, ejectedHappening.description);
	Assert.assertEquals("les_arbes", ejectedHappening.ejectedNation);
	Assert.assertEquals("bran_astor", ejectedHappening.ejectingNation);
	Assert.assertEquals("the_west_pacific", ejectedHappening.fromRegion);
	Assert.assertTrue(ejectedHappening.banned);
    }

}
