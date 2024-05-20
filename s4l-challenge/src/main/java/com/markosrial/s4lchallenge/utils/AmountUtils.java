package com.markosrial.s4lchallenge.utils;

import jakarta.validation.constraints.PositiveOrZero;

public class AmountUtils {

    /**
     * Round decimal amounts to 2 decimal places
     * @param value the amount value
     * @return the rounded amount
     */
    public static float roundAmount(float value) {
        return Math.round(value * 100) / 100f;
    }

    /**
     * Normalize a percentage
     * @param percentage the percentage
     * @return the percentage fraction value
     */
    public static float normalizePercentage(@PositiveOrZero float percentage) {return percentage / 100f; }

}
