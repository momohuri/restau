package exceptions;

public class BaseException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String reason;

    public BaseException() {}
    
    public BaseException(Exception e) {
        super(e);
    }

    public BaseException(String reason) {
        this.reason = reason;
    }
    
    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "Reason: " + reason + "\n" + super.toString();
    }
    
    @Override
    public String getMessage() {
        return "Reason: " + reason + "\n" + super.getMessage();
    }
    
}

