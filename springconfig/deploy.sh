#!/bin/bash

BASE_DIR=/c/local/git-repos/final-prototyp
SPRING_CONFIG_DIR=$BASE_DIR/springconfig/server

function build_config_server(){
    cd $SPRING_CONFIG_DIR
    echo Building config server
    mvn clean package docker:build -U
    docker push artifactory.six-group.net/meon-cd-dev/springconfigserver:latest
    oc login https://mpzhlosnod01.standard.six-group.net:8443 --token=$1
    oc project meon-cd-dev
    oc import-image meon-cd-dev/springconfigserver  --from=artifactory.six-group.net/meon-cd-dev/springconfigserver:latest --confirm
}

if [ ! -z $1 ] ; then
    build_config_server $1
else
    echo "Auth token not present"
fi
