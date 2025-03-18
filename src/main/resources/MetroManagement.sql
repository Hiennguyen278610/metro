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
INSERT INTO `khachhang` (`makh`, `tenkh`, `sdt`, `solan`)
VALUES (1, 'Nguyễn Văn Anh', '0387913347', '1'),
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
INSERT INTO `taikhoan` (`manv`, `matkhau`, `manhomquyen`, `trangthai`)
VALUES (1, '0000', 1, 1),
    -- Admin
    (2, '0000', 2, 1),
    -- User
    (3, '0000', 3, 1),
    -- Nhân viên
    (4, '0000', 2, 0);
-- NHANVIEN
create table nhanvien ( 
    manv INT AUTO_INCREMENT primary key,
    tennv VARCHAR(100) NOT NULL,
    sodienthoai VARCHAR(50) NOT NULL,
    gioitinh VARCHAR(50) NOT NULL,
    chucvu VARCHAR(100) NOT NULL
);



DROP table nhanvien;

DESC nhanvien;

insert into nhanvien(tennv,sodienthoai,gioitinh,chucvu)
values
('Nguyễn Văn A', '0901234567', 'Nam', 'Quản lý tuyến tàu điện'),
('Trần Thị B', '0907654321', 'Nữ', 'Thu ngân'),
('Lê Văn C', '0909876543', 'Nam', 'Nhân viên soát vé'),
('Phạm Thị D', '0901112222', 'Nữ', 'Quản lý tuyến tàu điện'),
('Nguyễn Văn E', '0903334444', 'Nam', 'Thu ngân'),
('Trần Thị F', '0905556666', 'Nữ', 'Nhân viên soát vé'),
('Lê Văn G', '0907778888', 'Nam', 'Quản lý tuyến tàu điện'),
('Phạm Thị H', '0909990000', 'Nữ', 'Thu ngân'),
('Nguyễn Văn I', '0901231231', 'Nam', 'Nhân viên soát vé'),
('Trần Thị J', '0904567890', 'Nữ', 'Quản lý tuyến tàu điện'),
('Lê Văn K', '0909012345', 'Nam', 'Thu ngân'),
('Phạm Thị L', '0902345678', 'Nữ', 'Nhân viên soát vé'),
('Nguyễn Văn M', '0903456789', 'Nam', 'Quản lý tuyến tàu điện'),
('Trần Thị N', '0904567891', 'Nữ', 'Thu ngân'),
('Lê Văn O', '0905678901', 'Nam', 'Nhân viên soát vé'),
('Phạm Thị P', '0906789012', 'Nữ', 'Quản lý tuyến tàu điện'),
('Nguyễn Văn Q', '0907890123', 'Nam', 'Thu ngân'),
('Trần Thị R', '0908901234', 'Nữ', 'Nhân viên soát vé'),
('Lê Văn S', '0909012345', 'Nam', 'Quản lý tuyến tàu điện'),
('Phạm Thị T', '0900123456', 'Nữ', 'Thu ngân');

-- Tạo bảng vé tàu
create table vetau (
    mave int NOT NULL,
    machuyen int NOT NULL,
    makh int NOT NULL,
    giave double NOT NULL
);
CREATE table tau (
    matau int NOT NULL primary key,
    soghe int NOT NULL,
    trangthaitau varchar(100) NOT NULL,
    ngaynhap date NOT NULL
);
INSERT into tau(matau, soghe, trangthaitau, ngaynhap)
values (1, 100, "Đang hoạt động", "2025-01-01"),
    (2, 100, "Đang bảo trì", "2025-01-02"),
    (3, 100, "Ngừng hoạt động", "2025-01-02"),
    (4, 100, "Đang hoạt động", "2025-01-03");
