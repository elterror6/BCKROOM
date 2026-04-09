# Vista

En esta carpeta se encuentran todos los archivos con los que el usuario va a poder interactuar con el sistema.

## ```login.html```

Este archivo contiene el formulario de inicio de sesión de un usuario en el sistema. El formulario para iniciar sesión pide el correo electrónico y contraseña del usuario.

## ```register.html```

Este archivo contiene el formulario de registro de un usuario. Este formulario para registrar un usuario contiente un campo para el nombre de usuario, correo electrónico, contraseña, varios para nombre y apellidos y dirección del  usuario, también se podrá elegir si se quiere crear un usuario inquilino o propietario.

## ```search.html```

Este archivo contendrá un buscador de inmuebles por disponibilidad y filtros de búsqueda para los mismos, además permitirá a un usuario inquilino que ha iniciado sesión añadir inmuebles a su lista de deseados y reservar los inmuebles, también el inquilino podrá ver desde aquí su lista de deseados y las reservas realizadas.

## ```propietario/home.html```

Este archivo contiene una pequeña zona personal para el usuario propietario. En esta zona el propietario podrá dar de alta nuevos inmuebles y podrá añadirles disponibilidad, a su vez podrá administrar las solicitudes de reserva desde aquí. Esta vista también permitirá que el usuario propietario pueda cerrar sesión con su cuenta.

## ```propietario/alta-inmueble.html```

Este archivo contiene el formulario de alta de un inmueble por parte de un usuario propietario. Este contendrá la dirección del inmueble, un conjunto de comodidades que puede tener el inmueble y el precio por noche por defecto que quieres que tenga el inmueble.

## ```propietario/nueva-disponibilidad.html```

Este archivo contendrá un formulario para añadir la disponibilidad a un inmueble que pertenezca al propietario. Este formulario contendrá la fecha de inicio y fin, si el tipo de reserva es inmediata o no y el precio al que se quiere alquilar, si no se poné nada se pondrá el precio por noche del inmueble.

## ```propietario/comprobar-disponibilidad.html```

Este archivo es una página que contendrá las distintas disponibilidades activas de un inmueble. Se enseñará en cada disponibilidad todos los datos de la misma, incluyendo la opción de eliminar cualquier disponibilidad que desee el propietario.


## ```inquilino/lista-deseos.html```

Este archivo te enseña la lista de inmuebles deseados del inquilino que ha iniciado sesión. En esta pestaña también se dejará reservar los inmuebles que esten disponibles y eliminar de la lista de deseados aquellos que quiera el inquilino eliminar.

## ```inquilino/reservas.html```

Este archivo contendrá una lista de inmuebles a los que el usuario haya solicitado una reserva o haya reservado automáticamente, también saldrá si un propietario a cancelado una reserva.