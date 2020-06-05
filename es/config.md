kibana 配置:
kibana.yml添加配置信息
server.port: 8888
server.host: "127.0.0.1"
elasticsearch.url: "http://127.0.0.1:9200"

logstash 使用jdbc同步数据 配置:
pipelines.yml 打开如下配置
 - pipeline.id: test
   pipeline.workers: 1
   pipeline.batch.size: 1
   config.string: "input { generator {} } filter { sleep { time => 1 } } output { stdout { codec => dots } }"
 - pipeline.id: another_test
   queue.type: persisted
   path.config: "/tmp/logstash/*.config"
   
Gemfile修改source为 "https://gems.ruby-china.com"
logstash-plugin.bat 添加javahome路径  SET JAVA_HOME=H:\jdk8
logstash bin目录下运行 logstash-plugin install --no-verify logstash-input-jdbc
新建logstash-yugioh.conf文件
运行 logstash.bat -f logstash-yugioh.conf