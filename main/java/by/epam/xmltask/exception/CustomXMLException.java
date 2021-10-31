package by.epam.xmltask.exception;

public class CustomXMLException extends Exception {
    public CustomXMLException() {
        super();
    }

    public CustomXMLException(String message) {
        super(message);
    }

    public CustomXMLException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomXMLException(Throwable cause) {
        super(cause);
    }
}
