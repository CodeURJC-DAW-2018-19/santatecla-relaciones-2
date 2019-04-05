# Base Image
FROM node:8.15-alpine

WORKDIR $HOME/santatecla-relaciones-2/frontend/

# Copy App
COPY /src $HOME/santatecla-relaciones-2/frontend/
COPY /angular.json $HOME/santatecla-relaciones-2/frontend/
COPY /package.json $HOME/santatecla-relaciones-2/frontend/
COPY /package-lock.json $HOME/santatecla-relaciones-2/frontend/
COPY /proxy.conf.json $HOME/santatecla-relaciones-2/frontend/
COPY /tsconfig.json $HOME/santatecla-relaciones-2/frontend/

RUN npm install

# Expose port number
EXPOSE 4200

#  Run app
ENTRYPOINT npm start


