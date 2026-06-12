package exception;

public class AcessoNegadoException extends RuntimeException {
    public AcessoNegadoException() {
        super("ERRO: Acesso negado.");
    }
}
