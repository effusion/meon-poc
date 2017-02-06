FROM artifactory.six-group.net/rhel7:latest

MAINTAINER Andreas Heubeck No Reply andreas.heubeck@six-group.com

ENV TZ Europe/Zurich
ENV TERM xterm
USER root

# Install Java
RUN yum install -y -q bind-utils java-1.8.0-openjdk-devel && \
    yum clean all

