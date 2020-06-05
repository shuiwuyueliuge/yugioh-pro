#!/bin/bash

CURRENT_FILE="$(pwd)"

# 卸载 docker
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

# 安装 docker
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

DOCKER_VOLUMES="/usr/local/docker"

echo -e "\033[33m 创建docker挂载文件夹 -> ${DOCKER_VOLUMES} \033[0m"
#mkdir -p /usr/local/docker/redis/conf /usr/local/docker/redis/data /usr/local/docker/nexus3/nexus-data /usr/local/docker/mongo/data /usr/local/docker/mongo/config /usr/local/docker/mysql/conf /usr/local/docker/mysql/logs /usr/local/docker/mysql/data /usr/local/docker/nginx/conf /usr/local/docker/nginx/logs
mkdir ${DOCKER_VOLUMES}
cd ${DOCKER_VOLUMES}
mkdir -p /redis/conf /redis/data /nexus3/nexus-data /mongo/data /mongo/config /mysql/conf /mysql/logs /mysql/data /nginx/conf /nginx/logs /logstash/log /logstash/config /es/config /es/plugins /es/data /es/logs
cd ${CURRENT_FILE}
cp redis.conf ${DOCKER_VOLUMES}/redis/conf
cp my.cnf ${DOCKER_VOLUMES}/mysql/conf
cp default.conf ${DOCKER_VOLUMES}/nginx/conf
chmod 666 ${DOCKER_VOLUMES}/*

sysctl -w vm.max_map_count=262144

docker network create esnetwork

# 运行 docker-compose
echo -e "\033[33m 安装 docker-compose \033[0m"
cd /usr/local/bin/
wget https://github.com/docker/compose/releases/download/1.14.0-rc2/docker-compose-Linux-x86_64
rename docker-compose-Linux-x86_64 docker-compose docker-compose-Linux-x86_64
chmod +x /usr/local/bin/docker-compose
docker-compose version
cd ${CURRENT_FILE}
echo -e "\033[33m 运行 docker-compose.yml \033[0m"
docker-compose up -d
docker ps