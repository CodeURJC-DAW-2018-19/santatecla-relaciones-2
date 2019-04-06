#! /bin/bash
cd /Users/KIKEPINA\ 1/Desktop/UNIVERSIDAD2/DAWPractica/santatecla-relaciones-2/frontend
docker run --rm --name angular-cli -v ${PWD}:/frontend -w /frontend node:8.15.1 /bin/bash -c "npm install -g @angular/cli; npm install; npm rebuild; ng build --prod --baseHref=/new/"
cd dist
mv my-app /Users/KIKEPINA\ 1/Desktop/UNIVERSIDAD2/DAWPractica/santatecla-relaciones-2/relman/src/main/resources/static
cd /Users/KIKEPINA\ 1/Desktop/UNIVERSIDAD2/DAWPractica/santatecla-relaciones-2/santatecla-relaciones-2/relman/src/main/resources/static
sudo rm -r new
mv my-app new
cd /Users/KIKEPINA\ 1/Desktop/UNIVERSIDAD2/DAWPractica/santatecla-relaciones-2/relman
sudo docker run -it --rm --name my-maven-project -v "$(pwd)":/relman -w /relman maven:3.3-jdk-8 mvn install

#sudo docker build -t ogomezr/relmanapp .
#cd $HOME/DAW/
#sudo docker login
#sudo docker push ogomezr/relmanapp
cd

