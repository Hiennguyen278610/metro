-- Xóa các bảng theo thứ tự phụ thuộc (nếu đã tồn tại)
DROP TABLE IF EXISTS lichbaotri;
DROP TABLE IF EXISTS vetau;
DROP TABLE IF EXISTS lichtrinh;
DROP TABLE IF EXISTS tuyen_tram;
DROP TABLE IF EXISTS tuyen;
DROP TABLE IF EXISTS tram;
DROP TABLE IF EXISTS tau;
DROP TABLE IF EXISTS nhanvien;
DROP TABLE IF EXISTS taikhoan;
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
    PRIMARY KEY (makh)
);

INSERT INTO khachhang (tenkh, sdt, solan) VALUES
    ('Nguyễn Văn Anh', '0387913347', 1),
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
    PRIMARY KEY (manhomquyen)
);

INSERT INTO nhomquyen (tennhomquyen) VALUES
    ('Quản lí hệ thống'),
    ('Nhân viên soát vé'),
    ('Nhân viên thống kê'),
    ('Nhân viên điều hành'),
    ('Kế toán');

-- ----------------------------------------------------------------
-- Bảng NHOMCHUCNANG
CREATE TABLE nhomchucnang (
    machucnang INT NOT NULL AUTO_INCREMENT,
    tenchucnang VARCHAR(100) NOT NULL,
    PRIMARY KEY (machucnang)
);

INSERT INTO nhomchucnang (tenchucnang) VALUES
    ('Quản lý khách hàng'),
    ('Quản lý nhân viên'),
    ('Quản lý nhóm quyền'),
    ('Quản lý tài khoản'),
    ('Thống kê báo cáo');

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

INSERT INTO chitietquyen (manhomquyen, machucnang, hanhdong) VALUES
    (1, 1, 'create'), (1, 1, 'delete'), (1, 1, 'update'), (1, 1, 'view'),
    (1, 2, 'create'), (1, 2, 'delete'), (1, 2, 'update'), (1, 2, 'view'),
    (1, 3, 'create'), (1, 3, 'delete'), (1, 3, 'update'), (1, 3, 'view'),
    (1, 4, 'create'), (1, 4, 'delete'), (1, 4, 'update'), (1, 4, 'view'),
    (1, 5, 'create'), (1, 5, 'delete'), (1, 5, 'update'), (1, 5, 'view');

-- ----------------------------------------------------------------
-- Bảng NHANVIEN
CREATE TABLE nhanvien (
    manv INT NOT NULL AUTO_INCREMENT,
    tennv VARCHAR(100) NOT NULL,
    sodienthoai VARCHAR(15) NOT NULL,
    gioitinh VARCHAR(50) NOT NULL,
    chucvu VARCHAR(100) NOT NULL,
    PRIMARY KEY (manv)
);

INSERT INTO nhanvien (tennv, sodienthoai, gioitinh, chucvu) VALUES
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

INSERT INTO taikhoan (manv, matkhau, manhomquyen, trangthai) VALUES
    (1, '0000', 1, 1),
    (2, '0000', 2, 1),
    (3, '0000', 3, 1);

-- ----------------------------------------------------------------
-- Bảng TRAM
CREATE TABLE tram (
    matram INT NOT NULL AUTO_INCREMENT,
    tentram VARCHAR(255) NOT NULL,
    x INT NOT NULL,
    y INT NOT NULL,
    PRIMARY KEY (matram)
);

INSERT INTO tram (tentram, x, y) VALUES
    ('Tram Ben Thanh', 100, 100),
    ('Tram Sai Gon', 300, 100),
    ('Tram Hoa Hung', 500, 100),
    ('Tram Tan Binh', 700, 100),
    ('Tram Phu Nhuan', 900, 100),
    ('Tram Go Vap', 100, 300),
    ('Tram Binh Thanh', 300, 300),
    ('Tram Thu Duc', 500, 300),
    ('Tram Quan 7', 700, 300),
    ('Tram Nha Be', 900, 300);

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

INSERT INTO tuyen (trambatdau, tramketthuc, thoigian, trangthai) VALUES
    (1, 2, 40, 'Hoat dong'),
    (3, 4, 35, 'Hoat dong'),
    (5, 6, 50, 'Hoat dong'),
    (7, 8, 45, 'Hoat dong'),
    (9, 10, 55, 'Hoat dong');

