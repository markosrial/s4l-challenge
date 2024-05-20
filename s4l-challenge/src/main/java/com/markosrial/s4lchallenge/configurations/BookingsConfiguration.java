package com.markosrial.s4lchallenge.configurations;

import com.markosrial.s4lchallenge.domain.bookings.selectors.BookingProfitOptimizer;
import com.markosrial.s4lchallenge.domain.bookings.selectors.BookingSelector;
import com.markosrial.s4lchallenge.domain.bookings.selectors.RandomSelector;
import com.markosrial.s4lchallenge.domain.bookings.selectors.SelectorStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Bookings configuration properties
 */
@ConfigurationProperties(prefix = "s4l-challenge.bookings")
public class BookingsConfiguration {

    private SelectorStrategy selectionStrategy = SelectorStrategy.PROFIT_OPTIMIZER;

    public SelectorStrategy getSelectionStrategy() {
        return selectionStrategy;
    }

    public void setSelectionStrategy(SelectorStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }


    /**
     * Get the selected strategy defined in the configuration properties
     *
     * @return the selector strategy
     */
    public BookingSelector selectedStrategy() {
        switch (this.getSelectionStrategy()) {
            case RANDOM_SELECTOR:
                return RandomSelector.INSTANCE;
            case PROFIT_OPTIMIZER:
            default:
                return BookingProfitOptimizer.INSTANCE;
        }
    }

}
