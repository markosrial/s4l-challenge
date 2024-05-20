package com.markosrial.s4lchallenge.domain.bookings.selectors;

import com.markosrial.s4lchallenge.domain.bookings.Booking;

import java.util.List;

/**
 * Booking selector that retrieves a random booking.
 */
public class RandomSelector implements BookingSelector {

    public static RandomSelector INSTANCE = new RandomSelector();

    @Override
    public List<Booking> select(List<Booking> bookings) {

        if (bookings.isEmpty()) {
            return List.of();
        }

        return List.of(bookings.stream().findAny().get());

    }
}
