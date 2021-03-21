package br.com.matheus.builder;

import br.com.matheus.model.domain.Nota;

public class NotaBuilder {

    public static Nota generate() {
       return Nota
               .builder()
               .valorNota(10)
               .build();
    }
}
