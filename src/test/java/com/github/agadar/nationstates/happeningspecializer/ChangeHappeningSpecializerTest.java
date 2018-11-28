package com.github.agadar.nationstates.happeningspecializer;

import org.junit.Assert;
import org.junit.Test;

import com.github.agadar.nationstates.domain.common.happening.ChangeHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;

public class ChangeHappeningSpecializerTest {

    @Test
    public void isOfSpecializedType_true_1() {
        System.out.println("isOfSpecializedType_true_1");

        // Arrange
        final ChangeHappeningSpecializer specializer = new ChangeHappeningSpecializer();
        final Happening happening = new Happening(193260412L, 1520094314L,
                "@@d-community@@ changed its national motto to \"Abrir todas las jaulas\".");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        Assert.assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_2() {
        System.out.println("isOfSpecializedType_true_2");

        // Arrange
        final ChangeHappeningSpecializer specializer = new ChangeHappeningSpecializer();
        final Happening happening = new Happening(193260412L, 1520094314L,
                "@@panzergrenadierbataillon_18@@ altered its national flag.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        Assert.assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_3() {
        System.out.println("isOfSpecializedType_true_3");

        // Arrange
        final ChangeHappeningSpecializer specializer = new ChangeHappeningSpecializer();
        final Happening happening = new Happening(193260412L, 1520094314L,
                "@@crouton_eaters@@ was reclassified from \"Capitalist Paradise\" to \"Corporate Bordello\".");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        Assert.assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_4() {
        System.out.println("isOfSpecializedType_true_4");

        // Arrange
        final ChangeHappeningSpecializer specializer = new ChangeHappeningSpecializer();
        final Happening happening = new Happening(193260412L, 1520094314L, "@@lebend@@ created a custom banner.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        Assert.assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_false() {
        System.out.println("isOfSpecializedType_false");

        // Arrange
        final ChangeHappeningSpecializer specializer = new ChangeHappeningSpecializer();
        final Happening happening = new Happening(193260412L, 1520094314L,
                "Following new legislation in @@terkaulia@@, naturists are jailed regularly for indecent exposure.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        Assert.assertFalse(isOfSpecializedType);
    }

    @Test
    public void toSpecializedType_1() {
        System.out.println("toSpecializedType_1");

        // Arrange
        final ChangeHappeningSpecializer specializer = new ChangeHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@d-community@@ changed its national motto to \"Abrir todas las jaulas\".");

        // Act
        final ChangeHappening lawHappening = specializer.toSpecializedType(happening);

        // Assert
        Assert.assertEquals(happening.getId(), lawHappening.getId());
        Assert.assertEquals(happening.getTimestamp(), lawHappening.getTimestamp());
        Assert.assertEquals(happening.getDescription(), lawHappening.getDescription());
        Assert.assertEquals("d-community", lawHappening.getNation());
        Assert.assertEquals("changed its national motto to \"Abrir todas las jaulas\"", lawHappening.getChange());
    }

    @Test
    public void toSpecializedType_2() {
        System.out.println("toSpecializedType_2");

        // Arrange
        final ChangeHappeningSpecializer specializer = new ChangeHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@panzergrenadierbataillon_18@@ altered its national flag.");

        // Act
        final ChangeHappening lawHappening = specializer.toSpecializedType(happening);

        // Assert
        Assert.assertEquals(happening.getId(), lawHappening.getId());
        Assert.assertEquals(happening.getTimestamp(), lawHappening.getTimestamp());
        Assert.assertEquals(happening.getDescription(), lawHappening.getDescription());
        Assert.assertEquals("panzergrenadierbataillon_18", lawHappening.getNation());
        Assert.assertEquals("altered its national flag", lawHappening.getChange());
    }

    @Test
    public void toSpecializedType_3() {
        System.out.println("toSpecializedType_3");

        // Arrange
        final ChangeHappeningSpecializer specializer = new ChangeHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@crouton_eaters@@ was reclassified from \"Capitalist Paradise\" to \"Corporate Bordello\".");

        // Act
        final ChangeHappening lawHappening = specializer.toSpecializedType(happening);

        // Assert
        Assert.assertEquals(happening.getId(), lawHappening.getId());
        Assert.assertEquals(happening.getTimestamp(), lawHappening.getTimestamp());
        Assert.assertEquals(happening.getDescription(), lawHappening.getDescription());
        Assert.assertEquals("crouton_eaters", lawHappening.getNation());
        Assert.assertEquals("was reclassified from \"Capitalist Paradise\" to \"Corporate Bordello\"",
                lawHappening.getChange());
    }

    @Test
    public void toSpecializedType_4() {
        System.out.println("toSpecializedType_4");

        // Arrange
        final ChangeHappeningSpecializer specializer = new ChangeHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L, "@@lebend@@ created a custom banner.");

        // Act
        final ChangeHappening lawHappening = specializer.toSpecializedType(happening);

        // Assert
        Assert.assertEquals(happening.getId(), lawHappening.getId());
        Assert.assertEquals(happening.getTimestamp(), lawHappening.getTimestamp());
        Assert.assertEquals(happening.getDescription(), lawHappening.getDescription());
        Assert.assertEquals("lebend", lawHappening.getNation());
        Assert.assertEquals("created a custom banner", lawHappening.getChange());
    }

}
