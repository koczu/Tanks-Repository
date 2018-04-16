package pl.sternik.as.projekt.repositories;

public class TankAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public TankAlreadyExistsException() {
    }

    public TankAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TankAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TankAlreadyExistsException(String message) {
        super(message);
    }

    public TankAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
