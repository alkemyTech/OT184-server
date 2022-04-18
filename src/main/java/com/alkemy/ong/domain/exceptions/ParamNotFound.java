package com.alkemy.ong.domain.exceptions;

public class ParamNotFound extends RuntimeException{

    public ParamNotFound(String param) {
        super ("Error, " + param + " is not found.");
    }
}
