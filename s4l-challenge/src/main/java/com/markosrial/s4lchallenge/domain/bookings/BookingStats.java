package com.markosrial.s4lchallenge.domain.bookings;

import lombok.Builder;
import lombok.Getter;

/**
 * Booking stats representation
 */
@Getter
@Builder
public class BookingStats {

    private float totalProfit;

    private int numNights;

    private float minProfitPerNight;

    private float maxProfitPerNight;

    private float avgProfitPerNight;

    float getProfitPerNight() {
        if (numNights == 0) {
            return 0;
        }
        return totalProfit / numNights;
    };


}
