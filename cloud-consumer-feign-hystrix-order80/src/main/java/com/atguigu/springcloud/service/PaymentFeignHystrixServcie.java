package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFeignHystrixServcie implements  PaymentFeignService {
    public String paymentInfo_OK(Integer id) {
        return "OK方法也挂了";
    }

    public String paymentInfo_TimeOut(Integer id) {
        return "能进来吗？";
    }
}
