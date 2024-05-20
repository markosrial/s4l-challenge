package com.markosrial.s4lchallenge.domain.bookings;

import java.time.LocalDate;
import java.util.List;

public class BookingFixtures {

    public static Booking bookingSample() {
        return Booking.builder()
                .checkIn(LocalDate.of(2024, 1, 1))
                .requestId("mock-id")
                .sellingRate(200)
                .nights(5)
                .margin(10)
                .build();
    }

    public static List<Booking> emptyBookingList() {
        return List.of();
    }

    public static List<Booking> singleBookingList() {
        return List.of(bookingSample());
    }

    public static List<Booking> sharingDaysBookingList() {
        return List.of(
                // 2024-01-01 to 2024-01-06
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 1))
                        .requestId("mock-id1")
                        .sellingRate(250)
                        .nights(5)
                        .margin(15)
                        .build(),
                // 2024-01-02 to 2024-01-07
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 2))
                        .requestId("mock-id2")
                        .sellingRate(200)
                        .nights(6)
                        .margin(10)
                        .build(),
                // 2024-01-06 to 2024-01-07
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 6))
                        .requestId("mock-id3")
                        .sellingRate(120)
                        .nights(1)
                        .margin(5)
                        .build()

        );
    }

    public static List<Booking> extendedBookingList() {

        /** Sample route:
         *
         *  01-04         06-08  09-10              16-21
         *    A ---|   |--- C --- D ---|  12-15  |--- G
         *         |---|               |--- F ---|
         *    B ---|   |------ E ------|         |--- H --- I
         *  03-05            06-10                  17-18  19-22
         *
         * Conditions:
         *  - G profit > (H + I) profit
         *  - (C + D) profit > E profit
         *  - B profit > A profit
         *  Best profit path = B -> C -> D -> F -> G
         */

        // Create unordered list with mentioned conditions
        return List.of(
                // I: 2024-01-19 to 2024-01-22
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 19))
                        .requestId("mock-id-I")
                        .sellingRate(200)
                        .nights(3)
                        .margin(5)
                        .build(),
                // G: 2024-01-16 to 2024-01-21
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 16))
                        .requestId("mock-id-G")
                        .sellingRate(500)
                        .nights(5)
                        .margin(10)
                        .build(),
                // H: 2024-01-17 to 2024-01-18
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 17))
                        .requestId("mock-id-H")
                        .sellingRate(150)
                        .nights(1)
                        .margin(20)
                        .build(),
                // ------------------------------
                // F: 2024-01-12 to 2024-01-15
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 12))
                        .requestId("mock-id-F")
                        .sellingRate(300)
                        .nights(3)
                        .margin(15)
                        .build(),
                // ------------------------------
                // C: 2024-01-06 to 2024-01-08
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 6))
                        .requestId("mock-id-C")
                        .sellingRate(200)
                        .nights(2)
                        .margin(20)
                        .build(),
                // D: 2024-01-09 to 2024-01-10
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 9))
                        .requestId("mock-id-D")
                        .sellingRate(200)
                        .nights(1)
                        .margin(25)
                        .build(),
                // E: 2024-01-06 to 2024-01-10
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 6))
                        .requestId("mock-id-E")
                        .sellingRate(400)
                        .nights(4)
                        .margin(20)
                        .build(),
                // ------------------------------
                // A: 2024-01-01 to 2024-01-04
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 1))
                        .requestId("mock-id-A")
                        .sellingRate(150)
                        .nights(3)
                        .margin(15)
                        .build(),
                // B: 2024-01-03 to 2024-01-05
                Booking.builder()
                        .checkIn(LocalDate.of(2024, 1, 3))
                        .requestId("mock-id-B")
                        .sellingRate(200)
                        .nights(2)
                        .margin(20)
                        .build()
        );
    }

}
