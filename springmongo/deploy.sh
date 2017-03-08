#!/bin/bash

BASE_DIR=/c/local/git-repos/final-prototyp
SPRING_MONGO_DIR=$BASE_DIR/springmongo

# $1 auth token
function build_config_server(){
    cd $SPRING_MONGO_DIR
    echo Building config server
    mvn clean package docker:build -U
    docker push artifactory.six-group.net/meon-cd-dev/spring-mongo:latest
    oc login https://mpzhlosnod01.standard.six-group.net:8443 --token=$1
    oc project meon-cd-dev
    oc import-image meon-cd-dev/spring-mongo --from=artifactory.six-group.net/meon-cd-dev/spring-mongo:latest --confirm
}
if [ ! -z $1 ] ; then
    build_config_server $1
else
    echo "Auth token not present"
fi

