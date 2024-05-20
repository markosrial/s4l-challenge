package com.markosrial.s4lchallenge.domain.bookings;

import java.util.List;

public interface BookingService {


    /**
     * Calculates the stats for a list of bookings.
     * @param bookings the booking list
     * @return {@link BookingStats}
     */
    BookingStats calculateStats(List<Booking> bookings);

    /**
     * Retrieves the bookings combination from a list of candidates
     * @param bookings the available bookings list
     * @return the bookings combination
     */
    List<Booking> retrieveBookingsCombination(List<Booking> bookings);

}
