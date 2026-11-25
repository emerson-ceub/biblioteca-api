package br.com.biblioteca.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarNaoEncontrado(RecursoNaoEncontradoException ex,
                                                            HttpServletRequest request) {
        return montarResposta(HttpStatus.NOT_FOUND, ex.getMessage(), request, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarValidacao(MethodArgumentNotValidException ex,
                                                        HttpServletRequest request) {
        Map<String, String> campos = new LinkedHashMap<>();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            campos.put(erro.getField(), erro.getDefaultMessage());
        }
        return montarResposta(HttpStatus.BAD_REQUEST, "Dados inválidos", request, campos);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> tratarJsonInvalido(HttpMessageNotReadableException ex,
                                                           HttpServletRequest request) {
        return montarResposta(HttpStatus.BAD_REQUEST,
                "Corpo da requisição inválido ou malformado", request, null);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResposta> tratarTipoInvalido(MethodArgumentTypeMismatchException ex,
                                                           HttpServletRequest request) {
        String mensagem = "Valor inválido para o parâmetro '" + ex.getName() + "': " + ex.getValue();
        return montarResposta(HttpStatus.BAD_REQUEST, mensagem, request, null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResposta> tratarIntegridade(DataIntegrityViolationException ex,
                                                          HttpServletRequest request) {
        return montarResposta(HttpStatus.CONFLICT,
                "Operação viola a integridade dos dados (registro em uso ou duplicado)", request, null);
    }

    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<ErroResposta> tratarErroDoSpring(ErrorResponseException ex,
                                                           HttpServletRequest request) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        return montarResposta(status, ex.getBody().getDetail(), request, null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErroResposta> tratarMetodoNaoSuportado(HttpRequestMethodNotSupportedException ex,
                                                                 HttpServletRequest request) {
        return montarResposta(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(), request, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> tratarErroGenerico(Exception ex, HttpServletRequest request) {
        log.error("Erro inesperado em {}", request.getRequestURI(), ex);
        return montarResposta(HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno no servidor", request, null);
    }

    private ResponseEntity<ErroResposta> montarResposta(HttpStatus status, String mensagem,
                                                        HttpServletRequest request,
                                                        Map<String, String> campos) {
        ErroResposta corpo = new ErroResposta(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                mensagem,
                request.getRequestURI(),
                campos
        );
        return ResponseEntity.status(status).body(corpo);
    }
}
