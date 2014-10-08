/**   
 * Copyright (c) 2014, 启创数据 All rights reserved
 */
package hn.travel.cms.web.account;

import hn.travel.cms.web.captcha.ImageCaptchaEngine;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * @Description 
 * @author chenbing
 * @date 2014-8-17
 * @version V1.0.0
 */
@Controller
public class CommonController {

    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    
    public String valid(HttpSession ses, String code) {
        ImageCaptchaEngine.getInstance().validateResponseForID(ses.getId(),
                code);
        
        return "";
    }
}
