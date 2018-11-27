package com.github.agadar.nationstates.domain.common.happening;

import lombok.Getter;
import lombok.Setter;

/**
 * A happening regarding a legislatory change in a nation, e.g. 'Following new
 * legislation in @@terkaulia@@, naturists are jailed regularly for indecent
 * exposure.'.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
@Getter
@Setter
public class LawHappening extends Happening {

    /**
     * The name of the nation affected by the legislatory change.
     */
    private String nation;

    /**
     * Description of the result of the legislatory change.
     */
    private String result;

    public LawHappening(long id, long timestamp, String description, String nation, String result) {
        super(id, timestamp, description);
        this.nation = nation;
        this.result = result;
    }

}
