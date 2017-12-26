package com.github.agadar.nationstates;

import com.github.agadar.nationstates.domain.nation.Nation;
import com.github.agadar.nationstates.domain.region.Region;
import com.github.agadar.nationstates.domain.world.World;
import com.github.agadar.nationstates.domain.worldassembly.WorldAssembly;
import com.github.agadar.nationstates.enumerator.Council;
import com.github.agadar.nationstates.query.NationQuery;
import com.github.agadar.nationstates.query.RegionQuery;
import com.github.agadar.nationstates.query.VersionQuery;
import com.github.agadar.nationstates.query.WorldAssemblyQuery;
import com.github.agadar.nationstates.query.WorldQuery;
import com.github.agadar.nationstates.shard.WorldAssemblyShard;
import com.github.agadar.nationstates.shard.WorldShard;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Simple integration test to check existence of presumably most-used endpoints.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class NationStatesTest {

    private final NationStates nationStates = new NationStates("Integration tests by Agadar");

    /**
     * Test of getNation method, of class NationStates.
     */
    @Test
    public void testGetNation() {
        System.out.println("getNation");

        // Arrange
        final NationQuery query = nationStates.getNation("testlandia");

        // Act
        final Nation result = query.execute();

        // Assert
        assertNotNull(result);
        assertEquals("Testlandia", result.name);
    }

    /**
     * Test of getRegion method, of class NationStates.
     */
    @Test
    public void testGetRegion() {
        System.out.println("getRegion");

        // Arrange
        final RegionQuery query = nationStates.getRegion("the rejected realms");

        // Act
        final Region result = query.execute();

        // Assert
        assertNotNull(result);
        assertEquals("the Rejected Realms", result.name);
    }

    /**
     * Test of getWorld method, of class NationStates.
     */
    @Test
    public void testGetWorld() {
        System.out.println("getWorld");

        // Arrange
        final WorldQuery query = nationStates.getWorld(WorldShard.NUMBER_OF_NATIONS);

        // Act
        final World result = query.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.numberOfNations > 0);
    }

    /**
     * Test of getWorldAssembly method, of class NationStates.
     */
    @Test
    public void testGetWorldAssembly() {
        System.out.println("getWorldAssembly");

        // Arrange
        final WorldAssemblyQuery query = nationStates
                .getWorldAssembly(Council.GENERAL_ASSEMBLY)
                .shards(WorldAssemblyShard.NUMBER_OF_MEMBERS);

        // Act
        final WorldAssembly result = query.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.numberOfMembers > 0);
    }

    /**
     * Test of getVersion method, of class NationStates.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");

        // Arrange
        final VersionQuery query = nationStates.getVersion();

        // Act
        final int version = query.execute();

        // Assert
        assertNotNull(version);
        assertTrue(version > 0);
    }
}
