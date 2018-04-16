package pl.sternik.as.projekt.repositories;

public class NoSuchTankException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchTankException(String string) {
		super(string);
	}

	public NoSuchTankException() {
	}


}
