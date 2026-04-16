package com.github.agadar.nationstates.happeningspecializer;

import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.domain.common.happening.LawHappening;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LawHappeningSpecializerTest {

    @Test
    public void isOfSpecializedType_true() {
        System.out.println("isOfSpecializedType_true");

        // Arrange
        final LawHappeningSpecializer specializer = new LawHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "Following new legislation in @@terkaulia@@, naturists are jailed regularly for indecent exposure.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_false() {
        System.out.println("isOfSpecializedType_false");

        // Arrange
        final LawHappeningSpecializer specializer = new LawHappeningSpecializer();
        final Happening happening = new Happening(193260412L, 1520094314L,
                "@@d-community@@ changed its national motto to \"Abrir todas las jaulas\".");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertFalse(isOfSpecializedType);
    }

    @Test
    public void toSpecializedType() {
        System.out.println("toSpecializedType");

        // Arrange
        final LawHappeningSpecializer specializer = new LawHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "Following new legislation in @@terkaulia@@, naturists are jailed regularly for indecent exposure.");

        // Act
        final LawHappening lawHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), lawHappening.getId());
        assertEquals(happening.getTimestamp(), lawHappening.getTimestamp());
        assertEquals(happening.getDescription(), lawHappening.getDescription());
        assertEquals("terkaulia", lawHappening.getNation());
        assertEquals("naturists are jailed regularly for indecent exposure", lawHappening.getResult());
    }

}
