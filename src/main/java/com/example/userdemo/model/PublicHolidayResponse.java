package com.example.userdemo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PublicHolidayResponse {
    private LocalDate date;
    private String localName;
    private long daysFromNow;
}