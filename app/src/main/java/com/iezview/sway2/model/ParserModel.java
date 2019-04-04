package com.iezview.sway2.model;

import com.iezview.sway2.bean.Action;
import com.iezview.sway2.bean.ParserCfg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t54 on 2019/4/4.
 */

public class ParserModel {
    List<ParserCfg> cfgs;
    String url;

    public ParserModel() {
        cfgs = new ArrayList<>();

    }

    public List<ParserCfg> getCfgs() {
        cfgs.clear();
        cfgs.add(new ParserCfg("http://api.47ks.com/webcloud/?v=", url, "阿拉德大陆引擎系统【全网通用】"));
        cfgs.add(new ParserCfg("https://api.653520.top/vip/?url=", url, "解析器1"));
        cfgs.add(new ParserCfg("http://jx.52xftv.cn/?url=", url, "解析器2"));
        return cfgs;
    }

    public void setTargetUrl(String url) {
        this.url = url;
    }
}
