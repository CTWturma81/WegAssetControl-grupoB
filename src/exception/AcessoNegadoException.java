package exception;

public class AcessoNegadoException extends AppException {
    public AcessoNegadoException() {
        super("ERRO: Acesso negado.");
    }
}
