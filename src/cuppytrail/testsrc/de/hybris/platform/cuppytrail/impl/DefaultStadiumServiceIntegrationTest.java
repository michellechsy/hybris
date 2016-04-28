package de.hybris.platform.cuppytrail.impl;

import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.services.StadiumService;
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
public class DefaultStadiumServiceIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private StadiumService stadiumService;
    @Resource
    private ModelService modelService;

    private StadiumModel stadiumModel;
    private final static String STADIUM_NAME = "wembley";
    private final static Integer STADIUM_CAPACITY = Integer.valueOf(12345);

    @Before
    public void setUp() {
        stadiumModel = new StadiumModel();
        stadiumModel.setCapacity(STADIUM_CAPACITY);
        stadiumModel.setCode(STADIUM_NAME);
    }

    @Test(expected = UnknownIdentifierException.class)
    public void testFailBehavior() {
        stadiumService.getStadiumForCode(STADIUM_NAME);
    }

    @Test
    public void testStadiumService() {
        List<StadiumModel> stadiumModels = stadiumService.getStadiums();
        final int size = stadiumModels.size();

        modelService.save(stadiumModel);

        stadiumModels = stadiumService.getStadiums();
        Assert.assertEquals(size+1, stadiumModels.size());
        Assert.assertEquals("Unexpected Stadium found", stadiumModel, stadiumModels.get(stadiumModels.size() - 1));

        final StadiumModel persistedStadiumModel = stadiumService.getStadiumForCode(STADIUM_NAME);
        Assert.assertNotNull("No stadium found", persistedStadiumModel);
        Assert.assertEquals("Different stadium found", stadiumModel, persistedStadiumModel);
    }
}
