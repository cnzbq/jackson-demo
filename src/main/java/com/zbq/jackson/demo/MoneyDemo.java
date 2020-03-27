package com.zbq.jackson.demo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * TODO
 *
 * @author zbq
 * @since 2020/3/28
 */
public class MoneyDemo {
    @JsonSerialize(using = LongAmountSerializer.class)
    private Long money;

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "MoneyDemo{" +
                "money=" + money +
                '}';
    }
}
