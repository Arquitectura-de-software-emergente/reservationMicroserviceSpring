package com.reservationmicroservice.reservationmicroservice.http;

import com.reservationmicroservice.reservationmicroservice.dto.TripDto;
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
