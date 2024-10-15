public class CustomException extends RuntimeException {
    private String message;
    private Throwable throwable;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
