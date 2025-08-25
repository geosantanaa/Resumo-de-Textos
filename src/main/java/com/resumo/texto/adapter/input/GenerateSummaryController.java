package com.resumo.texto.adapter.input;

import com.resumo.texto.application.port.input.IGenerateSummary;
import com.resumo.texto.dto.request.TextUserRequestDto;
import com.resumo.texto.dto.response.SummaryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api-generate")
public class GenerateSummaryController {

    @Autowired
    IGenerateSummary service;

    @PostMapping("summary")
    public ResponseEntity<SummaryResponseDto> generateText(
            @RequestBody TextUserRequestDto textUser,
            @RequestHeader ("Authorization") String token){
        ResponseEntity<SummaryResponseDto> response = service.generateSummary(textUser, token);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response).getBody();
    }
}
