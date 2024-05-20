package com.markosrial.s4lchallenge.presentation.rest;

import com.markosrial.s4lchallenge.domain.bookings.Booking;
import com.markosrial.s4lchallenge.domain.bookings.BookingService;
import com.markosrial.s4lchallenge.domain.bookings.BookingStats;
import lombok.AllArgsConstructor;
import org.openapitools.api.MaximizeApi;
import org.openapitools.api.StatsApi;
import org.openapitools.model.BookingRequest;
import org.openapitools.model.MaximizeResponse;
import org.openapitools.model.StatsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Booking Rest controller
 */
@RestController
@AllArgsConstructor
public class BookingRestController implements StatsApi, MaximizeApi {

    private BookingService bookingService;

    private BookingMapper bookingMapper;

    @Override
    public ResponseEntity<StatsResponse> statsPost(List<BookingRequest> bookingRequest) {
        return ResponseEntity.ok(bookingMapper.toStatsResponse(
                this.bookingService.calculateStats(this.bookingMapper.toBookings(bookingRequest)))
        );
    }

    @Override
    public ResponseEntity<MaximizeResponse> maximizePost(List<BookingRequest> bookingRequest) {

        List<Booking> bookingsCombination = this.bookingService.retrieveBookingsCombination(
                this.bookingMapper.toBookings(bookingRequest));
        BookingStats stats = this.bookingService.calculateStats(bookingsCombination);

        return ResponseEntity.ok(
                this.bookingMapper.toMaximizeResponse(bookingsCombination, stats)
        );

    }

}
