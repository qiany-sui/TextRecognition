package com.example.picturetext.service;

import com.baidu.aip.ocr.AipOcr;
import com.example.picturetext.config.MineConfigure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qianyuqi
 * @create 2020-12-07 20:41
 */
@Service
public class BaiduService {

    @Resource
    private MineConfigure mineConfigure;

    private static AipOcr client;

    @Value("${source-file-dir}")
    private String dir;

    // 获取百度 AipOcr
    public AipOcr getSingleApiOcr() {
        synchronized (this) {
            if (client == null) {
                // 初始化一个AipOcr
                client = new AipOcr(mineConfigure.getAppId(), mineConfigure.getAppKey(), mineConfigure.getSecretKey());
                // 可选：设置网络连接参数
                client.setConnectionTimeoutInMillis(10000);
                client.setSocketTimeoutInMillis(60000);
            }
            return client;
        }
    }
}
