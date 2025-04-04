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

-- TAIKHOAN
CREATE TABLE `taikhoan` (
    `manv` int(11) NOT NULL,
    `matkhau` varchar(255) NOT NULL,
    `manhomquyen` int(11) NOT NULL,
    `trangthai` int(11) NOT NULL
);

INSERT INTO taikhoan (manv, matkhau, manhomquyen, trangthai)
VALUES (1001, '0000', 1, 1),
    -- Admin
    (1002, '0000', 2, 1),
    -- Nhân viên
    (1003, '0000', 3, 1);
-- Quản lí
INSERT INTO taikhoan (manv, matkhau, manhomquyen, trangthai)
VALUES (1001, '0000', 1, 1),
    -- Admin
    (1002, '0000', 2, 1),
    -- Nhân viên
    (1003, '0000', 3, 1);
drop table nhanvien;
-- Quản lí
# DROP TABLE taikhoan;

-- NHANVIEN
create table nhanvien (
        manv INT AUTO_INCREMENT primary key,
        tennv VARCHAR(100) NOT NULL,
        sodienthoai VARCHAR(50) NOT NULL,
        gioitinh VARCHAR(50) NOT NULL,
        chucvu VARCHAR(100) NOT NULL
    );

# drop table nhanvien;
create table tram(
    matram int AUTO_INCREMENT primary key,
    tentram varchar(255) not null,
    x int not null,
    -- Toa do x
    y int not null -- Toa do y
);
SELECT * from tram;

insert into tram(tentram, x, y)
values
('Tram Ben Thanh', 200, 300),
('Tram Suoi Tien', 400, 100);

create table tuyen(
    matuyen int AUTO_INCREMENT primary key,
    trambatdau int not null,
    tramketthuc int not null,
    thoigian int not null,
    trangthai varchar(255) not null
);

INSERT into tuyen(trambatdau, tramketthuc, thoigian, trangthai)
values (1, 2, 40, 'Hoat dong');
DESC nhanvien;

insert into nhanvien(tennv, sodienthoai, gioitinh, chucvu)
values
    ('Nguyễn Văn A','0901234567','Nam','Quản lý tuyến tàu điện'),
    ('Trần Thị B', '0907654321', 'Nữ', 'Thu ngân'),
    ('Lê Văn C','0909876543','Nam','Nhân viên soát vé'),
    ('Phạm Thị D','0901112222','Nữ','Quản lý tuyến tàu điện'),
    ('Nguyễn Văn E', '0903334444', 'Nam', 'Thu ngân'),
    ('Trần Thị F','0905556666','Nữ','Nhân viên soát vé'),
    ('Lê Văn G','0907778888','Nam','Quản lý tuyến tàu điện'),
    ('Phạm Thị H', '0909990000', 'Nữ', 'Thu ngân'),
    ('Nguyễn Văn I','0901231231','Nam','Nhân viên soát vé'),
    ('Trần Thị J','0904567890','Nữ','Quản lý tuyến tàu điện'),
    ('Lê Văn K', '0909012345', 'Nam', 'Thu ngân'),
    ('Phạm Thị L','0902345678','Nữ','Nhân viên soát vé'),
    ('Nguyễn Văn M','0903456789','Nam','Quản lý tuyến tàu điện'),
    ('Trần Thị N', '0904567891', 'Nữ', 'Thu ngân'),
    ('Lê Văn O','0905678901','Nam','Nhân viên soát vé'),
    ('Phạm Thị P','0906789012','Nữ','Quản lý tuyến tàu điện'),
    ('Nguyễn Văn Q', '0907890123', 'Nam', 'Thu ngân'),
    ('Trần Thị R','0908901234','Nữ','Nhân viên soát vé'),
    ('Lê Văn S','0909012345','Nam','Quản lý tuyến tàu điện'),
    ('Phạm Thị T', '0900123456', 'Nữ', 'Thu ngân');

-- Tạo bảng vé tàu
create table vetau (
    mave int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    machuyen int NOT NULL,
    makh int NOT NULL,
    giave double NOT NULL
);

