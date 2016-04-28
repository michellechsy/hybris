package de.hybris.platform.cuppytrail.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cuppytrail.daos.StadiumDao;
import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.facades.DefaultStadiumFacade;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.services.StadiumService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by michelle on 16-4-27.
 */
@UnitTest
public class DefaultStadiumFacadeUnitTest {
    private DefaultStadiumFacade stadiumFacade;
    private StadiumService stadiumService;

    private final String STADIUM_NAME = "wembley";
    private final Integer STADIUM_CAPACITY = Integer.valueOf(12345);

    private List<StadiumModel> dummyDataStadiumList() {
        final StadiumModel wembley = new StadiumModel();
        wembley.setCapacity(STADIUM_CAPACITY);
        wembley.setCode(STADIUM_NAME);
        final List<StadiumModel> stadiums = new ArrayList<StadiumModel>();
        stadiums.add(wembley);
        return stadiums;
    }

    private StadiumModel dummyDataStadium() {
        final StadiumModel wembley = new StadiumModel();
        wembley.setCode(STADIUM_NAME);
        wembley.setCapacity(STADIUM_CAPACITY);
        return wembley;
    }

    @Before
    public void setUp() {
        stadiumFacade = new DefaultStadiumFacade();
        stadiumService = mock(StadiumService.class);
        stadiumFacade.setStadiumService(stadiumService);
    }

    @Test
    public void testGetAllStadiums() {
        final List<StadiumModel> stadiums = dummyDataStadiumList();
        final StadiumModel wembley = dummyDataStadium();
        when(stadiumService.getStadiums()).thenReturn(stadiums);

        final List<StadiumData> dto = stadiumFacade.getStadiums();
        Assert.assertNotNull(dto);
        Assert.assertEquals(stadiums.size(), dto.size());
        Assert.assertEquals(wembley.getCapacity().toString(), dto.get(0).getCapacity());
        Assert.assertEquals(wembley.getCode(), dto.get(0).getName());
    }

    @Test
    public void testGetStadium() {
        final StadiumModel wembley = dummyDataStadium();
        when(stadiumService.getStadiumForCode(STADIUM_NAME)).thenReturn(wembley);

        final StadiumData stadium = stadiumFacade.getStadium(STADIUM_NAME);
        Assert.assertEquals(wembley.getCode(), stadium.getName());
        Assert.assertEquals(wembley.getCapacity().toString(), stadium.getCapacity());
    }
}
