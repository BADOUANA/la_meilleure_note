package com.epf.back_end.mappers;

import com.epf.back_end.dto.request.RateDTORequest;
import com.epf.back_end.dto.request.UserDTORequest;
import com.epf.back_end.dto.response.RateDTO;
import com.epf.back_end.models.Rate;
import com.epf.back_end.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", implementationName = "RateMapperImpl")
public interface RateMapper  {

 RateDTO rateToRateDTO(Rate rate);

    @Mapping(source = "film.id",target = "film.id" )
    @Mapping(source = "user.id", target = "user.id")
    Rate rateDTOToRate(RateDTO rateDTO);

    @Mapping(source = "idFilm",target = "film.id" )
    @Mapping(source = "idUser", target = "user.id")
    Rate rateDTOResquestToRate(RateDTORequest rateDTORequest);

   @Mapping(source = "idFilm",target = "film.id" )
   @Mapping(source = "idUser", target = "user.id")
    void updateRateFromDTO(RateDTORequest rateDTORequest, @MappingTarget Rate rate);

}
