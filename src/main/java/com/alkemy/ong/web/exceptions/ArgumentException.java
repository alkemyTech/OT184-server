package com.alkemy.ong.web.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ArgumentException {

    Integer pageNumber;

    public IllegalArgumentException pageNumberError() {
        return new IllegalArgumentException("Wrong page number - page: " + pageNumber);
    }

}
