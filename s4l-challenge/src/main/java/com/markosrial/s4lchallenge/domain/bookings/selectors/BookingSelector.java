package com.markosrial.s4lchallenge.domain.bookings.selectors;

import com.markosrial.s4lchallenge.domain.bookings.Booking;

import java.util.List;

/**
 * Strategy interface for bookings selection
 */
public interface BookingSelector {

    /**
     * Method that will select the bookings depending on the strategy selected
     * @param bookings
     * @return
     */
    List<Booking> select(List<Booking> bookings);

}
