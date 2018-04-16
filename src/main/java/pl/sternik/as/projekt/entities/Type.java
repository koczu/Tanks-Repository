package pl.sternik.as.projekt.entities;


public enum Type {

    HEAVY_TANK("Heavy tank"),
    MEDIUM_TANK("Medium tank"),
    LIGHT_TANK("Light tank"),
    TANK_DESTROYER("Tank destroyer"),
    ARTILLERY("Artillery");


    public static final Type[] ALL = {HEAVY_TANK, MEDIUM_TANK, LIGHT_TANK, TANK_DESTROYER, ARTILLERY};


    private final String name;

    private Type(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
