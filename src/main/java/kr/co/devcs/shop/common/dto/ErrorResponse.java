package kr.co.devcs.shop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private Map<String, String> errors;
}
