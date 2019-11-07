#!/bin/bash

#格式化文件sed -i 's/\r$//' xxx.sh

echo "下载软件"
wget http://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.2/binaries/apache-maven-3.6.2-bin.tar.gz

echo "解压缩到指定目录"
tar -zxv -f apache-maven-3.6.2-bin.tar.gz -C /usr/local/

echo "设置环境变量"
echo "#!/bin/bash" >> /etc/profile.d/maven.sh
echo "export MAVEN_HOME=/usr/local/apache-maven-3.6.2" >> /etc/profile.d/maven.sh
echo "export PATH=$MAVEN_HOME/bin:$PATH" >> /etc/profile.d/maven.sh

source /etc/profile.d/maven.sh

echo "请手动运行 source /etc/profile"
echo "验证mvn -version"
