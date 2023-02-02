package hiruni.project.medicinefinderbackend.dtd;

//DTD - data type definition
public class ResponseDTD<T> {
    private boolean status;
    private String message;
    private T body;

    public ResponseDTD(boolean status, String message, T body) {
        this.setStatus(status);
        this.setMessage(message);
        this.setBody(body);
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
