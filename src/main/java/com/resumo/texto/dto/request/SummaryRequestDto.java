package com.resumo.texto.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SummaryRequestDto {

    private List<String> inputs;
}
