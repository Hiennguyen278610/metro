-- Xóa các bảng theo thứ tự phụ thuộc (nếu đã tồn tại)
DROP TABLE IF EXISTS lichbaotri;
DROP TABLE IF EXISTS vetau;
DROP TABLE IF EXISTS lichtrinh;
DROP TABLE IF EXISTS tuyen_tram;
DROP TABLE IF EXISTS tuyen;
DROP TABLE IF EXISTS tram;
DROP TABLE IF EXISTS tau;
DROP TABLE IF EXISTS taikhoan;
DROP TABLE IF EXISTS nhanvien;
DROP TABLE IF EXISTS chitietquyen;
DROP TABLE IF EXISTS nhomchucnang;
DROP TABLE IF EXISTS nhomquyen;
DROP TABLE IF EXISTS khachhang;
CREATE DATABASE IF NOT EXISTS quanlymetro;
USE quanlymetro;
-- ----------------------------------------------------------------
-- Bảng KHACHHANG
CREATE TABLE khachhang (
    makh INT NOT NULL AUTO_INCREMENT,
    tenkh VARCHAR(255) NOT NULL,
    sdt VARCHAR(15) NOT NULL,
    solan INT NOT NULL,
    isVisible BOOLEAN NOT NULL DEFAULT 1,
    PRIMARY KEY (makh)
);
INSERT INTO khachhang (tenkh, sdt, solan)
VALUES ('Nguyễn Văn Anh', '0387913347', 1),
    ('Trần Thị Chỉ', '0912345678', 2),
    ('Lê Hoàng Xem', '0987654321', 3),
    ('Phạm Minh Em', '0971122334', 4),
    ('Hoàng Văn Là', '0902233445', 1),
    ('Đỗ Thị Bạn', '0923344556', 2),
    ('Nguyễn Văn Thôi', '0934455667', 3),
    ('Trịnh Ngọc Và', '0965566778', 4),
    ('Lý Văn Đừng', '0946677889', 1),
    ('Dương Thị Mong', '0917788990', 2),
    ('Bùi Văn Gì', '0988899001', 3),
    ('Nguyễn Minh Khác', '0900011223', 4),
    ('Võ Minh Mối', '0911122334', 1),
    ('Cao Thị Quan', '0922233445', 2),
    ('Hồ Quang Hệ', '0933344556', 3),
    ('Tạ Văn Này', '0964455667', 4),
    ('Lương Hoàng Nên', '0945566778', 1),
    ('Đinh Ngọc Cho', '0956677889', 2),
    ('Châu Văn Nói', '0977788990', 3),
    ('Hà Thị Thành', '0988899001', 4);


-- ----------------------------------------------------------------
-- Bảng NHOMQUYEN
CREATE TABLE nhomquyen (
    manhomquyen INT NOT NULL AUTO_INCREMENT,
    tennhomquyen VARCHAR(100) NOT NULL,
    isVisible BOOLEAN NOT NULL DEFAULT 1,
    PRIMARY KEY (manhomquyen)
);
INSERT INTO nhomquyen (manhomquyen, tennhomquyen)
VALUES (1, 'Admin'),
    (2, 'Nhân viên bán vé'),
    (3, 'Quản lí tàu tuyến'),
    (4, 'Quản lí lịch trình'),
    (5, 'Quản lí trạm');
-- ----------------------------------------------------------------
-- Bảng NHOMCHUCNANG
CREATE TABLE nhomchucnang (
    machucnang INT NOT NULL AUTO_INCREMENT,
    tenchucnang VARCHAR(100) NOT NULL,
    PRIMARY KEY (machucnang)
);
INSERT INTO nhomchucnang (machucnang, tenchucnang)
VALUES (1, 'Tàu'),
    (2, 'Trạm'),
    (3, 'Tuyến Đường'),
    (4, 'Lịch Trình'),
    (5, 'Vé Tàu'),
    (6, 'Bảo Trì'),
    (7, 'Khách Hàng'),
    (8, 'Nhân Viên'),
    (9, 'Tài Khoản'),
    (10, 'Phân Quyền'),
    (11, 'Thống kê');
