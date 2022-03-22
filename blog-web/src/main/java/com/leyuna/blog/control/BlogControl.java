package com.leyuna.blog.control;

import com.leyuna.blog.bean.blog.BlogBean;
import com.leyuna.blog.bean.blog.DataResponse;
import com.leyuna.blog.co.blog.BlogCO;
import com.leyuna.blog.co.blog.LuceneCO;
import com.leyuna.blog.service.BlogService;
import com.leyuna.blog.service.SearchService;
import com.leyuna.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author pengli
 * @create 2021-08-10 15:04
    博客控制器- 控制博客行为 网站公告行为 以及各种公示行为
 */
@RestController()
@RequestMapping("/blog")
public class BlogControl{

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private SearchService searchService;
    /**
     * 发布博客
     * @param blogBean
     * @return
     */
    @PostMapping("/addBlog")
    public DataResponse addBlog(@RequestBody BlogBean blogBean){
        return blogService.addBlog(blogBean);
    }

    /**
     * 十条十条取当前所有的博客  每触发一次前端分页自动翻一页
     * @return
     */
    @GetMapping("/blogs")
    public DataResponse blogs(BlogBean blogBean){
        return blogService.getBlogsByPage(blogBean);
    }

    @GetMapping("/blog/{id}")
    public DataResponse<BlogCO> getBlogById(@PathVariable(value = "id")String blogId){
        return blogService.getBlogById(blogId);
    }

    @PostMapping("/edit")
    public DataResponse editBlog(@RequestBody BlogBean blogBean){
        return blogService.updateBlog(blogBean);
    }

    /**
     * 站内搜索博客
     * @return
     */
    @GetMapping("/search")
    public DataResponse<LuceneCO> blogSearch(BlogBean blogBean){
        return searchService.getBlogFromSearch(blogBean);
    }

    /**
     * 创建当前数据库中所有博客的索引库
     * @return
     */
    @PostMapping("/createDocument")
    public DataResponse createAllBlogDocument(){
        return searchService.createBlogSearch();
    }
}
