package com.example.demo.execption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {
    private List<Violation> violations = new ArrayList<>();
}

@Data
@AllArgsConstructor
class Violation {
    private String fieldName;
    private String message;
}
