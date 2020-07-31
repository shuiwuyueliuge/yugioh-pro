#!/bin/bash

CURRENT_FILE="$(pwd)"
echo -e "\033[33m 当前文件夹:${CURRENT_FILE} \033[0m"
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
mkdir -p ${DOCKER_VOLUMES}
cd ${DOCKER_VOLUMES}
mkdir -p ${DOCKER_VOLUMES}/redis/conf ${DOCKER_VOLUMES}/redis/data ${DOCKER_VOLUMES}/nexus3/nexus-data ${DOCKER_VOLUMES}/mongo/data ${DOCKER_VOLUMES}/mongo/config ${DOCKER_VOLUMES}/mysql/conf ${DOCKER_VOLUMES}/mysql/logs ${DOCKER_VOLUMES}/mysql/data ${DOCKER_VOLUMES}/nginx/conf ${DOCKER_VOLUMES}/nginx/logs ${DOCKER_VOLUMES}/logstash/log ${DOCKER_VOLUMES}/logstash/config/conf.d ${DOCKER_VOLUMES}/es/config ${DOCKER_VOLUMES}/es/plugins ${DOCKER_VOLUMES}/es/data ${DOCKER_VOLUMES}/es/logs ${DOCKER_VOLUMES}/vsftpd
cd ${CURRENT_FILE}
cp redis.conf ${DOCKER_VOLUMES}/redis/conf
cp my.cnf ${DOCKER_VOLUMES}/mysql/conf
cp default.conf ${DOCKER_VOLUMES}/nginx/conf
cp elasticsearch.yml ${DOCKER_VOLUMES}/es/config
cp logstash.yml ${DOCKER_VOLUMES}/logstash/log
cp logstash-yugioh.conf ${DOCKER_VOLUMES}/logstash/config/conf.d
cp logstash-yugioh.json ${DOCKER_VOLUMES}/logstash/config/conf.d
chmod -Rf 777 ${DOCKER_VOLUMES}

sysctl -w vm.max_map_count=262144

docker network create ygonetwork

# 运行 docker-compose
echo -e "\033[33m 安装 docker-compose \033[0m"
curl -L https://get.daocloud.io/docker/compose/releases/download/1.24.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
docker-compose version
echo -e "\033[33m 运行 docker-compose.yml \033[0m"
docker-compose up -d
docker ps