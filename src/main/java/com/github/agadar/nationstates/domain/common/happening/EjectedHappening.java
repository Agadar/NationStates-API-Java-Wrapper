package com.github.agadar.nationstates.domain.common.happening;

/**
 * A happening regarding an ejection, e.g. '@@ohieniupeoyp@@ was ejected and
 * banned from %%alpha_and_omega%% by @@benevolent_zero@@.'.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class EjectedHappening extends Happening {

    /**
     * The nation that did the ejecting.
     */
    public String ejectingNation;

    /**
     * The nation that was ejected.
     */
    public String ejectedNation;

    /**
     * Whether the ejected nation was also banned.
     */
    public boolean banned;

    /**
     * The region the nation was ejected from.
     */
    public String fromRegion;

    public EjectedHappening(long id, long timestamp, String description, String ejectingNation, String ejectedNation,
            boolean banned, String fromRegion) {
        super(id, timestamp, description);
        this.ejectingNation = ejectingNation;
        this.ejectedNation = ejectedNation;
        this.banned = banned;
        this.fromRegion = fromRegion;
    }

}
