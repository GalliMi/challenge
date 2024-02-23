La solución consiste de dos tablas creadas en Postgress SQL con las siguientes estructuras:
TABLA USUARIO:
nombre (VARCHAR 255),
apellido (VARCHAR 255),
email (VARCHAR 255),
password (VARCHAR 255), 
newsletter (VARCHAR 255), 
tipoSubscripcion (VARCHAR 255),
estadoSubscripcion (VARCHAR 255)

TABLA PAGOS:
status VARCHAR(255),
email VARCHAR(255),
tipoSubscripcion VARCHAR(255),
fecha DATE

Para realizar carga de usuarios mediante post man, se realiza un post request con body incluido, por ejemplo:
 {"nombre": "Juan",
    "apellido": "Garcia",
    "email": "juangarcia@gmail.com",
    "password": "gato1234",
    "newsletter": "no",
    "tipoSubscripcion": "anual"
    "estadoSubscripcion": "activa"
    }

Así registra un nuevo usuario con su contraseña la cual es encriptada a la hora de guardar en la bdd.

Crear un usuario de Stripe, en la seccion "Desarrolladores" copiar la API key de prueba y reemplazar "INSERT-STRIPE-KEY" en application.properties por dicha clave.
Para realizar una compra por medio de Stripe se debe hacer un post request con los siguientes datos:
nombre, email y tipo de subscripcion. 
Un ejemplo a la hora de hacer un post request sería:
http://localhost:8080/realizarCompra?nombre=juan&email=lopez@gmail.com&tipoSubscripcion=anual
Cada vez que se realiza un pago, sea exitoso o no, este se registra en la base de datos "Pagos"

Para acceder al perfil hacemos un get request en el endpoint usuarios/perfil asignandole un email y la contraseña, solo se mostrará si la cotraseña coincide con la que está almacenada en la bdd. Por ejemplo:
http://localhost:8080/usuarios/perfil?email=lopez@gmail.com&&password=gato1234
