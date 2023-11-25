package com.epf.back_end.dto.response;

import com.epf.back_end.dto.response.FilmDTO;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.models.Film;
import com.epf.back_end.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RateDTO {
    private Long id;
    private String name;
    private float note;
    private String summary;
    private String detailSummary;

    // Assume these fields are not included in the DTO to enhance security
     private Film film;
     private User user;
}
