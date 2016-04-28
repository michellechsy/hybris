package de.hybris.platform.cuppytrail.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.cuppytrail.daos.StadiumDao;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by michelle on 16-4-25.
 */
@IntegrationTest
public class DefaultStadiumDaoIntegrationTest extends ServicelayerTransactionalTest {
    @Resource(name="stadiumDao")
    private StadiumDao stadiumDao;

    /** used for creation of test data */
    @Resource
    private ModelService modelService;

    private static final String STADIUM_NAME = "wembley";
    private static final Integer STADIUM_CAPACITY = Integer.valueOf(12345);

    @Test
    public void stadiumDaoTest() {
        List<StadiumModel> stadiumByCode = stadiumDao.findStadiumsByCode(STADIUM_NAME);
        Assert.assertTrue("No Stadium should be returned.", stadiumByCode.isEmpty());

        List<StadiumModel> allStadiums = stadiumDao.findStadiums();
        final int size = allStadiums.size();

        final StadiumModel stadiumModel = new StadiumModel();
        stadiumModel.setCode(STADIUM_NAME);
        stadiumModel.setCapacity(STADIUM_CAPACITY);
        modelService.save(stadiumModel);

        allStadiums = stadiumDao.findStadiums();
        Assert.assertEquals(size+1, allStadiums.size());
        Assert.assertEquals("Uexpected stadium found", stadiumModel, allStadiums.get(allStadiums.size() - 1));

        stadiumByCode = stadiumDao.findStadiumsByCode(STADIUM_NAME);
        Assert.assertEquals("Did not find the stadium we just saved", 1, stadiumByCode.size());
        Assert.assertEquals("Retrieved Stadium's name incorrect", STADIUM_NAME, stadiumByCode.get(0).getCode());
        Assert.assertEquals("Retrieved Stadium's capacity incorrect", STADIUM_CAPACITY, stadiumByCode.get(0).getCapacity());

    }
}
