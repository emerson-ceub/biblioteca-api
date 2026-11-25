package br.com.biblioteca.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Corpo padrão das respostas de erro da API.
 *
 * @param dataHora momento em que o erro ocorreu
 * @param status   código HTTP (ex.: 404)
 * @param erro     descrição do status HTTP (ex.: "Not Found")
 * @param mensagem mensagem explicando o erro
 * @param caminho  endpoint que foi chamado
 * @param campos   erros de validação por campo (null quando não se aplica)
 */
public record ErroResposta(
        LocalDateTime dataHora,
        int status,
        String erro,
        String mensagem,
        String caminho,
        Map<String, String> campos
) {
}
