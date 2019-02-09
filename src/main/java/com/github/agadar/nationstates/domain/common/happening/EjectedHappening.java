package com.github.agadar.nationstates.domain.common.happening;

import lombok.Getter;
import lombok.Setter;

/**
 * A happening regarding an ejection, e.g. '@@ohieniupeoyp@@ was ejected and
 * banned from %%alpha_and_omega%% by @@benevolent_zero@@.'.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
@Getter
@Setter
public class EjectedHappening extends Happening {

    /**
     * The nation that did the ejecting.
     */
    private String ejectingNation;

    /**
     * The nation that was ejected.
     */
    private String ejectedNation;

    /**
     * Whether the ejected nation was also banned.
     */
    private boolean banned;

    /**
     * The region the nation was ejected from. Empty if this happening
     * was retrieved from the Region shard.
     */
    private String fromRegion;

    public EjectedHappening(long id, long timestamp, String description, String ejectingNation, String ejectedNation,
            boolean banned, String fromRegion) {
        super(id, timestamp, description);
        this.ejectingNation = ejectingNation;
        this.ejectedNation = ejectedNation;
        this.banned = banned;
        this.fromRegion = fromRegion;
    }

}
