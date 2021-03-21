package br.com.matheus.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Messages {

    NOTA_INVALIDA("Nota Inválida: ");

    private final String description;
}
