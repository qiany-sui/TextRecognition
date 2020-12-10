package com.example.picturetext.controller;

import com.baidu.aip.ocr.AipOcr;
import com.example.picturetext.service.BaiduService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author qianyuqi
 * @create 2020-12-07 20:49
 */
@RestController
@CrossOrigin
@Api(value = "图片内容截取", tags = {"图片内容截取"})
public class CapturePictureController {

    @Resource
    private BaiduService baiduService;

    @GetMapping("test/picture")
    @ApiOperation(value = "通用截取")
    public String getPictureText(@RequestParam String imageName) throws IOException {
        AipOcr client = baiduService.getClient();
        return baiduService.genericCaptureTextInPicture(client, imageName);
    }
}
