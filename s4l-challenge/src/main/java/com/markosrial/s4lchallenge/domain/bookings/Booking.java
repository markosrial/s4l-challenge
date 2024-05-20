package com.markosrial.s4lchallenge.domain.bookings;

import com.markosrial.s4lchallenge.utils.AmountUtils;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Booking {

    private String requestId;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private int nights;

    private float sellingRate;

    private float margin;

    @Builder
    public Booking(String requestId, LocalDate checkIn, int nights, float sellingRate, float margin) {
        this.requestId = requestId;
        this.checkIn = checkIn;
        this.nights = nights;
        this.sellingRate = sellingRate;
        this.margin = margin;
        // Compute 'checkOut' when created, so it will improve performance when checking this value between bookings
        this.checkOut = calculateCheckOut();
    }

    private LocalDate calculateCheckOut() {
        return checkIn.plusDays(nights);
    }

    /**
     * Calculates the total booking profit.
     * @return the profit
     */
    public float getProfit() {
        return this.sellingRate * AmountUtils.normalizePercentage(margin);
    }

    /**
     * Calculates the profit per night for this booking.
     * @return the profit per night
     */
    public float getProfitPerNight() {
        return this.getProfit() / nights;
    }

}
