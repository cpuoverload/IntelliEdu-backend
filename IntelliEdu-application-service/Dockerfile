FROM openjdk:8-jdk

# 创建应用目录
WORKDIR /app

# 复制 JAR 文件到容器中
COPY target/IntelliEdu-application-service-0.0.1-SNAPSHOT.jar application-service.jar

# 启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "application-service.jar", "--spring.profiles.active=prod"]

# 打包：
    # 1. 根目录执行 mvn clean install -DskipTests
    # 2. cd IntelliEdu-application-service
    # 3.1 打包成 linux amd64 架构
        # docker buildx build --platform linux/amd64 -t cpuoverload/intelliedu-application-service:latest .
    # 3.2 如果是 mac M 系列芯片，产物是 arm 架构，无法在服务器上运行
        # docker build -t cpuoverload/intelliedu-application-service:latest .

# 运行：
    # cd 到有 .env 的目录
    # docker run --env-file .env -p 8082:8082 cpuoverload/intelliedu-application-service:latest
