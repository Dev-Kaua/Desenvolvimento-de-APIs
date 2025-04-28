package Desenvolvimento_de_APIs.my_first_web_api.handler;

public class BusinessException extends RuntimeException{
    public BusinessException(String mensagem) {
        super(mensagem);
    }

    public BusinessException(String message, Object ... params) {
        super(String.format(message, params));
    }
}
