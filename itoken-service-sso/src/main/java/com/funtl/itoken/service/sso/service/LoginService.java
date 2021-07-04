package com.funtl.itoken.service.sso.service;

import com.funtl.itoken.common.domain.TbSysUser;

/**
 * 登录业务
 * @ClassName LoginService
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/4 15:56
 * @Version 1.0
 **/
public interface LoginService {
    public TbSysUser login(String loginCode, String plantPassword);
}
