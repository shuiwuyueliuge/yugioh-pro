Զ�ֿ̲�:https://t5t8q6wn.mirror.aliyuncs.com
vi /etc/docker/daemon.json
{
    "registry-mirrors": [
        "https://t5t8q6wn.mirror.aliyuncs.com"
    ]
}

systemctl restart docker
##############################################################
���;���nexus��
1����¼
docker login http://xxxxx.com
2����¼˽��hub������Ŀ
   ������Ŀ�У�abc-dev
2���������tag
����docker tag 2e25d8496557 xxxxx.com/abc-dev/arc:1334
����2e25d8496557��IMAGE ID��������docker images �鿴
����xxxxx.com��˽��hub����
����abc-dev����Ŀ����
����arc����������
����1334������汾��
4������
����docker push xxxxx.com/abc-dev/arc:1334
################################################################
�޸�/etc/docker/daemon.json
{
 "registry-mirrors": ["https://t5t8q6wn.mirror.aliyuncs.com"],
 "insecure-registries": ["127.0.0.1:5000"],
 "disable-legacy-registry": true
}

systemctl daemon-reload
systemctl restart docker

5����װdocker-compose
cd /usr/local/bin/
wget https://github.com/docker/compose/releases/download/1.14.0-rc2/docker-compose-Linux-x86_64
rename docker-compose-Linux-x86_64 docker-compose docker-compose-Linux-x86_64
chmod +x /usr/local/bin/docker-compose
docker-compose version

6����������docker
crontab -e
@reboot sh /home/docker-run.sh