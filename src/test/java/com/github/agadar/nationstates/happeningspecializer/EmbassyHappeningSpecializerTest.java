package com.github.agadar.nationstates.happeningspecializer;

import org.junit.Assert;
import org.junit.Test;

import com.github.agadar.nationstates.domain.common.happening.EmbassyHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.EmbassyHappeningType;

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
	Assert.assertTrue(isOfSpecializedType);
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
	Assert.assertTrue(isOfSpecializedType);
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
	Assert.assertTrue(isOfSpecializedType);
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
	Assert.assertTrue(isOfSpecializedType);
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
	Assert.assertTrue(isOfSpecializedType);
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
	Assert.assertTrue(isOfSpecializedType);
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
	Assert.assertTrue(isOfSpecializedType);
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
	Assert.assertTrue(isOfSpecializedType);
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
	Assert.assertTrue(isOfSpecializedType);
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
	Assert.assertTrue(isOfSpecializedType);
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
	Assert.assertFalse(isOfSpecializedType);
    }

    @Test
    public void toSpecializedType_1() {
	System.out.println("toSpecializedType_1");

	// Arrange
	final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@the_indo-malaysian_consulate@@ aborted construction of embassies between %%great_socialist_states_of_odinovich%% and %%plum_island%%.");

	// Act
	final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, embassyHappening.id);
	Assert.assertEquals(happening.timestamp, embassyHappening.timestamp);
	Assert.assertEquals(happening.description, embassyHappening.description);
	Assert.assertEquals("the_indo-malaysian_consulate", embassyHappening.nation);
	Assert.assertEquals("great_socialist_states_of_odinovich", embassyHappening.region1);
	Assert.assertEquals("plum_island", embassyHappening.region2);
	Assert.assertEquals(EmbassyHappeningType.NATION_ABORTED_CONSTRUCTION, embassyHappening.embassyHappeningType);
    }
    
    @Test
    public void toSpecializedType_2() {
	System.out.println("toSpecializedType_2");

	// Arrange
	final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@vespertania@@ agreed to construct embassies between %%centrum_essentia%% and %%bus_stop%%.");

	// Act
	final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, embassyHappening.id);
	Assert.assertEquals(happening.timestamp, embassyHappening.timestamp);
	Assert.assertEquals(happening.description, embassyHappening.description);
	Assert.assertEquals("vespertania", embassyHappening.nation);
	Assert.assertEquals("centrum_essentia", embassyHappening.region1);
	Assert.assertEquals("bus_stop", embassyHappening.region2);
	Assert.assertEquals(EmbassyHappeningType.AGREED_TO_CONSTRUCT, embassyHappening.embassyHappeningType);
    }
    
    @Test
    public void toSpecializedType_3() {
	System.out.println("toSpecializedType_3");

	// Arrange
	final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"Embassy established between %%warzone_asia%% and %%the_east_pacific%%.");

	// Act
	final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, embassyHappening.id);
	Assert.assertEquals(happening.timestamp, embassyHappening.timestamp);
	Assert.assertEquals(happening.description, embassyHappening.description);
	Assert.assertEquals(null, embassyHappening.nation);
	Assert.assertEquals("warzone_asia", embassyHappening.region1);
	Assert.assertEquals("the_east_pacific", embassyHappening.region2);
	Assert.assertEquals(EmbassyHappeningType.EMBASSY_ESTABLISHED, embassyHappening.embassyHappeningType);
    }
    
    @Test
    public void toSpecializedType_4() {
	System.out.println("toSpecializedType_4");

	// Arrange
	final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@eastern_mystaulem@@ ordered the closure of embassies between %%the_fuel_of_the_ages%% and %%the_bar_on_the_corner_of_every_region%%.");

	// Act
	final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, embassyHappening.id);
	Assert.assertEquals(happening.timestamp, embassyHappening.timestamp);
	Assert.assertEquals(happening.description, embassyHappening.description);
	Assert.assertEquals("eastern_mystaulem", embassyHappening.nation);
	Assert.assertEquals("the_fuel_of_the_ages", embassyHappening.region1);
	Assert.assertEquals("the_bar_on_the_corner_of_every_region", embassyHappening.region2);
	Assert.assertEquals(EmbassyHappeningType.ORDERED_CLOSURE, embassyHappening.embassyHappeningType);
    }
    
    @Test
    public void toSpecializedType_5() {
	System.out.println("toSpecializedType_5");

	// Arrange
	final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@vespertania@@ proposed constructing embassies between %%centrum_essentia%% and %%the_western_isles%%.");

	// Act
	final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, embassyHappening.id);
	Assert.assertEquals(happening.timestamp, embassyHappening.timestamp);
	Assert.assertEquals(happening.description, embassyHappening.description);
	Assert.assertEquals("vespertania", embassyHappening.nation);
	Assert.assertEquals("centrum_essentia", embassyHappening.region1);
	Assert.assertEquals("the_western_isles", embassyHappening.region2);
	Assert.assertEquals(EmbassyHappeningType.PROPOSED_CONSTRUCTION, embassyHappening.embassyHappeningType);
    }
    
    @Test
    public void toSpecializedType_6() {
	System.out.println("toSpecializedType_6");

	// Arrange
	final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@terra_voltera@@ rejected a request from %%the_worlds_order%% for an embassy with %%the_hole_to_hide_in%%.");

	// Act
	final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, embassyHappening.id);
	Assert.assertEquals(happening.timestamp, embassyHappening.timestamp);
	Assert.assertEquals(happening.description, embassyHappening.description);
	Assert.assertEquals("terra_voltera", embassyHappening.nation);
	Assert.assertEquals("the_worlds_order", embassyHappening.region1);
	Assert.assertEquals("the_hole_to_hide_in", embassyHappening.region2);
	Assert.assertEquals(EmbassyHappeningType.REJECTED_REQUEST, embassyHappening.embassyHappeningType);
    }
    
    @Test
    public void toSpecializedType_7() {
	System.out.println("toSpecializedType_7");

	// Arrange
	final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@the_indo-malaysian_consulate@@ withdrew a request for embassies between %%great_socialist_states_of_odinovich%% and %%versailles_isle%%.");

	// Act
	final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, embassyHappening.id);
	Assert.assertEquals(happening.timestamp, embassyHappening.timestamp);
	Assert.assertEquals(happening.description, embassyHappening.description);
	Assert.assertEquals("the_indo-malaysian_consulate", embassyHappening.nation);
	Assert.assertEquals("great_socialist_states_of_odinovich", embassyHappening.region1);
	Assert.assertEquals("versailles_isle", embassyHappening.region2);
	Assert.assertEquals(EmbassyHappeningType.WITHDREW_REQUEST, embassyHappening.embassyHappeningType);
    }
    
    @Test
    public void toSpecializedType_8() {
	System.out.println("toSpecializedType_8");

	// Arrange
	final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"@@the_indo-malaysian_consulate@@ cancelled the closure of embassies between %%great_socialist_states_of_odinovich%% and %%sikhi_empire%%.");

	// Act
	final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, embassyHappening.id);
	Assert.assertEquals(happening.timestamp, embassyHappening.timestamp);
	Assert.assertEquals(happening.description, embassyHappening.description);
	Assert.assertEquals("the_indo-malaysian_consulate", embassyHappening.nation);
	Assert.assertEquals("great_socialist_states_of_odinovich", embassyHappening.region1);
	Assert.assertEquals("sikhi_empire", embassyHappening.region2);
	Assert.assertEquals(EmbassyHappeningType.CANCELLED_CLOSURE, embassyHappening.embassyHappeningType);
    }
    
    @Test
    public void toSpecializedType_9() {
	System.out.println("toSpecializedType_9");

	// Arrange
	final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"Embassy cancelled between %%union_of_russia_and_italy_and_ireland%% and %%the_western_isles%%.");

	// Act
	final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, embassyHappening.id);
	Assert.assertEquals(happening.timestamp, embassyHappening.timestamp);
	Assert.assertEquals(happening.description, embassyHappening.description);
	Assert.assertEquals(null, embassyHappening.nation);
	Assert.assertEquals("union_of_russia_and_italy_and_ireland", embassyHappening.region1);
	Assert.assertEquals("the_western_isles", embassyHappening.region2);
	Assert.assertEquals(EmbassyHappeningType.EMBASSY_CANCELLED, embassyHappening.embassyHappeningType);
    }

    @Test
    public void toSpecializedType_10() {
	System.out.println("toSpecializedType_10");

	// Arrange
	final EmbassyHappeningSpecializer specializer = new EmbassyHappeningSpecializer();
	final Happening happening = new Happening(193257689L, 1520092742L,
		"Construction of embassies aborted between %%world_trade_centre%% and %%the_embassy%%.");

	// Act
	final EmbassyHappening embassyHappening = specializer.toSpecializedType(happening);

	// Assert
	Assert.assertEquals(happening.id, embassyHappening.id);
	Assert.assertEquals(happening.timestamp, embassyHappening.timestamp);
	Assert.assertEquals(happening.description, embassyHappening.description);
	Assert.assertEquals(null, embassyHappening.nation);
	Assert.assertEquals("world_trade_centre", embassyHappening.region1);
	Assert.assertEquals("the_embassy", embassyHappening.region2);
	Assert.assertEquals(EmbassyHappeningType.CONSTRUCTION_ABORTED, embassyHappening.embassyHappeningType);
    }
}
