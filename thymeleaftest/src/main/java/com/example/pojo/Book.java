package com.example.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class Book {
    private Integer id;
    @NotBlank(message = "name不许为空")
    @Length(min = 2, max = 10, message = "name长度必须在{min}-{max}之间")
    private String name;
    @NotNull(message = "price 不允许为空")
    @DecimalMin(value = "0.1", message = "价格不能低于 {value}")
    private BigDecimal price;

    public Book(Integer id, String name, BigDecimal price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
