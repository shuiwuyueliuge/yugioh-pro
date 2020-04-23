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

# 安装rabbitmq
echo -e "\033[33m docker下载rabbitmq镜像 \033[0m"
docker pull rabbitmq:management
echo -e "\033[33m 创建并运行rabbitmq容器 15672->15672 5672->5672 \033[0m"
docker run -d --hostname my-rabbit --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:management

# 安装redis
echo -e "\033[33m docker下载redis镜像 \033[0m"
docker pull redis
echo -e "\033[33m 拷贝redis配置文件到[/usr/local/docker/redis/conf] \033[0m"
mkdir -p /usr/local/docker/redis/conf
mkdir -p /usr/local/docker/redis/data
cp redis.conf /usr/local/docker/redis/conf
echo -e "\033[33m 创建并运行redis容器 6379->6379 \033[0m"
docker run -p 6379:6379 --name redis --privileged=true -v /usr/local/docker/redis/conf/redis.conf:/etc/redis/redis.conf -v /usr/local/docker/redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes

# 安装nexus3
echo -e "\033[33m docker下载nexus3镜像 \033[0m"
docker pull docker.io/sonatype/nexus3
echo -e "\033[33m 创建nexus3 data文件夹 \033[0m"
mkdir -p /usr/local/docker/nexus3/nexus-data
chmod 777 /usr/local/docker/nexus3/nexus-data
echo -e "\033[33m 创建并运行nexus3容器 8081->8081 \033[0m"
docker run -d --name nexus3 --privileged=true -p 8081:8081 -v /usr/local/docker/nexus3/nexus-data:/nexus-data sonatype/nexus3
#######################################################################################################
#新建远程仓库http://maven.aliyun.com/nexus/content/groups/public
#######################################################################################################

# 安装mongodb
echo -e "\033[33m docker下载mongodb镜像 \033[0m"
docker pull mongo
echo -e "\033[33m 创建mongo data文件夹 \033[0m"
mkdir -p /usr/local/docker/mongo/data
chmod 777 /usr/local/docker/mongo/data
mkdir -p /usr/local/docker/mongo/config
chmod 777 /usr/local/docker/mongo/config
echo -e "\033[33m 创建并运行mongodb容器 27017->27017 \033[0m"
docker run -p 27017:27017 -e TZ="Asia/Shanghai" -v /usr/local/docker/mongo/config:/data/configdb -v /usr/local/docker/mongo/data:/data/db --privileged=true --name mongodb -d mongo --auth
#########################################################################################################
#echo -e "\033[33m 进入mongodb容器创建帐号 account->admin pwd->admin role->admin \033[0m"
#docker exec -it mongodb mongo admin
#db.createUser({ user: 'admin', pwd: 'admin', roles: [{ role: "userAdminAnyDatabase", db: "admin" }]});
#db.auth("admin","admin");
#exit
#use your db
#创建帐号 权限readWrite
##########################################################################################################

# 安装mysql
echo -e "\033[33m docker下载mysql镜像 \033[0m"
docker pull mysql
echo -e "\033[33m 创建mysql -> conf log data文件夹 \033[0m"
mkdir -p /usr/local/docker/mysql/conf /usr/local/docker/mysql/logs /usr/local/docker/mysql/data
cp my.cnf /usr/local/docker/mysql/conf
echo -e "\033[33m 创建并运行mysql容器 3306->3306 初始密码root \033[0m"
docker run -p 3306:3306 -e TZ="Asia/Shanghai" --name mysql --privileged=true -v /usr/local/docker/mysql/conf:/etc/mysql/conf.d -v /usr/local/docker/mysql/logs:/logs -v /usr/local/docker/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql
#########################################################################################################
#docker exec -it mysql /bin/bash
#登录mysql,设置远程连接
#ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root';
#flush privileges;
##########################################################################################################
docker ps




docker run --name nginx -d -p 8080:80 -v /usr/local/docker/nginx/conf:/etc/nginx/conf.d --privileged=true  -v /usr/local/docker/nginx/logs:/var/log/nginx nginx