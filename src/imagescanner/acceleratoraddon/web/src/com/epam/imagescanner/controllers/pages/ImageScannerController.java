package com.epam.imagescanner.controllers.pages;

import com.epam.imagescanner.services.ImageScannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/imagescanner/")
public class ImageScannerController {

    @Resource(name = "imageScannerService")
    private ImageScannerService imageScannerService;

    public  void getSimilarImage() {

    }
}