-- Backup/test
INSERT INTO vetau (machuyen, makh,giave)
VALUES (3, 6,8500.00),
       (3, 15,21000.00),
       (3, 9,13750.50),
       (3, 2,9000.00),
       (3, 21,23000.00),
       (3, 1,15000.00),
       (6, 19,7450.00),
       (6, 14,18000.00),
       (6, 8,23900.00),
       (6, 17,7100.00),
       (6, 10,20000.00),
       (6, 6,16000.00),
       (6, 3,9500.00),
       (6, 22,22200.00),
       (6, 7,12350.00),
       (9, 11, 14000.00),
       (9, 20,23500.00),
       (9, 12,10000.00),
       (9, 13,18800.00),
       (9, 4,7800.00),
       (9, 12,10000.00);

CREATE table tau (
    matau int NOT NULL primary key,
    soghe int NOT NULL,
    trangthaitau varchar(100) NOT NULL,
    ngaynhap date NOT NULL
);
# drop table tau;
INSERT into tau(matau, soghe, trangthaitau, ngaynhap)
values (2, 50, 'Đang hoạt động', '2025-01-01'),
    (1, 20, 'Đang bảo trì', '2025-01-02'),
    (3, 100, 'Ngừng hoạt động', '2025-01-02'),
    (4, 25, 'Đang hoạt động', '2025-01-03');

-- Tạo bảng lịch trình
CREATE TABLE lichtrinh (
    machuyen INT NOT NULL,
    manv INT NOT NULL,
    matau INT NOT NULL,
    matuyen INT NOT NULL,
    huongdi BOOLEAN NOT NULL,
    thoigiankh DATETIME NOT NULL,
    tgdenthucte DATETIME NOT NULL,
    trangthailichtrinh VARCHAR(100) NOT NULL
);

-- Backup/test
INSERT INTO lichtrinh (machuyen, manv, matau, matuyen, huongdi, thoigiankh, tgdenthucte, trangthailichtrinh)
VALUES
(21, 2, 1, 1, 1, '2025-04-01 09:00:00', '2025-04-01 09:30:00', 'Chờ khởi hành'),
(22, 5, 2, 1, 1, '2025-04-02 10:00:00', '2025-04-02 10:20:00', 'Đang khởi hành'),
(23, 4, 3, 1, 1, '2025-04-03 11:00:00', '2025-04-03 11:25:00', 'Hoàn Thành'),
(24, 2, 1, 1, 1, '2025-04-04 07:15:00', '2025-04-04 07:45:00', 'Chờ khởi hành'),
(25, 7, 2, 1, 1, '2025-04-05 12:00:00', '2025-04-05 12:30:00', 'Đang khởi hành'),
(26, 3, 4, 1, 1, '2025-04-06 13:00:00', '2025-04-06 13:25:00', 'Hoàn Thành'),
(27, 1, 3, 1, 1, '2025-04-07 14:00:00', '2025-04-07 14:30:00', 'Chờ khởi hành'),
(28, 6, 4, 1, 1, '2025-04-08 16:00:00', '2025-04-08 16:20:00', 'Đang khởi hành'),
(29, 5, 1, 1, 1, '2025-04-09 06:45:00', '2025-04-09 07:10:00', 'Hoàn Thành'),
(30, 4, 3, 1, 0, '2025-05-01 06:00:00', '2025-05-01 06:05:00', 'Đang khởi hành');


# lich bao tri
CREATE TABLE lichbaotri (
    mabaotri INT PRIMARY KEY AUTO_INCREMENT,
    matau INT NOT NULL,
    ngaybaotri DATE NOT NULL,
    trangthaibaotri VARCHAR(100) NOT NULL,
    ngaytao DATETIME NOT NULL
);
ALTER TABLE lichbaotri
MODIFY COLUMN ngaybaotri DATE;
# drop table lichbaotri
INSERT INTO lichbaotri (
        mabaotri,
        matau,
        ngaybaotri,
        trangthaibaotri,
        ngaytao
    )
VALUES (1,1,'2025-03-23 10:30:00','Đang bảo trì','2025-03-22 15:45:00'),
    (2,2,'2025-03-22 15:45:00','Hoàn thành','2025-03-21 15:45:00'),
    (3,3,'2025-03-21 09:00:00','Đang bảo trì','2025-03-22 15:45:00'),
    (4,4,'2025-03-20 14:20:00','Chờ kiểm tra','2025-03-22 15:45:00');

-- Truy Vấn
select *
from lichtrinh;
select *
from khachhang;
select *
from taikhoan;
select *
from nhanvien;
select *
from tau;
select *
from vetau;