package com.resumo.texto.dto.request;

import com.resumo.texto.domain.model.Content;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SummaryRequestDto {

    private List<Content> contents;
}
