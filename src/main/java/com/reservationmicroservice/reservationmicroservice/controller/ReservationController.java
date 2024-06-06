package com.reservationmicroservice.reservationmicroservice.controller;

import com.reservationmicroservice.reservationmicroservice.dto.ReservationDto;
import com.reservationmicroservice.reservationmicroservice.entities.Reservation;
import com.reservationmicroservice.reservationmicroservice.http.ReservationResponse;
import com.reservationmicroservice.reservationmicroservice.service.impl.ReservationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationServiceImpl reservationService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDto reservationDto) {
        logger.info("Received request to create reservation: {}", reservationDto);
        Reservation reservation = new Reservation();
        reservation.setTripId(reservationDto.getTripId());
        reservation.setTouristId(reservationDto.getTouristId());
        reservation.setReservationDate(parseDate(reservationDto.getReservationDate()));
        reservation.setStartDate(parseDate(reservationDto.getStartDate()));
        reservation.setStatus(reservationDto.getStatus());
        Reservation createdReservation = reservationService.createReservation(reservation);
        logger.info("Created reservation: {}", createdReservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        logger.info("Received request to get all reservations");
        List<ReservationResponse> reservations = reservationService.getAllReservations();
        logger.info("Retrieved reservations: {}", reservations);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<Void> updateReservation(@PathVariable("id") int id, @RequestBody ReservationDto reservationDto) {
        logger.info("Received request to update reservation with id: {}", id);
        Reservation reservation = reservationService.getReservationById(id);
        reservation.setTripId(reservationDto.getTripId());
        reservation.setTouristId(reservationDto.getTouristId());
        reservation.setReservationDate(parseDate(reservationDto.getReservationDate()));
        reservation.setStartDate(parseDate(reservationDto.getStartDate()));
        reservation.setStatus(reservationDto.getStatus());
        reservationService.updateReservation(reservation);
        logger.info("Updated reservation with id: {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") int id) {
        logger.info("Received request to delete reservation with id: {}", id);
        reservationService.deleteReservation(id);
        logger.info("Deleted reservation with id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable("id") int id) {
        logger.info("Received request to get reservation by id: {}", id);
        try {
            ReservationResponse reservation = reservationService.getServiceByReservationId(id);
            logger.info("Retrieved reservation for id: {}", id);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Reservation not found for id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reservations/by-tourist/{touristId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByTouristId(@PathVariable("touristId") int touristId) {
        logger.info("Received request to get reservations by touristId: {}", touristId);
        List<ReservationResponse> reservations = reservationService.getReservationByTouristIdWithTrip(touristId);
        logger.info("Retrieved reservations for touristId: {}", touristId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/reservations/by-trip/{tripId}")
    public ResponseEntity<List<ReservationResponse>> getReservationsByTripId(@PathVariable("tripId") int tripId) {
        logger.info("Received request to get reservations by tripId: {}", tripId);
        List<ReservationResponse> reservations = reservationService.getReservationByTripIdWithTrip(tripId);
        logger.info("Retrieved reservations for tripId: {}", tripId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    // Método para convertir String a Date
    private Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            logger.error("Invalid date format: {}", dateStr, e);
            throw new RuntimeException("Invalid date format. Use 'yyyy-MM-dd'");
        }
    }
}
