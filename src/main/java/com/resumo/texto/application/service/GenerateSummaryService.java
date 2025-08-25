package com.resumo.texto.application.service;

import com.resumo.texto.adapter.output.ISummaryFeignClient;
import com.resumo.texto.application.port.input.IGenerateSummary;
import com.resumo.texto.application.usecase.SummaryTextUseCase;
import com.resumo.texto.domain.exception.SummaryException;
import com.resumo.texto.dto.request.SummaryRequestDto;
import com.resumo.texto.dto.request.TextUserRequestDto;
import com.resumo.texto.dto.response.SummaryResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GenerateSummaryService implements IGenerateSummary {

    @Autowired
    SummaryTextUseCase useCase;

    @Autowired
    ISummaryFeignClient feignClient;

    @Override
    public ResponseEntity<SummaryResponseDto> generateSummary(TextUserRequestDto textUser, String token) {
        try{
            log.info("Iniciando chamada à IA");
            log.info("Sumarização o texto: {}", textUser);
            SummaryRequestDto prompt = useCase.formatText(textUser.getInputText());
            SummaryResponseDto response = feignClient.generateSummary(prompt, token);
            log.info("Finalizando sumarização");
            return ResponseEntity.ok(response);
        }catch (SummaryException e){
            log.error("ocorreu um erro durante a execução da chamada da IA");
            throw new SummaryException(e.getMessage(), e.getCause());
        }
    }
}

