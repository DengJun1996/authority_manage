package com.dj.config;


import org.springframework.stereotype.Component;

/**
 * @author wxl
 * @Description:
 * @date 2020/7/3
 * @company www.dfdk.com.cn
 */
@Component
public class ApiConfig {

    private final ConfigProp configProp;

    public ApiConfig(ConfigProp configProp) {
        this.configProp = configProp;
    }

    public String getAreaListApiUrl(){
        return "http://"
                + urlWithPort()
                + "/api/"
                + configProp.getAreaApi();
    }

    public String getWsUrl () {
        return "ws://"
                + urlWithPort()
                + "/websocket/"
                + configProp.getTopic();
    }

    private String urlWithPort () {
        return  configProp.getUrl()
                + ":"
                + configProp.getPort();
    }

}
