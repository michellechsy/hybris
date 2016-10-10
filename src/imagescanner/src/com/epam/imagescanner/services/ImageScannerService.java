package com.epam.imagescanner.services;

import com.epam.imagescanner.strategies.ImageScannerStrategy;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("imageComparisonService")
public class ImageScannerService {
    @Resource
    private ImageScannerStrategy imageScannerStrategy;

    @Resource
    private MediaService mediaService;

    @Resource
    private ProductService productService;

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource(name = "catalogVersionService")
    private CatalogVersionService catalogVersionService;

    @Value("imagescanner.allowed.diff.percentage")
    private int allowedDiffPercentage;

    private static final Logger LOG = Logger.getLogger(ImageScannerService.class);

    public Set<MediaModel> findSimilarImages(final String imgUrl) {
        Set<MediaModel> matchedMedia = new HashSet<>();

        try {
            Collection<CatalogVersionModel> catalogVersions = catalogVersionService
                    .getSessionCatalogVersions();
            if (catalogVersions.isEmpty()) {
                //TODO TBC
                catalogVersions = catalogVersionService.getAllCatalogVersions();
            }
            BufferedImage srcImg = getPolishedImage(imgUrl);
            List<MediaModel> existingMedia = getMediaList();
            for (MediaModel media : existingMedia) {
                BufferedImage comparedImg = getPolishedImage(media.getURL());
                double diff = imageScannerStrategy.compareImages(srcImg, comparedImg);
                if ( diff <= (allowedDiffPercentage / 100.0)) {
                    matchedMedia.add(media);
                }
            }

        } catch (IOException e) {
            LOG.error("Failed to get Image data: " + e);
        }

        return matchedMedia;
    }

    private ProductModel getProductByMedia(final MediaModel media) {

    }

    private BufferedImage getPolishedImage(final String imgUrl) throws IOException {
        BufferedImage originalImg = ImageIO.read(new URL(imgUrl));
        BufferedImage resizedImg = imageScannerStrategy.reduceSize(originalImg);
        return resizedImg;
    }

    private List<MediaModel> getMediaList() {
        final String query = "SELECT {pk} FROM {MediaFolder} ORDER BY pk ASC";
        SearchResult result = flexibleSearchService.search(query);
        return result.getResult();
    }
}
