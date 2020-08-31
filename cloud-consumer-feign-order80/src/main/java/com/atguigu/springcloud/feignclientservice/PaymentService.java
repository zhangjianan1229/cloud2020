package com.atguigu.springcloud.feignclientservice;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentService {

    @GetMapping(value = "/payment/getPaymentById/{id}")
    CommonResult<Payment> getPayment(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    String testFeignTimeout();

}
