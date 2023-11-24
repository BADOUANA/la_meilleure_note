package com.epf.back_end.mappers;

import com.epf.back_end.dto.RateDTO;
import com.epf.back_end.models.Rate;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", implementationName = "RateMapperImpl")
public interface RateMapper  {

 RateDTO rateToRateDTO(Rate rate);
    Rate rateDTOToRate(RateDTO rateDTO);

    List<RateDTO> ratesToRateDTOs(List<Rate> rates);
    List<Rate> rateDTOsToRates(List<RateDTO> rateDTOs);
}
