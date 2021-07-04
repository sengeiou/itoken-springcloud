package com.funtl.itoken.web.posts.service.fallback;

import com.funtl.itoken.common.hystrix.Fallback;
import com.funtl.itoken.web.posts.service.PostsService;

/**
 * @ClassName PostsServiceFallback
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/10 11:04
 * @Version 1.0
 **/
public class PostsServiceFallback implements PostsService {
    @Override
    public String page(int pageNum, int pageSize, String tbPostsPostJson) {
        return Fallback.badGateway();
    }

    @Override
    public String get(String postGuid) {
        return null;
    }

    @Override
    public String save(String tbPostsPostJson, String optsBy) {
        return null;
    }
}
