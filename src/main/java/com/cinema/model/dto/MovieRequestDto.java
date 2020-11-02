package com.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class MovieRequestDto {
    @NotNull(message = "The movie must contain title!")
    private String title;
    @NotNull(message = "The movie must contain description!")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
