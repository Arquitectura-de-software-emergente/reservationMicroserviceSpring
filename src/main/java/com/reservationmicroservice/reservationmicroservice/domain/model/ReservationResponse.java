package com.reservationmicroservice.reservationmicroservice.domain.model;

import com.reservationmicroservice.reservationmicroservice.mapping.dto.TripDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponse {
    private int id;
    private int tripId;
    private int touristId;
    private Date reservationDate;
    private Date startDate;
    private String status;

    private TripDto trip;  // Incluir el objeto TripDto
}
