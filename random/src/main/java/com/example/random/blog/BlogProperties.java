package com.example.random.blog;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class BlogProperties {
    @Value("${com.didispace.blog.name}")
    private String name;

    @Value("${com.didispace.blog.title}")
    private String title;

    @Value("${com.didispace.blog.desc}")
    private String desc;

    @Value("${com.didispace.blog.value}")
    private String value;

    @Value("${com.didispace.blog.number}")
    private Long number;

    @Value("${com.didispace.blog.bignumber}")
    private Long bignumber;

    @Value("${com.didispace.blog.test1}")
    private Long test1;

    @Value("${com.didispace.blog.test2}")
    private Long test2;
}
