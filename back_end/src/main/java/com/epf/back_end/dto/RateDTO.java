package com.epf.back_end.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RateDTO {
    private Long idRate;
    private String name;
    private float note;
    private String summary;
    private String detailSummary;

    // Assume these fields are not included in the DTO to enhance security
    // private FilmDTO film;
    // private UserDTO user;
}
