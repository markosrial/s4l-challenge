package com.markosrial.s4lchallenge.presentation.rest;

import com.markosrial.s4lchallenge.domain.bookings.Booking;
import com.markosrial.s4lchallenge.domain.bookings.BookingStats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.BookingRequest;
import org.openapitools.model.MaximizeResponse;
import org.openapitools.model.StatsResponse;

import java.util.List;

/**
 * Booking Dtos mapper
 */
@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toBooking(BookingRequest bookingRequest);

    List<Booking> toBookings(List<BookingRequest> bookingRequests);


    @Mapping(source = "minProfitPerNight", target = "minNight")
    @Mapping(source = "maxProfitPerNight", target = "maxNight")
    @Mapping(source = "avgProfitPerNight", target = "avgNight")
    StatsResponse toStatsResponse(BookingStats stats);


    @Mapping(target = "requestIds", expression = "java(this.toRequestIds(bookings))")
    @Mapping(source = "stats.minProfitPerNight", target = "minNight")
    @Mapping(source = "stats.maxProfitPerNight", target = "maxNight")
    @Mapping(source = "stats.avgProfitPerNight", target = "avgNight")
    MaximizeResponse toMaximizeResponse(List<Booking> bookings, BookingStats stats);

    default List<String> toRequestIds(List<Booking> bookings) {
        return bookings.stream().map(Booking::getRequestId).toList();
    }

}
