package br.com.matheus.model.domain;

import br.com.matheus.builder.NotaBuilder;
import br.com.matheus.exception.NotaInvalidaException;
import br.com.matheus.model.service.impl.NotaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static br.com.matheus.constant.Messages.NOTA_INVALIDA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class NotaTest {

    private final Nota nota = NotaBuilder.generate();
    private NotaServiceImpl notaService;

    @BeforeEach
    public void setUp() {
        notaService = new NotaServiceImpl(nota);
    }

    @Test
    void obterNotaValida() {
        final var response = this.notaService.obterNota(nota.getValorNota());

        assertNotNull(response);
        assertNotNull(notaService);
        assertNotNull(nota);
        assertThat(response, is(equalTo(1)));
    }

    @Test
    void obterNotaInvalidaValorNull() {
        this.nota.setValorNota(null);

        final var response = this.notaService.obterNota(this.nota.getValorNota());

        assertNotNull(response);
        assertNotNull(notaService);
        assertThat(response, is(equalTo(0)));
        assertThat(this.nota.getValorNota(), is(equalTo(null)));
    }

    @Test
    void obterNotaInvalidaValorMenorQueZero() {
        this.nota.setValorNota(-1);

        final var response = this.notaService.obterNota(this.nota.getValorNota());

        assertNotNull(response);
        assertNotNull(notaService);
        assertThat(response, is(equalTo(0)));
        assertThat(nota.getValorNota(), is(equalTo(-1)));
    }

    @ParameterizedTest
    @CsvSource({
            "10, A",
            "7, B",
            "5, R"
    })
    void avaliarTodosOsCasosDeUso(int valorNota, char answer) {
        this.nota.setValorNota(valorNota);

        final var response = this.notaService.avaliarNota();

        assertThat(response, is(equalTo(answer)));
        assertNotNull(notaService);
    }

    @Test
    void avaliarNotaInvalidaNula() {
        this.nota.setValorNota(null);

        final var exception = assertThrows(NotaInvalidaException.class,
                this.notaService::avaliarNota,
                "Deve retornar um NotaInvalidaException!");

        assertNotNull(exception);
        assertNotNull(notaService);
        assertTrue(exception.getMessage().contains(NOTA_INVALIDA.getDescription() + this.nota.getValorNota()));
    }

    @Test
    void avaliarNotaInvalidaForaDoIntervalo() {
        this.nota.setValorNota(6);

        final var exception = assertThrows(NotaInvalidaException.class,
                this.notaService::avaliarNota,
                "Deve retornar um NotaInvalidaException!");

        assertNotNull(exception);
        assertNotNull(notaService);
        assertTrue(exception.getMessage().contains(NOTA_INVALIDA.getDescription() + this.nota.getValorNota()));
    }
}
