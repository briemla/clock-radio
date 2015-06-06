package de.briemla.clockradio.dabpi;

public enum ScanDirection {
    UP("up"), DOWN("down");

    private final String direction;

    private ScanDirection(String direction) {
        this.direction = direction;
    }

    public String serialize() {
        return direction;
    }
}
