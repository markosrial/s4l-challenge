package com.markosrial.s4lchallenge.domain.bookings.selectors;

import com.markosrial.s4lchallenge.domain.bookings.Booking;
import com.markosrial.s4lchallenge.domain.bookings.BookingFixtures;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomSelectorTest {

    @Test
    void testRandomSelect_EmptyList() {
        assertEquals(List.of(), RandomSelector.INSTANCE.select(BookingFixtures.emptyBookingList()));
    }

    @Test
    void testRandomSelect_SingleList() {

        List<Booking> bookings = BookingFixtures.singleBookingList();
        List<Booking> result = RandomSelector.INSTANCE.select(bookings);

        assertEquals(1, result.size());
        assertEquals(bookings.get(0), result.get(0));

    }

    @Test
    void  testRandomSelect_MultipleList() {

        List<Booking> bookings = BookingFixtures.sharingDaysBookingList();
        List<Booking> result = RandomSelector.INSTANCE.select(bookings);

        assertEquals(1, result.size());
        assertTrue(bookings.contains(result.get(0)));

    }

}