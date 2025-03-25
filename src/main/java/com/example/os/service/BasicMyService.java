package com.example.os.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BasicMyService implements MyService {
    @Override
    public List<BigInteger> factorials(Integer n) {
        validateN(n);

        List<BigInteger> factorials = new ArrayList<BigInteger>();
        factorials.add(BigInteger.ONE);
        for (int i = 2; i <= n; i++) {
            BigInteger next = factorials.getLast().multiply(BigInteger.valueOf(i));
            factorials.add(next);
        }
        return factorials;
    }

    @Override
    public <T> List<T> getUnmodifying(List<T> input) {
        return Collections.unmodifiableList(input);
    }

    @Override
    public List<BigInteger> linkedFactorials(Integer n) {
        validateN(n);
        List<BigInteger> factorials = new LinkedList<>();
        saveFactorials(factorials, n);
        return factorials;
    }

    private void validateN(Integer n){
        if(n < 1){
            throw new IllegalArgumentException("n must be greater than 0");
        }
        else if(n > 50){
            throw new IllegalArgumentException("too big value for factorial. n must be not higher that 50");
        }
    }

    private void saveFactorials(List<BigInteger> toSave, Integer n) {
        if(n == 1){
            toSave.add(BigInteger.ONE);
        }
        else {
            saveFactorials(toSave, n - 1);
            BigInteger next = toSave.getLast().multiply(BigInteger.valueOf(n));
            toSave.add(next);
        }
    }
}
