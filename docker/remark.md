Զ�ֿ̲�:https://t5t8q6wn.mirror.aliyuncs.com
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