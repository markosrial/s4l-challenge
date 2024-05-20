package com.markosrial.s4lchallenge.configurations;

import com.markosrial.s4lchallenge.domain.bookings.selectors.BookingProfitOptimizer;
import com.markosrial.s4lchallenge.domain.bookings.selectors.RandomSelector;
import com.markosrial.s4lchallenge.domain.bookings.selectors.SelectorStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingsConfigurationTest {

    @Test
    void testSelectionStrategy() {
        BookingsConfiguration configuration = new BookingsConfiguration();

        // Validate code Profit optimized is active
        configuration.setSelectionStrategy(SelectorStrategy.PROFIT_OPTIMIZER);
        assertEquals(BookingProfitOptimizer.INSTANCE, configuration.selectedStrategy());

        // Validate code Random selector is active
        configuration.setSelectionStrategy(SelectorStrategy.RANDOM_SELECTOR);
        assertEquals(RandomSelector.INSTANCE, configuration.selectedStrategy());

    }

}
