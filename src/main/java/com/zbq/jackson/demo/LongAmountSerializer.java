package com.zbq.jackson.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 金额序列化器，由人民币分序列化为人民币元
 *
 * @author zbq
 * @since 2020/3/12
 */
public class LongAmountSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        BigDecimal divide = new BigDecimal(value.toString()).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_DOWN);
        gen.writeString(divide.toString());
    }
}
