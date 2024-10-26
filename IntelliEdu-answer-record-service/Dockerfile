FROM openjdk:8-jdk

# 创建应用目录
WORKDIR /app

# 复制 JAR 文件到容器中
COPY target/IntelliEdu-answer-record-service-0.0.1-SNAPSHOT.jar answer-record-service.jar

# 启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "answer-record-service.jar", "--spring.profiles.active=prod"]

# 打包：
# 1. 根目录执行 mvn clean install -DskipTests
# 2. cd IntelliEdu-answer-record-service && docker build -t cpuoverload/intelliedu-answer-record-service:latest .

# 运行：
# cd 到有 .env 的目录
# docker run --env-file .env -p 8084:8084 cpuoverload/intelliedu-answer-record-service:latest
