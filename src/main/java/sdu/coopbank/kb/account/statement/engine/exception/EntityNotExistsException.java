package sdu.coopbank.kb.account.statement.engine.exception;

public class EntityNotExistsException extends RuntimeException {

    public EntityNotExistsException(String message) {
        super(message);
    }

    public EntityNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
