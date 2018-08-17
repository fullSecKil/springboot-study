FROM java:8
VOLUME /tmp
ADD docker-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENV JAVA_OPTS -Xmx768m -Djava.security.egd=file:/dev/./urandom
EXPOSE 9191
#指定执行启动spring boot小项目     ENTRYPOINT 为容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]