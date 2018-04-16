package pl.sternik.as.projekt.entities;


public enum Status {

    WORKING("Working"),
    IN_MAINTENANCE("In maintenance"),
    DESTROYED("Destroyed"),
    DUPLICATE("Duplicate");


    public static final Status[] ALL = {WORKING, IN_MAINTENANCE, DESTROYED, DUPLICATE};


    private final String name;

    private Status(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
