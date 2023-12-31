package com.dudzinski.portfolio.application.currency.mapper;

import com.dudzinski.portfolio.application.currency.dto.CurrencySearchResultDTO;
import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    @Mapping(target = "date", source = "date", qualifiedByName = "toStringDate")
    CurrencySearchResultDTO toCurrencySearchResultDTO(CurrencyEntity currencyEntity);

    @Named("toStringDate")
    default String toStringDate(LocalDate localDate) {
        return localDate.toString();
    }
}
