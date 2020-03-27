package com.zbq.jackson.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author zbq
 * @since 2020/3/26
 */
@RestController
public class JacksonDemoController {

    @GetMapping("/jackson")
    public Map<String, Object> jacksonTest() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("Date:", new Date());
        map.put("LocalDate", LocalDate.now());
        map.put("LocalDateTime", LocalDateTime.now());
        map.put("LocalTime", LocalTime.now());
        return map;
    }

    @GetMapping("/jackson2")
    public DemoBean jacksonTest2() {
        DemoBean demoBean = new DemoBean();
        demoBean.setName("zbq");
        demoBean.setAge(18);
        demoBean.setSex("男");
        demoBean.setGmtCreat(LocalDateTime.now());
        return demoBean;
    }

    @GetMapping("/jackson3")
    public MoneyDemo jacksonTest3() {
        MoneyDemo moneyDemo = new MoneyDemo();
        moneyDemo.setMoney(10000L);
        return moneyDemo;
    }
}
