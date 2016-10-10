package com.epam.imagescanner.strategies;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

@Service("imageScannerStrategy")
public class ImageScannerStrategy {
    private static final int IMG_WIDTH = 8;
    private static final int IMG_HEIGHT = 8;

    private static final Logger LOG = Logger.getLogger(ImageScannerStrategy.class);

    public double findImageSimilarity(final URL srcImageUrl, final URL comparedImageUrl) {
        double matchedPercentage = 0;

        validateParameterNotNull(srcImageUrl, "Cannot get the image for comparison");
        validateParameterNotNull(comparedImageUrl, "Cannot get the existing image from Hybris to "
                + "compare");

        try {
            BufferedImage srcImg = ImageIO.read(srcImageUrl);
            BufferedImage compareImg = ImageIO.read(comparedImageUrl);

            // Resize Images
            BufferedImage resizedSrcImg = reduceColor(reduceSize(srcImg));
            BufferedImage resizedCompareImg = reduceColor(reduceSize(compareImg));

            // reduce color
            // average color

            return compareImages(resizedSrcImg, resizedCompareImg);


        } catch (IOException e) {
            LOG.error("Failed to get Image data: " + e);
        }
        // return the match percentages
        return matchedPercentage;
    }

    public BufferedImage reduceSize(final BufferedImage originalImage) {
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
                : originalImage.getType();
        BufferedImage resizedImg = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D graphics2D = resizedImg.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        graphics2D.dispose();
        graphics2D.setComposite(AlphaComposite.Src);

        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImg;
    }

    private BufferedImage reduceColor(BufferedImage image) {
        /*
        BufferedImage reducedImg = new BufferedImage(image.getWidth(), image.getHeight(),
                image.getType());

        ColorConvertOp colorConvertOp = new ColorConvertOp(image.getColorModel()
                .getColorSpace(), reducedImg.getColorModel().getColorSpace(), null);
        colorConvertOp.filter(image, reducedImg);
*/
        int width = image.getWidth();
        int height = image.getHeight();

        // convert to grayscale
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // cal average
                int avg = (r + g + b) / 3;
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                image.setRGB(x, y, p);
            }
        }
        return image;
    }

    public double compareImages(final BufferedImage img1, final BufferedImage img2) {
        int width1 = img1.getWidth(null);
        int width2 = img2.getWidth(null);

        int height1 = img1.getHeight(null);
        int height2 = img2.getHeight(null);

        long diff = 0;
        for (int y = 0; y < height1; y++) {
            for (int x = 0; x < width1; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);

                diff += Math.abs(getRGB(rgb1, 16) - getRGB(rgb2, 16));
                diff += Math.abs(getRGB(rgb1, 8) - getRGB(rgb2, 8));
                diff += Math.abs(getRGB(rgb1, 0) - getRGB(rgb2, 0));
            }
        }
        double n = width1 * height1 * 3;
        return diff / n / 255.0;
    }

    private int getRGB(int rgb, int bit) {
        return (bit == 0) ? rgb & 0xff : (rgb >> bit) & 0xff;
    }
}
