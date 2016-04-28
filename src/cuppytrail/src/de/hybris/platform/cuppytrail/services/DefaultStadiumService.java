package de.hybris.platform.cuppytrail.services;

import de.hybris.platform.cuppytrail.daos.StadiumDao;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * Created by michelle on 16-4-27.
 */
public class DefaultStadiumService implements StadiumService {

    private StadiumDao stadiumDao;

    @Override
    public List<StadiumModel> getStadiums() {
        return stadiumDao.findStadiums();
    }

    @Override
    public StadiumModel getStadiumForCode(final String stadiumName) {
        final List<StadiumModel> result = stadiumDao.findStadiumsByCode(stadiumName);
        if (result.isEmpty()) {
            throw new UnknownIdentifierException("Stadium with code '" + stadiumName + "' not found");
        }
        else if (result.size() > 1) {
            throw new AmbiguousIdentifierException("Stadium code '" + stadiumName + "' is not unique, "
                    + result.size() + " stadiums found.");
        }
        return result.get(0);
    }

    @Required
    public void setStadiumDao(final StadiumDao stadiumDao) {
        this.stadiumDao  = stadiumDao;
    }

}
