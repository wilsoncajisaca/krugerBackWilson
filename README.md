# krugerBackWilson

Para levantar el proyecto se debe considerar lo siguiente:
Ir al directorio EXTRAS y correr los scripts.
1. Crear la base de datos.
2. Crear el nuevo usuario rol para la conexion con la base 

# -------------------- Levantar el servicio back --------------------

Ejecutar los pasos 3, 4 y 5 solo si el sistema a la hora de levantar el proyecto no ejecutó las inserciones en la base.
(Verificar en la base -> Tabla employee)

3. Inserta los roles directamente con los scripts
4. Insertar el usuario admin de pruebas 
5. Ingresar el rol con el usuario

# ----------------------------------------------------------------------

# Nota: 
*Cuando intente crear un nuevo empleado, el PRIMER INTENTO devolvera error, esto debido al incrementable "id" de la tabla "employee" ya que en el anterior paso 4 r se inserta directamente/manualmente en la base.)*

----------------------------------------------------------------------

En el directorio EXTRAS tambien encontrará adjunto el modelado de la base.

El sistema ya cuenta con un usuario ADMINISTRADOR mismo que se ingreso anteriormente este usuario corresponde a
username = 0123456789 
password = 12345

Los usuarios de tipo empleado solo se crean atraves de la aplicacion.

Los credenciales de los mismo se devuelven en formato json en los campos:
username y password

Para mayor información puede consultar la documentacion en swagger
link: localhost:8090/swagger-ui.html
