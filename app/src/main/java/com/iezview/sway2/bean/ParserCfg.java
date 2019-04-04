package com.iezview.sway2.bean;

/**
 * Created by t54 on 2019/4/4.
 */

public class ParserCfg {
    String url;
    String targetUrl;
    String name;

    public ParserCfg(String url, String targetUrl, String name) {
        this.url = url;
        this.targetUrl = targetUrl;
        this.name = name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getUrl() {
        return url + targetUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}