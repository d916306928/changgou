package com.changgou.comment.service.Impl;

import com.changgou.comment.dao.CommentMapper;
import com.changgou.comment.service.CommentService;
import com.changgou.goods.pojo.Comment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.TokenDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/****
 * @Author:sz.itheima
 * @Description:Comment业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;



    /**
     * 根据商品skuid查询评价数据的后台服务接口
     * @param SkuId
     * @return
     */
    @Override
    public List<Comment> findSkuId(Long SkuId) {
        Comment comment = new Comment();
        comment.setSkuId(SkuId);
        List<Comment> select = commentMapper.select(comment);
        return select;
    }

    /**
     * Comment条件+分页查询
     * @param comment 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Comment> findPage(Comment comment, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(comment);
        //执行搜索
        return new PageInfo<Comment>(commentMapper.selectByExample(example));
    }

    /**
     * Comment分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Comment> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Comment>(commentMapper.selectAll());
    }

    /**
     * Comment条件查询
     * @param comment
     * @return
     */
    @Override
    public List<Comment> findList(Comment comment){
        //构建查询条件
        Example example = createExample(comment);
        //根据构建的条件查询数据
        return commentMapper.selectByExample(example);
    }


    /**
     * Comment构建查询对象
     * @param comment
     * @return
     */
    public Example createExample(Comment comment){
        Example example=new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        if(comment!=null){
            // 
            if(!StringUtils.isEmpty(comment.getId())){
                    criteria.andEqualTo("id",comment.getId());
            }
            // 订单编号

            if(!StringUtils.isEmpty(comment.getOrderId())){
                    criteria.andEqualTo("orderId",comment.getOrderId());
            }
            // skuID
            if(!StringUtils.isEmpty(comment.getSkuId())){
                    criteria.andEqualTo("skuId",comment.getSkuId());
            }
            // 用户名
            if(!StringUtils.isEmpty(comment.getUsername())){
                    criteria.andLike("username","%"+comment.getUsername()+"%");
            }
            // 评论时间
            if(!StringUtils.isEmpty(comment.getCreateTime())){
                    criteria.andEqualTo("createTime",comment.getCreateTime());
            }
            // 0-5对应评分星星个数，默认为五颗星
            if(!StringUtils.isEmpty(comment.getGrade())){
                    criteria.andEqualTo("grade",comment.getGrade());
            }
            // 评论内容
            if(!StringUtils.isEmpty(comment.getContent())){
                    criteria.andEqualTo("content",comment.getContent());
            }
            // 评论图片
            if(!StringUtils.isEmpty(comment.getImages())){
                    criteria.andEqualTo("images",comment.getImages());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        commentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Comment
     * @param comment
     */
    @Override
    public void update(Comment comment){
        commentMapper.updateByPrimaryKey(comment);
    }

    /**
     * 增加Comment
     * @param comment
     */
    @Override
    public void add(Comment comment){
        String username  = "zhangsan";
        comment.setUsername(username);
        comment.setCreateTime(new Date());
        commentMapper.insertSelective(comment);
    }

    /**
     * 根据ID查询Comment
     * @param id
     * @return
     */
    @Override
    public Comment findById(Integer id){

        return  commentMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Comment全部数据
     * @return
     */
    @Override
    public List<Comment> findAll() {
        return commentMapper.selectAll();
    }
}
