远程仓库:https://t5t8q6wn.mirror.aliyuncs.com
vi /etc/docker/daemon.json
{
    "registry-mirrors": [
        "https://t5t8q6wn.mirror.aliyuncs.com"
    ]
}

systemctl restart docker
##############################################################
推送镜像到nexus：
1、登录
docker login http://xxxxx.com
2、登录私有hub创建项目
   例如项目叫：abc-dev
2、给镜像打tag
　　docker tag 2e25d8496557 xxxxx.com/abc-dev/arc:1334
　　2e25d8496557：IMAGE ID，可以用docker images 查看
　　xxxxx.com：私有hub域名
　　abc-dev：项目名称
　　arc：镜像名称
　　1334：镜像版本号
4、推送
　　docker push xxxxx.com/abc-dev/arc:1334
################################################################
修改/etc/docker/daemon.json
{
 "registry-mirrors": ["https://t5t8q6wn.mirror.aliyuncs.com"],
 "insecure-registries": ["127.0.0.1:5000"],
 "disable-legacy-registry": true
}

systemctl daemon-reload
systemctl restart docker

5、安装docker-compose
cd /usr/local/bin/
wget https://github.com/docker/compose/releases/download/1.14.0-rc2/docker-compose-Linux-x86_64
rename docker-compose-Linux-x86_64 docker-compose docker-compose-Linux-x86_64
chmod +x /usr/local/bin/docker-compose
docker-compose version

6、开启启动docker
crontab -e
@reboot sh /home/docker-run.sh