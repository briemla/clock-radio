#!/bin/sh
echo "Pulling changes from git"
git pull

echo "Building clock-radio using maven"
mvn clean compile assembly:single && { echo 'Successfully build clock-radio ; } || { echo 'Building clock-radio failed' ; exit 1; }
echo "Deploying artifacts to clock-radio.local"
scp target/clock-radio-0.0.1-SNAPSHOT-jar-with-dependencies.jar pi@clock-radio.local:/opt/clock-radio/
 