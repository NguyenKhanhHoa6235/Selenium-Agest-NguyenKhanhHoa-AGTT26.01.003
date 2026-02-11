package Constant;

public enum StationTicket {
    SAIGON("Sài Gòn"),
    PHANTHIET("Phan Thiết"),
    NHATRANG("Nha Trang"),
    DANANG("Đà Nẵng"),
    HUE("Huế"),
    QUANGNGAI("Quảng Ngãi");

    private final String displayText;

    StationTicket(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
