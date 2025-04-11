# 部署说明

## 环境要求

- Docker Engine 20.10+
- Docker Compose 2.0+

## 部署步骤

### 1. 准备环境变量文件

复制环境变量示例文件并编辑：

```bash
cp env-example .env
```

根据需要修改.env文件中的变量，特别是Nacos相关配置：

```
# Nacos配置
NACOS_SERVER=nacos.myimkit.com:8848
NACOS_USERNAME=nacos
NACOS_PASSWORD=abcdimkit8989a
NACOS_DISCOVERY_GROUP=DEFAULT_GROUP
NACOS_CONFIG_GROUP=DEFAULT_GROUP
```

### 2. 配置Docker Registry登录信息

设置以下环境变量：

```bash
export REGISTRY_USERNAME=您的用户名
export REGISTRY_PASSWORD=您的密码
```

### 3. 部署服务

使用标签部署（例如使用GitHub SHA作为标签）：

```bash
./deploy.sh 具体的标签
```

或者使用最新版本部署：

```bash
./deploy.sh
```

如果想要临时覆盖Nacos配置，可以在启动时指定：

```bash
NACOS_SERVER=192.168.1.100:8848 ./deploy.sh
```

### 4. 查看服务状态

```bash
docker-compose ps
```

### 5. 查看服务日志

```bash
docker-compose logs -f 服务名称
```

例如：

```bash
docker-compose logs -f im-gateway
```

## Host配置说明

docker-compose.yml已配置每个服务的extra_hosts，添加了以下主机映射：

```
nacos.myimkit.com:192.168.1.101
mysql.myimkit.com:192.168.1.101
redis.myimkit.com:192.168.1.101
```

这确保了容器内部可以通过这些域名访问对应的服务。

## Nacos配置说明

在docker-compose.yml中，每个服务都配置了Nacos相关的环境变量：

```yaml
environment:
  - SPRING_CLOUD_NACOS_SERVER_ADDR=${NACOS_SERVER:-nacos.myimkit.com:8848}
  - SPRING_CLOUD_NACOS_USERNAME=${NACOS_USERNAME:-nacos}
  - SPRING_CLOUD_NACOS_PASSWORD=${NACOS_PASSWORD:-abcdimkit8989a}
  - SPRING_CLOUD_NACOS_DISCOVERY_GROUP=${NACOS_DISCOVERY_GROUP:-DEFAULT_GROUP}
  - SPRING_CLOUD_NACOS_CONFIG_GROUP=${NACOS_CONFIG_GROUP:-DEFAULT_GROUP}
```

这些环境变量会覆盖应用程序中的@nacos.server@等占位符，使应用能够连接到指定的Nacos服务。

## 更新部署

当有新版本发布时，只需重新运行部署脚本即可：

```bash
./deploy.sh 新的标签
```

## 停止所有服务

```bash
docker-compose down
``` 