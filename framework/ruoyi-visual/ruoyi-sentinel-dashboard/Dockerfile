# 贝尔实验室 Spring 官方推荐镜像 JDK下载地址 https://bell-sw.com/pages/downloads/
FROM bellsoft/liberica-openjdk-debian:17.0.11-cds
#FROM bellsoft/liberica-openjdk-debian:21.0.3-cds
#FROM findepi/graalvm:java17-native



RUN mkdir -p /ruoyi/sentinel-dashboard/logs \
    /ruoyi/skywalking/agent

WORKDIR /ruoyi/sentinel-dashboard

ENV LANG=C.UTF-8 LC_ALL=C.UTF-8 JAVA_OPTS=""

EXPOSE 8718

ADD ./target/ruoyi-sentinel-dashboard.jar ./app.jar

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom \
           #-Dskywalking.agent.service_name=ruoyi-sentinel-dashboard \
           #-javaagent:/ruoyi/skywalking/agent/skywalking-agent.jar \
           ${JAVA_OPTS} -jar app.jar
