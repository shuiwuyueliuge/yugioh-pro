# 依赖的镜像（docker会自动pull）
FROM java:8
# 维护人
MAINTAINER yugioh-pro

# 获取打包后的jar名称
ARG JAR_FILE

ADD ${JAR_FILE} /home/yugioh-basic-registry.jar
RUN mkdir -p /home/config/
ADD /src/main/resources/application.properties /home/config/
#  执行 java -jar 命令 （CMD：在启动容器时才执行此行。RUN：构建镜像时就执行此行）

ENTRYPOINT ["java","-jar","/home/yugioh-basic-registry.jar","--spring.config.location=/home/config/application.properties"]
 
#  设置对外端口为 9000
EXPOSE 9000