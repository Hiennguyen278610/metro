package org.metro.util;

import org.metro.model.TaiKhoanModel;

public class SessionManager {
    private static TaiKhoanModel currentUser;

    public static void setCurrentUser(TaiKhoanModel user) {
        currentUser = user;
    }

    public static TaiKhoanModel getCurrentUser() {
        return currentUser;
    }
    public static void clearSession() {
        currentUser = null;
    }
}
