package com.example.stavtreck_test_project.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDTO {

    double[] startPos;
    double[] endPos;
    String startAddress;
    String endAddress;
    double distance;
}
