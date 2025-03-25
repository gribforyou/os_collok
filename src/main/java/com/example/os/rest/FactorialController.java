package com.example.os.rest;

import com.example.os.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class FactorialController {
    @Autowired
    MyService myService;

    @GetMapping("/factorials/{n}")
    List<BigInteger> getFactorials(@PathVariable Integer n) {
        var result = myService.factorials(n);
        return myService.getUnmodifying(result);
    }

    @GetMapping("/factorials/linked/{n}")
    List<BigInteger> getFactorialsLinked(@PathVariable Integer n) {
        var result = myService.linkedFactorials(n);
        return myService.getUnmodifying(result);
    }
}
