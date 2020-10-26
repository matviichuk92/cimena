package com.cinema.model.dto.mapper;

import com.cinema.model.CinemaHall;
import com.cinema.model.dto.CinemaHallRequestDto;
import com.cinema.model.dto.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallDtoMapper {

    public CinemaHall mapToCinemaHall(CinemaHallRequestDto hallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(hallRequestDto.getCapacity());
        cinemaHall.setDescription(hallRequestDto.getDescription());
        return cinemaHall;
    }

    public CinemaHallResponseDto mapToDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto hallResponseDto = new CinemaHallResponseDto();
        hallResponseDto.setHallId(cinemaHall.getId());
        hallResponseDto.setCapacity(cinemaHall.getCapacity());
        hallResponseDto.setDescription(cinemaHall.getDescription());
        return hallResponseDto;
    }
}
