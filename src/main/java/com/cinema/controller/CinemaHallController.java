package com.cinema.controller;

import com.cinema.model.dto.CinemaHallRequestDto;
import com.cinema.model.dto.CinemaHallResponseDto;
import com.cinema.model.dto.mapper.CinemaHallDtoMapper;
import com.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService service;
    private final CinemaHallDtoMapper mapper;

    public CinemaHallController(CinemaHallService service, CinemaHallDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public void addCinemaHall(@RequestBody @Valid CinemaHallRequestDto hall) {
        service.add(mapper.mapToCinemaHall(hall));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return service.getAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }
}
