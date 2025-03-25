package com.example.os.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BasicMyServiceTest {
    private static final MyService myService = new BasicMyService();

    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 51})
    public void testValidationFactorialNumber(int n) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myService.factorials(n);
        });
    }

    @Test
    public void testImmutableList(){
        //Given
        List<BigInteger> factorials = List.of(BigInteger.ONE, BigInteger.TWO);

        //When
        var result = myService.getUnmodifying(factorials);

        //Then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            result.add(BigInteger.ONE);
        });

        Assertions.assertThrows(UnsupportedOperationException.class, result::removeLast);
    }

    @ParameterizedTest
    @MethodSource("factorialMethods")
    public void testFactorials(Supplier<List<BigInteger>> factorialsSupplier) {
        //When
        List<BigInteger> factorials = factorialsSupplier.get();

        //Then
        int res = 1;
        for (int i = 0; i < 5; i++) {
            res *= (i + 1);
            Assertions.assertEquals(BigInteger.valueOf(res), factorials.get(i));
        }
    }

    public static Stream<Arguments> factorialMethods(){
        return Stream.of(
                Arguments.of((Supplier<List<BigInteger>>) () -> myService.factorials(5)),
                Arguments.of((Supplier<List<BigInteger>>) () -> myService.linkedFactorials(5))
        );
    }
}
