package de.hybris.platform.cuppytrail.facades;

import de.hybris.platform.cuppytrail.data.StadiumData;

import java.util.List;

/**
 * Created by michelle on 16-4-27.
 */
public interface StadiumFacade {

    List<StadiumData> getStadiums();

    StadiumData getStadium(String name);
}
