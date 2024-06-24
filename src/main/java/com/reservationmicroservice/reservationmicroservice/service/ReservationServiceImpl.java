package com.reservationmicroservice.reservationmicroservice.service;

import com.reservationmicroservice.reservationmicroservice.api.client.TripClient;
import com.reservationmicroservice.reservationmicroservice.mapping.dto.TripDto;
import com.reservationmicroservice.reservationmicroservice.domain.model.Reservation;
import com.reservationmicroservice.reservationmicroservice.domain.model.ReservationResponse;
import com.reservationmicroservice.reservationmicroservice.domain.persistence.ReservationRepository;
import com.reservationmicroservice.reservationmicroservice.domain.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TripClient tripClient;

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        List<Reservation> reservations = getAll();
        List<ReservationResponse> responses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            TripDto trip = tripClient.getTripById(reservation.getTripId());
            ReservationResponse response = ReservationResponse.builder()
                    .id(reservation.getId())
                    .tripId(reservation.getTripId())
                    .touristId(reservation.getTouristId())
                    .reservationDate(reservation.getReservationDate())
                    .startDate(reservation.getStartDate())
                    .status(reservation.getStatus())
                    .trip(trip)  // Incluir el objeto TripDto en la respuesta
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Override
    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation getReservationById(int id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    public List<Reservation> getReservationByTouristId(int touristId) {
        return reservationRepository.findByTouristId(touristId);
    }

    @Override
    public List<ReservationResponse> getReservationByTouristIdWithTrip(int touristId) {
        List<Reservation> reservations = getReservationByTouristId(touristId);
        List<ReservationResponse> responses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            TripDto trip = tripClient.getTripById(reservation.getTripId());
            ReservationResponse response = ReservationResponse.builder()
                    .id(reservation.getId())
                    .tripId(reservation.getTripId())
                    .touristId(reservation.getTouristId())
                    .reservationDate(reservation.getReservationDate())
                    .startDate(reservation.getStartDate())
                    .status(reservation.getStatus())
                    .trip(trip)  // Incluir el objeto TripDto en la respuesta
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Override
    public List<Reservation> getReservationByTripId(int tripId) {
        return reservationRepository.findByTripId(tripId);
    }

    @Override
    public List<ReservationResponse> getReservationByTripIdWithTrip(int tripId) {
        List<Reservation> reservations = getReservationByTripId(tripId);
        List<ReservationResponse> responses = new ArrayList<>();
        for (Reservation reservation : reservations) {
            TripDto trip = tripClient.getTripById(reservation.getTripId());
            ReservationResponse response = ReservationResponse.builder()
                    .id(reservation.getId())
                    .tripId(reservation.getTripId())
                    .touristId(reservation.getTouristId())
                    .reservationDate(reservation.getReservationDate())
                    .startDate(reservation.getStartDate())
                    .status(reservation.getStatus())
                    .trip(trip)  // Incluir el objeto TripDto en la respuesta
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Override
    public ReservationResponse getServiceByReservationId(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        TripDto trip = tripClient.getTripById(reservation.getTripId());
        return ReservationResponse.builder()
                .id(reservation.getId())
                .tripId(reservation.getTripId())
                .touristId(reservation.getTouristId())
                .reservationDate(reservation.getReservationDate())
                .startDate(reservation.getStartDate())
                .status(reservation.getStatus())
                .trip(trip)  // Incluir el objeto TripDto en la respuesta
                .build();
    }
}
