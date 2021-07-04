package com.funtl.itoken.web.posts.controller;

import com.funtl.itoken.common.domain.TbPostsPost;
import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.untils.MapperUtils;
import com.funtl.itoken.common.web.constants.WebConstants;
import com.funtl.itoken.common.web.controller.BaseController;
import com.funtl.itoken.web.posts.service.PostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName PostsController
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/10 10:00
 * @Version 1.0
 **/
@Controller
public class PostsController extends BaseController<TbPostsPost, PostsService> {


    @Autowired
    private PostsService postsService;

    @ModelAttribute
    public TbPostsPost tbPostsPost(String postGuid) {
        TbPostsPost tbPostsPost = null;

        if (StringUtils.isBlank(postGuid)) {
            tbPostsPost = new TbPostsPost();
        } else {
            String json = postsService.get(postGuid);
            try {
                tbPostsPost = MapperUtils.json2pojo(json, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 二次确认是否为空
            if (tbPostsPost == null) {
                tbPostsPost = new TbPostsPost();
            }
        }

        return tbPostsPost;
    }

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String main() {
        return "main";
    }



    /**
     * 跳转到列表页
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 跳转表单页面
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "form";
    }

    /**
     * 保存文章
     *
     * @param tbPostsPost
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbPostsPost tbPostsPost, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        // 初始化

        tbPostsPost.setTimePublished(new Date());

        String tbPostsPostJson = null;
        TbSysUser admin = (TbSysUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
        BaseResult baseResult = null;
        try {
            tbPostsPostJson = MapperUtils.obj2json(tbPostsPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String json = postsService.save(tbPostsPostJson, admin.getUserCode());
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect:/index";
        }
        return "redirect:/form";
    }
}
