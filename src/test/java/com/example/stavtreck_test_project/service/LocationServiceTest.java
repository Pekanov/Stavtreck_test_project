package com.example.stavtreck_test_project.service;

import com.example.stavtreck_test_project.dto.RequestDTO;
import com.example.stavtreck_test_project.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationServiceTest {

    private final LocationService locationService = new LocationService();

    @Test
    void testCalculateDistance() {
        double[] startPos = {37.7749, -122.4194}; // San Francisco
        double[] endPos = {34.0522, -118.2437}; // Los Angeles

        double distance = locationService.calculateDistance(startPos, endPos);

        assertEquals(559000, Math.round(distance), 10000); // Допустимая погрешность 10 км
    }

    @Test
    void testGetLocationValue() {
        // Мокаем поведение внешнего вызова в getAddress
        LocationService mockService = Mockito.spy(new LocationService());
        Mockito.doReturn("Mocked Address")
                .when(mockService)
                .getAddress(37.7749, -122.4194);
        Mockito.doReturn("Mocked Address")
                .when(mockService)
                .getAddress(34.0522, -118.2437);

        // Тестируем с мокающим сервисом
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setStartPos(new double[]{37.7749, -122.4194});
        requestDTO.setEndPos(new double[]{34.0522, -118.2437});

        ResponseDTO responseDTO = mockService.getLocationValue(requestDTO);

        assertEquals(requestDTO.getStartPos(), responseDTO.getStartPos());
        assertEquals(requestDTO.getEndPos(), responseDTO.getEndPos());
        assertEquals("Mocked Address", responseDTO.getStartAddress());
        assertEquals("Mocked Address", responseDTO.getEndAddress());
    }
}
