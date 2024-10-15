## Dependencies

| Dependency           | Version    |
|----------------------|------------|
| JDK                  | 1.8        |
| Spring Boot          | 2.7.6      |
| Spring Cloud         | 2021.0.5   |
| Spring Cloud Alibaba | 2021.0.5.0 |
| Nacos                | 2.2.0      |
| MySQL                | 8.3.0      |

## Notes

### Environmental Variables

You need to configure the environment variables for the database connection in the `application.yml` file in your system or IDE.

### Start Nacos

The Nacos version used in this project is `v2.2.0`. It should be compatible
with `spring-cloud-starter-alibaba-nacos-discovery` version `2021.0.5.0`.

If you are using macOS ARM chip (Apple Silicon), it is recommended to install Nacos by Docker slim
version (`nacos/nacos-server:v2.2.0-slim`). Otherwise, you may encounter compatibility issues.

```bash
docker pull nacos/nacos-server:v2.2.0-slim

docker run --name nacos-quick -e MODE=standalone -p 8848:8848 -p 9848:9848 -d nacos/nacos-server:v2.2.0-slim
```

You can refer to the following links for more details:

- [是否考虑提供一个apple芯片可以运行的docker image #11432](https://github.com/alibaba/nacos/issues/11432)
- [解决M1 无法使用nacos高版本(2.0.3)问题 #8674](https://github.com/alibaba/nacos/issues/8674#issuecomment-1173255720)
- [https://github.com/nacos-group/nacos-docker](https://github.com/nacos-group/nacos-docker)
- [https://hub.docker.com/r/nacos/nacos-server/tags](https://hub.docker.com/r/nacos/nacos-server/tags)

After starting Nacos, you can access the web interface at `http://localhost:8848/nacos`.

username: `nacos`  
password: `nacos`
