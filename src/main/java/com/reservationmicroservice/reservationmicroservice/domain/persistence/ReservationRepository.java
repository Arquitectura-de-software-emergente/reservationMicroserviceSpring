package com.reservationmicroservice.reservationmicroservice.domain.persistence;

import com.reservationmicroservice.reservationmicroservice.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByTouristId(int touristId);
    List<Reservation> findByTripId(int tripId); // Agrega este método
}
