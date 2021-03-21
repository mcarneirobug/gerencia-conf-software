package br.com.matheus.model.service.impl;

import br.com.matheus.exception.NotaInvalidaException;
import br.com.matheus.model.domain.Nota;
import br.com.matheus.model.service.NotaService;

import static br.com.matheus.constant.Messages.NOTA_INVALIDA;

public class NotaServiceImpl implements NotaService {

    private final Nota nota;

    public NotaServiceImpl(Nota nota) {
        this.nota = nota;
    }

    @Override
    public Integer obterNota(Integer valorNota) {
        return valorNota == null || valorNota < 0 ? 0 : 1;
    }

    @Override
    public char avaliarNota() {
        if(this.obterNota(nota.getValorNota()) == 1) {
            char answer = 0;
            return this.verificaIntervalos(nota.getValorNota(), answer);
        }
        throw new NotaInvalidaException(NOTA_INVALIDA.getDescription() + nota.getValorNota());
    }

    private char verificaIntervalos(Integer nota, char answer) {
        if(nota < 6) {
            answer += 'R';
        } else if(nota > 6 && nota < 9) {
            answer += 'B';
        } else if (nota >= 9){
            answer += 'A';
        } else {
            throw new NotaInvalidaException(NOTA_INVALIDA.getDescription() + nota);
        }
        return answer;
    }
}
