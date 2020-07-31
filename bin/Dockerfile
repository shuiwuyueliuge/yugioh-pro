FROM logstash:7.1.1
RUN logstash-plugin install logstash-input-jdbc
CMD ["-f", "/usr/share/logstash/conf.d/logstash-yugioh.conf"]