-- Backup//test
INSERT INTO vetau (mave, machuyen, makh, giave)
VALUES (1, 3, 6, 8500.00),
    (2, 3, 15, 21000.00),
    (3, 3, 9, 13750.50),
    (4, 3, 2, 9000.00),
    (5, 3, 21, 23000.00),
    (6, 3, 1, 15000.00),
    (7, 6, 19, 7450.00),
    (8, 6, 14, 18000.00),
    (9, 6, 8, 23900.00),
    (10, 6, 17, 7100.00),
    (11, 6, 10, 20000.00),
    (12, 6, 6, 16000.00),
    (13, 6, 3, 9500.00),
    (14, 6, 22, 22200.00),
    (15, 6, 7, 12350.00),
    (16, 9, 11, 14000.00),
    (17, 9, 20, 23500.00),
    (18, 9, 12, 10000.00),
    (19, 9, 13, 18800.00),
    (20, 9, 4, 7800.00),
    (21, 9, 12, 10000.00);
-- Tạo bảng lịch trình
create table lichtrinh (
    machuyen int NOT NULL,
    manv int NOT NULL,
    matau int NOT NULL,
    matuyen int NOT NULL,
    thoigiankh datetime NOT NULL,
    tgdenthucte datetime NOT NULL,
    trangthailichtrinh varchar(100) NOT NULL
);
-- Backup/test
INSERT INTO lichtrinh (machuyen, manv, matau, matuyen, thoigiankh, tgdenthucte, trangthailichtrinh)
VALUES (1, 3, 2, 1, '2025-01-05 08:15:00', '2025-01-05 08:45:00', 'Chờ khởi hành'),
       (2, 1, 4, 1, '2025-01-12 14:00:00', '2025-01-12 14:30:00', 'Đang khởi hành'),
       (3, 7, 1, 1, '2025-02-03 09:00:00', '2025-02-03 09:05:00', 'Hoàn Thành'),
       (4, 5, 3, 1, '2025-01-20 07:30:00', '2025-01-20 07:50:00', 'Đang khởi hành'),
       (5, 2, 2, 1, '2025-03-15 12:00:00', '2025-03-15 12:20:00', 'Chờ khởi hành'),
       (6, 4, 4, 1, '2025-02-25 18:15:00', '2025-02-25 18:45:00', 'Hoàn Thành'),
       (7, 1, 1, 1, '2025-03-05 06:00:00', '2025-03-05 06:10:00', 'Chờ khởi hành'),
       (8, 6, 3, 1, '2025-03-22 15:30:00', '2025-03-22 15:45:00', 'Đang khởi hành'),
       (9, 7, 2, 1, '2025-02-10 10:00:00', '2025-02-10 10:35:00', 'Hoàn Thành'),
       (10, 3, 4, 1, '2025-01-28 13:00:00', '2025-01-28 13:25:00', 'Chờ khởi hành'),
       (11, 2, 1, 1, '2025-03-10 11:15:00', '2025-03-10 11:40:00', 'Đang khởi hành'),
       (12, 4, 3, 1, '2025-02-17 17:00:00', '2025-02-17 17:20:00', 'Chờ khởi hành'),
       (13, 5, 2, 1, '2025-01-07 16:30:00', '2025-01-07 16:55:00', 'Hoàn Thành'),
       (14, 6, 1, 1, '2025-03-03 05:45:00', '2025-03-03 06:05:00', 'Đang khởi hành'),
       (15, 7, 4, 1, '2025-02-28 20:00:00', '2025-02-28 20:30:00', 'Chờ khởi hành'),
       (16, 1, 3, 1, '2025-01-15 09:30:00', '2025-01-15 09:50:00', 'Hoàn Thành'),
       (17, 3, 2, 1, '2025-03-18 07:45:00', '2025-03-18 08:10:00', 'Đang khởi hành'),
       (18, 5, 3, 1, '2025-02-05 12:15:00', '2025-02-05 12:40:00', 'Chờ khởi hành'),
       (19, 4, 1, 1, '2025-03-25 14:00:00', '2025-03-25 14:25:00', 'Hoàn Thành'),
       (20, 2, 4, 1, '2025-01-30 10:00:00', '2025-01-30 10:20:00', 'Đang khởi hành');
-- test truy vấn lịch trình đã hoàn thành vào tháng 2
select *
from lichtrinh
where trangthailichtrinh = 'Hoàn Thành'
    and MONTH(thoigiankh) = 2;
# DROP TABLE taikhoan;
DROP TABLE khachhang;
select * from khachhang;
select * from taikhoan;
select * from users;
select * from nhanvien;
select * from tau;
select * from vetau;
select * from lichtrinh;