-- ----------------------------------------------------------------
-- Bảng TAU
CREATE TABLE tau (
    matau INT NOT NULL AUTO_INCREMENT,
    soghe INT NOT NULL,
    trangthaitau VARCHAR(100) NOT NULL,
    ngaynhap DATE NOT NULL,
    PRIMARY KEY (matau)
);

INSERT INTO tau (soghe, trangthaitau, ngaynhap) VALUES
    (50, 'Đang hoạt động', '2025-01-01'),
    (20, 'Đang bảo trì', '2025-01-02'),
    (100, 'Ngừng hoạt động', '2025-01-02'),
    (25, 'Đang hoạt động', '2025-01-03');

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

INSERT INTO tuyen_tram (matram, matuyen, thutu) VALUES
    (1, 1, 1), (2, 1, 2), (3, 1, 3),
    (4, 2, 1), (5, 2, 2), (6, 2, 3),
    (7, 3, 1), (8, 3, 2), (9, 3, 3),
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

INSERT INTO lichtrinh (manv, matau, matuyen, huongdi, thoigiankh, tgdenthucte, trangthailichtrinh) VALUES
    (2, 1, 1, 1, '2025-04-01 09:00:00', '2025-04-01 09:30:00', 'Chờ khởi hành'),
    (5, 2, 1, 1, '2025-04-02 10:00:00', '2025-04-02 10:20:00', 'Đang khởi hành'),
    (4, 3, 1, 1, '2025-04-03 11:00:00', '2025-04-03 11:25:00', 'Hoàn Thành'),
    (2, 1, 1, 1, '2025-04-04 07:15:00', '2025-04-04 07:45:00', 'Chờ khởi hành'),
    (7, 2, 1, 1, '2025-04-05 12:00:00', '2025-04-05 12:30:00', 'Đang khởi hành'),
    (3, 4, 1, 1, '2025-04-06 13:00:00', '2025-04-06 13:25:00', 'Hoàn Thành'),
    (1, 3, 1, 1, '2025-04-07 14:00:00', '2025-04-07 14:30:00', 'Chờ khởi hành'),
    (6, 4, 1, 1, '2025-04-08 16:00:00', '2025-04-08 16:20:00', 'Đang khởi hành'),
    (5, 1, 1, 1, '2025-04-09 06:45:00', '2025-04-09 07:10:00', 'Hoàn Thành'),
    (4, 3, 1, 0, '2025-05-01 06:00:00', '2025-05-01 06:05:00', 'Đang khởi hành');

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

INSERT INTO vetau (machuyen, makh, giave) VALUES
    (3, 1, 8500.00), (3, 2, 21000.00), (3, 3, 13750.50), (3, 4, 9000.00), (3, 5, 23000.00), (3, 6, 15000.00),
    (6, 7, 7450.00), (6, 8, 18000.00), (6, 9, 23900.00), (6, 10, 7100.00), (6, 11, 20000.00), (6, 12, 16000.00),
    (6, 13, 9500.00), (6, 14, 22200.00), (6, 15, 12350.00),
    (9, 16, 14000.00), (9, 17, 23500.00), (9, 18, 10000.00), (9, 19, 18800.00), (9, 20, 7800.00);

-- ----------------------------------------------------------------
-- Bảng LICHBAOTRI
CREATE TABLE lichbaotri (
    mabaotri INT NOT NULL AUTO_INCREMENT,
    matau INT NOT NULL,
    ngaybaotri DATE NOT NULL,
    trangthaibaotri VARCHAR(100) NOT NULL,
    ngaytao DATETIME NOT NULL,
    PRIMARY KEY (mabaotri),
    CONSTRAINT FK_baotri_matau FOREIGN KEY (matau) REFERENCES tau(matau) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO lichbaotri (matau, ngaybaotri, trangthaibaotri, ngaytao) VALUES
    (1, '2025-03-23', 'Đang bảo trì', '2025-03-22 15:45:00'),
    (2, '2025-03-22', 'Hoàn thành', '2025-03-21 15:45:00'),
    (3, '2025-03-21', 'Đang bảo trì', '2025-03-22 15:45:00'),
    (4, '2025-03-20', 'Chờ kiểm tra', '2025-03-22 15:45:00');