package com.markosrial.s4lchallenge.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmountUtilsTest {

    @Test
    void testRoundAmount() {
        // Assert for 0
        assertEquals(0f, AmountUtils.roundAmount(0f));
        // Assert for float without decimals
        assertEquals(7f, AmountUtils.roundAmount(7f));
        // Assert for float upper limit
        assertEquals(8.5f, AmountUtils.roundAmount(8.4999999f));
        // Assert for float lower limit
        assertEquals(10.15f, AmountUtils.roundAmount(10.15111111f));
    }

    @Test
    void testNormalizePercentage() {
        // Assert for 0
        assertEquals(0.f, AmountUtils.normalizePercentage(0));
        // Assert for number without decimals
        assertEquals(0.2f, AmountUtils.normalizePercentage(20));
        // Assert for number with decimals
        assertEquals(0.205f, AmountUtils.normalizePercentage(20.5f));
    }

}