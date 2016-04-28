package de.hybris.platform.cuppytrail.daos;

import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.List;

/**
 * Created by michelle on 16-4-25.
 */
public interface StadiumDao {
    List<StadiumModel> findStadiums();

    List<StadiumModel> findStadiumsByCode(String code);
}
