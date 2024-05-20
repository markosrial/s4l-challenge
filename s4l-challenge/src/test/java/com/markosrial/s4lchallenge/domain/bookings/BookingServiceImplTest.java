package com.markosrial.s4lchallenge.domain.bookings;

import com.markosrial.s4lchallenge.configurations.BookingsConfiguration;
import com.markosrial.s4lchallenge.domain.bookings.selectors.RandomSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BookingServiceImplTest {

    @Mock
    private BookingsConfiguration configuration;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateStats_Empty() {

        // Validate stats for empty list
        BookingStats stats = bookingService.calculateStats(List.of());

        assertEquals(0f, stats.getTotalProfit());
        assertEquals(0, stats.getNumNights());
        assertEquals(0f, stats.getProfitPerNight());
        assertEquals(0f, stats.getMinProfitPerNight());
        assertEquals(0f, stats.getMaxProfitPerNight());
        assertEquals(0f, stats.getAvgProfitPerNight());

        // Validate stats for single list
        stats = bookingService.calculateStats(BookingFixtures.singleBookingList());

        assertEquals(20f, stats.getTotalProfit());
        assertEquals(5, stats.getNumNights());
        assertEquals(4f, stats.getProfitPerNight());
        assertEquals(4f, stats.getMinProfitPerNight());
        assertEquals(4f, stats.getMaxProfitPerNight());
        assertEquals(4f, stats.getAvgProfitPerNight());

        // Validate stats for multiple item list ()
        stats = bookingService.calculateStats(
                List.of(
                        Booking.builder()
                                .checkIn(LocalDate.of(2024, 1, 1))
                                .requestId("mock-id1")
                                .sellingRate(400)
                                .nights(5)
                                .margin(20)
                                .build(),
                        Booking.builder()
                                .checkIn(LocalDate.of(2024, 1, 7))
                                .requestId("mock-id2")
                                .sellingRate(1500)
                                .nights(15)
                                .margin(10)
                                .build()
                )
        );

        assertEquals(230f, stats.getTotalProfit());
        assertEquals(20, stats.getNumNights());
        assertEquals(11.5f, stats.getProfitPerNight());
        assertEquals(10f, stats.getMinProfitPerNight());
        assertEquals(16f, stats.getMaxProfitPerNight());
        assertEquals(13f, stats.getAvgProfitPerNight());


    }

    @Test
    void testRetrieveBookingsCombination() {

        Mockito.when(configuration.selectedStrategy()).thenReturn(RandomSelector.INSTANCE);

        // Simple base test. The more detailed testing are defined in each selector test
        assertEquals(List.of(), bookingService.retrieveBookingsCombination(BookingFixtures.emptyBookingList()));

    }

}