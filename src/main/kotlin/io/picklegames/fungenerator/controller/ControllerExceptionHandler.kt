package io.picklegames.fungenerator.controller

import io.picklegames.fungenerator.dto.ErrorResponse
import io.picklegames.fungenerator.exception.NotFoundException
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun badRequestExceptionHandler(exception: Exception): ErrorResponse {
        return ErrorResponse(exception.message)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun notFoundExceptionHandler(exception: Exception): ErrorResponse {
        return ErrorResponse(exception.message)
    }
}