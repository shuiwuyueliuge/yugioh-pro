
				docker部署

**********************************************************************************************
				1.介绍
				    	文件目录：/bin/
				        	文件：docker-compose.yml
				            	说明：docker-compose安装redis，rabbitmq，nexus3，mongodb，mysql，nginx
				        	文件：docker-compose-run.sh
				            	说明：通过yml卸载/安装docker，新建挂载目录，执行docker-compose
				        	文件：docker-run.sh
				            	说明：系统启动运行docker
				        	文件：*.conf
				            	说明：docker挂载的配置文件
                      *注：以下目录是在宿主机的挂载目录
                      *例如：/usr/local/docker/nexus3/nexus-data/
                      *注：【里面的内容是说明，不是具体值】
                      *例如：【psw】为填写自己定义的密码
**********************************************************************************************
				2.nexus3使用配置
						帐号/密码：
							帐号admin，初始密码在/usr/local/docker/nexus3/nexus-data/admin.password
				        maven私服配置：
				        	    登陆并且修改密码后新建maven repository（proxy），name -> maven-aliyun，remote storage -> http://maven.aliyun.com/nexus/content/groups/public
				                        把maven-aliyun添加到maven-public中
				        docker镜像仓库配置：
				        	    新建docker repository（hosted）http -> 8082，
				        	勾选Allow anonymous docker pull ( Docker Bearer Token Realm required )，
				        	勾选Allow clients to use the V1 API to interact with this repository，
				        	勾选Validate that all content uploaded to this repository is of a MIME type appropriate for the repository format
				        	    新建docker repository（proxy）remote storage -> https://t5t8q6wn.mirror.aliyuncs.com，
				        	勾选Use Docker Hub，
				        	勾选Allow clients to use the V1 API to interact with this repository，
				        	勾选Validate that all content uploaded to this repository is of a MIME type appropriate for the repository format
				        	    新建docker repository（group）http -> 8083，
				        	勾选Allow anonymous docker pull ( Docker Bearer Token Realm required )，
				        	勾选Allow clients to use the V1 API to interact with this repository，
				                          勾选Validate that all content uploaded to this repository is of a MIME type appropriate for the repository format
				         配置本地setting.xml文件：
				                          <?xml version="1.0" encoding="UTF-8"?>
											<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" 
											    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
											
											    <localRepository>D:/repository</localRepository>
											    <servers>
											        <server>
											            <id>nexus-releases</id>
											            <username>admin</username>
											            <password>admin</password>
											        </server>
											        <server>
											            <id>nexus-snapshots</id>
											            <username>admin</username>
											            <password>admin</password>
											        </server>
											        <server>
											            <id>nexus-docker-registry</id>
											            <username>admin</username>
											            <password>admin</password>
											        </server>
											    </servers>
											    <mirrors>
											        <mirror>
											            <id>nexus</id>
											            <mirrorOf>*</mirrorOf>
											            <name>central-mirror</name>
											            <url>http://172.18.146.180:8081/repository/maven-public/</url>
											            <!--<url>http://121.43.153.227:8088/nexus/content/groups/public/</url>-->
											        </mirror>
											</mirrors>
											</settings>
				 使用mvn deploy把jar上传到nexus3：配置pom.xml
				                              	<distributionManagement>
													<repository>
														<!--这里的id需要和settings.xml中的server的id一致 -->
														<id>ygo-release</id>
														<name>Ygo release Repository</name>
														<url>http://127.0.0.1:8081/repository/ygo-release/</url>
													</repository>
													<snapshotRepository>
														<id>ygo-snapshots</id>
														<name>Ygo snapshots Repository</name>
														<url>http://127.0.0.1:8081/repository/ygo-snapshot/</url>
													</snapshotRepository>
												</distributionManagement>           
**********************************************************************************************
				3.mysql使用配置
						进入docker容器：docker exec -it mysql /bin/bash
						登录mysql,设置远程连接
						允许远程访问：ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root';
						           flush privileges;
**********************************************************************************************
				4.mongodb使用配置：
						进入容器：docker exec -it mongodb mongo admin
						创建管理角色的用户：db.createUser({ user: 'admin', pwd: 'admin', roles: [{ role: "userAdminAnyDatabase", db: "admin" }]});
				                验证用户：db.auth("admin","admin");
				                切换库：use 【your db】
				                创建读写权限用户：db.createUser({ user: '【user】', pwd: '【pwd】', roles: [{ role: "readWrite", db: "【your db】" }]});
**********************************************************************************************
				5.安装docker-compose
						cd /usr/local/bin/
						wget https://github.com/docker/compose/releases/download/1.14.0-rc2/docker-compose-Linux-x86_64
						rename docker-compose-Linux-x86_64 docker-compose docker-compose-Linux-x86_64
						chmod +x /usr/local/bin/docker-compose
						docker-compose version
**********************************************************************************************
				6.开机启动docker
						centos命令：crontab -e
						写入并保存：@reboot sh /home/docker-run.sh
