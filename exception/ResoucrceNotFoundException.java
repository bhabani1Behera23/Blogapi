package com.BlogApi.BlogApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResoucrceNotFoundException extends RuntimeException {
private Long id;
public ResoucrceNotFoundException(long id){
    super("ResoucrceNotFound for id:"+id);
}

}
