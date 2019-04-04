package com.iezview.sway2.model;

import com.iezview.sway2.bean.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t54 on 2019/4/4.
 */

public class ActionModel {
    List<Action> result;

    public ActionModel() {
        result = new ArrayList<>();
        result.add(new Action(
                "腾讯视频",
                "https://v.qq.com/",
                "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3774094549,311598158&fm=179&app=42&f=JPEG?w=56&h=56"));
        result.add(new Action(
                "爱奇艺视频",
                "https://www.iqiyi.com/",
                "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2596009146,1243342679&fm=179&app=42&f=PNG?w=56&h=56"));
        result.add(new Action(
                "芒果视频",
                "https://www.mgtv.com/",
                "https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=199783531,2798712360&fm=179&app=42&f=JPEG?w=56&h=56"));
    }

    public List<Action> getActions() {
        return result;
    }
}
