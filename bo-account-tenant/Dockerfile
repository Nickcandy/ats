FROM shc-itsmax-harbor.hpeswlab.net/itsmax-bo/master/java:8-jdk-alpine
VOLUME /tmp
ADD account-tenant-service-0.2.0-SNAPSHOT.war app.war
RUN sh -c 'touch /app.war'
ENV JAVA_OPTS=" -Xmx128m -Xss256k -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=31927"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.war" ]
EXPOSE 31027 31927
