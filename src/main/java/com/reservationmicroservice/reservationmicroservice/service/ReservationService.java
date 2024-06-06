package com.reservationmicroservice.reservationmicroservice.service;

import com.reservationmicroservice.reservationmicroservice.entities.Reservation;
import com.reservationmicroservice.reservationmicroservice.http.ReservationResponse;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);

    List<Reservation> getAll();

    List<ReservationResponse> getAllReservations();
    void updateReservation(Reservation reservation);
    void deleteReservation(int id);
    Reservation getReservationById(int id);
    List<Reservation> getReservationByTouristId(int touristId);
    List<ReservationResponse> getReservationByTouristIdWithTrip(int touristId);
    List<Reservation> getReservationByTripId(int tripId);
    List<ReservationResponse> getReservationByTripIdWithTrip(int tripId);
    ReservationResponse getServiceByReservationId(int reservationId);
}
