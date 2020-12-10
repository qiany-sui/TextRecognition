package com.example.picturetext.service;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import org.springframework.stereotype.Service;

/**
 * @author qianyuqi
 * @create 2020-12-07 20:41
 */
@Service
public class BaiduService {

    //设置APPID/AK/SK
    public static final String APP_ID = "22746306";
    public static final String API_KEY = "pPADygcE9kEoDlzR69n8psCE";
    public static final String SECRET_KEY = "UDvGBBh2pub7qrWHNgCZovrYvToxbPuv";

    private static AipOcr client;

    public AipOcr getClient() {
        if(client == null) {
            // 初始化一个AipOcr
            client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
            // 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(10000);
            client.setSocketTimeoutInMillis(60000);
        }
        return client;
    }

    public String genericCaptureTextInPicture(AipOcr client, String imageName) throws IOException {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
        String filePath = "C:\\Users\\Administrator\\Desktop\\西安\\" + imageName;
        JSONObject res = client.basicGeneral(filePath, options);

        StringBuilder builder = new StringBuilder();
        JSONArray words_result = res.getJSONArray("words_result");
        for (int i = 0; i < words_result.length(); i++) {
            String words = words_result.getJSONObject(i).getString("words");
            builder.append(words).append("\n");
        }
        return builder.toString();

//        return res.toString(2);

        // 参数为本地图片二进制数组
//        byte[] file = Util.readFileByBytes(image);
//        res = client.basicGeneral(file, options);
//        System.out.println(res.toString(2));


        // 通用文字识别, 图片参数为远程url图片
//        JSONObject res = client.basicGeneralUrl(url, options);
//        return res.toString(2);
    }


    // 高精度
    public void sample2(AipOcr client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
        String image = "test.jpg";
        JSONObject res = client.basicAccurateGeneral(image, options);
        System.out.println(res.toString(2));

        // 参数为本地图片二进制数组
//        byte[] file = readImageFile(image);
//        res = client.basicAccurateGeneral(file, options);
//        System.out.println(res.toString(2));

    }


//    public void sample3(AipOcr client) {
//        // 传入可选参数调用接口
//        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("detect_direction", "true");
//        options.put("detect_language", "true");
//
//
//        // 参数为本地图片路径
//        String image = "test.jpg";
//        JSONObject res = client.webImage(image, options);
//        System.out.println(res.toString(2));
//
//        // 参数为本地图片二进制数组
//        byte[] file = Util.readImageFile(image);
//
//        res = client.webImage(file, options);
//        System.out.println(res.toString(2));
//
//
//        // 网络图片文字识别, 图片参数为远程url图片
//        JSONObject res = client.webImageUrl(url, options);
//        System.out.println(res.toString(2));
//
//    }
}
