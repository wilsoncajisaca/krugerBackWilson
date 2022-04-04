# krugerBackWilson

Para levantar el proyecto se debe considerar lo siguiente:
Ir al directorio EXTRAS y correr los scripts.
1. Crear la base de datos.
2. Crear el nuevo usuario rol para la conexion con la base 

# -------------------------- Levantar el servicio back --------------------------------

3. Inserta los roles directamente con los scripts
4. Insertar el usuario admin de pruebas (Nota: Cuando cree un nuevo empleado el PRIMER INTENTO devolvera error, esto debido al incrementable "id" ya que se inserta directamente en la base)
5. Ingresar el rol con el usuario

En el directorio EXTRAS tambien encontrará adjunto el modelado de la base.

El sistema ya cuenta con un usuario ADMINISTRADOR mismo que se ingreso anteriormente este usuario corresponde a
username = 0123456789 
password = 12345

Los usuarios de tipo empleado solo se crean atraves de la aplicacion.

Los credenciales de los mismo se devuelven en formato json en los campos:
username y password

Para mayor información puede consultar la documentacion en swagger
link: localhost:8090/swagger-ui.html
