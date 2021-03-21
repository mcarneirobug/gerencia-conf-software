package br.com.matheus.model.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class Nota {

    private Integer valorNota;

    public Integer obterNota(Integer valorNota) {
        return valorNota == null || valorNota < 0 ? 0 : 1;
    }

    public char avaliarNota() {
        if(this.obterNota(this.valorNota) == 1) {
            char answer = 0;
            return this.verificaIntervalos(this.valorNota, answer);
        }
        return 'E';
    }

    private char verificaIntervalos(Integer nota, char answer) {
        if(nota < 6) {
            answer += 'R';
        } else if(nota > 6 && nota < 9) {
            answer += 'B';
        } else if (nota >= 9){
            answer += 'A';
        } else {
            answer += 'E';
        }
        return answer;
    }
}
