package com.dudzinski.portfolio.rest.currency;

import com.dudzinski.portfolio.application.currency.CurrencyFacade;
import com.dudzinski.portfolio.application.currency.dto.CurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.currency.dto.CurrencySearchResultDTO;
import com.dudzinski.portfolio.infrastructure.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.dudzinski.portfolio.rest.currency.CurrencyControllerConstants.SEARCH_RESOURCE_CURRENCY;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = SEARCH_RESOURCE_CURRENCY)
class CurrencySearchController {

    final CurrencyFacade currencyFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    Page<CurrencySearchResultDTO> search(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String code,
                                         @RequestParam(name = "_start") int start,
                                         @RequestParam(name = "_end") int end,
                                         @RequestParam(name = "_order") String order,
                                         @RequestParam(name = "_sort") String sort,
                                         @RequestParam(required = false) LocalDate dateFrom,
                                         @RequestParam(required = false) LocalDate dateTo) {

        return currencyFacade.search(
                wrapSearchParams(
                        name,
                        code,
                        start,
                        end,
                        order,
                        sort,
                        dateFrom,
                        dateTo
                )
        );
    }

    private CurrencySearchParamsDTO wrapSearchParams(
            String name,
            String code,
            int start,
            int end,
            String order,
            String sort,
            LocalDate dateFrom,
            LocalDate dateTo) {
        return new CurrencySearchParamsDTO(
                name,
                code,
                dateFrom,
                dateTo,
                PageableUtils.preparePageable(start, end, sort, order)
        );
    }
}
