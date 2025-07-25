* Dự án Spring Boot Todo App
Ứng dụng quản lý công việc cá nhân (TodoApp) sử dụng Spring Boot. Hướng dẫn này giúp build và chạy ứng dụng bằng Docker Compose.

* Yêu cầu
Docker
Docker Compose: Thường đã kèm sẵn với Docker Desktop

* Cấu trúc thư mục
.
├── Dockerfile
├── docker-compose.yml
├── target/
│   └── prj1-0.0.1-SNAPSHOT.jar
├── src/
├── pom.xml
└── README.md

* Cách chạy ứng dụng bằng Docker Compose
1. Build project bằng Maven
Trước tiên, build ứng dụng Java thành file JAR:
./mvnw clean package

Hoặc trên Windows PowerShell:
.\mvnw.cmd clean package
Sau khi build xong, file JAR sẽ nằm trong thư mục target/.

2. Khởi động bằng Docker Compose
docker-compose up --build
* Lệnh này sẽ:
  - Build image Docker từ Dockerfile 
  - Khởi động container theo cấu hình trong docker-compose.yml

Đảm bảo đã có target/prj1-0.0.1-SNAPSHOT.jar trước khi chạy.

3. Truy cập ứng dụng
Mặc định, ứng dụng sẽ chạy tại:
http://localhost:8080

Swagger UI (nếu có):
http://localhost:8080/swagger-ui/index.html

Chạy Unit Test (tuỳ chọn)
Có thể chạy test trước khi build image, dùng Maven:

./mvnw test
Dọn dẹp Docker
Sau khi không dùng nữa, có thể tắt container:
docker-compose down
 
* Lưu ý
Nếu sử dụng cơ sở dữ liệu như MySQL/PostgreSQL, hãy bổ sung service tương ứng trong docker-compose.yml.
Đảm bảo port 8080 chưa bị chiếm.

