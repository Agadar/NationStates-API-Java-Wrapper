package com.github.agadar.nationstates.domain.common.happening;

import com.github.agadar.nationstates.enumerator.DispatchCategory;
import com.github.agadar.nationstates.enumerator.DispatchSubCategory;

import lombok.Getter;
import lombok.Setter;

/**
 * A happening regarding a dispatch, e.g. '@@talao@@ published
 * "<a href="page=dispatch/id=982940">Talist Manifesto</a>" (Bulletin:
 * Campaign).'.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
@Getter
@Setter
public class DispatchHappening extends Happening {

    /**
     * The name of the nation whose dispatch this happening pertains to.
     */
    private String nation;

    /**
     * The unique identifier of the dispatch. Not supplied when retrieved from Nation resource.
     */
    private long dispatchId;

    /**
     * The name of the dispatch.
     */
    private String dispatchName;

    /**
     * The category of the dispatch.
     */
    private DispatchCategory dispatchCategory;

    /**
     * The sub-category of the dispatch.
     */
    private DispatchSubCategory dispatchSubCategory;

    public DispatchHappening(long id, long timestamp, String description, String nation, long dispatchId,
            String dispatchName, DispatchCategory dispatchCategory, DispatchSubCategory dispatchSubCategory) {
        super(id, timestamp, description);
        this.nation = nation;
        this.dispatchId = dispatchId;
        this.dispatchName = dispatchName;
        this.dispatchCategory = dispatchCategory;
        this.dispatchSubCategory = dispatchSubCategory;
    }

}
