package com.resumo.texto.application.mapper;

import com.resumo.texto.dto.response.SummaryItemResponse;
import com.resumo.texto.dto.response.SummaryResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SummaryMapperTest {

    private SummaryMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new SummaryMapper();
    }

    @Test
    void shouldMapFirstItemToResponseDto() {
        SummaryItemResponse item = new SummaryItemResponse();
        item.setSummary_text("Resumo do texto");
        List<SummaryItemResponse> items = List.of(item);

        SummaryResponseDto result = mapper.toResponseDto(items);

        assertEquals("Resumo do texto", result.getSummaryTextResponse());
    }

    @Test
    void shouldThrowExceptionWhenListIsEmpty() {
        List<SummaryItemResponse> items = new ArrayList<>();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            mapper.toResponseDto(items);
        });

        assertEquals("Nenhum resumo foi gerado para os inputs fornecidos.", exception.getReason());
    }

    @Test
    void shouldThrowExceptionWhenListIsNull() {

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            mapper.toResponseDto(null);
        });

        assertEquals("Nenhum resumo foi gerado para os inputs fornecidos.", exception.getReason());
    }

}