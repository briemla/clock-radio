#!/bin/bash
cd `dirname $0`
echo "Execute start script"
#java -Xdebug -Xrunjdwp:transport=dt_socket,address=8001,server=y,suspend=y -jar clock-radio-0.0.1-SNAPSHOT-jar-with-dependencies.jar
java -jar clock-radio-0.0.1-SNAPSHOT-jar-with-dependencies.jar >& clock-radio.log
