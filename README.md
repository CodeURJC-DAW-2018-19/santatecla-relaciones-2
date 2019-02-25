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

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/httpscertificate.PNG?raw=true)

**Menú Principal 1**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/menuprincipal1.PNG/)

**Menú Principal 2 (Páginado)**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/menuprincipal2.PNG?raw=true)

**Login**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/loginteacher.PNG?raw=true)

**Unidad Contexto**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/unidades.PNG?raw=true)

**Unidad Detalle**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/unidadesDetalle.PNG?raw=true)

**DIAGRAMAS UML**

**Diagrama de Clases BDD**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/DiagramaBDDF.PNG?raw=true)

**Diagrama de Clases App**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/diagramaClass.PNG?raw=true)

**Relaciones de Templates con sus Controllers**

![Screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-relaciones-2/blob/master/capturas/diagramaTemplates.PNG?raw=true)


**CONFIGURACIÓN DEL ENTORNO DE DESARROLLO**

-Descargar e Instalar Spring Boot https://spring.io/projects/spring-boot
  * Una vez instalado el Spring Boot habrá que crear un proyecto Spring Starter Project (File >> New >> Spring Starter Project)
  * Y ahí podrás darle el nombre que quieras al proyecto, que la URL del servicio sea https://start.spring.io, que sea de tipo Maven, que se compartimente con paquetes .jar y que se implemente en lenguaje Java
  *Dándole a finalizar, ya tendrás tu proyecto Spring creado
  *Ya creado el proyecto, a grandes rasgos, tendrás por un lado los paquetes de java en donde se almacenarán las clases controller, repositorios, etc.. que se utilizarán posteriormente, por otro,  los templates que compondrán las diferentes plantillas de la aplicación
resultado de las peticiones realizadas por los usuarios a los diferentes controladores, y por otro, el fichero pom.xml que estará compuesto por las dependencias (librerias) que se importarán a la app para usarlas
  *Y para ejecutar un proyecto Spring, habrá que irse a la clase application y lanzarlo como una Spring Boot App. a la hora de ejecutarse, las peticiones se escucharán por el puerto 8080 por defecto, pero se podrá cambiar en las application properties de la app.
  
-Descargar mysql para la migración de h2 a mysql https://dev.mysql.com/downloads/mysql/
  *Para ello hay que pulsar sobre el Windows msi installer
  * Y una vez instalado y ya en Spring, habrá que cambiar la dependencia correspondiente a h2 por la de mysql
  
-Descargar Graphviz para visualizar correctamente los uml correspondientes al contexto y jerarquías de las entidades de la aplicación
 https://www.graphviz.org/








