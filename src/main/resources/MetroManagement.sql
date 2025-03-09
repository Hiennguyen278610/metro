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
    `makh` INT AUTO_INCREMENT NOT NULL,
    `tenkh` VARCHAR(255) NOT NULL,
    `sdt` VARCHAR(255) NOT NULL,
    `solan` INT(10) NOT NULL,
    UNIQUE KEY (`makh`)
);


INSERT INTO `khachhang` (`makh`, `tenkh`, `sdt`, `solan`) VALUES
(1, 'Nguyễn Văn Anh', '0387913347', '1'),
(2, 'Trần Thị Chỉ', '0912345678', '2'),
(3, 'Lê Hoàng Xem', '0987654321', '3'),
(4, 'Phạm Minh Em', '0971122334', '4'),
(5, 'Hoàng Văn Là', '0902233445', '1'),
(6, 'Đỗ Thị Bạn', '0923344556', '2'),
(7, 'Nguyễn Văn Thôi', '0934455667', '3'),
(8, 'Trịnh Ngọc Và', '0965566778', '4'),
(9, 'Lý Văn Đừng', '0946677889', '1'),
(10, 'Dương Thị Mong', '0917788990', '2'),
(11, 'Bùi Văn Gì', '0988899001', '3'),
(12, 'Nguyễn Minh Khác', '0900011223', '4'),
(13, 'Võ Minh Mối', '0911122334', '1'),
(14, 'Cao Thị Quan', '0922233445', '2'),
(15, 'Hồ Quang Hệ', '0933344556', '3'),
(16, 'Tạ Văn Này', '0964455667', '4'),
(17, 'Lương Hoàng Nên', '0945566778', '1'),
(18, 'Đinh Ngọc Cho', '0956677889', '2'),
(19, 'Châu Văn Nó', '0977788990', '3'),
(20, 'Hà Thị Thành', '0188899001', '4'),
(21, 'Trương Văn Kỉ', '0990011223', '1'),
(22, 'Lê Hoàng Niệm', '0882233445', '2');


CREATE TABLE `taikhoan` (
    `manv` int(11) NOT NULL,
    `matkhau` varchar(255) NOT NULL,
    `manhomquyen` int(11) NOT NULL,
    `trangthai` int(11) NOT NULL
);
INSERT INTO `taikhoan` (`manv`, `matkhau`, `manhomquyen`, `trangthai`) VALUES
    (1, '0000', 1, 1),  -- Admin
    (2, '0000', 2, 1),  -- User
    (3, '0000', 3, 1),  -- Nhân viên
    (4, '0000', 2, 0);


# DROP TABLE taikhoan;
# DROP TABLE khachhang;
select * from khachhang;
select * from taikhoan;
select * from users;


