package com.example.userdemo.service;

import com.example.userdemo.model.PublicHolidayResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PublicHolidayClientService {

    private final RestTemplate restTemplate;

    public PublicHolidayClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PublicHolidayResponse getFirstPublicHoliday(int year, String countryCode) {
        String url = "http://localhost:8090/api/public-holidays/first/" + year + "/" + countryCode;
        return restTemplate.getForObject(url, PublicHolidayResponse.class);
    }
}
