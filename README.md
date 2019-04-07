# RELMAN #

## GRUPO 11 ##

* **Enrique Pina Boyer**
*e.pina.2016@alumnos.urjc.es*
*KikeAjani*

* **Óscar Gómez Ramírez**
*o.gomezr.2016@alumnos.urjc.es*
*ogomezr*

* **Diego Muñoz Martin**
*d.munozm.2016@alumnos.urjc.es*
*DiegoMzmn*

* **Francisco Javier Gutiérrez Sánchez**
*fj.gutierrezs.2016@alumnos.urjc.es*
*chicocoriano*


* **Jesús Horcajo Ortiz**
*j.horcajo.2016@alumnos.urjc.es*
*itsjach*

## INFO ##

[Vídeo Fase 4 Relman Youtube](https://youtu.be/py74H9wWN2o)

[Trello Grupo 11](https://trello.com/b/7mBmoTZa/daw11)

[Google Docs Grupo 11](https://docs.google.com/document/d/1dOFhYoHVeqbiS_8xWnMKT5sNLMF60pURrx-0x542xg8/edit)

## CAPTURAS FASE 2 ##

**Comunicación HTTP (Certificado Propio)**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/httpscertificate.PNG?raw=true)

**Menú Principal 1**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/menuprincipal1.PNG/)

**Menú Principal 2 (Páginado)**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/menuprincipal2.PNG?raw=true)

**Login**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/loginteacher.PNG?raw=true)

**Registro**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/registroUsuario.PNG?raw=true)

**Unidad Contexto**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/unidades.PNG?raw=true)

**Unidad Detalle**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/unidadesDetalle.PNG?raw=true)

**DIAGRAMAS UML**

**Diagrama de Clases BDD**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/DiagramaBDDF.PNG?raw=true)

**Diagrama de Clases App**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/diagramaClass.png?raw=true)

**Relaciones de Templates con sus Controllers**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/diagramaTemplates.PNG?raw=true)


**CONFIGURACIÓN DEL ENTORNO DE DESARROLLO**

 * Descargar e Instalar Spring Tools 4 Eclipse https://spring.io/tools
  * Una vez instalado  STS habrá que abrir el proyecto descargado, desde la carpeta "relman".
  * Ya creado el proyecto, a grandes rasgos, tendrás por un lado los paquetes de java en donde se almacenarán las clases controller, repositorios, etc.. que se utilizarán posteriormente. Por otro, los templates que compondrán las diferentes plantillas de la aplicación resultado de las peticiones realizadas por los usuarios a los diferentes controladores, y los ficheros estáticos (JS, CSS, HTML). Finamente, el fichero pom.xml que estará compuesto por las dependencias (librerias) que se importarán a la app para usarlas.
  -  Para ejecutar el proyecto Spring, habrá que irse a la clase application y lanzarlo como una Spring Boot App. A la hora de ejecutarse, las peticiones se escucharán por el puerto 8443: [https://localhost:8443/](https://localhost:8443/)

  * La aplicación se apoya en mysql para las bases de datos. Aquí su descarga: [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/)
  * Para ello hay que pulsar sobre el Windows msi installer
  * Importante tener generado el esquema en el MySQL workbench, con nombre "relman". Además, la contraseña del usuario "root" deberá ser "pass". Si su contraseña fuera diferente, la podrá cambiar en el fichero .properties situado en la carpeta "resources" del proyecto.


- Descargar Graphviz para visualizar correctamente los uml correspondientes al contexto y jerarquías de las entidades de la aplicación.
 [https://www.graphviz.org/](https://www.graphviz.org/)

 **DESCARGA DE DOCKER**

 * [Linux](https://docs.docker.com/v17.12/install/)
 * [Windows/MacOS](https://www.docker.com/products/docker-desktop)

 **EJECUCIÓN CON DOCKER**

 *	Se deberán colocar los archivos docker-compose.yml y generate_image.sh, que se encuentran en la carpeta docker, dentro de la carpeta raíz del repositorio. Asimismo, el archivo dockerfile se deberá ubicar dentro de la carpeta relman. De esta forma, nos aseguraremos de una correcta ejecución.
 *	En primer lugar, hay que ejecutar el script generate_image.sh con bash, de esta manera, si se realiza algún cambio en el código, se puede volver a compilar el proyecto.
 *	A través de la Shell, nos situaremos en la localización /relman, tras la que procedemos a ejecutar los comandos
 *	Ejecutaremos los comandos siguientes:
	  * docker-compose build
	  * docker-compose up


 **Diagrama de Navegación**

 - A partir del tercer nivel solo podrán acceder los usuarios, o los visitantes que quieran registrarse como usuarios.
 + A partir del cuarto nivel, solo los usuarios que son profesores podrán tener acceso a los formularios de adición de contenido.


![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/DiagramaNavegaci%C3%B3n.png?raw=true)

**API**

[API.md](API.md)

**Diagrama de Clases y Templates Angular**

 * Los componentes HTML irán de color naranja
 * Los componentes TS irán de color azul, las relaciones que se pueden apreciar entre diagrama serán entre este tipo de componentes
 * Los servicios TS irán de color verde
 * Los CSS irán de color morado
 * Los modelos TS irán de color rosa
 
 
 ![Screenshot]( https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/DiagramaClasesTemplateAngular.PNG?raw=true)
 
 **Instrucciones de ejecución con Angular**
 
 1. Descargar e instalar [Node.js y npm](https://nodejs.org/en/)
 2. Descargar e instalar Angular CLI: Poner `npm install -g @angular/cli` en la consola de comandos
 3. Descargar e instalar [Visual Studio Code](https://code.visualstudio.com/)
 4. Abrir el proyecto Angular de la aplicación (carpeta frontend)
 5. Abrir el terminal dentro de VSC y poner `npm install` y `npm install rxjs-compat`
 6. Poner `npm start` en el terminal para abrir la app en el puerto 4200 (Se debe ejecutar junto con el proyecto Spring)
 
* Si se quiere usar la aplicación dentro del proyecto Spring poner `npm build` y copiar los contenido de la carpeta dist en /relman/src/main/resources/static/new/. La app será accesible desde localhost:8080/new/.
* También se pueden seguir las instrucciones de ejecución de Docker para ejecutarla app entera dentro del contenedor.
