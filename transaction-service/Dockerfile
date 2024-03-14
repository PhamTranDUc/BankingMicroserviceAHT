FROM ubuntu:latest
LABEL authors="huy nguyen"

# Sử dụng image cơ bản có chứa Java 17 để chạy ứng dụng Spring Boot
FROM openjdk:17

# Thiết lập thư mục làm việc mặc định
WORKDIR /app

# Sao chép file JAR đã được build vào thư mục /app trong container
COPY target/transaction-service-0.0.1-SNAPSHOT.jar /app/your-application.jar

# Cổng mà ứng dụng Spring Boot lắng nghe (hãy đảm bảo cổng này được khai báo trong ứng dụng của bạn)
EXPOSE 9000

# Lệnh chạy ứng dụng Spring Boot khi container được khởi chạy
CMD ["java", "-jar", "your-application.jar"]


