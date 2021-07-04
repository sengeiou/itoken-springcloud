package com.funtl.itoken.service.sso.controller;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.untils.CookieUtils;
import com.funtl.itoken.common.untils.MapperUtils;
import com.funtl.itoken.service.sso.service.LoginService;
import com.funtl.itoken.service.sso.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/4 16:16
 * @Version 1.0
 **/
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    /**
     * 跳转登录页
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String url,
                        HttpServletRequest request,
                        Model model) {
        String token = CookieUtils.getCookieValue(request, "token");
        // token 不为空， 可能已登陆
        if (StringUtils.isNotBlank(token)) {
            String loginCode = redisService.get(token);
            if (StringUtils.isNotBlank(loginCode)) {
                String json = redisService.get(loginCode);
                if (StringUtils.isNotBlank(json)) {
                    try {
                        TbSysUser tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        // 已登录
                        if (tbSysUser != null) {
                            if (StringUtils.isNotBlank(url)) {
                                return "redirect:" + url;
                            }
                        }
                        // 否则 将登录信息传到前端
                        model.addAttribute("tbSysUser", tbSysUser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        if (StringUtils.isNotBlank(url)) {
            model.addAttribute("url", url);
        }
        return "login";
    }

    /**
     * 登录业务
     * @param loginCode
     * @param password
     * @param url
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(
            @RequestParam(required = true) String loginCode,
            @RequestParam(required = true) String password,
            @RequestParam(required = false) String url,
            HttpServletRequest request,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {

        TbSysUser tbSysUser = loginService.login(loginCode, password);
        // 登录失败
        if (tbSysUser == null) {
            redirectAttributes.addAttribute("message", "用户名或密码错误，请重新登陆");
        } else {// 登陆成功
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, loginCode, 60 * 60 * 24);
            // 将 token 放入缓存
            if (StringUtils.isNotBlank(result) && "ok".equals(result)) {
                CookieUtils.setCookie(request, response, "token", token, 60 * 60 * 24);
                if (StringUtils.isNotBlank(url)) {
                    return "redirect:" + url;
                }
            } else {// 熔断处理
                redirectAttributes.addAttribute("message", "服务器异常，请稍后再试");
            }
        }
        return "redirect:/login";
    }

    /**
     * 注销
     * @param request
     * @param response
     * @param url
     * @param model
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam(required = false) String url,
                         Model model) {
        CookieUtils.deleteCookie(request, response, "token");
        return login(url, request, model);
    }
}
