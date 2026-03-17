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