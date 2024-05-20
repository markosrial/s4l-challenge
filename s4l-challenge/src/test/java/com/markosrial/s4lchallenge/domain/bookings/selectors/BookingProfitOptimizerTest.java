package com.markosrial.s4lchallenge.domain.bookings.selectors;

import com.markosrial.s4lchallenge.domain.bookings.Booking;
import com.markosrial.s4lchallenge.domain.bookings.BookingFixtures;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingProfitOptimizerTest {

    @Test
    void testProfitOptimized_EmptyList() {
        assertEquals(List.of(), BookingProfitOptimizer.INSTANCE.select(BookingFixtures.emptyBookingList()));
    }

    @Test
    void testRandomSelect_SingleList() {

        List<Booking> bookings = BookingFixtures.singleBookingList();
        List<Booking> result = BookingProfitOptimizer.INSTANCE.select(bookings);

        assertEquals(1, result.size());
        assertEquals(bookings.get(0), result.get(0));

    }

    @Test
    void testRandomSelect_AllSharingDays() {

        List<Booking> bookings = BookingFixtures.sharingDaysBookingList();
        List<Booking> result = BookingProfitOptimizer.INSTANCE.select(bookings);

        // When all share days then all nodes are leafs, and just will match with the best profitable
        assertEquals(1, result.size());
        assertEquals(
                bookings.stream().filter(b -> "mock-id1".equals(b.getRequestId())).findFirst().get(),
                result.get(0));

    }

    @Test
    void testRandomSelect_ExtendedSample() {

        List<Booking> bookings = BookingFixtures.extendedBookingList();
        List<Booking> result = BookingProfitOptimizer.INSTANCE.select(bookings);

        assertEquals(5, result.size());
        assertEquals(
                List.of(
                        bookings.stream().filter(bk -> "mock-id-B".equals(bk.getRequestId())).findFirst().get(),
                        bookings.stream().filter(bk -> "mock-id-C".equals(bk.getRequestId())).findFirst().get(),
                        bookings.stream().filter(bk -> "mock-id-D".equals(bk.getRequestId())).findFirst().get(),
                        bookings.stream().filter(bk -> "mock-id-F".equals(bk.getRequestId())).findFirst().get(),
                        bookings.stream().filter(bk -> "mock-id-G".equals(bk.getRequestId())).findFirst().get()
                ),
                result
        );

    }

}