package org.example.controller;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;

@RestController
public class WxDemoController {

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "/wxServerValdation")
    public String wxServerValdation(String signature, String timestamp, String nonce, String echostr) {
        if (StrUtil.isEmpty(signature) || StrUtil.isEmpty(timestamp) || StrUtil.isEmpty(nonce) || StrUtil.isEmpty(echostr)) {
            return "";
        }
        ArrayList<String> list = new ArrayList<String>();
        list.add(nonce);
        list.add(timestamp);
        list.add("my_wx_test");//这是第5步中你设置的Token
        Collections.sort(list);
        String sha1Singnature = DigestUtils.sha1Hex(list.get(0) + list.get(1) + list.get(2));
        if (sha1Singnature.equals(signature)) {
            return echostr;
        } else {
            return "";
        }

    }
}
