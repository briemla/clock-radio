#!/bin/sh
mvn clean compile assembly:single
scp target/clock-radio-0.0.1-SNAPSHOT-jar-with-dependencies.jar pi@clock-radio.local:/opt/clock-radio/
 