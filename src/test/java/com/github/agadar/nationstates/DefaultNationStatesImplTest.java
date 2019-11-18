package com.github.agadar.nationstates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.enumerator.Council;
import com.github.agadar.nationstates.enumerator.DailyDumpMode;
import com.github.agadar.nationstates.exception.NationStatesAPIException;
import com.github.agadar.nationstates.shard.WorldAssemblyShard;
import com.github.agadar.nationstates.shard.WorldShard;

/**
 * Simple integration test to check existence of presumably most-used endpoints.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class DefaultNationStatesImplTest {

    private DefaultNationStatesImpl nationStates;

    @Before
    public void initialise() throws NationStatesAPIException {
        nationStates = new DefaultNationStatesImpl("Integration tests by Agadar");
    }

    /**
     * Test of getNation method, of class NationStates.
     * 
     * @throws NationStatesAPIException
     */
    @Test
    public void testGetNation() throws NationStatesAPIException {
        System.out.println("getNation");

        // Arrange
        var query = nationStates.getNation("testlandia");

        // Act
        var result = query.execute();

        // Assert
        assertNotNull(result);
        assertEquals("Testlandia", result.getName());
    }

    /**
     * Test of getRegion method, of class NationStates.
     * 
     * @throws NationStatesAPIException
     */
    @Test
    public void testGetRegion() throws NationStatesAPIException {
        System.out.println("getRegion");

        // Arrange
        var query = nationStates.getRegion("the rejected realms");

        // Act
        var result = query.execute();

        // Assert
        assertNotNull(result);
        assertEquals("the Rejected Realms", result.getName());
    }

    /**
     * Test of getWorld method, of class NationStates.
     * 
     * @throws NationStatesAPIException
     */
    @Test
    public void testGetWorld() throws NationStatesAPIException {
        System.out.println("getWorld");

        // Arrange
        var query = nationStates.getWorld(WorldShard.NUMBER_OF_NATIONS);

        // Act
        var result = query.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.getNumberOfNations() > 0);
    }

    /**
     * Test of getWorldAssembly method, of class NationStates.
     * 
     * @throws NationStatesAPIException
     */
    @Test
    public void testGetWorldAssembly() throws NationStatesAPIException {
        System.out.println("getWorldAssembly");

        // Arrange
        var query = nationStates.getWorldAssembly(Council.GENERAL_ASSEMBLY)
                .shards(WorldAssemblyShard.NUMBER_OF_MEMBERS);

        // Act
        var result = query.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.getNumberOfMembers() > 0);
    }

    /**
     * Test of getVersion method, of class NationStates.
     * 
     * @throws NationStatesAPIException
     */
    @Test
    public void testGetVersion() throws NationStatesAPIException {
        System.out.println("getVersion");

        // Arrange
        var query = nationStates.getVersion();

        // Act
        int version = query.execute();

        // Assert
        assertNotNull(version);
        assertEquals(9, version);
    }

    /**
     * Test of getRegionDump method, of class NationStates.
     * <p>
     * Tests the "download then read locally" method.
     * 
     * @throws NationStatesAPIException
     */
    @Test
    public void testGetRegionDump_downloadAndRead() throws NationStatesAPIException {
        System.out.println("getRegionDump (DOWNLOAD_THEN_READ_LOCAL)");

        // Arrange
        Predicate<Region> filter = region -> region.getName().equals("The Western Isles");
        var query = nationStates.getRegionDump(DailyDumpMode.DOWNLOAD_THEN_READ_LOCAL, filter);

        // Act
        var regions = query.execute();

        // Assert
        assertNotNull(regions);
        assertEquals(1, regions.size());
        assertEquals("The Western Isles", regions.iterator().next().getName());
    }

    /**
     * Test of getRegionDump method, of class NationStates.
     * <p>
     * Tests the "read remote" method.
     * 
     * @throws NationStatesAPIException
     */
    @Test
    public void testGetRegionDump_readRemote() throws NationStatesAPIException {
        System.out.println("getRegionDump (READ_REMOTE)");

        // Arrange
        Predicate<Region> filter = region -> region.getName().equals("The Western Isles");
        var query = nationStates.getRegionDump(DailyDumpMode.READ_REMOTE, filter);

        // Act
        var regions = query.execute();

        // Assert
        assertNotNull(regions);
        assertEquals(1, regions.size());
        assertEquals("The Western Isles", regions.iterator().next().getName());
    }

    /**
     * Test of getNationDump method, of class NationStates.
     * <p>
     * Tests the "download then read locally" method.
     * 
     * @throws NationStatesAPIException
     */
    @Test
    public void testGetNationDump_downloadAndRead() throws NationStatesAPIException {
        System.out.println("getNationDump (DOWNLOAD_THEN_READ_LOCAL)");

        // Arrange
        Predicate<Nation> filter = nation -> nation.getName().equals("Agadar");
        var query = nationStates.getNationDump(DailyDumpMode.DOWNLOAD_THEN_READ_LOCAL, filter);

        // Act
        var nations = query.execute();

        // Assert
        assertNotNull(nations);
        assertEquals(1, nations.size());
        assertEquals("Agadar", nations.iterator().next().getName());
    }

    /**
     * Test of getNationDump method, of class NationStates.
     * <p>
     * Tests the "read remote" method.
     * 
     * @throws NationStatesAPIException
     */
    @Test
    public void testGetNationDump_readRemote() throws NationStatesAPIException {
        System.out.println("getNationDump (READ_REMOTE)");

        // Arrange
        Predicate<Nation> filter = nation -> nation.getName().equals("Agadar");
        var query = nationStates.getNationDump(DailyDumpMode.READ_REMOTE, filter);

        // Act
        var nations = query.execute();

        // Assert
        assertNotNull(nations);
        assertEquals(1, nations.size());
        assertEquals("Agadar", nations.iterator().next().getName());
    }
}