**********************************************************************************************
				7.配置docker连接nexus3仓库：
						修改仓库地址：vi /etc/docker/daemon.json
						例如：nexus3的ip为172.18.146.180，docker-hosted端口是8082，docker-group端口是8083
						daemon.json修改为：
						{
						    "registry-mirrors": ["http://172.18.146.180:8083"],
						    "insecure-registries": ["172.18.146.180:8082","172.18.146.180:8083"]
						}
						重启docker：systemctl restart docker
						查看registry-mirrors：docker info
						docker设置远程访问：vi /lib/systemd/system/docker.service
						把ExecStart=/usr/bin/dockerd-current \修改为ExecStart=/usr/bin/dockerd-current -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock \
						重启docker：systemctl restart docker
**********************************************************************************************
				8.docker打包上传到nexus3
				        docker镜像格式：【ip】:【端口】/【镜像名称】:【版本号】
				                 登录nexus3:docker login 172.18.146.180:8082，输入账号密码
						例如：docker tag redis 172.18.146.180:8082/redis
						描述：把本地redis镜像标记为172.18.146.180:8082/redis:1.0
						上传tag：docker push 172.18.146.180:8082/redis:1.0
						docker查询镜像：不带版本号默认为latest。，不带【ip】:【端口】前缀的默认从docker.io查询。，从nexus3查询需要带有【ip】:【端口】，如果是latest则不需要，
						例如查询上面上传的redis镜像：docker search 172.18.146.180:8082/redis:1.0
**********************************************************************************************
				9.springboot打包成docker镜像发布到nexus3和docker-server（docker所在的服务器）
						编写Dockerfile：
										# 依赖的镜像（docker会自动pull）
										FROM java:8
										
										# 维护人
										MAINTAINER tester
										
										# 获取打包后的jar名称
										ARG JAR_FILE
										
										# 把jar复制到/home/
										ADD ${JAR_FILE} /home/app.jar
										
										# RUN 可以执行linux命令
										RUN mkdir -p /home/config/
										
										# 使用外部的配置文件
										ADD /src/main/resources/application.properties /home/config/
										
										# 执行 java -jar 命令 （CMD：在启动容器时才执行此行。RUN：构建镜像时就执行此行），指定配置文件地址。docker容器中默认的在/下运行jar
										# "-Djava.security.egd=file:/dev/./urandom"好像可以加快tomcat的启动速度（待测试）
										ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/home/app.jar","--spring.config.location=/home/config/application.properties"]
										 
										# 设置对外端口为 9300
										EXPOSE 9300
						配置pom：打包命令 -> mvn clean compile package docker:build -DpushImage -Dmaven.test.skip=true
						       <plugin>
				                <groupId>com.spotify</groupId>
				                <artifactId>docker-maven-plugin</artifactId>
				                <version>1.2.0</version>
				                <configuration>
				                    <!--打包docker镜像的docker服务器-->
				                    <dockerHost>http://172.18.146.180:2375</dockerHost>
				                    <!--镜像名称及版本[ip:port/name:tag]-->
				                    <imageName>172.18.146.180:8082/web-demo:1.1</imageName>
				                    <!--nexus3 hosted 仓库地址-->
				                    <registryUrl>172.18.146.180:8082</registryUrl>
				                    <buildArgs>
				                        <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
				                    </buildArgs>
				                    <!--Dockerfile路径-->
				                    <dockerDirectory>D:\program\demo\demo2</dockerDirectory>
				                    
				                    <!--不使用Dockerfile可以使用下面2个参数-->
									<!--<baseImage>java:8</baseImage>-->
									<!--<entryPoint>["java", "-jar", "/${project.build.finalName}.jar"]</entryPoint>-->
									
				                    <!--是否强制覆盖已有镜像-->
				                    <forceTags>true</forceTags>
				                    <imageTags>
				                        <!--镜像tag-->
				                        <imageTag>latest</imageTag>
				                    </imageTags>
				                    <!--复制jar包到docker容器指定目录配置-->
				                    <resources>
				                        <resource>
				                            <targetPath>/</targetPath>
				                            <directory>${project.build.directory}</directory>
				                            <include>${project.build.finalName}.jar</include>
				                        </resource>
				                    </resources>
				                    <!--在maven settings.xml中配置的server的id值-->
				                    <serverId>nexus-docker-registry</serverId>
				                </configuration>
				            </plugin>
**********************************************************************************************
				10.springboot容器和mysql容器通讯：
						改写地址：jdbc:mysql://127.0.0.1:3306/test 改为 jdbc:mysql://【mysql】:3306/test
						查看mysql容器的地址：docker inspect mysql，"Networks"中的第一个为mysql容器的net。例如：查到为home_default
						使用链接：docker run --name web-demo -p 9300:9300 --link mysql:mysql --net home_default  172.18.146.180:8082/web-demo:1.1
						        --link 【容器名称】:【mysql】
**********************************************************************************************