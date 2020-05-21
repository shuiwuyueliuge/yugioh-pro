#!/bin/bash

#格式化文件sed -i 's/\r$//' xxx.sh

echo "下载软件"

MAVEN_VERSION="apache-maven-3.6.1"

wget https://zysd-shanghai.oss-cn-shanghai.aliyuncs.com/software/linux/maven/${MAVEN_VERSION}-bin.tar.gz

echo "解压缩到指定目录"
tar -zxv -f ${MAVEN_VERSION}-bin.tar.gz -C /usr/local/

echo "设置环境变量"
echo "#!/bin/bash" >> /etc/profile.d/maven.sh
echo "export MAVEN_HOME=/usr/local/${MAVEN_VERSION}" >> /etc/profile.d/maven.sh
echo "export PATH=\$MAVEN_HOME/bin:\$PATH" >> /etc/profile.d/maven.sh

source /etc/profile.d/maven.sh

echo "请手动运行 source /etc/profile"
echo "验证mvn -version"
