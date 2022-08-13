package com.github.fabriciolfj.productservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;

@RedisHash("product")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {
    private String id;
    private String name;
    private BigDecimal price;
}
