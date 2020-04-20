#!/bin/bash

# 安装docker
echo 'docker install start'
yum install -y docker
echo 'docker install end'

echo 'docker service start'
systemctl start docker

# 安装rabbitmq
echo 'rabbitmq install start'
docker pull rabbitmq:management
echo 'run rabbitmq 15672->15672 5672->5672'
docker run -d --hostname my-rabbit --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:management

# 安装redis
echo 'redis install start'
docker pull redis
mkdir -p /usr/local/docker/redis/conf
mkdir -p /usr/local/docker/redis/data
docker run -p 6379:6379 --name redis --privileged=true -v /usr/local/docker/redis/conf/redis.conf:/etc/redis/redis.conf -v /usr/local/docker/redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes

