# this dockerfile needs to be in project root directory
FROM bellsoft/liberica-openjdk-alpine:latest
# add curl and jq to check the grid status
RUN apk add curl jq
# define workdir, otherwise it will be root
WORKDIR /home/selenium-docker
# add all the necesssary files to the workdir
ADD target/docker-resources ./
ADD runner.sh runner.sh

# environment variables
# BROWSER
# HUB_HOST
# TEST_SUITE
# THREAD_COUNT

# add the entrypoint to run the tests
# ENTRYPOINT java -cp 'libs/*' -Dselenium-grid-enabled=true -Dselenium-grid-hubhost=${HUB_HOST} -Dbrowser=${BROWSER} org.testng.TestNG -threadcount ${THREAD_COUNT} test-suites/${TEST_SUITE}
ENTRYPOINT sh runner.sh

# run with environment variable and volume mapping from the command line.
# docker run -e BROWSER=firefox -e HUB_HOST=192.168.0.119 -e TEST_SUITE=FlightReservation.xml -e THREAD_COUNT=2 -v C:\Users\Baban\Docker-Workspaces\share\selenium-docker-result:/home/selenium-docker/test-output pseudofunc/selindoc


