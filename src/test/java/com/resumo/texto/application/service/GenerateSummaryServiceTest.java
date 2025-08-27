package com.resumo.texto.application.service;

import com.resumo.texto.adapter.output.ISummaryFeignClient;
import com.resumo.texto.application.mapper.SummaryMapper;
import com.resumo.texto.domain.exception.SummaryException;
import com.resumo.texto.dto.request.SummaryRequestDto;
import com.resumo.texto.dto.response.SummaryItemResponse;
import com.resumo.texto.dto.response.SummaryResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GenerateSummaryServiceTest {

    @Mock
    private ISummaryFeignClient feignClient;

    @Mock
    private SummaryMapper mapper;

    @InjectMocks
    private GenerateSummaryService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSummaryResponse() {
        SummaryRequestDto requestDto = new SummaryRequestDto();
        requestDto.setInputs(List.of("Texto de teste"));

        SummaryItemResponse itemResponse = new SummaryItemResponse();
        itemResponse.setSummary_text("Resumo do texto");
        List<SummaryItemResponse> summaryItems = List.of(itemResponse);

        SummaryResponseDto responseDto = new SummaryResponseDto("Resumo do texto");

        when(feignClient.generateSummary(requestDto, "Bearer TOKEN")).thenReturn(summaryItems);
        when(mapper.toResponseDto(summaryItems)).thenReturn(responseDto);

        ResponseEntity<SummaryResponseDto> result = service.generateSummary(requestDto, "Bearer TOKEN");

        assertNotNull(result);
        assertEquals("Resumo do texto", result.getBody().getSummaryTextResponse());
    }

    @Test
    void shouldThrowSummaryExceptionWhenFeignFails() {
        SummaryRequestDto requestDto = new SummaryRequestDto();
        requestDto.setInputs(List.of("Texto de teste"));

        when(feignClient.generateSummary(requestDto, "Bearer TOKEN")).thenThrow(new RuntimeException("Erro IA"));

        SummaryException exception = assertThrows(SummaryException.class, () -> {
            service.generateSummary(requestDto, "Bearer TOKEN");
        });

        assertTrue(exception.getMessage().contains("Erro IA"));
    }

}