package com.dudzinski.portfolio.rest.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.CryptoCurrencyFacade;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyDetailsParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyDetailsResultDTO;
import com.dudzinski.portfolio.infrastructure.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dudzinski.portfolio.rest.cryptocurrency.CryptoCurrencyControllerConstants.DETAILS_RESOURCE_CRYPTO_CURRENCY;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = DETAILS_RESOURCE_CRYPTO_CURRENCY)
class CryptoCurrencyDetailsController {

    private final CryptoCurrencyFacade cryptoCurrencyFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    List<CryptoCurrencyDetailsResultDTO> getAll(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String code,
                                                @RequestParam(name = "_start") int start,
                                                @RequestParam(name = "_end") int end,
                                                @RequestParam(name = "_order") String order,
                                                @RequestParam(name = "_sort") String sort) {
        return cryptoCurrencyFacade.getDetails(wrapSearchParams(name, code, start, end, order, sort));
    }

    private CryptoCurrencyDetailsParamsDTO wrapSearchParams(
            String name,
            String code,
            int start,
            int end,
            String order,
            String sort) {
        return new CryptoCurrencyDetailsParamsDTO(
                name,
                code,
                start,
                end,
                PageableUtils.preparePageable(start, end, sort, order)
        );
    }
}

