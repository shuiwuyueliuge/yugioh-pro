#!/bin/bash

#卸载docker
echo -e "\033[33m 检查是否通过yum安装docker \033[0m"
YUM_DOCKER_LIST_RES="$(yum list installed | grep docker)"

DOCKER_LIST=(${YUM_DOCKER_LIST_RES// / })

if [ -n "${YUM_DOCKER_LIST_RES}" ];
then
  echo -e "\033[33m 开始卸载docker!!! \033[0m"
  for str in ${DOCKER_LIST[@]}
  do
    if [[ $str == docker* ]];
    then
      echo -e "\033[33m 正在卸载${str} \033[0m"
      yum -y remove ${str}
    fi
  done
  echo -e "\033[33m 删除[/var/lib/docker]镜像/容器 \033[0m"
  rm -rf /var/lib/docker
  rm -rf /usr/local/docker
fi

# 安装docker
echo -e "\033[33m yum安装docker \033[0m"
yum install -y docker
echo -e "\033[33m yum安装docker成功 \033[0m"
echo -e "\033[33m 启动docker服务 \033[0m"
systemctl start docker
echo -e "\033[33m registry-mirrors：https://t5t8q6wn.mirror.aliyuncs.com \033[0m"
sed -i '1d' /etc/docker/daemon.json
echo "{" >> /etc/docker/daemon.json
echo "    \"registry-mirrors\": [" >> /etc/docker/daemon.json
echo "        \"https://t5t8q6wn.mirror.aliyuncs.com\"" >> /etc/docker/daemon.json
echo "    ]" >> /etc/docker/daemon.json
echo "}" >> /etc/docker/daemon.json
systemctl restart docker

echo -e "\033[33m 创建docker挂载文件夹 -> /usr/local/docker \033[0m"
mkdir -p /usr/local/docker/redis/conf /usr/local/docker/redis/data /usr/local/docker/nexus3/nexus-data /usr/local/docker/mongo/data /usr/local/docker/mongo/config /usr/local/docker/mysql/conf /usr/local/docker/mysql/logs /usr/local/docker/mysql/data /usr/local/docker/nginx/conf /usr/local/docker/nginx/logs
cp redis.conf /usr/local/docker/redis/conf
cp my.cnf /usr/local/docker/mysql/conf
cp default.conf /usr/local/docker/nginx/conf
chmod 777 /usr/local/docker
chmod 777 /usr/local/docker/nexus3/nexus-data

echo -e "\033[33m redis,mongodb,mysql,rabbitmq,nexus3 下载镜像->创建容器->启动容器 \033[0m"
sysctl -w vm.max_map_count=262144
docker-compose up -d

docker ps