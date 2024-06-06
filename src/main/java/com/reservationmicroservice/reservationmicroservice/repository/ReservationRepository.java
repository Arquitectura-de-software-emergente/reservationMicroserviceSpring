package com.reservationmicroservice.reservationmicroservice.repository;

import com.reservationmicroservice.reservationmicroservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByTouristId(int touristId);
    List<Reservation> findByTripId(int tripId); // Agrega este método
}
