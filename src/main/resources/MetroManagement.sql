show databases;

use quanlymetro;
create database quanlymetro;

create table users (
    username varchar(50),
    password varchar(50),
    sodienthoai varchar(9)
);
-- KHACH HANG
CREATE TABLE `khachhang` (
    `makh` int(11) NOT NULL,
    `tenkh` varchar(255) NOT NULL,
    `sdt` varchar(255) NOT NULL,
    `matuyen` varchar(255) NOT NULL,
    `ngaythamgia` datetime NOT NULL DEFAULT current_timestamp()
);


INSERT INTO `khachhang` (`makh`, `tenkh`, `sdt`, `matuyen`, `ngaythamgia`) VALUES
(1, 'Nguyễn Văn Anh', '0387913347', 'tuyen1', '2023-04-19 09:52:29'),
(2, 'Trần Thị Chỉ', '0912345678', 'tuyen2', '2023-05-10 14:32:45'),
(3, 'Lê Hoàng Xem', '0987654321', 'tuyen3', '2023-06-15 08:21:13'),
(4, 'Phạm Minh Em', '0971122334', 'tuyen4', '2023-07-20 10:45:30'),
(5, 'Hoàng Văn Là', '0902233445', 'tuyen1', '2023-08-01 12:00:00'),
(6, 'Đỗ Thị Bạn', '0923344556', 'tuyen2', '2023-09-05 16:15:42'),
(7, 'Nguyễn Văn Thôi', '0934455667', 'tuyen3', '2023-10-10 18:25:36'),
(8, 'Trịnh Ngọc Và', '0965566778', 'tuyen4', '2023-11-11 09:30:00'),
(9, 'Lý Văn Đừng', '0946677889', 'tuyen1', '2023-12-25 07:45:20'),
(10, 'Dương Thị Mong', '0917788990', 'tuyen2', '2024-01-03 11:12:33'),
(11, 'Bùi Văn Gì', '0988899001', 'tuyen3', '2024-02-14 13:50:25'),
(12, 'Nguyễn Minh Khác', '0900011223', 'tuyen4', '2024-03-21 15:33:47'),
(13, 'Võ Minh Mối', '0911122334', 'tuyen1', '2024-04-05 08:10:55'),
(14, 'Cao Thị Quan', '0922233445', 'tuyen2', '2024-05-18 17:45:30'),
(15, 'Hồ Quang Hệ', '0933344556', 'tuyen3', '2024-06-23 19:20:40'),
(16, 'Tạ Văn Này', '0964455667', 'tuyen4', '2024-07-30 20:10:22'),
(17, 'Lương Hoàng Nên', '0945566778', 'tuyen1', '2024-08-15 10:05:55'),
(18, 'Đinh Ngọc Cho', '0956677889', 'tuyen2', '2024-09-10 14:18:29'),
(19, 'Châu Văn Nó', '0977788990', 'tuyen3', '2024-10-05 09:40:15'),
(20, 'Hà Thị Thành', '0188899001', 'tuyen4', '2024-11-20 11:55:30'),
(21, 'Trương Văn Kỉ', '0990011223', 'tuyen1', '2024-12-01 08:30:45'),
(22, 'Lê Hoàng Niệm', '0882233445', 'tuyen2', '2024-12-15 10:45:20');




select * from khachhang;
select * from users;


