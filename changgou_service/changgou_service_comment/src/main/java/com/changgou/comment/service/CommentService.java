package com.changgou.comment.service;

import com.changgou.goods.pojo.Comment;
import com.changgou.goods.pojo.Sku;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:sz.itheima
 * @Description:Comment业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface CommentService {


    /**
     * 根据商品skuid查询评价数据的后台服务接口
     * @param SkuId
     * @return
     */
    List<Comment> findSkuId(Long SkuId);

    /***
     * Comment多条件分页查询
     * @param comment
     * @param page
     * @param size
     * @return
     */
    PageInfo<Comment> findPage(Comment comment, int page, int size);

    /***
     * Comment分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Comment> findPage(int page, int size);

    /***
     * Comment多条件搜索方法
     * @param comment
     * @return
     */
    List<Comment> findList(Comment comment);

    /***
     * 删除Comment
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Comment数据
     * @param comment
     */
    void update(Comment comment);

    /***
     * 新增Comment
     * @param comment
     */
    void add(Comment comment);

    /**
     * 根据ID查询Comment
     * @param id
     * @return
     */
     Comment findById(Integer id);

    /***
     * 查询所有Comment
     * @return
     */
    List<Comment> findAll();
}
