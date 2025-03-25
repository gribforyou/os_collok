package com.example.os.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

public interface MyService {
    List<BigInteger> factorials(Integer n);

    <T> List<T> getUnmodifying(List<T> input);

    List<BigInteger> linkedFactorials(Integer n);
}
