package com.markosrial.s4lchallenge.domain.bookings;

import com.markosrial.s4lchallenge.configurations.BookingsConfiguration;
import com.markosrial.s4lchallenge.domain.bookings.selectors.BookingSelector;
import com.markosrial.s4lchallenge.utils.AmountUtils;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingsConfiguration configuration;

    @Override
    public BookingStats calculateStats(@NotNull List<Booking> requests) {

        // Initial stats
        float minProfit = 0.f;
        float maxProfit = 0.f;
        float averageProfit = 0.f;
        float acummProfit = 0.f;
        int acummNights = 0;

        // Initialize first max and min values
        if (requests.size() > 0) {
            Booking initBooking = requests.get(0);
            minProfit = initBooking.getProfitPerNight();
            maxProfit = initBooking.getProfitPerNight();
        }

        for (Booking request : requests) {

            if (minProfit > request.getProfitPerNight()) {
                minProfit = request.getProfitPerNight();
            }

            if (maxProfit < request.getProfitPerNight()) {
                maxProfit = request.getProfitPerNight();
            }

            acummProfit += request.getProfit();
            acummNights += request.getNights();
            averageProfit += request.getProfitPerNight() / requests.size();

        }

        return BookingStats.builder()
                .totalProfit(acummProfit)
                .numNights(acummNights)
                .avgProfitPerNight(AmountUtils.roundAmount(averageProfit))
                .minProfitPerNight(AmountUtils.roundAmount(minProfit))
                .maxProfitPerNight(AmountUtils.roundAmount(maxProfit))
                .build();

    }

    @Override
    public List<Booking> retrieveBookingsCombination(@NotNull List<Booking> requests) {

        // Reads the configured selector strategy and retrieves the booking combination.
        BookingSelector bookingSelector = configuration.selectedStrategy();

        return bookingSelector.select(requests);

    }

}
