# Base Image
FROM openjdk:8-jre-alpine

RUN apk add --update graphviz ttf-ubuntu-font-family && \
	mkdir /dot


WORKDIR $HOME/santatecla-relaciones-2/relman/

# Copy App
COPY /target/relman-1.0.2.jar $HOME/santatecla-relaciones-2/relman/
COPY images $HOME/santatecla-relaciones-2/relman/images

# Expose port number
EXPOSE 8080 

#  Run app
CMD java -jar relman-1.0.2.jar


