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
 
 **Diagrama de Navegación**
 
 - A partir del tercer nivel solo podrán acceder los usuarios, o los visitantes que quieran registrarse como usuarios.
 + A partir del cuarto nivel, solo los usuarios que son profesores podrán tener acceso a los formularios de adición de contenido.
 

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/DiagramaNavegaci%C3%B3n.png?raw=true)

**API**

[API.md](API.md)




