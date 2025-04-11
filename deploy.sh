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

# 停止并删除现有容器
echo "停止并删除现有容器..."
$DOCKER_COMPOSE_CMD down

# 清理任何可能残留的旧容器
echo "清理残留容器..."
containers=$(docker ps -a --filter "name=im-gateway|im-core-server|ruoyi-auth|ruoyi-system" --format "{{.Names}}")
if [ ! -z "$containers" ]; then
    echo "发现残留容器: $containers"
    
    # 先优雅地停止容器
    echo "正在停止容器..."
    docker stop $containers 2>/dev/null || true
    
    # 确认容器已停止后再删除
    echo "正在删除容器..."
    docker rm $containers 2>/dev/null || true
fi

# 强制重新创建启动服务
echo "启动服务..."
$DOCKER_COMPOSE_CMD up -d --force-recreate

echo "部署完成!" 