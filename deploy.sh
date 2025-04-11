#!/bin/bash

# 设置镜像标签，如果没有指定则使用latest
export TAG=${1:-latest}

# 登录Docker Registry
echo "登录Docker Registry..."
docker login ${DOCKER_REGISTRY} --username=$REGISTRY_USERNAME --password=$REGISTRY_PASSWORD

# 拉取最新镜像
echo "拉取最新镜像..."
docker-compose pull

# 启动服务
echo "启动服务..."
docker-compose up -d

echo "部署完成!" 