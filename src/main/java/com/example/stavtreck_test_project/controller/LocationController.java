package com.example.stavtreck_test_project.controller;


import com.example.stavtreck_test_project.dto.RequestDTO;
import com.example.stavtreck_test_project.dto.ResponseDTO;
import com.example.stavtreck_test_project.service.LocationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = "/getLocationValue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO getLocationValue(@RequestBody RequestDTO requestDTO) {
        return locationService.getLocationValue(requestDTO);
    }


}
