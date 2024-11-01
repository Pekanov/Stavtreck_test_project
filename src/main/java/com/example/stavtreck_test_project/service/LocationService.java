package com.example.stavtreck_test_project.service;


import com.example.stavtreck_test_project.dto.RequestDTO;
import com.example.stavtreck_test_project.dto.ResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Map;

@Service
public class LocationService {
    private static final double EARTH_RADIUS_METERS = 6_371_000;
    private final RestTemplate restTemplate = new RestTemplate();



    public ResponseDTO getLocationValue(RequestDTO requestDTO) {
        return ResponseDTO.builder()
                .startPos(requestDTO.getStartPos())
                .endPos(requestDTO.getEndPos())
                .startAddress(getAddress(requestDTO.getStartPos()[0], requestDTO.getStartPos()[1]))
                .endAddress(getAddress(requestDTO.getEndPos()[0], requestDTO.getEndPos()[1]))
                .distance(calculateDistance(requestDTO.getStartPos(), requestDTO.getEndPos()))
                .build();
    }

    private String getAddress(double latitude, double longitude) {
        try {
            String url = String.format(Locale.US, "https://nominatim.openstreetmap.org/reverse?format=json&lat=%f&lon=%f", latitude, longitude);
            var response = restTemplate.getForObject(url, Map.class);
            if (response != null && response.containsKey("display_name")) {
                return (String) response.get("display_name");
            } else {
                return "Address not found";
            }
        } catch (Exception e) {
            System.err.println("Error occurred while fetching the address: " + e.getMessage());
            return "Address not found";
        }
    }

    private double calculateDistance(double[] start, double[] end) {
        double latitudeDelta = toRadians(end[0] - start[0]);
        double longitudeDelta = toRadians(end[1] - start[1]);
        double startLatRad = toRadians(start[0]);
        double endLatRad = toRadians(end[0]);

        double a = haversine(latitudeDelta) +
                Math.cos(startLatRad) * Math.cos(endLatRad) * haversine(longitudeDelta);

        return 2 * EARTH_RADIUS_METERS * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    private double toRadians(double angleInDegrees) {
        return Math.toRadians(angleInDegrees);
    }

    private double haversine(double angle) {
        return Math.pow(Math.sin(angle / 2), 2);
    }

}
