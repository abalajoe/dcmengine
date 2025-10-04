package sdu.coopbank.kb.account.statement.engine.exception;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException(String message) {
        super(message);
    }

    public EntityExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
