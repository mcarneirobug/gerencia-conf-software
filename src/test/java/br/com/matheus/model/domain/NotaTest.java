package br.com.matheus.model.domain;

import br.com.matheus.builder.NotaBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotaTest {

    @Test
    void obterNotaValida() {
        final var nota = NotaBuilder.generate();
        final var notaToCompare = NotaBuilder.generate();

        final var response = notaToCompare.obterNota(nota.getValorNota());

        assertNotNull(response);
        assertThat(notaToCompare.getValorNota(), is(equalTo(nota.getValorNota())));
        assertThat(response, is(equalTo(1)));
    }

    @Test
    void obterNotaInvalidaValorNull() {
        final var nota = NotaBuilder.generate();
        final var notaToCompare = NotaBuilder.generate();

        nota.setValorNota(null);

        final var response = notaToCompare.obterNota(nota.getValorNota());

        assertNotNull(response);
        assertThat(response, is(equalTo(0)));
        assertThat(nota.getValorNota(), is(equalTo(null)));
    }

    @Test
    void obterNotaInvalidaValorMenorQueZero() {
        final var nota = NotaBuilder.generate();
        final var notaToCompare = NotaBuilder.generate();

        nota.setValorNota(-1);

        final var response = notaToCompare.obterNota(nota.getValorNota());

        assertNotNull(response);
        assertThat(response, is(equalTo(0)));
        assertThat(nota.getValorNota(), is(equalTo(-1)));
    }

    @ParameterizedTest
    @CsvSource({
            "10, A",
            "7, B",
            "5, R",
            "6, E"
    })
    void avaliarTodosOsCasosDeUso(int valorNota, char answer) {
        final var nota = NotaBuilder.generate();
        nota.setValorNota(valorNota);

        final var response = nota.avaliarNota();

        assertThat(response, is(equalTo(answer)));
    }

    @Test
    void avaliarNotaInvalida() {
        final var nota = NotaBuilder.generate();
        nota.setValorNota(null);

        final var response = nota.avaliarNota();

        assertThat(response, is(equalTo('E')));
    }
}
