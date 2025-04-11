#!/bin/bash

# 设置镜像标签，如果没有指定则使用latest
export TAG=${1:-latest}

# 检测docker compose命令类型
if command -v docker-compose &> /dev/null; then
    DOCKER_COMPOSE_CMD="docker-compose"
elif docker compose version &> /dev/null; then
    DOCKER_COMPOSE_CMD="docker compose"
else
    echo "错误: 未找到docker-compose或docker compose命令，请先安装Docker Compose"
    exit 1
fi

# 登录Docker Registry
echo "登录Docker Registry..."
docker login ${DOCKER_REGISTRY} --username=$REGISTRY_USERNAME --password=$REGISTRY_PASSWORD

# 拉取最新镜像
echo "拉取最新镜像..."
$DOCKER_COMPOSE_CMD pull

# 启动服务
echo "启动服务..."
$DOCKER_COMPOSE_CMD up -d

echo "部署完成!" 