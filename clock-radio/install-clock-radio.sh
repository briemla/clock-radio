#!/bin/bash

installDabpi () {
echo "Installing dabpi_ctl"
wget https://github.com/elmo2k3/dabpi_ctl/raw/master/install_on_fresh_raspbian.sh 
chmod a+x install_on_fresh_raspbian.sh 
./install_on_fresh_raspbian.sh
echo "dabpi_ctl installed"
}

installJava () {
echo "Installing java"

echo "java installed"
}

createServiceFile () {
serviceFile=tempClockRadioService

echo "#! /bin/sh" > ${serviceFile}
echo "# /etc/init.d/clock-radio" >> ${serviceFile}
echo "" >> ${serviceFile}
echo "touch /var/lock/clock-radio" >> ${serviceFile}
echo "" >> ${serviceFile}
echo "case \"\$1\" in" >> ${serviceFile}
echo "start)" >> ${serviceFile}
echo "echo \"Starting Clock-Radio ... \"" >> ${serviceFile}
echo "/home/pi/clock-radio/clock-radio.sh >/var/log/clock-radio.log &" >> ${serviceFile}
echo ";;" >> ${serviceFile}
echo "stop)" >> ${serviceFile}
echo "echo \"Killing Clock-Radio ...\"" >> ${serviceFile}
echo "killall java" >> ${serviceFile}
echo ";;" >> ${serviceFile}
echo "*)" >> ${serviceFile}
echo "echo \"Usage: /etc/init.d/clock-radio {start|stop}\"" >> ${serviceFile}
echo "exit 1" >> ${serviceFile}
echo ";;" >> ${serviceFile}
echo "esac" >> ${serviceFile}
echo "exit 0" >> ${serviceFile}
}

initService () {
serviceFile=/etc/init.d/clock-radio
sudo mv tempClockRadioService ${serviceFile}
sudo chmod 755 ${serviceFile}
sudo update-rc.d clock-radio defaults
}

setupService () {
echo "Setting up clock-radio service"
createServiceFile
initService
echo "Service set up"
}

installMaven () {
sudo apt-get -y install maven
sudo apt-get -y install openjdk-8-jdk
}

installFxmlTemplateLoader () {
mkdir -p templateLoader
cd templateLoader
#git clone https://github.com/briemla/de.briemla.fxmltemplateloader.git .
cd fxml-template-loader
pwd
mvn test install
}

installFxmlTemplateLoader
