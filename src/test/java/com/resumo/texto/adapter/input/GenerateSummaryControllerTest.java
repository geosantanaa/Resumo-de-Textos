package com.resumo.texto.adapter.input;

import com.resumo.texto.application.port.input.IGenerateSummary;
import com.resumo.texto.dto.request.SummaryRequestDto;
import com.resumo.texto.dto.response.SummaryResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class GenerateSummaryControllerTest {

    @Mock
    private IGenerateSummary service;

    @InjectMocks
    private GenerateSummaryController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSummaryResponse() {
        SummaryRequestDto requestDto = new SummaryRequestDto();
        requestDto.setInputs(List.of("Texto de teste"));

        SummaryResponseDto responseDto = new SummaryResponseDto("Resumo do texto");

        when(service.generateSummary(any(SummaryRequestDto.class), anyString()))
                .thenReturn(ResponseEntity.status(202).body(responseDto));

        SummaryResponseDto result = controller.generateText(requestDto, "Bearer TOKEN").getBody();

        assertEquals("Resumo do texto", result.getSummaryTextResponse());
    }

}