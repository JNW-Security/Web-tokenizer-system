# Web-Hash-Anonimization-System

- Use commands:
  - mvn package
  - mvn tomcat7:run

link: localhost:8080/index.html

## Integrantes
- Wilsooon Delgado
- Juan Monroy 
- Nicolas Piñeros

## Descrición
Es una plataforma de registro y tratamiento de datos sobre las vacunas para el COVID-19, de forma que presentaremos 3 casos de uso, donde en cada uno de ellos se mostraran las vulnerabilidades del sistema y las capas de protección que se van añadiendo para garantizar que el sistema se anonimice y por tanto brinde mayor robustez. 

## Casos de Uso
### Caso 1
Se realiza el guardado en la base de datos de credenciales de autenticación en texto plano, es decir, no se aplica ningún tipo de cifrado para proteger la confidencialidad de los datos.
Para demostrar la vulnerabilidad del caso se va a ejecutar:
  -	Filtración de datos de la base de datos.
  -	SQL injection.
### Caso 2	
En este punto, el aplicativo cuenta con un cifrado de SHA-256 el cual es un algoritmo de hash criptográfico que genera hashes irreversibles para las contraseñas y con un método de anonimización para los datos registrados en las categorías.
Para demostrar la vulnerabilidad del caso se va a ejecutar:
-	Rainbowtable
### Caso 3
Luego de tener un cifrado con SHA-256, aplicaremos una capa de cifrado mas con SALT, para agregar mas complejidad y aleatoriedad al hash, y así hacer mas complejo que un ataque se pueda materializar.
Para demostrar que se mitigo la vulnerabilidad del caso se volvera a ejecutar:
-	Rainbowtable
