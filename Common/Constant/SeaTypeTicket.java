package Constant;

public enum SeaTypeTicket {

    HARD_SEAT("Hard seat"),
    SOFT_SEAT("Soft seat"),
    SOFT_SEAT_WITH_AIR_CONDITIONER("Soft seat with air conditioner"),
    HARD_BED("Hard bed"),
    SOFT_BED("Soft bed"),
    SOFT_BED_WITH_AIR_CONDITIONER("Soft bed with air conditioner");

    private final String displayText;

    SeaTypeTicket(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
