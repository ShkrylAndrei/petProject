package ru.shkryl.petavito.dto.error;

public class ErrorDTO {
    private String code;
    private String message;

    public ErrorDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
