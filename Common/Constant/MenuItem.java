package Constant;

public enum MenuItem {

    HOME("Home"),
    FAQ("FAQ"),
    CONTACT("Contact"),
    TIMETABLE("Timetable"),
    TICKET_PRICE("Ticket price"),
    BOOK_TICKET("Book ticket"),
    MY_TICKET("My ticket"),
    REGISTER("Register"),
    LOGIN("Login"),
    LOGOUT("Log out");

    private final String displayText;

    MenuItem(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
