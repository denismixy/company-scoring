package ru.mikhailin.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("ru.mikhailin.mybatis")
public class MyBatisConfiguration {
}
