FROM artifactory.six-group.net/meon-cd-dev/rhel-meon-java8:latest

EXPOSE 8888

ENV JAVA_OPTS=""

USER root

RUN useradd -r -u 997 -m -c "meon" -d "/opt/app/" -s /bin/false meon && \
    chown -R meon:root  /opt/app/

ADD springconfigserver-1.1-SNAPSHOT.jar /opt/app/app.jar
RUN chown -R meon:root  /opt/app/ && chmod -R g+rw /opt/app/ &&\
    find /opt/app/ -type d -exec chmod g+x {} +

USER 997

#ENTRYPOINT ["sh"]

CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /opt/app/app.jar" ]