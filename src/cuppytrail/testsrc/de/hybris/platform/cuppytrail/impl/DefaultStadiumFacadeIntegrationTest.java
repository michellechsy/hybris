package de.hybris.platform.cuppytrail.impl;

import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.facades.StadiumFacade;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by michelle on 16-4-27.
 */
public class DefaultStadiumFacadeIntegrationTest extends ServicelayerTransactionalTest {
    @Resource
    private StadiumFacade stadiumFacade;

    @Resource
    private ModelService modelService;

    private StadiumModel stadiumModel;
    private final String STADIUM_NAME = "wembley";
    private final Integer STADIUM_CAPACITY = Integer.valueOf(12345);

    @Before
    public void setUp() {
        stadiumModel = new StadiumModel();
        stadiumModel.setCode(STADIUM_NAME);
        stadiumModel.setCapacity(STADIUM_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter() {
        stadiumFacade.getStadium(null);
    }

    @Test(expected = UnknownIdentifierException.class)
    public void testInvalidParameter() {
        stadiumFacade.getStadium(STADIUM_NAME);
    }

    @Test
    public void testStadiumFacade() {
        List<StadiumData> stadiumListData = stadiumFacade.getStadiums();
        Assert.assertNotNull(stadiumListData);
        final int size = stadiumListData.size();
        modelService.save(stadiumModel);

        stadiumListData = stadiumFacade.getStadiums();
        Assert.assertNotNull(stadiumListData);
        Assert.assertEquals(size + 1, stadiumListData.size());
        Assert.assertEquals(STADIUM_NAME, stadiumListData.get(0).getName());
        Assert.assertEquals(STADIUM_CAPACITY.toString(), stadiumListData.get(0).getCapacity());

        final StadiumData persistedStadiumData = stadiumFacade.getStadium(STADIUM_NAME);
        Assert.assertNotNull(persistedStadiumData);
        Assert.assertEquals(STADIUM_NAME, persistedStadiumData.getName());
        Assert.assertEquals(STADIUM_CAPACITY.toString(), persistedStadiumData.getCapacity());
    }
}
