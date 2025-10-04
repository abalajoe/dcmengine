package sdu.coopbank.kb.account.statement.engine.exception;

public class NonUniqueResultsException extends RuntimeException {

    public NonUniqueResultsException(String message) {
        super(message);
    }

    public NonUniqueResultsException(String message, Throwable cause) {
        super(message, cause);
    }
}
