package org.wolf.security3.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wolf.security3.utils.InMemoryStorage;
import org.wolf.security3.utils.VerifyCodeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(value = "/test")
@Controller
public class TestController {

    @ResponseBody
    @GetMapping(value = "test1")
    public String test1() {
        return "test111111111";
    }

    @ResponseBody
    @GetMapping(value = "/me")
    public UserDetails me(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }


    /**
     * getverifycode
     * <p>Title:getAuthCode <／p>
     * <p>Description: 生成图片二维码<／p>
     *
     * @param timestamp
     * @author ZTC
     * @date 2018年2月1日
     * 2018年2月1日
     * {"getverifycode"}
     */
    @RequestMapping({"/getverifycode/{deviceId}"})
    public void getAuthCode(@PathVariable String deviceId, HttpServletRequest request, HttpServletResponse response, String timestamp) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        InMemoryStorage.map.put(deviceId, verifyCode);
        // 生成图片
        int w = 100, h = 50;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
}
