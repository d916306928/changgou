package com.changgou.goods.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:sz.itheima
 * @Description:Comment构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_comment")
public class Comment implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;//

    @Column(name = "order_id")
	private String orderId;//订单编号


    @Column(name = "sku_id")
	private Long skuId;//skuID

    @Column(name = "username")
	private String username;//用户名

    @Column(name = "create_time")
	private Date createTime;//评论时间

    @Column(name = "grade")
	private String grade;//0-5对应评分星星个数，默认为五颗星

    @Column(name = "content")
	private String content;//评论内容

    @Column(name = "images")
	private String images;//评论图片



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getOrderId() {
		return orderId;
	}

	//set方法
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	//get方法
	public Long getSkuId() {
		return skuId;
	}

	//set方法
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	//get方法
	public String getUsername() {
		return username;
	}

	//set方法
	public void setUsername(String username) {
		this.username = username;
	}
	//get方法
	public Date getCreateTime() {
		return createTime;
	}

	//set方法
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	//get方法
	public String getGrade() {
		return grade;
	}

	//set方法
	public void setGrade(String grade) {
		this.grade = grade;
	}
	//get方法
	public String getContent() {
		return content;
	}

	//set方法
	public void setContent(String content) {
		this.content = content;
	}
	//get方法
	public String getImages() {
		return images;
	}

	//set方法
	public void setImages(String images) {
		this.images = images;
	}


}
