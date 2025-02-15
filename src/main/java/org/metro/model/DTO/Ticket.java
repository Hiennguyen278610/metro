package org.metro.model.DTO;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketID; // Mã vé
    private String customerPhone; // Số điện thoại khách hàng
    private String customerName; // Tên khách hàng
    private LocalDateTime departTime; // Thời gian khởi hành
    private String startStation; // Tên trạm xuất phát
    private String endStation; // Tên trạm cần đến
    private int travelTime; // Thời gian di chuyển ước tính
    private double price; // Giá vé
}