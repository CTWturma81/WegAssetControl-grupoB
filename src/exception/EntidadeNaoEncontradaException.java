package exception;

public class EntidadeNaoEncontradaException extends AppException {
    public EntidadeNaoEncontradaException() {
        super("ERRO: Entidade não encontrada.");
    }
}
