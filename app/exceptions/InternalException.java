package exceptions;

public class InternalException extends BaseException {

    private static final long serialVersionUID = 1L;

    public InternalException() {}
    
    public InternalException(Exception e) {
        super(e);
    }

    public InternalException(String reason) {
        super(reason);
        // TODO Auto-generated constructor stub
    }

}
