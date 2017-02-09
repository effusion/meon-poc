oc new-app ssh://git@stash.six-group.net:22/~txh9o/prototyps.git --context-dir=springmongo/src/main/base/  --name=rhel-meon-java8 --strategy=docker

oc import-image meon-cd-dev/spring-mongo  --from=artifactory.six-group.net/meon-cd-dev/spring-mongo:latest --confirm

oc tag artifactory.six-group.net/meon-cd-dev/spring-mongo:latest meon-cd-dev/spring-mongo:latest --scheduled=true

docker push artifactory.six-group.net/meon-cd-dev/spring-mongo:latest

docker push artifactory.six-group.net/meon-cd-dev/springconfigserver:latest

oc import-image meon-cd-dev/springconfigserver  --from=artifactory.six-group.net/meon-cd-dev/springconfigserver:latest --confirm