package com.reservationmicroservice.reservationmicroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "trip_id", nullable = false)
    private int tripId;

    @Column(name = "tourist_id", nullable = false)
    private int touristId;

    @Temporal(TemporalType.DATE)
    @Column(name = "reservation_date", nullable = false)
    private Date reservationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "status", nullable = false)
    private String status;
}
