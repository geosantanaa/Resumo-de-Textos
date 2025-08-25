package com.resumo.texto.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Content {

    private List<Parts> parts;
}
