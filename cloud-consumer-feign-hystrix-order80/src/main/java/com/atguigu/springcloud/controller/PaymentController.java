package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_TimeOutGloabHandler")
public class PaymentController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentFeignService.paymentInfo_OK(id);
        return result + "\n" + serverPort;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentFeignService.paymentInfo_TimeOut(id);
        return result + "\n" + serverPort;
    }

    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id) {
        return serverPort + ": 系统异常，我是80服务, id=" + id;
    }

    public String paymentInfo_TimeOutGloabHandler() {
        return serverPort + ": 系统异常，我是80000000000000000000000服务，id : ";
    }

}
