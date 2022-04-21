package com.alkemy.ong.domain.slide;

public interface SlideGateway {

    Slide findById(Long id);

    void delete(Long id);
}
