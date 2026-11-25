package br.com.biblioteca.exception;

/**
 * Lançada quando um registro solicitado não existe no banco de dados.
 * É convertida em resposta HTTP 404 pelo {@link GlobalExceptionHandler}.
 */
public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    /**
     * Construtor de conveniência. Exemplo: {@code new RecursoNaoEncontradoException("Livro", 5L)}
     * gera a mensagem "Livro com id 5 não encontrado(a)".
     */
    public RecursoNaoEncontradoException(String recurso, Long id) {
        super(recurso + " com id " + id + " não encontrado(a)");
    }
}
