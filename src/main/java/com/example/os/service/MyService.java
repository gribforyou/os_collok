package com.example.os.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public interface MyService {
    List<BigInteger> factorials(Integer n);

    <T> List<T> processList(List<T> input);

    List<BigInteger> linkedFactorials(Integer n);
}
