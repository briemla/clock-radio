#!/bin/sh
echo "Pulling changes from git"
git pull

echo "Building clock-radio using maven"
mvn clean compile test assembly:single && { echo 'Successfully build clock-radio' ; } || { echo 'Building clock-radio failed' ; exit 1; }

echo "Deploying artifacts to clock-radio.local"
scp target/clock-radio-0.0.1-SNAPSHOT-jar-with-dependencies.jar clock-radio:/home/pi/clock-radio/

scp clock-radio.sh clock-radio:/home/pi/clock-radio/

echo "Restarting clock radio software"
ssh clock-radio ./restart-clock-radio.sh
 
