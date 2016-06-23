# negocio-services
## Versión develop

Servicios base para envío de email y consumo de Alfresco, para despliegue en Jboss EAP 6.

## Configuración para despliegue de servicio

### Servicio Email - Configuración de Jboss EAP

Primero es necesario verificar que el sistema de mensajería esté habilitado con el api Hornetq, para ello ingresar a la interfaz
web de administración, en la pestaña *"PROFILE"* y verificar que en el menú exista la opción *"Messaging"*. Caso no este habilitado el 
sistema de mensajería, seguir los pasos descritos en el siguiente link:

[Configuración de servicio JMS](https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/6.1/html/Administration_and_Configuration_Guide/sect-Configuration.html)

Para realizar el despliegue exitoso del servicio hay que realizar una serie de pasos:

1. Crear usuario de aplicación en el sistema EAP:

  Iniciar terminal de comandos e ingresar a la ruta *"$EAP_HOME/bin"*, luego ejecutar el archivo *"add-user.sh"*.
  
  ```
  cd $EAP_HOME/bin
  
  ./add-user.sh
  ```
  
  Seguir las siguientes instrucciones:
   
   - Seleccionar la opción *"b"*, para usuario de aplicaciones.
   - Indicar que pertenece al dominio *"ApplicationRealm"*.
   - Definir un nombre y contraseña para el registro. 
   - Indicar que debe pertenecer al rol "guest".
   - Confirmar creación de usuario.
   - Negar acceso del usuario para otro host.
  
  Siguiendo estos pasos el nuevo usuario debe quedar registrado.

2. Crear cola Queue para envío de email asincrono.

  Iniciar terminal de comandos e ingresar a la ruta *"$EAP_HOME/bin"*, luego ejecutar el archivo *"jboss-cli.sh"* con el parametro *"--conect"*.
  
  ```
  cd $JBOSS_EAP_HOME/bin
  
  ./jboss-cli.sh --connect
  ```
  
  Cambiar a sub-sistema de mensajería.
  
  ```
  cd /subsystem=messaging/hornetq-server=default
  ```
  
  Finalmente ejecutar comando de creación.
  
  ```
  ./jms-queue=subdereEmail:add(durable=true,entries=["java:jboss/exported/jms/queue/SubdereEmail","java:/queue/SubdereEmail"])
  ```

3. Crear sesión de email:

  ###### *Obs.: Se utilizaron los datos de correo electrónico y servidor SMTP de Google y Imagemaker.*
  
  Iniciar terminal de comandos e ingresar a la ruta *"$EAP_HOME/bin"*, luego ejecutar el archivo *"jboss-cli.sh"* con el parametro *"--conect"*.
  
  ```
  cd $EAP_HOME/bin
  
  ./jboss-cli.sh --connect
  ```
  
  Crear sesión de email en EAP ejecutando el siguiente comando.
  
  ```
  /subsystem=mail/mail-session=subdereEmail:add(jndi-name=java:jboss/mail/Subdere, from=subdere@imagemaker.cl)
  ```
  
  Agregar puente de conexión al servidor SMTP en EAP ejecutando el siguiente comando.
  
  ```
  /socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=subdere-smtp-binding:add(host=smtp.gmail.com, port=587)
  ```
  
  Enlazar sesión con conexión por protocolo SMTP.
  
  ```
  /subsystem=mail/mail-session=subdereEmail/server=smtp:add(outbound-socket-binding-ref=subdere-smtp-binding, username=subdere@imagemaker.cl, password=imit2016, tls=true)
  ```

4. Agregar variables globales:

  Ingresar a interfaz web de administración en pestaña *"PROFILE"* y seleccionar la opción menú *"System Properties"*. Por cada 
  una de los siguientes pares hacer click en el botón "Add", luego agregar par de datos y guardar.
  
   - **Name**: mail.smtp.jndi, **Value**: java:jboss/mail/Subdere *{JNDI Sesión de email creada en el paso 3}*
   - **Name**: jms.username, **Value**: quickstartUser *{Nombre de Usuario de aplicaciones creado en el paso 1}*
   - **Name**: jms.password, **Value**: quickstartPwd1! *{Contraseña de Usuario de aplicaciones creado en el paso 1}*
   - **Name**: destination, **Value**: jms/queue/SubdereEmail *{JNDI de cola queue creada en paso 2}*

### Servicio Alfresco - Configuración de Jboss EAP

Para el correcto funcionamiento de las funcionalidades de este módulo es necesario instalar dos módulos más en el Jboss EAP
de forma global.

1. Para ello es necesario ingresar a la ruta *"$EAP_HOME/modules"* y generar la siguiente estructura:

  ```
  ├── com
  |   ├── google
  |   └── gson
  |       └── main
  |           ├── module.xml
  |           └── gson-2.3.jar
  └── org
      └── apache
          └── commons
              └── httpclient
                  └── main
                      ├── module.xml
                      └── commons-httpclient-3.1.jar
  ```

  ###### *Los archivos gson-2.3.jar, commons-httpclient-3.1.jar y los module.xml se encuentran en la ruta /modules del proyecto.*

2. Reiniciar Jboss EAP.
