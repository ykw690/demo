package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TestClass1 implements TestClass{
    private String s1 = "s1";
    private String s2 = "s2";
    private String s3;
    private List list = new ArrayList();
    private Optional<String> optional;
    private AtomicReference atomicReference;
}
