package com.funtl.itoken.common.hystrix;

import com.funtl.itoken.common.constants.HttpStatusConstants;
import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.untils.MapperUtils;
import com.google.common.collect.Lists;

/**
 * 通用的熔断方法
 * @ClassName Fallback
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/4 16:07
 * @Version 1.0
 **/
public class Fallback {
    /**
     * 502
     * @return
     */
    public static String badGateway() {
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(
                new BaseResult.Error(
                        String.valueOf(HttpStatusConstants.BAD_GATEWAY.getStatus()),
                        HttpStatusConstants.BAD_GATEWAY.getContent())));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
