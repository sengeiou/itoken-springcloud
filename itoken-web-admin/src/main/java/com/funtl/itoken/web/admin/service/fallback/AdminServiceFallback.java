package com.funtl.itoken.web.admin.service.fallback;

import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.hystrix.Fallback;
import com.funtl.itoken.common.untils.MapperUtils;
import com.funtl.itoken.web.admin.service.AdminService;
import org.springframework.stereotype.Component;

/**
 * @ClassName AdminServiceFallback
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/3 11:19
 * @Version 1.0
 **/
@Component
public class AdminServiceFallback implements AdminService {
    @Override
    public String get(String userCode) {
        try {
            String json = MapperUtils.obj2json(new TbSysUser());
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String save(String tbSysUserJson, String optsBy) {
        return Fallback.badGateway();
    }

    @Override
    public String page(int pageNum, int pageSize, String tbSysUserJson) {
        return Fallback.badGateway();
    }
}
