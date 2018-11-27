package com.github.agadar.nationstates.domain.common.happening;

import com.github.agadar.nationstates.enumerator.EmbassyHappeningType;

/**
 * A happening regarding a change in embassies between regions, e.g. 'Embassy
 * established between %%the_embassy%% and %%skywardsins%%.'.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class EmbassyHappening extends Happening {

    /**
     * The nation responsible for the change. Not always available / applicable.
     */
    public String nation;

    /**
     * One of the two regions involved in the embassy happening.
     */
    public String region1;

    /**
     * One of the two regions involved in the embassy happening.
     */
    public String region2;

    /**
     * The type of the embassy happening.
     */
    public EmbassyHappeningType embassyHappeningType;

    public EmbassyHappening(long id, long timestamp, String description, String nation, String region1, String region2,
            EmbassyHappeningType embassyHappeningType) {
        super(id, timestamp, description);
        this.nation = nation;
        this.region1 = region1;
        this.region2 = region2;
        this.embassyHappeningType = embassyHappeningType;
    }

}
