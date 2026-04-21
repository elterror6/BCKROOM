# Modelo

En esta carpeta se guarda el modelo de la aplicación web. Aquí se encuentra la lógica de negocio de la aplicación, es lo que tiene la información que se podrá mostrar en la vista.

## ```DireccionUsuario```

Esta clase representa la dirección de un usuario. Solo tiene el tipo de vía que és, el nombre de la calle, la provincia, localidad,país y código postal.

## ```DireccionInmueble```

Esta clase representa la dirección de un inmueble. Esta clase tiene contenida una dirección de usuario, número, piso y puerta.

## ```Usuario```

Esta clase representa a un usuario cualquiera. Esta clase contiene el nombre de usuario, correo electrónico, contraseña, nombre, apellidos, dirección del usuario y la dirección.

## ```Inquilino```

Esta clase es una especialización de usuario. Este tipo de usuario tiene una lista de inmuebles deseados y un conjunto de reservas que ha realizado el usuario.

## ```Propietario```

Esta clase es una especialización de usuario. Este tipo de usuario tiene un conjunto de inmuebles que le pertenecen.

## ```Inmueble```

Esta clase representa un inmueble. Contiene la direción del inmueble, el tipo de inmueble que és, el precio por noche y un conjunto de disponibilidades.

## ```Disponibilidad```

Esta clase representa una disponibilidad de un inmueble. Contiene las fechas de inicio y fin, el precio de la disponibilidad y el inmueble al que pertenece la disponibilidad.

## ```ListaDeseos```

Esta clase representa una lista de deseos de un inquilino. Contiene un conjunto de inmuebles que el inquilino ha marcado como deseados.

## ```TipoCalle```

Esta clase enumerada representan el tipo de vía que puede ser una una calle de una dirección. Esto con la intención de que no haya datos incorrectos en el sistema.

## ```Comodidad```

Esta clase representa una comodidad de un inmueble. Esta clase se realiza con la intención de poder guardar en la persistencia las comodidades para no modificar ninguna lógica al insertar nuevas comodidades.

## Diagrama de Clases

```mermaid
classDiagram
    class Usuario {
        -id: Long 
        -username: String
        -email: String
        -password: String
        -nombre: String
        -primerApellido: String
        -segundoApellido: String
        -direccion: DireccionUsuario

        +Usuario()
        +Usuario(username: String, email: String, password: String, nombre: String, primerApellido: String, segundoApellido: String, direccion: DireccionUsuario)

        +getters():String|DireccionUsuario
        +setters():void

        +toString(): String
        +equals(obj: Object): boolean
    }

    class Inquilino {
        -listaDeseos: ListaDeseos
        -reservasRealizadas: Set

        +Inquilino()
        +Inquilino(username: String, email: String, password: String, nombre: String, primerApellido: String, segundoApellido: String, direccion: DireccionUsuario, listaDeseos: ListaDeseos, reservasRealizadas: Set)
    
        +getters(): ListaDeseos|Set
        +setters(): void

        +toString(): String
    }

    class Propietario {
        -inmueblesEnPropiedad: Set

        +Propietario()
        +Propietario(username: String, email: String, password: String, nombre: String, primerApellido: String, segundoApellido: String, direccion: DireccionUsuario, inmueblesEnPropiedad: Set)

        +getters(): Set
        +setters(): void

        +toString(): String
        +equals(obj: Object): boolean
    }

    class Inmueble {
        -id: Long
        -direccion: DireccionInmueble
        -tipoInmueble: TipoInmueble
        -precioNoche: double
        -disponibilidades: Set

        +Inmueble()
        +Inmueble(direccion: DireccionInmueble, tipoInmueble: TipoInmueble, precioNoche: double, disponibilidades: Set)

        +getters(): 
        +setters(): void

        +toString(): String
        +equals(obj: Object): boolean
    }

    class DireccionUsuario {
        -tipoVia: TipoCalle
        -nombreVia: String
        -provincia: String
        -localidad: String
        -pais: String
        -codigoPostal: String

        +DireccionUsuario()
        +DireccionUsuario(tipoVia: TipoCalle, nombreVia: String, provincia: String, localidad: String, pais: String, codigoPostal: String)

        +getters():
        +setters(): void

        +toString(): String
        +equals(obj: Object): boolean
    }

    class DireccionInmueble {
        -direccionBase: DireccionUsuario
        -numero: String
        -piso: String
        -puerta: String
        -comodidades: Set

        +DireccionInmueble()
        +DireccionInmueble(direccionBase: DireccionUsuario, numero: String, piso: String, puerta: String)

        +getters():
        +setters(): void

        +toString(): String
        +equals(obj: Object): boolean
    }

    class Disponibilidad {
        -id: Long
        -fechaInicio: Datetime
        -fechaFinal: Datetime
        -precio: double
        -inmuebleDisponible: Inmueble

        +Disponibilidad()
        +Disponibilidad(fechaInicio: Datetime, fechaFinal: Datetime, precio: double, inmuebleDisponible: Inmueble)

        +getters():
        +setters(): void

        +toString(): String
        +equals(obj: Object): boolean
    }

    class ListaDeseos {
        -id: Long
        -inquilino: Inquilino
        -inmueblesDeseados: Set

        +ListaDeseos()
        +ListaDeseos(inquilino: Inquilino, inmueblesDeseados: Set)

        +getters():
        +setters(): void

        +toString(): String
        +equals(obj: Object): boolean
    }

    class TipoCalle {
        ... tipos de calle ...
    }

    class Comodidad {
        -id: Long
        -nombreComodidad: String

        +Comodidad()
        +Comodidad(nombreComodidad: String)

        +getters():
        +setters(): void

        +toString(): String
        +equals(obj: Object): boolean
    }

    Inquilino --|> Usuario
    Propietario --|> Usuario

    Propietario "1" *-- "1, *" Inmueble

    Usuario "1" *-- "1" DireccionUsuario

    DireccionInmueble "1" *-- "1" DireccionUsuario
    Inmueble "1" -- "1" DireccionInmueble

    Disponibilidad "0, *" -- "1" Inmueble

    Inquilino "1" -- "1" ListaDeseos
    ListaDeseos "1" o--> "0,*" Inmueble

    DireccionUsuario "1" --> "1" TipoCalle
```