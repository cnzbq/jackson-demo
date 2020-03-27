package com.zbq.jackson.demo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

/**
 * TODO
 *
 * @author zbq
 * @since 2020/3/28
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    private final static String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static DateTimeFormatter NORM_DATETIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final static DateTimeFormatter NORM_DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter NORM_TIME_PATTERN = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = converter.getObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        // java8 time
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(NORM_DATETIME_PATTERN));
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(NORM_DATE_PATTERN));
        simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(NORM_TIME_PATTERN));
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(NORM_DATETIME_PATTERN));
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(NORM_DATE_PATTERN));
        simpleModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(NORM_TIME_PATTERN));
        objectMapper.registerModule(simpleModule);
        // 关闭遇到未知属性抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat(PATTERN));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 设置格式化内容
        converter.setObjectMapper(objectMapper);
        converters.add(0, converter);
    }
}
