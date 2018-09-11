package com.github.agadar.nationstates.happeningspecializer;

import org.junit.Assert;
import org.junit.Test;

import com.github.agadar.nationstates.domain.common.happening.DispatchHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.DispatchCategory;
import com.github.agadar.nationstates.enumerator.DispatchSubCategory;

public class DispatchHappeningSpecializerTest {

    @Test
    public void isOfSpecializedType_true() {
        System.out.println("isOfSpecializedType_true");

        // Arrange
        final DispatchHappeningSpecializer specializer = new DispatchHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@talao@@ published \"<a href=\"page=dispatch/id=982940\">Talist Manifesto</a>\" (Bulletin: Campaign).");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        Assert.assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_false() {
        System.out.println("isOfSpecializedType_false");

        // Arrange
        final DispatchHappeningSpecializer specializer = new DispatchHappeningSpecializer();
        final Happening happening = new Happening(193260412L, 1520094314L,
                "@@d-community@@ changed its national motto to \"Abrir todas las jaulas\".");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        Assert.assertFalse(isOfSpecializedType);
    }

    @Test
    public void toSpecializedType() {
        System.out.println("toSpecializedType");

        // Arrange
        final DispatchHappeningSpecializer specializer = new DispatchHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@talao@@ published \"<a href=\"page=dispatch/id=982940\">Talist Manifesto</a>\" (Bulletin: Campaign).");

        // Act
        final DispatchHappening lawHappening = specializer.toSpecializedType(happening);

        // Assert
        Assert.assertEquals(happening.id, lawHappening.id);
        Assert.assertEquals(happening.timestamp, lawHappening.timestamp);
        Assert.assertEquals(happening.description, lawHappening.description);
        Assert.assertEquals("talao", lawHappening.nation);
        Assert.assertEquals(982940L, lawHappening.dispatchId);
        Assert.assertEquals("Talist Manifesto", lawHappening.dispatchName);
        Assert.assertEquals(DispatchCategory.BULLETIN, lawHappening.dispatchCategory);
        Assert.assertEquals(DispatchSubCategory.CAMPAIGN, lawHappening.dispatchSubCategory);
    }

}
