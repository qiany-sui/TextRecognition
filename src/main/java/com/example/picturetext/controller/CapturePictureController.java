package com.example.picturetext.controller;

import com.example.picturetext.service.CommonTRService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author qianyuqi
 * @create 2020-12-07 20:49
 */
@RestController
@CrossOrigin
@Api(value = "图片内容截取", tags = {"图片内容截取"})
@RequestMapping("discern")
public class CapturePictureController {

    @Resource
    private CommonTRService commonTRService;

    @GetMapping("basic-general")
    @ApiOperation(value = "通用文字识别")
    public String basicGeneral(@RequestParam String imageName) {
        return commonTRService.basicGeneral(imageName);
    }

    @GetMapping("basic-accurate-general")
    @ApiOperation(value = "通用文字识别（高精度版）")
    public String basicAccurateGeneral(@RequestParam String imageName) {
        return commonTRService.basicAccurateGeneral(imageName);
    }

    @GetMapping("enhanced-general")
    @ApiOperation(value = "通用文字识别（含生僻字版）")
    public String enhancedGeneral(@RequestParam String imageName) {
        return commonTRService.enhancedGeneral(imageName);
    }


    @GetMapping("web-image")
    @ApiOperation(value = "网络图片文字识别")
    public String webImage(@RequestParam String imageName) {
        return commonTRService.webImage(imageName);
    }

    @GetMapping("qrcode")
    @ApiOperation(value = "二维码识别")
    public String qrcode(@RequestParam String imageName) {
        return commonTRService.qrcode(imageName);
    }

    @GetMapping("handwriting")
    @ApiOperation(value = "手写文字识别")
    public String handwriting(@RequestParam String imageName) {
        return commonTRService.handwriting(imageName);
    }

}
