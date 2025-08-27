package com.resumo.texto.application.port.input;

import com.resumo.texto.dto.request.SummaryRequestDto;
import com.resumo.texto.dto.response.SummaryResponseDto;
import org.springframework.http.ResponseEntity;

public interface IGenerateSummary {

    ResponseEntity<SummaryResponseDto> generateSummary(SummaryRequestDto textUser, String token);
}
