package com.resumo.texto.application.usecase;

import com.resumo.texto.domain.model.Content;
import com.resumo.texto.dto.request.SummaryRequestDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SummaryTextUseCase {

    public String joinText(String text){
        return "Resuma o seguinte texto:" + text;
    }

    public SummaryRequestDto formatText(String text){

        Content messages = new Content();
        messages.setRole("user");
        messages.setContent(joinText(text));

        List<Content> messageList = new ArrayList<>();
        messageList.add(messages);

        SummaryRequestDto summaryRequest = new SummaryRequestDto();
        summaryRequest.setModel("gpt-3.5-turbo");
        summaryRequest.setMessage(messageList);

        return  summaryRequest;
    }
}
