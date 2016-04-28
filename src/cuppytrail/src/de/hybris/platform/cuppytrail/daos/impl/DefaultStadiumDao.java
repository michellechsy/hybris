package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.StadiumDao;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by michelle on 16-4-25.
 */
@Component("stadiumDao")
public class DefaultStadiumDao implements StadiumDao {

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    final String queryStr = "SELECT {p:" + StadiumModel.PK + "} FROM {"
            + StadiumModel._TYPECODE + " AS p} ";

    @Override
    public List<StadiumModel> findStadiums() {

        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryStr);

        // can also specify the paginating logic for a safeguard against returning very large amounts of data, or hogging the DB when there are miillions of items being returned.
        // query.setStart(start);
        // query.setCount(count);

        return flexibleSearchService.<StadiumModel>search(query).getResult();
    }

    @Override
    public List<StadiumModel> findStadiumsByCode(String code) {
        final String queryByCodeStr = queryStr + " WHERE {p:" + StadiumModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryByCodeStr);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<StadiumModel>search(query).getResult();
    }
}
