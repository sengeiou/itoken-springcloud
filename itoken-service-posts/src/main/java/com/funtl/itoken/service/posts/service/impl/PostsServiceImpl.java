package com.funtl.itoken.service.posts.service.impl;

import com.funtl.itoken.common.domain.TbPostsPost;
import com.funtl.itoken.common.service.impl.BaseServiceImpl;
import com.funtl.itoken.service.posts.mapper.TbPostsPostExtendMapper;
import com.funtl.itoken.service.posts.service.PostsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName PostsServiceImpl
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/8 17:28
 * @Version 1.0
 **/
@Service
@Transactional(readOnly = true)
public class PostsServiceImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostExtendMapper> implements PostsService<TbPostsPost> {
}
