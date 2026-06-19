package exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException() {
        super("ERRO: Entidade não encontrada.");
    }
}
