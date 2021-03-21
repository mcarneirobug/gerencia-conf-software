package br.com.matheus.model.domain;


import br.com.matheus.builder.NotaBuilder;
import br.com.matheus.exception.NotaInvalidaException;
import br.com.matheus.model.service.impl.NotaServiceImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static br.com.matheus.constant.Messages.NOTA_INVALIDA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class TesteNGTest {

    private final Nota nota = NotaBuilder.generate();
    private NotaServiceImpl notaService;

    /**
     * O método será executado antes de cada método de teste
     */
    @BeforeMethod
    public void setUp() {
        notaService = new NotaServiceImpl(nota);
    }

    @Test
    public void obterNotaValida() {
        final var response = this.notaService.obterNota(nota.getValorNota());

        assertNotNull(response);
        assertNotNull(notaService);
        assertNotNull(nota);
        assertThat(response, is(equalTo(1)));
    }

    /**
     * Exemplo somente para desabilitar a execução de um teste
     */
    @Test(enabled = false)
    public void disableTest() {
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

    @Test
    @Parameters(value = {"valorNota", "answer"})
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
