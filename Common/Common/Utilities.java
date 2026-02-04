package Common;

import java.util.Random;

public class Utilities {

    /**
     * Lấy đường dẫn gốc của project hiện tại
     * Được sử dụng để định vị file driver hoặc dữ liệu
     */
    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    /** 
     * Tạo một chuỗi ký tự ngẫu nhiên với độ dài n
     */
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            result.append(characters.charAt(rnd.nextInt(characters.length())));
        }
        return result.toString();
    }

    /**
     * Tạo một địa chỉ Email ngẫu nhiên để phục vụ đăng ký tài khoản
     */
    public static String generateRandomEmail() {
        return "test_" + generateRandomString(5) + "@gmail.com";
    }
}