package com.epf.back_end.dto.request;

import com.epf.back_end.dto.response.FilmDTO;
import com.epf.back_end.dto.response.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RateDTORequest {
    private String name;
    private float note;
    private String summary;
    private String detailSummary;
    private Long idFilm;
    private Long idUser;
}
