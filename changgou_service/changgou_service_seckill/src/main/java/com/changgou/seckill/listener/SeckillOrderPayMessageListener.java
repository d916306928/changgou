package com.changgou.seckill.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.seckill.service.SeckillOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Steven
 * @version 1.0
 * @description com.changgou.order.listener
 * @date 2020-1-5
 */
@Component
public class SeckillOrderPayMessageListener {
    @Autowired
    private SeckillOrderService seckillOrderService;

    //监听mq.pay.queue.seckillorder=queue.seckillorder的队列
    @RabbitListener(queues = "${mq.pay.queue.seckillorder}")
    public void payListener(String json){
        System.out.println("秒杀订单系统，监听到支付消息，内容为：" + json);
        //1、把json转换为Map
        Map<String,String> map = JSON.parseObject(json, Map.class);
        //2、识别业务逻辑
        if (map != null && "SUCCESS".equalsIgnoreCase(map.get("return_code"))) {
            //SUCCESS代表支付成功
            String result_code = map.get("result_code");
            //商户订单号
            String out_trade_no = map.get("out_trade_no");
            //交易流水号（微信订单号）
            String transaction_id = map.get("transaction_id");

            //取出附加参数
            Map<String,String> attachMap = JSON.parseObject(map.get("attach"), Map.class);
            //用户名
            String username = attachMap.get("username");
            if("SUCCESS".equalsIgnoreCase(result_code)){
                System.out.println("支付成功，完订单更新...");

                seckillOrderService.updatePayStatus(out_trade_no, transaction_id, username);
            }else{
                System.out.println("支付失败，完订单取消...");

                seckillOrderService.closeOrder(username);
            }
        }
    }
}
