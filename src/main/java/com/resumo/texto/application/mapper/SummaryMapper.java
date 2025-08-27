package com.resumo.texto.application.mapper;

import com.resumo.texto.dto.response.SummaryItemResponse;
import com.resumo.texto.dto.response.SummaryResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@Slf4j
public class SummaryMapper {

    public SummaryResponseDto toResponseDto(List<SummaryItemResponse> items) {
        log.info("mapeando texto resumido");
        if (items == null || items.isEmpty()) {
            log.error("erro ao mapear o resumo obtido");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum resumo foi gerado para os inputs fornecidos.");
        }
        SummaryItemResponse first = items.get(0);
        SummaryResponseDto response = new SummaryResponseDto();
        response.setSummaryTextResponse(first.getSummary_text());
        return response;
    }
}
