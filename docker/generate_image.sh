#! /bin/bash

cd $HOME/santatecla-relaciones-2/relman
sudo docker run -it --rm --name my-maven-project -v "$(pwd)":/relman -w /relman maven:3.3-jdk-8 mvn install

#sudo docker build -t ogomezr/relmanapp .
#cd $HOME/DAW/
#sudo docker login
#sudo docker push ogomezr/relmanapp
cd


