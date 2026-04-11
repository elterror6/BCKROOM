# Controladores

En esta carpeta se encuentran los archivos controladores de la aplicación web. Los controladores son los componentes que responden a los eventos que el usuario realiza e invoca información del modelo y puede dar información a la vista.

## ```GestorUsuario```

Este controlador se encarga de manejar los eventos de los usuario. Los eventos que puede realizar un usuario son el registro, inicio de sesión y cierre de sesión.

## ```GestorInmuebles```

Este controlador se encarga de manejar los eventos que tienen que ver con los inmuebles. Los eventos que se pueden realizar hacia los inmuebles son el alta o dar de baja un inmueble del sistema, añadir o quitar una disponibilidad de un inmueble y consultar los inmuebles y sus disponibilidades.

## ```GestorBusqueda```

Este controlador se encarga de manejar los eventos que tienen que ver con la búsqueda de inmuebles. Los eventos que principalmente maneja es la búsqueda de inmuebles conforme a la fecha de disponibilidad, precio, ciudad y comodidades y de añadir inmuebles a la lista de deseados de un usuario inquilino.

## ```GestorReservas```

Este controlador se encarga de manejar los eventos que tienen que ver con las reservas de inmuebles. Los eventos que manejan son la reserva inmediata de un inmueble por parte de un inquilino, el envío de solicitudes de reserva a los propietarios y cancelar una reserva por parte del inquilino.

## ```GestorNotificaciones```

Este controlador se encarga de manejar los eventos que tienen que ver con las notificaciones de las solicitudes de reserva. El evento principal de este controlador es la aprobación o no de la solicitud de una reserva que no es inmediata.

## ```GestorPagos```

Este controlador se encarga de los eventos de pago de la reserva de un inmueble. El principal evento es el pago de la reserva hecha por un inmueble.