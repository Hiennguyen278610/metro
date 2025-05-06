package org.metro.util;

public class Helper {

    // Kiểm tra tên (chỉ chứa chữ cái và khoảng trắng, không để trống)
    public static boolean isValidName(String name) {
        return name != null && name.matches("^[\\p{L} ]{2,50}$");
    }

    // Kiểm tra số điện thoại (Việt Nam: 10 chữ số, bắt đầu bằng 0)
    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("^0\\d{9}$");
    }

    // Kiểm tra tên tài khoản (chỉ chữ cái, số, không khoảng trắng, 4-20 ký tự)
    public static boolean isValidUsername(String username) {
        return username != null && username.matches("^[a-zA-Z0-9]{4,20}$");
    }

    // Kiểm tra mật khẩu (ít nhất 6 ký tự, có chữ và số)
    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$");
    }

    // Kiểm tra email
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    // Kiểm tra mã số (mã nhân viên, mã khách hàng,.. — viết hoa, số, độ dài từ 3 đến 10)
    public static boolean isValidCode(String code) {
        return code != null && code.matches("^[A-Z0-9]{1,10}$");
    }

    // Kiểm tra không để trống
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
}

