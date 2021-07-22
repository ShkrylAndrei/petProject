package ru.shkryl.petavito.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.shkryl.petavito.dto.error.ErrorDTO;

//В эттом пакете должны быть только експешн
//Это перенести в пакет контроллер, создать отдельный пакет
@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ErrorDTO> internalServerError(Exception e)
    {
        final ErrorDTO error = new ErrorDTO("внутренняя ошибка сервиса",
                "произошла внутрення ошибка в микросервисе"
        );
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}