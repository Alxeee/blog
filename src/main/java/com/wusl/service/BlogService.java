package com.wusl.service;

import com.wusl.pojo.Blog;
import com.wusl.pojo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    /*后台管理博客*/
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /*首页显示博客*/
    Page<Blog> listBlog(Pageable pageable);

    /*查询博客*/
    Page<Blog> listBlog(Pageable pageable, String query);

    /*博客标签*/
    Page<Blog> listBlog(Long tagId, Pageable pageable);

    /*保存博客*/
    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    List<Blog> listRecommendBlogTop(Integer size);

    void deleteBlog(Long id);

    /*用于前端展示转换markdown  为Html*/
    Blog getAndConvert(Long id);

    /*归档*/
    Map<String, List<Blog>> archiveBlog();

    /*博客总条数*/
    Long countBlog();


}
