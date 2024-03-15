package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTests {
    StringCalculator calculator;


    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("Empty string should return 0.")
    public void emptyString() throws Exception {
        int value = calculator.Calculate("");
        Assertions.assertEquals(0, value);
    }

    @Test
    @DisplayName("Single number returns the value.")
    public void singleNumber() throws Exception {
        int value = calculator.Calculate("1");
        Assertions.assertEquals(1, value);
    }

    @Test
    @DisplayName("Two numbers, comma delimited, returns the sum.")
    public void twoNumbersComma() throws Exception {
        int value = calculator.Calculate("5,10");
        Assertions.assertEquals(15, value);
    }

    @Test
    @DisplayName("Two numbers, newline delimited, returns the sum.")
    public void twoNumbersNewline() throws Exception {
        int value = calculator.Calculate("5\n10");
        Assertions.assertEquals(15, value);
    }

    @Test
    @DisplayName("Three numbers, delimited either way, returns the sum.")
    public void threeNumbers() throws Exception {
        int value = calculator.Calculate("1\n2\n3");
        Assertions.assertEquals(6, value);
    }

    @Test
    @DisplayName("Negative numbers throws an exception.")
    public void negativeNumbers() throws Exception {
        Throwable exception = assertThrows(Exception.class, () -> calculator.Calculate("-10\n5"));
        Assertions.assertEquals("Negatives not allowed.", exception.getMessage());
    }
    @Test
    @DisplayName("Numbers greater than 1000 are ignored.")
    public void bigNumbers() throws Exception {
        int value = calculator.Calculate("2000\n2");
        Assertions.assertEquals(2, value);
    }
    @Test
    @DisplayName("A single char delimiter can be defined on the first line.")
    public void singleCharDelimiter() throws Exception {
        int value = calculator.Calculate("//#\n1#2#3");
        Assertions.assertEquals(6, value);
    }

    @Test
    @DisplayName("A multichar delimiter can be defined on the first line.")
    public void multicharDelimiter() throws Exception {
        int value = calculator.Calculate("//[###]\n1###2,3");
        Assertions.assertEquals(6, value);
    }
    @Test
    @DisplayName("Many delimiters can be defined on the first line.")
    public void manyDelimiters() throws Exception {
        int value = calculator.Calculate("//[###][o]\n1###2o3");
        Assertions.assertEquals(6, value);
    }
}