-- ----------------------------------------------------------------
-- Bảng CHITIETQUYEN
CREATE TABLE chitietquyen (
    manhomquyen INT NOT NULL,
    machucnang INT NOT NULL,
    hanhdong VARCHAR(50) NOT NULL,
    PRIMARY KEY (manhomquyen, machucnang, hanhdong),
    CONSTRAINT fk_chitietquyen_nhomquyen FOREIGN KEY (manhomquyen) REFERENCES nhomquyen(manhomquyen) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_chitietquyen_nhomchucnang FOREIGN KEY (machucnang) REFERENCES nhomchucnang(machucnang) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO chitietquyen (manhomquyen, machucnang, hanhdong) # quyen admin
VALUES (1, 1, 'create'),
    (1, 1, 'detail'),
    (1, 1, 'delete'),
    (1, 1, 'update'),
    (1, 2, 'create'),
    (1, 2, 'detail'),
    (1, 2, 'delete'),
    (1, 2, 'update'),
    (1, 3, 'create'),
    (1, 3, 'detail'),
    (1, 3, 'delete'),
    (1, 3, 'update'),
    (1, 4, 'create'),
    (1, 4, 'detail'),
    (1, 4, 'delete'),
    (1, 4, 'update'),
    (1, 5, 'create'),
    (1, 5, 'detail'),
    (1, 5, 'delete'),
    (1, 5, 'update'),
    (1, 6, 'create'),
    (1, 6, 'detail'),
    (1, 6, 'delete'),
    (1, 6, 'update'),
    (1, 7, 'create'),
    (1, 7, 'detail'),
    (1, 7, 'delete'),
    (1, 7, 'update'),
    (1, 8, 'create'),
    (1, 8, 'detail'),
    (1, 8, 'delete'),
    (1, 8, 'update'),
    (1, 9, 'create'),
    (1, 9, 'detail'),
    (1, 9, 'delete'),
    (1, 9, 'update'),
    (1, 10, 'create'),
    (1, 10, 'detail'),
    (1, 10, 'delete'),
    (1, 10, 'update'),
    (1, 11, 'create'),
    (1, 11, 'detail'),
    (1, 11, 'delete'),
    (1, 11, 'update'),
    #quyen nhan vien ban ve
    (2, 5, 'create'),
    (2, 5, 'detail'),
    (2, 7, 'detail'),
    (2, 7, 'update'),
    (2, 3, 'detail'),
    #quyen quan li tau
    (3, 1, 'detail'),
    (3, 1, 'update'),
    (3, 4, 'detail'),
    (3, 3, 'detail'),
    #quan li lich trinh
    (4, 1, 'detail'),
    (4, 8, 'create'),
    (4, 8, 'detail'),
    (4, 8, 'delete'),
    (4, 8, 'update'),
    (4, 4, 'create'),
    (4, 4, 'detail'),
    (4, 4, 'delete'),
    (4, 4, 'update'),
    (4, 3, 'detail'),
    #quan li tram
    (5, 5, 'detail'),
    (5, 1, 'detail'),
    (5, 7, 'detail'),
    (5, 8, 'create'),
    (5, 8, 'detail'),
    (5, 8, 'delete'),
    (5, 8, 'update'),
    (5, 4, 'detail'),
    (5, 3, 'detail'),
    (5, 9, 'create'),
    (5, 9, 'detail'),
    (5, 9, 'delete'),
    (5, 9, 'update');
select *
from chitietquyen;
-- ----------------------------------------------------------------
-- Bảng NHANVIEN
CREATE TABLE nhanvien (
    manv INT NOT NULL AUTO_INCREMENT,
    tennv VARCHAR(100) NOT NULL,
    sodienthoai VARCHAR(15) NOT NULL,
    gioitinh VARCHAR(50) NOT NULL,
    chucvu VARCHAR(100) NOT NULL,
    isVisible BOOLEAN NOT NULL DEFAULT 1,
    PRIMARY KEY (manv)
);
INSERT INTO nhanvien (tennv, sodienthoai, gioitinh, chucvu)
VALUES (
        'Nguyễn Văn A',
        '0901234567',
        'Nam',
        'Quản lý tuyến tàu điện'
    ),
    ('Trần Thị B', '0907654321', 'Nữ', 'Thu ngân'),
    (
        'Lê Văn C',
        '0909876543',
        'Nam',
        'Nhân viên soát vé'
    ),
    (
        'Phạm Thị D',
        '0901112222',
        'Nữ',
        'Quản lý tuyến tàu điện'
    ),
    ('Nguyễn Văn E', '0903334444', 'Nam', 'Thu ngân'),
    (
        'Trần Thị F',
        '0905556666',
        'Nữ',
        'Nhân viên soát vé'
    ),
    (
        'Lê Văn G',
        '0907778888',
        'Nam',
        'Quản lý tuyến tàu điện'
    ),
    ('Phạm Thị H', '0909990000', 'Nữ', 'Thu ngân'),
    (
        'Nguyễn Văn I',
        '0901231231',
        'Nam',
        'Nhân viên soát vé'
    ),
    (
        'Trần Thị J',
        '0904567890',
        'Nữ',
        'Quản lý tuyến tàu điện'
    ),
    ('Lê Văn K', '0909012345', 'Nam', 'Thu ngân'),
    (
        'Phạm Thị L',
        '0902345678',
        'Nữ',
        'Nhân viên soát vé'
    ),
    (
        'Nguyễn Văn M',
        '0903456789',
        'Nam',
        'Quản lý tuyến tàu điện'
    ),
    ('Trần Thị N', '0904567891', 'Nữ', 'Thu ngân'),
    (
        'Lê Văn O',
        '0905678901',
        'Nam',
        'Nhân viên soát vé'
    ),
    (
        'Phạm Thị P',
        '0906789012',
        'Nữ',
        'Quản lý tuyến tàu điện'
    ),
    (
        'Trần Thị R',
        '0908901234',
        'Nữ',
        'Nhân viên soát vé'
    ),
    (
        'Lê Văn S',
        '0909012345',
        'Nam',
        'Quản lý tuyến tàu điện'
    );
-- ----------------------------------------------------------------
-- Bảng TAIKHOAN
CREATE TABLE taikhoan (
    manv INT NOT NULL,
    matkhau VARCHAR(255) NOT NULL,
    manhomquyen INT NOT NULL,
    trangthai INT NOT NULL,
    PRIMARY KEY (manv),
    CONSTRAINT FK_taikhoan_nhanvien FOREIGN KEY (manv) REFERENCES nhanvien(manv) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_taikhoan_nhomquyen FOREIGN KEY (manhomquyen) REFERENCES nhomquyen(manhomquyen) ON DELETE NO ACTION ON UPDATE NO ACTION
);
INSERT INTO taikhoan (manv, matkhau, manhomquyen, trangthai)
VALUES (1, '0000', 1, 1),
    (2, '0000', 2, 1),
    (3, '0000', 3, 1),
    (4,'0000',4,1),
    (5,'0000',5,1);
-- ----------------------------------------------------------------
-- Tạo tram
CREATE TABLE tram (
    matram INT NOT NULL AUTO_INCREMENT,
    tentram VARCHAR(255) NOT NULL,
    x INT NOT NULL,
    y INT NOT NULL,
    diachi VARCHAR(255) NOT NULL,
    chiphitram DOUBLE NOT NULL,
    PRIMARY KEY (matram)
);
INSERT INTO tram (tentram, x, y, diachi, chiphitram)
VALUES (
        'Tram Ben Thanh',
        300,
        300,
        'Công viên 23/9, Phường Bến Thành, Quận 1, TP.HCM',
        10000.00
    ),
    (
        'Tram Sai Gon',
        200,
        400,
        'Đại học Sài Gòn, Phường 3, Quận 5, TP.HCM',
        10000.00
    ),
    (
        'Tram Hoa Hung',
        100,
        500,
        'Ngã tư Hòa Hưng, Phường 12, Quận 10, TP.HCM',
        10000.00
    ),
    (
        'Tram Tan Binh',
        50,
        150,
        'Công viên Lê Thị Riêng, Phường 15, Quận Tân Bình, TP.HCM',
        10000.00
    ),
    (
        'Tram Phu Nhuan',
        220,
        180,
        'Chợ Phú Nhuận, Phường 15, Quận Phú Nhuận, TP.HCM',
        10000.00
    ),
    (
        'Tram Go Vap',
        400,
        100,
        'Vòng xoay Ngã 6 Gò Vấp, Quận Gò Vấp, TP.HCM',
        10000.00
    ),
    (
        'Tram Binh Thanh',
        350,
        200,
        'Cầu Bình Lợi, Phường 13, Quận Bình Thạnh, TP.HCM',
        10000.00
    ),
    (
        'Tram Thu Duc',
        500,
        50,
        'Ngã tư Thủ Đức, Phường Linh Chiểu, TP. Thủ Đức, TP.HCM',
        10000.00
    ),
    (
        'Tram Quan 7',
        550,
        500,
        'Trung tâm thương mại SC VivoCity, Phường Tân Phong, Quận 7, TP.HCM',
        10000.00
    ),
    (
        'Tram Nha Be',
        600,
        600,
        'Bến phà Bình Khánh, Huyện Nhà Bè, TP.HCM',
        10000.00
    );
-- ----------------------------------------------------------------
-- Bảng TUYEN
CREATE TABLE tuyen (
    matuyen INT NOT NULL AUTO_INCREMENT,
    trambatdau INT NOT NULL,
    tramketthuc INT NOT NULL,
    thoigian INT NOT NULL,
    trangthai VARCHAR(255) NOT NULL,
    PRIMARY KEY (matuyen),
    CONSTRAINT FK_tuyen_trambatdau FOREIGN KEY (trambatdau) REFERENCES tram(matram) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_tuyen_tramketthuc FOREIGN KEY (tramketthuc) REFERENCES tram(matram) ON DELETE NO ACTION ON UPDATE NO ACTION
);
INSERT INTO tuyen (trambatdau, tramketthuc, thoigian, trangthai)
VALUES (1, 2, 40, 'Đang hoạt động'),
    (2, 3, 35, 'Đang hoạt động'),
    (1, 4, 50, 'Đang hoạt động'),
    (1, 7, 45, 'Đang hoạt động'),
    (7, 6, 55, 'Đang hoạt động'),
    (7, 5, 20, 'Đang hoạt động'),
    (6, 8, 25, 'Đang hoạt động'),
    (1, 9, 30, 'Đang hoạt động'),
    (9, 10, 20, 'Đang hoạt động');
-- ----------------------------------------------------------------
-- Bảng TAU
CREATE TABLE tau (
    matau INT NOT NULL AUTO_INCREMENT,
    soghe INT NOT NULL,
    trangthaitau VARCHAR(100) NOT NULL,
    ngaynhap DATE NOT NULL,
    chiphitau DOUBLE DEFAULT 0,
    isVisible BOOLEAN NOT NULL DEFAULT 1,
    PRIMARY KEY (matau)
);
INSERT INTO tau (soghe, trangthaitau, ngaynhap, chiphitau)
VALUES (50, 'Đang hoạt động', '2025-01-01', 10000.00),
    (20, 'Đang bảo trì', '2025-01-02', 10000.00),
    (100, 'Ngừng hoạt động', '2025-01-02', 10000.00),
    (25, 'Đang hoạt động', '2025-01-03', 10000.00),
    (40, 'Đang hoạt động', '2025-01-04', 10000.00),
    (60, 'Đang hoạt động', '2025-02-10', 10000.00),
    (80, 'Đang bảo trì', '2025-02-15', 10000.00),
    (55, 'Ngừng hoạt động', '2025-03-01', 10000.00),
    (30, 'Đang hoạt động', '2025-03-10', 10000.00);
-- ----------------------------------------------------------------
-- Bảng TUYEN_TRAM
CREATE TABLE tuyen_tram (
    matram INT NOT NULL,
    matuyen INT NOT NULL,
    thutu INT NOT NULL,
    PRIMARY KEY (matram, matuyen),
    CONSTRAINT FK_tuyen_tram_matram FOREIGN KEY (matram) REFERENCES tram(matram) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_tuyen_tram_matuyen FOREIGN KEY (matuyen) REFERENCES tuyen(matuyen) ON DELETE NO ACTION ON UPDATE NO ACTION
);
INSERT INTO tuyen_tram (matram, matuyen, thutu)
VALUES (1, 1, 1),
    (2, 1, 2),
    (3, 1, 3),
    (4, 2, 1),
    (5, 2, 2),
    (6, 2, 3),
    (7, 3, 1),
    (8, 3, 2),
    (9, 3, 3),
    (10, 4, 1);
-- ----------------------------------------------------------------
-- Bảng LICHTRINH
CREATE TABLE lichtrinh (
    machuyen INT NOT NULL AUTO_INCREMENT,
    manv INT NOT NULL,
    matau INT NOT NULL,
    matuyen INT NOT NULL,
    huongdi BOOLEAN NOT NULL,
    thoigiankh DATETIME NOT NULL,
    tgdenthucte DATETIME NOT NULL,
    trangthailichtrinh VARCHAR(100) NOT NULL,
    PRIMARY KEY (machuyen),
    CONSTRAINT FK_lichtrinh_nhanvien FOREIGN KEY (manv) REFERENCES nhanvien(manv) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_lichtrinh_tau FOREIGN KEY (matau) REFERENCES tau(matau) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_lichtrinh_tuyen FOREIGN KEY (matuyen) REFERENCES tuyen(matuyen) ON DELETE NO ACTION ON UPDATE NO ACTION
);
INSERT INTO lichtrinh (
        manv,
        matau,
        matuyen,
        huongdi,
        thoigiankh,
        tgdenthucte,
        trangthailichtrinh
    )
VALUES (
        2,
        1,
        1,
        1,
        '2019-04-01 09:00:00',
        '2019-04-01 09:00:00',
        'Chờ khởi hành'
    ),
    (
        5,
        2,
        1,
        1,
        '2018-04-02 10:00:00',
        '2018-04-02 10:10:00',
        'Đang khởi hành'
    ),
    (
        4,
        3,
        1,
        1,
        '2025-04-03 11:00:00',
        '2025-04-03 11:25:00',
        'Hoàn Thành'
    ),
    (
        2,
        1,
        1,
        1,
        '2025-04-04 07:15:00',
        '2025-04-04 07:45:00',
        'Chờ khởi hành'
    ),
    (
        7,
        2,
        1,
        1,
        '2025-08-05 12:00:00',
        '2025-08-05 12:00:00',
        'Đang khởi hành'
    ),
    (
        3,
        4,
        1,
        1,
        '2018-08-06 13:00:00',
        '2018-08-06 13:25:00',
        'Hoàn Thành'
    ),
    (
        1,
        3,
        1,
        1,
        '2020-04-07 14:00:00',
        '2020-04-07 14:30:00',
        'Chờ khởi hành'
    ),
    (
        6,
        4,
        1,
        1,
        '2025-04-08 16:00:00',
        '2025-04-08 16:20:00',
        'Đang khởi hành'
    ),
    (
        5,
        1,
        1,
        1,
        '2025-11-09 06:45:00',
        '2025-11-09 07:10:00',
        'Hoàn Thành'
    ),
    (
        4,
        3,
        1,
        0,
        '2022-12-01 06:00:00',
        '2022-12-01 06:05:00',
        'Đang khởi hành'
    ),
    (
        2,
        5,
        2,
        1,
        '2016-04-10 08:00:00',
        '2016-04-10 08:45:00',
        'Hoàn Thành'
    ),
    (
        3,
        5,
        2,
        1,
        '2017-09-11 08:00:00',
        '2017-09-11 08:50:00',
        'Đang khởi hành'
    ),
    (
        4,
        6,
        3,
        0,
        '2025-04-12 09:00:00',
        '2025-04-12 09:40:00',
        'Hoàn Thành'
    ),
    (
        5,
        6,
        4,
        0,
        '2024-04-13 10:00:00',
        '2024-04-13 10:35:00',
        'Hoàn Thành'
    ),
    (
        1,
        7,
        5,
        1,
        '2024-02-14 07:30:00',
        '2024-02-14 08:00:00',
        'Hoàn Thành'
    );
-- ----------------------------------------------------------------
-- Bảng VETAU
CREATE TABLE vetau (
    mave INT NOT NULL AUTO_INCREMENT,
    machuyen INT NOT NULL,
    makh INT NOT NULL,
    giave DOUBLE NOT NULL,
    PRIMARY KEY (mave),
    CONSTRAINT FK_mave_lichtrinh FOREIGN KEY (machuyen) REFERENCES lichtrinh(machuyen) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT FK_mave_khachhang FOREIGN KEY (makh) REFERENCES khachhang(makh) ON DELETE NO ACTION ON UPDATE NO ACTION
);
INSERT INTO vetau (machuyen, makh, giave)
VALUES (3, 1, 85000.00),
    (3, 2, 21000.00),
    (3, 3, 137500.50),
    (3, 4, 9000.00),
    (3, 5, 23000.00),
    (3, 6, 15000.00),
    (6, 7, 7450.00),
    (6, 8, 18000.00),
    (6, 9, 23900.00),
    (6, 10, 7100.00),
    (6, 11, 20000.00),
    (6, 12, 16000.00),
    (6, 13, 9500.00),
    (6, 14, 22200.00),
    (6, 15, 123500.00),
    (9, 16, 14000.00),
    (9, 17, 23500.00),
    (9, 18, 10000.00),
    (9, 19, 18800.00),
    (9, 20, 7800.00),
    (10, 1, 9500.00),
    (10, 2, 15000.00),
    (10, 3, 13500.00),
    (10, 4, 14500.00),
    (11, 5, 13000.00),
    (11, 6, 9000.00),
    (11, 7, 8700.00),
    (11, 8, 9200.00),
    (12, 9, 16000.00),
    (12, 10, 200000.00);
-- ----------------------------------------------------------------
-- Bảng LICHBAOTRI
CREATE TABLE lichbaotri (
    mabaotri INT NOT NULL AUTO_INCREMENT,
    matau INT NOT NULL,
    ngaybaotri DATE NOT NULL,
    trangthaibaotri VARCHAR(100) NOT NULL,
    ngaytao DATETIME NOT NULL,
    chiphibaotri DOUBLE NOT NULL,
    PRIMARY KEY (mabaotri),
    CONSTRAINT FK_baotri_matau FOREIGN KEY (matau) REFERENCES tau(matau) ON DELETE NO ACTION ON UPDATE NO ACTION
);
INSERT INTO lichbaotri (
        matau,
        ngaybaotri,
        trangthaibaotri,
        ngaytao,
        chiphibaotri
    )
VALUES (
        1,
        '2025-03-23',
        'Đang bảo trì',
        '2025-03-22 15:45:00',
        20000.00
    ),
    (
        2,
        '2025-03-22',
        'Hoàn thành',
        '2025-03-21 15:45:00',
        20000.00
    ),
    (
        3,
        '2025-03-21',
        'Đang bảo trì',
        '2025-03-22 15:45:00',
        20000.00
    ),
    (
        4,
        '2025-03-20',
        'Chờ kiểm tra',
        '2025-03-22 15:45:00',
        20000.00
    ),
    (
        6,
        '2025-03-27',
        'Chờ kiểm tra',
        '2025-03-26 19:00:00',
        20000.00
    ),
    (
        7,
        '2025-03-28',
        'Đang bảo trì',
        '2025-03-27 20:00:00',
        20000.00
    );