package de.hybris.platform.cuppytrail.services;

import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.List;

/**
 * Created by michelle on 16-4-27.
 */
public interface StadiumService {
    StadiumModel getStadiumForCode(String stadiumName);

    List<StadiumModel> getStadiums();
}
