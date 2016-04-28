package de.hybris.platform.cuppytrail.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cuppytrail.daos.StadiumDao;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.services.DefaultStadiumService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by michelle on 16-4-27.
 */
@UnitTest
public class DefaultStadiumServiceUnitTest {

    private DefaultStadiumService stadiumService;
    private StadiumDao stadiumDao;

    private StadiumModel stadiumModel;
    private final static String STADIUM_NAME = "wembley";
    private final static Integer STADIUM_CAPACITY = Integer.valueOf(12345);

    @Before
    public void setUp() {
        stadiumService = new DefaultStadiumService();
        stadiumDao = mock(StadiumDao.class);
        stadiumService.setStadiumDao(stadiumDao);

        stadiumModel = new StadiumModel();
        stadiumModel.setCode(STADIUM_NAME);
        stadiumModel.setCapacity(STADIUM_CAPACITY);
    }

    @Test
    public void testGetAllStadiums() {
        final List<StadiumModel> stadiumModels = Arrays.asList(stadiumModel);
        when(stadiumDao.findStadiums()).thenReturn(stadiumModels);
        final List<StadiumModel> result = stadiumService.getStadiums();
        Assert.assertEquals("We should find one", 1, result.size());
        Assert.assertEquals("And should eequals what the mock returned", stadiumModel, result.get(0));
    }

    @Test
    public void testGetStadium() {
        when(stadiumDao.findStadiumsByCode(STADIUM_NAME)).thenReturn(Collections.singletonList(stadiumModel));
        final StadiumModel result = stadiumService.getStadiumForCode(STADIUM_NAME);
        Assert.assertEquals("Stadium should equals() what the mock returned", stadiumModel, result);
    }

}
