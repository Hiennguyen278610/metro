# metro

## cách setup để kết nối databases
    *cài đặt mysql rồi tạo user and password
## sử dụng inteliJ,vscode
    1. mở database tool 
    2.ấn vào + thêm database tool -> chọn MySQL -> nhập user + password vào
    3.ấn download file về xong ấn test connect thử 
    4.trong file resources tạo 1 file config.properties xong copy nội dung trong file config.exam.properties qua 
    5.trong file config.properties thay đổi your_user + your_password của mình vào 
    6.ấn lưu sau đó vào file .gitignore kiểm tra xem có dòng này không ? src/main/resources/config.properties nếu 
    có thì ấn ctrl + s lại là được 

## note
    1.nếu kết nối thành công rồi thì thử git status xong kiểm tra cái src/main/resources/config.properties có hiện không
    nếu không thì không sao nếu có thì làm theo sau 
    b1: git rm --cached -f src/main/resources/config.properties
    b2 : git status lại kiểm tra xem src.main/resources/config.properties có hiện nữa không ? nếu không thì ok commit bth

    
