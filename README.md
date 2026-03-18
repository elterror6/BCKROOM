# BCKROOM
Aplicación web tipo Airbnb donde personas particulares pueden reservar alojamientos de propietarios particulares.

## Visualización de la Documentación
La documentación se ha elegido realizar en LaTeX para el mejor mantenimiento de la misma. Para generación del pdf se puede elegir una herramienta online como [Overleaf](https://www.overleaf.com) o en una máquina en local utilizando VSCode.

### Instalación de LaTeX
Para utilizarlo en local hay que seguir los siguientes pasos:

1. Instalar un compilador de LaTeX. Para Windows [Miktex](https://miktex.org/), para Linux instalar el paquete ```sudo apt install texlive-full```.
2. Instalar extensión en VSCode ```LaTeX Workshop```.
3. Instalar [Strawberry Perl](https://strawberryperl.com) para que pueda compilar los archivos de LaTeX.

### Visualización en local
Una vez realizado el proceso de instalación de LaTeX en local lo único que hay que hacer es la combinación de teclas ```Ctrl+Alt+B``` para hacer el build del código LaTeX y la combinación ```Ctrl+Alt+V``` para poder visualizar el PDF resultante en pantalla dividida.

## Despliegue del Sistema

Para poder desplegar el sistema debes moverte a la carpeta donde este el archivo ```pom.xml``` y por línea de comandos realizar el comando ```mvn spring-boot:run```.

## Diseño de la aplicación

En esta sección se indicarán y enseñarán los distintos tipos de diseños utilizados en el proyecto para facilitar la compresión del código y la relación de los distintos componentes en distintos niveles.

### Diseño de Clases

Al usar Java para realizar el proyecto se utilizará el paradigma POO, para facilitar la realización del código y su comprensión se ha realizado el diagrama de clases del dominio de la aplicación.

```mermaid
---
config:
  layout: dagre
---
classDiagram
direction BT
    class Usuario {
	    -Long id
	    -String username
	    -String passwd
	    -String nombre
	    -String primerApellido
	    -String segundoApellido
	    -DireccionUsuario direccion
	    +Usuario()
	    +Usuario(usuario: Usuario)
	    +Usuario(username: String, passwd: String, nombre: String, primerApellido: String, segundoApellido: String, direccion: DireccionUsuario)
	    +getters*()
	    +setter*()
	    +checkPasswd(String passwd) : boolean
    }

    class Inquilino {
	    -ListaDeseos listaDeseos
	    +Inquilino()
	    +Inquilino(usuario: Usuario)
	    +Inquilino(username: String, passwd: String, nombre: String, primerApellido: String, segundoApellido: String, direccion: DireccionUsuario, listaDeseos: ListaDeseos)
	    +getters*()
	    +setters*()
    }

    class Propietario {
	    -List:Inmueble inmuebles
	    +Propietario()
	    +Propietario(usuario: Usuario)
	    +Propietario(username: String, passwd: String, nombre: String, primerApellido: String, segundoApellido: String, direccion: DireccionUsuario, inmuebles: List:Inmueble)
	    +getters*()
	    +setters*()
    }

    class Inmueble {
	    -Long id
	    -Propietario propietario
	    -DireccionInmueble direccion
	    -Double precioNoche
		-disponibilidad: List
	    +Inmueble()
	    +Inmueble(propietario: Propietario, direccion: DireccionInmueble, precioNoche: Double)
	    +getters*()
	    +setters*()
		+addDisponibilidad(disponibilidad: Disponibilidad): void
		+delDisponibilidad(disponibilidad: Disponibilidad): void
    }

    class ListaDeseos {
	    -Long id
	    -Inquilino inquilino
	    -List:Inmueble inmuebles
	    +ListaDeseos()
	    +ListaDeseos(inquilino: Inquilino, inmuebles: List:Inmueble)
	    +addInmueble(inmueble: Inmueble) : void
	    +deleteInmueble(inmueble: Inmueble) : void
	    +getters*()
	    +setters*()
    }

    class DireccionUsuario {
	    -TipoCalle tipoCalle
	    -String calle
	    -String ciudad
	    -String estadoProvincia
	    -String codigoPostal
	    -String pais
	    +DireccionUsuario()
	    +DireccionUsuario(tipoCalle: TipoCalle, calle: String, ciudad: String, estadoProvincia: String, codigoPostal: String, pais: String)
	    +getters*()
	    +setters*()
    }

    class DireccionInmueble {
	    -DireccionUsuario base
	    -String numero
	    -String piso
	    -String puerta
	    +DireccionInmueble()
	    +DireccionInmueble(base: DireccionUsuario, numero: String, piso: String, puerta: String)
	    +getters*()
	    +setters*()
    }

    class TipoCalle {
	    <<enumeration>>
	    CALLE
        AVENIDA 
        PLAZA
        RONDA
        PASEO
        TRAVESIA
        CALLEJON
        URBANIZACION
        GLORIETA
    }
    
	class Disponibilidad {
		-id: Long
		-fechaInicio: Date
		-fechaFin: Date
		-precio: double
		-inmueble: Inmueble
		+Disponibilidad()
		+Disponibilidad(fechaInicio: Date, fechaFin: Date, precio: double, inmueble: Inmueble)
		+getters*()
		+setters*()
		+fechasDentroDeRango(fechaInicio: Date, fechaFin: Date): boolean
	}
	
    Inquilino --|> Usuario
    Propietario --|> Usuario
    Propietario "1" --> "1,*" Inmueble : inmuebles
    Inquilino "1" --> "1" ListaDeseos : listaDeseos
    ListaDeseos "1" --> "0,*" Inmueble : inmuebles
    Usuario "1" --> "1" DireccionUsuario : direccion
    Inmueble "1" --> "1" DireccionInmueble : direccion
    DireccionInmueble "1" --> "1" DireccionUsuario : direccionBase
    DireccionUsuario "1" --> "1" TipoCalle : tipoCalle
	Disponibilidad "0,*" <-- "1" Inmueble: disponibilidad