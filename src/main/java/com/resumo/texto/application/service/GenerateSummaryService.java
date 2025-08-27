package com.resumo.texto.application.service;

import com.resumo.texto.adapter.output.ISummaryFeignClient;
import com.resumo.texto.application.mapper.SummaryMapper;
import com.resumo.texto.application.port.input.IGenerateSummary;
import com.resumo.texto.domain.exception.SummaryException;
import com.resumo.texto.dto.request.SummaryRequestDto;
import com.resumo.texto.dto.response.SummaryItemResponse;
import com.resumo.texto.dto.response.SummaryResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GenerateSummaryService implements IGenerateSummary {

    @Autowired
    ISummaryFeignClient feignClient;

    @Autowired
    SummaryMapper mapper;

    @Override
    public ResponseEntity<SummaryResponseDto> generateSummary(SummaryRequestDto textUser, String token) {
        try{
            log.info("Iniciando chamada à IA");
            log.info("Sumarização o texto: {}", textUser);
            List<SummaryItemResponse> summaryItens = feignClient.generateSummary(textUser, token);
            SummaryResponseDto response = mapper.toResponseDto(summaryItens);
            log.info("Finalizando sumarização");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            log.error("ocorreu um erro durante a execução da chamada da IA");
            throw new SummaryException(e.getMessage(), e.getCause());
        }
    }
}

