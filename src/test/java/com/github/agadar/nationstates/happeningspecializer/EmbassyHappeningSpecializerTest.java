package com.github.agadar.nationstates.happeningspecializer;

import com.github.agadar.nationstates.domain.common.happening.EmbassyHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.EmbassyHappeningType;
import com.github.agadar.nationstates.exception.NationStatesAPIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmbassyHappeningSpecializerTest {

    @Test
    public void isOfSpecializedType_true_1() {
        System.out.println("isOfSpecializedType_true_1");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@the_indo-malaysian_consulate@@ aborted construction of embassies between %%great_socialist_states_of_odinovich%% and %%plum_island%%.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_2() {
        System.out.println("isOfSpecializedType_true_2");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@vespertania@@ agreed to construct embassies between %%centrum_essentia%% and %%bus_stop%%.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_3() {
        System.out.println("isOfSpecializedType_true_3");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "Embassy established between %%warzone_asia%% and %%the_east_pacific%%.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_4() {
        System.out.println("isOfSpecializedType_true_4");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@eastern_mystaulem@@ ordered the closure of embassies between %%the_fuel_of_the_ages%% and %%the_bar_on_the_corner_of_every_region%%.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_5() {
        System.out.println("isOfSpecializedType_true_5");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@vespertania@@ proposed constructing embassies between %%centrum_essentia%% and %%the_western_isles%%.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_6() {
        System.out.println("isOfSpecializedType_true_6");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@terra_voltera@@ rejected a request from %%the_worlds_order%% for an embassy with %%the_hole_to_hide_in%%.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_7() {
        System.out.println("isOfSpecializedType_true_7");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@the_indo-malaysian_consulate@@ withdrew a request for embassies between %%great_socialist_states_of_odinovich%% and %%versailles_isle%%.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_8() {
        System.out.println("isOfSpecializedType_true_8");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@the_indo-malaysian_consulate@@ cancelled the closure of embassies between %%great_socialist_states_of_odinovich%% and %%sikhi_empire%%.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_9() {
        System.out.println("isOfSpecializedType_true_9");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "Embassy cancelled between %%union_of_russia_and_italy_and_ireland%% and %%the_western_isles%%.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_10() {
        System.out.println("isOfSpecializedType_true_10");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "Construction of embassies aborted between %%world_trade_centre%% and %%the_embassy%%.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_11() {
        System.out.println("isOfSpecializedType_true_11");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L, "EO:warzone_sandbox.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_true_12() {
        System.out.println("isOfSpecializedType_true_12");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L, "EC:peoples_federation_of_qandaristan.");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertTrue(isOfSpecializedType);
    }

    @Test
    public void isOfSpecializedType_false() {
        System.out.println("isOfSpecializedType_false");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193260412L, 1520094314L,
                "@@d-community@@ changed its national motto to \"Abrir todas las jaulas\".");

        // Act
        final boolean isOfSpecializedType = specializer.isOfSpecializedType(happening);

        // Assert
        assertFalse(isOfSpecializedType);
    }

    @Test
    public void toSpecializedType_1() throws NationStatesAPIException {
        System.out.println("toSpecializedType_1");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@the_indo-malaysian_consulate@@ aborted construction of embassies between %%great_socialist_states_of_odinovich%% and %%plum_island%%.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertEquals("the_indo-malaysian_consulate", embassyHappening.getNation());
        assertEquals("great_socialist_states_of_odinovich", embassyHappening.getRegion1());
        assertEquals("plum_island", embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.CONSTRUCTION_ABORTED, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_2() throws NationStatesAPIException {
        System.out.println("toSpecializedType_2");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@vespertania@@ agreed to construct embassies between %%centrum_essentia%% and %%bus_stop%%.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertEquals("vespertania", embassyHappening.getNation());
        assertEquals("centrum_essentia", embassyHappening.getRegion1());
        assertEquals("bus_stop", embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.AGREED_TO_CONSTRUCT, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_3() throws NationStatesAPIException {
        System.out.println("toSpecializedType_3");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "Embassy established between %%warzone_asia%% and %%the_east_pacific%%.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertNull(embassyHappening.getNation());
        assertEquals("warzone_asia", embassyHappening.getRegion1());
        assertEquals("the_east_pacific", embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.EMBASSY_ESTABLISHED, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_4() throws NationStatesAPIException {
        System.out.println("toSpecializedType_4");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@eastern_mystaulem@@ ordered the closure of embassies between %%the_fuel_of_the_ages%% and %%the_bar_on_the_corner_of_every_region%%.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertEquals("eastern_mystaulem", embassyHappening.getNation());
        assertEquals("the_fuel_of_the_ages", embassyHappening.getRegion1());
        assertEquals("the_bar_on_the_corner_of_every_region", embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.ORDERED_CLOSURE, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_5() throws NationStatesAPIException {
        System.out.println("toSpecializedType_5");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@vespertania@@ proposed constructing embassies between %%centrum_essentia%% and %%the_western_isles%%.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertEquals("vespertania", embassyHappening.getNation());
        assertEquals("centrum_essentia", embassyHappening.getRegion1());
        assertEquals("the_western_isles", embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.PROPOSED_CONSTRUCTION, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_6() throws NationStatesAPIException {
        System.out.println("toSpecializedType_6");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@terra_voltera@@ rejected a request from %%the_worlds_order%% for an embassy with %%the_hole_to_hide_in%%.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertEquals("terra_voltera", embassyHappening.getNation());
        assertEquals("the_worlds_order", embassyHappening.getRegion1());
        assertEquals("the_hole_to_hide_in", embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.REJECTED_REQUEST, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_7() throws NationStatesAPIException {
        System.out.println("toSpecializedType_7");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@the_indo-malaysian_consulate@@ withdrew a request for embassies between %%great_socialist_states_of_odinovich%% and %%versailles_isle%%.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertEquals("the_indo-malaysian_consulate", embassyHappening.getNation());
        assertEquals("great_socialist_states_of_odinovich", embassyHappening.getRegion1());
        assertEquals("versailles_isle", embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.WITHDREW_REQUEST, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_8() throws NationStatesAPIException {
        System.out.println("toSpecializedType_8");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "@@the_indo-malaysian_consulate@@ cancelled the closure of embassies between %%great_socialist_states_of_odinovich%% and %%sikhi_empire%%.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertEquals("the_indo-malaysian_consulate", embassyHappening.getNation());
        assertEquals("great_socialist_states_of_odinovich", embassyHappening.getRegion1());
        assertEquals("sikhi_empire", embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.CANCELLED_CLOSURE, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_9() throws NationStatesAPIException {
        System.out.println("toSpecializedType_9");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "Embassy cancelled between %%union_of_russia_and_italy_and_ireland%% and %%the_western_isles%%.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertNull(embassyHappening.getNation());
        assertEquals("union_of_russia_and_italy_and_ireland", embassyHappening.getRegion1());
        assertEquals("the_western_isles", embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.EMBASSY_CANCELLED, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_10() throws NationStatesAPIException {
        System.out.println("toSpecializedType_10");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L,
                "Construction of embassies aborted between %%world_trade_centre%% and %%the_embassy%%.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertNull(embassyHappening.getNation());
        assertEquals("world_trade_centre", embassyHappening.getRegion1());
        assertEquals("the_embassy", embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.CONSTRUCTION_ABORTED, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_11() throws NationStatesAPIException {
        System.out.println("toSpecializedType_11");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L, "EO:warzone_sandbox.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertNull(embassyHappening.getNation());
        assertEquals("warzone_sandbox", embassyHappening.getRegion1());
        assertNull(embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.EMBASSY_ESTABLISHED, embassyHappening.getEmbassyHappeningType());
    }

    @Test
    public void toSpecializedType_12() throws NationStatesAPIException {
        System.out.println("toSpecializedType_12");

        // Arrange
        final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
        final Happening happening = new Happening(193257689L, 1520092742L, "EC:peoples_federation_of_qandaristan.");

        // Act
        final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

        // Assert
        assertEquals(happening.getId(), embassyHappening.getId());
        assertEquals(happening.getTimestamp(), embassyHappening.getTimestamp());
        assertEquals(happening.getDescription(), embassyHappening.getDescription());
        assertNull(embassyHappening.getNation());
        assertEquals("peoples_federation_of_qandaristan", embassyHappening.getRegion1());
        assertNull(embassyHappening.getRegion2());
        assertEquals(EmbassyHappeningType.EMBASSY_CANCELLED, embassyHappening.getEmbassyHappeningType());
    }
}
