package exceptions;

public class StorageBackendException extends BaseException {

    private static final long serialVersionUID = 1L;

    public StorageBackendException() {}
    
    public StorageBackendException(String reason) {
        super(reason);
        
        // TODO Auto-generated constructor stub
    }

    public StorageBackendException(Exception e) {
        super(e);
    }
}
