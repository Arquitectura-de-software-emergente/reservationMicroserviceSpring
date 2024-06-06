package com.reservationmicroservice.reservationmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private int tripId;
    private int touristId;
    private String reservationDate;
    private String startDate;
    private String status;
}
