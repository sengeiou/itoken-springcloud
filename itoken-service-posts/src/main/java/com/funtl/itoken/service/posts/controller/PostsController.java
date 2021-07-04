package com.funtl.itoken.service.posts.controller;

import com.funtl.itoken.common.domain.TbPostsPost;
import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.untils.MapperUtils;
import com.funtl.itoken.service.posts.service.PostsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName PostsController
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/8 17:31
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "v1/posts")
public class PostsController {
    @Autowired
    private PostsService<TbPostsPost> postsService;

    /**
     * 根据 ID 获取文章
     * @param postGuid
     * @return
     */
    @RequestMapping(value = "{postGuid}", method = RequestMethod.GET)
    public BaseResult get(
            @PathVariable(value = "postGuid") String postGuid) {
        TbPostsPost tbPostsPost = new TbPostsPost();
        tbPostsPost.setPostGuid(postGuid);
        TbPostsPost obj = postsService.selectOne(tbPostsPost);
        return BaseResult.ok(obj);
    }

    /**
     * 保存文章
     * @param tbPostsPostJson
     * @param optsBy
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(
            @RequestParam(required = true) String tbPostsPostJson,
            @RequestParam(required = true) String optsBy
    ){
        int result = 0;
        TbPostsPost tbPostsPost = null;
        try {
            tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (tbPostsPost != null) {
            // 新增
            if (StringUtils.isBlank(tbPostsPost.getPostGuid())) {
                // 初始化
                tbPostsPost.setPostGuid(UUID.randomUUID().toString());

                result = postsService.insert(tbPostsPost, optsBy);
            }else {// 修改
                result = postsService.update(tbPostsPost, optsBy);
            }

            // 最少有一行数据受影响
            if (result > 0) {
                return BaseResult.ok("保存文章成功");
            }
        }
        return BaseResult.ok("保存文章失败");
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param tbPostsPostJson
     * @return
     */
    @ApiOperation(value = "文章服务分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "tbSysUserJson", value = "文章对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String tbPostsPostJson
    ) {
        TbPostsPost tbPostsPost = null;
        if (StringUtils.isNotBlank(tbPostsPostJson)) {
            try {
                tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PageInfo pageInfo = postsService.page(pageNum, pageSize, tbPostsPost);
        // 分页后的结果集
        List<TbPostsPost> list = pageInfo.getList();

        // 封装 cursor 对象
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }
}
