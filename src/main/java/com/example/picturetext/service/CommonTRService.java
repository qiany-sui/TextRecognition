package com.example.picturetext.service;

import com.baidu.aip.ocr.AipOcr;
import com.example.picturetext.config.MineConfigure;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 通用文字识别
 *
 * @author qianyuqi
 * @create 2021-09-18 10:03
 */
@Service
public class CommonTRService {

    @Resource
    private BaiduService baiduService;

    @Resource
    private MineConfigure mineConfigure;

    @Value("${source-file-dir}")
    private String dir;


    // 通用文字识别
    public String basicGeneral(String imageName) {
        AipOcr aipOcr = baiduService.getSingleApiOcr();
        // 传入可选参数调用接口
        HashMap<String, String> options = getOptionsMap();
        // 参数为本地图片路径
        JSONObject res = aipOcr.basicGeneral(dir + imageName, options);
        return getResString(res);
    }


    // 通用文字识别（高精度版）
    public String basicAccurateGeneral(String imageName) {
        AipOcr aipOcr = baiduService.getSingleApiOcr();
        // 传入可选参数调用接口
        HashMap<String, String> options = getOptionsMap();
        // 参数为本地图片路径
        JSONObject res = aipOcr.basicAccurateGeneral(dir + imageName, options);
        return getResString(res);
    }

    // 通用文字识别（含生僻字版）
    public String enhancedGeneral(String imageName) {
        AipOcr aipOcr = baiduService.getSingleApiOcr();
        // 传入可选参数调用接口
        HashMap<String, String> options = getOptionsMap();
        // 参数为本地图片路径
        JSONObject res = aipOcr.enhancedGeneral(dir + imageName, options);
        return getResString(res);
    }

    // 网络图片文字识别
    public String webImage(String imageName) {
        AipOcr aipOcr = baiduService.getSingleApiOcr();
        // 传入可选参数调用接口
        HashMap<String, String> options = getOptionsMap();
        // 参数为本地图片路径
        JSONObject res = aipOcr.webImage(dir + imageName, options);
        return getResString(res);
    }

    // 二维码识别
    public String qrcode(String imageName) {
        AipOcr aipOcr = baiduService.getSingleApiOcr();
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        // 参数为二进制数组
        byte[] file = getImageToBase64(dir + imageName);
        JSONObject res = aipOcr.qrcode(file, options);
        return getResString(res);
    }

    // 手写文字识别
    public String handwriting(String imageName) {
        AipOcr aipOcr = baiduService.getSingleApiOcr();
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("recognize_granularity", "big");
        options.put("probability", "false");
        options.put("detect_direction", "true");
        // 参数为二进制数组
        byte[] file = getImageToBase64(dir + imageName);
        JSONObject res = aipOcr.handwriting(file, options);
        return getResString(res);
    }


    private HashMap<String, String> getOptionsMap() {
        HashMap<String, String> options = new HashMap<>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        return options;
    }


    private String getResString(JSONObject res) {
        StringBuilder builder = new StringBuilder();
        JSONArray wordsResult = res.getJSONArray("words_result");
        for (int i = 0; i < wordsResult.length(); i++) {
            String words = wordsResult.getJSONObject(i).getString("words");
            builder.append(words).append("\n");
        }
        return builder.toString();
    }

    private byte[] getImageToBase64(String imagePath) {
        byte[] data = null;
        // 读取图片字节数组
        try (InputStream in = new FileInputStream(imagePath)) {
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // 对字节数组进行Base64编码，得到Base64编码的字符串
        String encode = new BASE64Encoder().encode(data);
        return encode.getBytes(StandardCharsets.UTF_8);
    }
}
