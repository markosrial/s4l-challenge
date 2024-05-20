package com.markosrial.s4lchallenge.domain.bookings;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingTest {

    @Test
    void testBookingInstance() {
        // Use booking base sample
        Booking booking = BookingFixtures.bookingSample();

        // Validate checkout date
        assertEquals(LocalDate.of(2024, 1, 6), booking.getCheckOut());
        // Validate profits
        assertEquals(20f, booking.getProfit());
        assertEquals(4f, booking.getProfitPerNight());
    }

}