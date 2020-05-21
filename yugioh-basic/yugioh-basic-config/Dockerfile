# jdk 是一个已有的包含 jdk 的镜像
 FROM java:8
 
#  执行 java -jar 命令 （CMD：在启动容器时才执行此行。RUN：构建镜像时就执行此行）
ENTRYPOINT ["java","-jar","yugioh-basic-config.jar"]

ARG JAR_FILE
ADD ${JAR_FILE} /yugioh-basic-config.jar
 
#  设置对外端口为 9100
 EXPOSE 9100