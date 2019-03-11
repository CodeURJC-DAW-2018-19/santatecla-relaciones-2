# DOCUMENTACIÓN API REST

## PARA USAR LA API REST
1. Descargar [Postman] (https://www.getpostman.com/)
2. Solo utilizaremos los métodos GET, POST y DELETE para interactuar con los diferentes recursos de nuestra aplicación web
## Operaciones de nuestra API
En
Todas las consultas a la API vendrán siempre precedidas por el tipo de conexión que se le realiza a la misma https://locahost:8443 seguido
de /api y la url al contenido que se quiera solicitar

## Autenticación
Conjunto de solicitudes que tendrán que ver con la autenticación del usuario en nuestra API

### Login
* ##### Descripción
Esta operación permite al usuario iniciar la sesión
* ##### URL:
	< /login >
* ##### Operaciones: 
	GET
* ##### Consulta:
		/api/logIn
* ##### Respuesta en caso de éxito:
	```
	{
		"id":60,
		"name":"teacher",
		"passwordHash":"$2a$10$3ZpGinZjKryIXd0GvPvd1.uFM9E1TUnXkKB.UUoUhi5BzO0JM9fXK",
		"roles":["ROLE_USER","ROLE_ADMIN"]
	}
	
	```

* ##### Respuesta en caso de error:
	  Código de Error : 401 No Autorizado
			
		
### Logout
* ##### Descripción
Esta operación permite al usuario desconectar su cuenta 
* ##### URL:
	< /logOut >
* ##### Operaciones: 
	GET
* ##### Consulta:
		/api/logOut
* ##### Respuesta en caso de éxito
	true

* ##### Respuesta en caso de error:
	  Código de Error : 401 No Autorizado
		
		
		
		
		
		
		
		
		
		
## Solicitudes de Unidades
Conjunto de solicitudes que tendrán que ver con los recursos de las unidades que se manejan en la aplicación

### Mostrar todas las unidades
* ##### Descripción
Esta operación permite mostrar todas las unidades que se tienen
* ##### URL:
		< /units >
* ##### Operaciones: 
		GET
* ##### Consulta:
		/api/units
* ##### Respuesta en caso de éxito
	```
		{
    "content": [
        {
            "name": "HTML",
            "photoComp": false,
            "photoClas": false
        },
        {
            "name": "HTML2.1",
            "photoComp": false,
            "photoClas": false
        },
        {
            "name": "HTML4.0",
            "photoComp": false,
            "photoClas": false
        },
        {
            "name": "CSS",
            "photoComp": false,
            "photoClas": false
        },
        {
            "name": "JAVASCRIPT",
            "photoComp": false,
            "photoClas": false
        },
        {
            "name": "API_REST",
            "photoComp": false,
            "photoClas": false
        },
        {
            "name": "SPRING",
            "photoComp": false,
            "photoClas": false
        },
        {
            "name": "ANGULAR",
            "photoComp": false,
            "photoClas": false
        },
        {
            "name": "XML",
            "photoComp": false,
            "photoClas": false
        },
        {
            "name": "SGML",
            "photoComp": false,
            "photoClas": false
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 10,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "totalElements": 25,
    "last": false,
    "totalPages": 3,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 10,
    "first": true,
    "empty": false
}
		
### Mostrar una unidad
* ##### Descripción
Esta operación nos permite mostrar una unidad en concreto
* ##### URL:
	< /unit/{unitName} >
* ##### Parámetros de la URL: 
	unitName = String
* ##### Operaciones: 
	GET
* ##### Consulta:
		/api/unit/HTML
* ##### Respuesta en caso de éxito
	```
	{
    "name": "HTML",
    "photoComp": true,
    "photoClas": true
}

* ##### Respuesta en caso de error:
	  Código de Error : 404 NOT FOUND (Para cuando no se puede encontrar la unidad que se pide)
		
### Mostrar la imagen de una unidad
* ##### Descripción
Esta operación nos permite mostrar el diagrama uml que se quiera de una unidad en concreto
* ##### URL:
	< /unit/{unitName}/image/{typeImage} >
* ##### Parámetros de la URL: 
	unitName = String
	typeImage = String (describe el diagrama uml que querramos de la unidad)
* ##### Operaciones: 
	GET
* ##### Consulta:
		/api/unit/HTML/image/context
* ##### Respuesta en caso de éxito
	Te devuelve la imagen del diagrama pedido, si se ejecuta en postman se podrá visualizar la imagen pedida

* ##### Respuesta en caso de error:
	  Código de Error : 404 NOT FOUND (Para cuando no se puede encontrar la unidad que se pide o cuando no se pueda encontrar el tipo
		de diagrama uml que se pide)
		
### Creación de una Unidad
* ##### Descripción
Esta operación nos permite crear una unidad
* ##### URL:
	< /unit >
* ##### Operaciones: 
	POST
* ##### Consulta:
		/api/unit
* ##### Formulario en Postman:
	```
	{
    "name": "Fortnite",
    "photoComp": true,
    "photoClas": true
}

* ##### Respuesta en caso de éxito
	```
	{
    "name": "Fortnite",
    "photoComp": true,
    "photoClas": true
}

* ##### Respuesta en caso de error:
	  Código de Error : 409 CONFLICT (Para cuando se quiere crear una unidad que está creada)
		
### Borrar Unidad
* ##### Descripción
Esta operación nos permite borrar una unidad
* ##### URL:
	< /unit/{unitName} >
* ##### Operaciones: 
	DELETE
* ##### Consulta:
		/api/unit/HTML

* ##### Respuesta en caso de éxito
	```
	{
    "name": "HTML",
    "photoComp": true,
    "photoClas": true
}

* ##### Respuesta en caso de error:
	  Código de Error : 404 NOT FOUND (Para cuando no se puede encontrar la unidad que se quiere borrar)
		
		
		
		
		
		
			
## Solicitudes de Fichas
Todas las consultas realizadas en este apartado vienen precedidas de /api/unit/{unitName} mencionados anteriormente


### Mostrar Fichas
* ##### Descripción
Esta operación nos permite mostrar todas las fichas

* ##### URL:
		< /cards >
	
* ##### Parámetros de la URL
		unitName = String
* ##### Operaciones: 
		GET
* ##### Consulta:
		/api/unit/CSS/cards
* ##### Respuesta en caso de éxito
	```
		{
    "content": [
        {
            "type": "Por que",
            "unitAsoc": {
                "name": "CSS",
                "photoComp": false,
                "photoClas": false
            },
            "photo": false,
            "desc": "Porque es facil de aprender y de usar"
        },
        {
            "type": "Cuando",
            "unitAsoc": {
                "name": "CSS",
                "photoComp": false,
                "photoClas": false
            },
            "photo": false,
            "desc": "CSS se lanzó inicialmente en 1996"
        },
        {
            "type": "Como",
            "unitAsoc": {
                "name": "CSS",
                "photoComp": false,
                "photoClas": false
            },
            "photo": false,
            "desc": "En internet hay muchos tutoriales para aprender a como usar css"
        },
        {
            "type": "Para que",
            "unitAsoc": {
                "name": "CSS",
                "photoComp": false,
                "photoClas": false
            },
            "photo": false,
            "desc": "Para representar informacion de manera usando estilos"
        },
        {
            "type": "Quien",
            "unitAsoc": {
                "name": "CSS",
                "photoComp": false,
                "photoClas": false
            },
            "photo": false,
            "desc": "Lenguaje de hoja de estilos"
        },
        {
            "type": "Donde",
            "unitAsoc": {
                "name": "CSS",
                "photoComp": false,
                "photoClas": false
            },
            "photo": false,
            "desc": "World Wide Web Consortium"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 10,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "totalElements": 6,
    "last": true,
    "totalPages": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 6,
    "first": true,
    "empty": false
}
	
				
### Obtener Ficha
* ##### Descripción
Esta operación nos permite obtener una ficha dentro de la unidad
* ##### URL:
		< /card/{type} >
* ##### Operaciones: 
		POST
* ##### Parámetros de la URL
		unitName = String
		type = String (describe el tipo de ficha que le pasemos)
* ##### Consulta:
		/api/unit/CSS/card/Como
* ##### Respuesta en caso de éxito
	```
		{
    "type": "Como",
    "unitAsoc": {
        "name": "CSS",
        "photoComp": false,
        "photoClas": false
    },
    "photo": false,
    "desc": "En internet hay muchos tutoriales para aprender a como usar css"
}
	
* ##### Respuesta en caso de error:
	  Código de Error : 404 NOT FOUND (Para cuando no se encuentra la ficha que se quiere devolver de la unidad que se ha
		especificado)
		
### Crear Ficha
* ##### Descripción
Esta operación nos permite crear una ficha dentro de una unidad
* ##### URL:
		< /card >
* ##### Operaciones: 
		POST
* ##### Parámetros de la URL
		unitName = String
		type = String (describe el tipo de ficha que le pasemos)
* ##### Consulta:
		/api/unit/CSS/card
		
* ##### Formulario Postman:
	```
	{
    "type": "Finalidad",
    "unitAsoc": {
        "name": "HTML",
        "photoComp": false,
        "photoClas": false
    },
    "photo": false,
    "desc": "Hacer el desarrollo web mas facil"
}

* ##### Respuesta en caso de éxito
		```
		{
    "type": "Finalidad",
    "unitAsoc": {
        "name": "HTML",
        "photoComp": false,
        "photoClas": false
    },
    "photo": false,
    "desc": "Hacer el desarrollo web mas facil"
}
	
* ##### Respuesta en caso de error:
	  Código de Error : 409 CONFLICT (Para cuando se intenta crear una ficha que ya está creada)
		
		
### Borrar Ficha
* ##### Descripción
Esta operación nos permite borrar una ficha de la unidad especificada
* ##### URL:
		< /card/{type} >
* ##### Operaciones: 
		GET
* ##### Parámetros de la URL
		unitName = String
		type = String (describe el tipo de ficha que le pasemos)
* ##### Consulta:
		/api/unit/CSS/card/Como
		
* ##### Respuesta en caso de éxito
	```
		{
    "type": "Como",
    "unitAsoc": {
        "name": "CSS",
        "photoComp": false,
        "photoClas": false
    },
    "photo": false,
    "desc": "En internet hay muchos tutoriales para aprender a como usar css"
}
	
* ##### Respuesta en caso de error:
	  Código de Error : 404 NOT FOUND (Para cuando no se encuentra la ficha)
		
### Obtener Imagen de Ficha
* ##### Descripción
Esta operación nos permite obtener la imagen de la ficha de la unidad especificada
* ##### URL:
		< /card/{type}/image >
* ##### Operaciones: 
		GET
* ##### Parámetros de la URL
		unitName = String
		type = String (describe el tipo de ficha que le pasemos)
* ##### Consulta:
		/api/unit/CSS/card/Quien/image
		
* ##### Respuesta en caso de éxito
		
		En postman muestra la imagen solicitada por pantalla
		
* ##### Respuesta en caso de error:
	  Código de Error : 404 NOT FOUND (Para cuando no se encuentra la imagen en la ficha)
		
		
### Subir Imagen
* ##### Descripción
Esta operación nos permite subir la imagen a la ficha especificada
* ##### URL:
		< /card/{type}/image >
* ##### Operaciones: 
		POST
* ##### Parámetros de la URL
		unitName = String
		type = String (describe el tipo de ficha que le pasemos)
* ##### Consulta:
		/api/unit/CSS/card/Quien/image
* ##### Respuesta en caso de éxito
		Sube la imagen a la ficha correspondiente, en el postman, habrá que irse al apartado body y adjuntar el archivo que se vaya a subir
* ##### Respuesta en caso de error:
	  Código de Error : 409 CONFLICT (Para cuando se sube una imagen a una ficha en la que ya hay una imagen subida)
	  		    400 BAD_REQUEST (Para cuando hay algún error con el archivo subido)

		

		
		
		
		
		
		
		
	
## Solicitudes de Relaciones
Todas las consultas realizadas en este apartado vendrán precedidas por /api/unit/{unitName} mencionadas anteriormente

### Mostrar Relaciones
* ##### Descripción
Esta operación permite mostrar todas las relaciones que se tienen
* ##### URL:
	< /context >
* ##### Operaciones: 
		GET
* ##### Consulta:
		/api/unit/HTML/context
* ##### Respuesta en caso de éxito
	```
	[
    {
        "content": [
            {
                "type": "inheritance",
                "origin": {
                    "name": "SGML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                }
            },
            {
                "type": "inheritance",
                "origin": {
                    "name": "MAQUETACIÓN_WEB",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                }
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "offset": 0,
            "pageSize": 10,
            "pageNumber": 0,
            "unpaged": false,
            "paged": true
        },
        "totalElements": 2,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 0,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "numberOfElements": 2,
        "first": true,
        "empty": false
    },
    {
        "content": [
            {
                "type": "inheritance",
                "origin": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML2.1",
                    "photoComp": false,
                    "photoClas": false
                }
            },
            {
                "type": "inheritance",
                "origin": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML4.0",
                    "photoComp": false,
                    "photoClas": false
                }
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "offset": 0,
            "pageSize": 10,
            "pageNumber": 0,
            "unpaged": false,
            "paged": true
        },
        "totalElements": 2,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 0,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "numberOfElements": 2,
        "first": true,
        "empty": false
    },
    {
        "content": [
            {
                "type": "composition",
                "origin": {
                    "name": "CSS",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                }
            },
            {
                "type": "composition",
                "origin": {
                    "name": "ETIQUETAS",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                }
            },
            {
                "type": "composition",
                "origin": {
                    "name": "ATRIBUTOS",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                }
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "offset": 0,
            "pageSize": 10,
            "pageNumber": 0,
            "unpaged": false,
            "paged": true
        },
        "totalElements": 3,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 0,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "numberOfElements": 3,
        "first": true,
        "empty": false
    },
    {
        "content": [
            {
                "type": "composition",
                "origin": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "DISEÑO_WEB",
                    "photoComp": false,
                    "photoClas": false
                }
            },
            {
                "type": "composition",
                "origin": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "ETIQUETAS",
                    "photoComp": false,
                    "photoClas": false
                }
            },
            {
                "type": "composition",
                "origin": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "VIDEOS",
                    "photoComp": false,
                    "photoClas": false
                }
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "offset": 0,
            "pageSize": 10,
            "pageNumber": 0,
            "unpaged": false,
            "paged": true
        },
        "totalElements": 3,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 0,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "numberOfElements": 3,
        "first": true,
        "empty": false
    },
    {
        "content": [
            {
                "type": "use",
                "origin": {
                    "name": "JAVASCRIPT",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                }
            },
            {
                "type": "use",
                "origin": {
                    "name": "ANGULAR",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                }
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "offset": 0,
            "pageSize": 10,
            "pageNumber": 0,
            "unpaged": false,
            "paged": true
        },
        "totalElements": 2,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 0,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "numberOfElements": 2,
        "first": true,
        "empty": false
    },
    {
        "content": [
            {
                "type": "use",
                "origin": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "CSS",
                    "photoComp": false,
                    "photoClas": false
                }
            },
            {
                "type": "use",
                "origin": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "XML",
                    "photoComp": false,
                    "photoClas": false
                }
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "offset": 0,
            "pageSize": 10,
            "pageNumber": 0,
            "unpaged": false,
            "paged": true
        },
        "totalElements": 2,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 0,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "numberOfElements": 2,
        "first": true,
        "empty": false
    },
    {
        "content": [
            {
                "type": "association",
                "origin": {
                    "name": "CSS",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                }
            },
            {
                "type": "association",
                "origin": {
                    "name": "XML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                }
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "offset": 0,
            "pageSize": 10,
            "pageNumber": 0,
            "unpaged": false,
            "paged": true
        },
        "totalElements": 2,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 0,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "numberOfElements": 2,
        "first": true,
        "empty": false
    },
    {
        "content": [
            {
                "type": "association",
                "origin": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "CSS",
                    "photoComp": false,
                    "photoClas": false
                }
            },
            {
                "type": "association",
                "origin": {
                    "name": "HTML",
                    "photoComp": false,
                    "photoClas": false
                },
                "destiny": {
                    "name": "XML",
                    "photoComp": false,
                    "photoClas": false
                }
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "offset": 0,
            "pageSize": 10,
            "pageNumber": 0,
            "unpaged": false,
            "paged": true
        },
        "totalElements": 2,
        "totalPages": 1,
        "last": true,
        "size": 10,
        "number": 0,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "numberOfElements": 2,
        "first": true,
        "empty": false
    }
]


### Obtener Relación
* ##### Descripción
Esta operación permite obtener una relacíon de una unidad 
* ##### URL:
		< /relations/{type} >
* ##### Operaciones: 
		GET
* ##### Parámetros de la URL:
		unitName = String
		type = String (que describe el tipo de relación que se pide)
* ##### Consulta:
		/api/unit/HTML/relations/parents
* ##### Respuesta en caso de éxito
  ```
		{
    "content": [
        {
            "type": "inheritance",
            "origin": {
                "name": "SGML",
                "photoComp": false,
                "photoClas": false
            },
            "destiny": {
                "name": "HTML",
                "photoComp": false,
                "photoClas": false
            }
        },
        {
            "type": "inheritance",
            "origin": {
                "name": "MAQUETACIÓN_WEB",
                "photoComp": false,
                "photoClas": false
            },
            "destiny": {
                "name": "HTML",
                "photoComp": false,
                "photoClas": false
            }
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 10,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "totalElements": 2,
    "totalPages": 1,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
}	

* ##### Respuesta en caso de error:
	  Código de Error : 404 NOT FOUND (En caso de que no se encuentre la relación pedida)
		
### Crear Relación
* ##### Descripción
Esta operación permite crear una relación 
* ##### URL:
		< /relations >
* ##### Operaciones: 
		POST
* ##### Parámetros de la URL:
		unitName = String
		type = String (que describe el tipo de relación que se pide)
* ##### Consulta:
		/api/unit/HTML/relations/

* ##### Formulario Postman:
  ```
  {
            "type": "inheritance",
            "origin": {
                "name": "CSS",
                "photoComp": false,
                "photoClas": false
            },
            "destiny": {
                "name": "HTML",
                "photoComp": true,
                "photoClas": true
            }
        }

* ##### Respuesta en caso de éxito
  ```

		{
    "type": "inheritance",
    "origin": {
        "name": "CSS",
        "photoComp": false,
        "photoClas": false
    },
    "destiny": {
        "name": "HTML",
        "photoComp": true,
        "photoClas": true
    }
}

* ##### Respuesta en caso de error:
	  Código de Error : 409 CONFLICT (En caso de que se cree una relación que ya estaba creada)
		
### Eliminación Relación
* ##### Descripción
Esta operación permite eliminar una relación entre dos unidades
* ##### URL:
		< /relations/{type}/related/{unitRelated} >
* ##### Operaciones: 
		DELETE
* ##### Parámetros de la URL:
		unitName = String
		type = String (que describe el tipo de relación que se pide)
		unitRelated = String (el otro extremos de la relación)
* ##### Consulta:
		api/unit/HTML/relations/parents/related/SGML
* ##### Respuesta en caso de éxito
	```
	{
    "type": "inheritance",
    "origin": {
        "name": "SGML",
        "photoComp": false,
        "photoClas": false
    },
    "destiny": {
        "name": "HTML",
        "photoComp": true,
        "photoClas": true
    }
}
	
* ##### Respuesta en caso de error:
	  Código de Error : 404 NOT FOUND (En caso de que no se encuentre la relación pedida)
	
	
	


  



