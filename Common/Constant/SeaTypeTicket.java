package Constant;

public enum SeaTypeTicket {

    HOME("Hard seat"),
    CONTACT("Soft seat"),
    TIMETABLE("Soft seat with air conditioner"),
    TICKET_PRICE("Hard bed"),
    BOOK_TICKET("Soft bed"),
    MY_TICKET("Soft bed with air conditioner");

    private final String displayText;

    SeaTypeTicket(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
