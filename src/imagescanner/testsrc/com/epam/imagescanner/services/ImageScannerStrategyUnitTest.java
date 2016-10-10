package com.epam.imagescanner.services;

import com.epam.imagescanner.strategies.ImageScannerStrategy;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Test;

import java.net.URL;

@UnitTest
public class ImageScannerStrategyUnitTest {
    private ImageScannerStrategy imageScannerStrategy =  new ImageScannerStrategy();

    @Test
    public void testSimilarImages() {
        URL img1 = getImageFile("/imagescanner/test/images/img1.jpg");
        URL img2 = getImageFile("/imagescanner/test/images/img2.jpg");

        double diff = imageScannerStrategy.findImageSimilarity(img1, img2);
        System.out.println(diff);
    }

    private URL getImageFile(final String filename) {
        return this.getClass().getResource(filename);
    }

}
