 # DIAGRAMA UML DE CLASES
![DiagramaObligatorio](https://github.com/Barbaspin/repositorio_Previtali_Sosa/assets/169061200/78d1b182-71c8-43ea-be9a-92cdb4b9494e)

## BREVE DESCRIPCIóN DE LOS PROCESOS DE CARGA Y REALIZACION DE LOS REPORTES

### PROCESOS DE CARGA

  La clase OrdenarCSV se encarga de leer los datos del archivo csv y separar cada fila del archivo como una canción diferente, con sus atributos correspondientes (nombre, artistas, etc). Al separar las canciones, estas se guardan en un hash que tiene como clave la fecha en la que se guardaron los datos de la cancion, y el dato que guarda es una lista que contiene las canciones antes mencionadas. Esta manera de ordenarla facilitar las consultas, ya que estas dependen de la fecha que se quiera consultar, y nos brinda una fácil obtención de las canciones.
  Para cargar los datos del csv, creamos instancias de Cancion, estas las creamos usando las filas del csv guardadas como un arrays ,separando cada value utilizando split("\",\""), el constructor se encarga de leer el array y crear la instancia.
  A la hora de guardarla se verifica la existencia de una lista con la clave fecha en el hash, si esta no existe se crea, y se le agrega la instancia de Cancion a la lista.
  También busca cuales son la primer y última fecha registradas en el csv, para comparaciones en las demás consultas

#### TIEMPO DE LEER CSV Y GUARDARLO

![tiempo leer csv y guardarlo](https://github.com/Barbaspin/repositorio_Previtali_Sosa/assets/169061200/080f4728-9765-48a1-9159-58341234603e)

#### MEMORIA OCUPADA POR LA LECTURA Y EL GUARDADO

![image](https://github.com/Barbaspin/repositorio_Previtali_Sosa/assets/169061951/c24953a1-f50b-4d5d-b9b2-f8222c3c2b43)


![memoria total](https://github.com/Barbaspin/repositorio_Previtali_Sosa/assets/169061200/19d91cf5-cfe4-4999-93a0-cee58767b121)

## REALIZACIóN DE LOS REPORTES

Los tiempos mostrados a continuacion son resultado de un ejemplo de uso de cada consulta.

  ### REPORTE TOP 10 PAIS FECHA
    Este reporte busca que la fecha indicada se encuentre en el hash y obtiene la lista de canciones en esa fecha.
    El reporte se encarga de buscar las canciones que se encuentren en el top 10 y que el país coincida con el país pedido. 
    Para esto creamos un hash donde la clave es la posición. Para finalizar, 
    la consulta busca en el hash las posiciones del ranking de manera creciente, y en cada paso muestra los datos solicitados de la canción.

#### TIEMPO UTILIZADO
    
![image](https://github.com/Barbaspin/repositorio_Previtali_Sosa/assets/169061200/74572625-084d-462b-9ba5-f40bc08d28c4)



###  REPORTE TOP 5 CANCIONES QUE MÁS APARECEN EN EL TOP 50
    Este reporte busca que la fecha indicada se encuentre en el hash y obtiene la lista de canciones en esa fecha.
    Luego se encarga de recorrer toda la lista de canciones y contar cuántas veces aparece cada una. 
    Para finalizar, se eligen las 5 canciones que más aparecen y se muestran de manera descendiente.

 #### TIEMPO UTILIZADO
    
![Tiempo Consulta top 50](https://github.com/Barbaspin/repositorio_Previtali_Sosa/assets/169061200/569dee18-5429-4978-97f1-9618e71a5676)


###  REPORTE TOP 7 ARTISTAS EN UN RANGO DE FECHAS
    Este reporte busca que la fecha indicada se encuentre en el hash y obtiene la lista de canciones de la primer fecha.
    Después se encarga de recorrer toda la lista de canciones y contar cuántas veces aparece cada artista.
    Luego repite esto mismo para cada fecha entre las dos solicitadas.
    Para finalizar, se eligen los 7 artistas que más aparecen y se muestran de manera descendiente.

####  TIEMPO UTILIZADO
  
![image](https://github.com/Barbaspin/repositorio_Previtali_Sosa/assets/169061200/6e3424dc-8a12-4aba-80cd-e9349810f222)



 ### REPORTE CANTIDAD DE VECES QUE APARECE UN ARTISTA
    Este reporte busca que la fecha indicada se encuentre en el hash y obtiene la lista de canciones del primer día.
    Luego se encarga de recorrer toda la lista de canciones y contar cuántas veces aparece cada artista. 
    Para finalizar, se eligen los 7 artistas que más aparecen y se muestran de manera descendiente.

####  TIEMPO UTILIZADO
  
![tiempo consulta cuantas veces aparece artista](https://github.com/Barbaspin/repositorio_Previtali_Sosa/assets/169061200/93ca6b19-3645-42d9-b84b-addada93b9af)



###  REPORTE CANTIDAD DE CANCIONES CON UN TEMPO ESPECIFICO
    Este reporte busca que la fecha indicada se encuentre en el hash y obtiene la lista de canciones de la primer fecha. 
    Luego se encarga de recorrer toda la lista de canciones y contar cuántas canciones tienen un tempo que se encuentre entre el rango solicitado.
    Luego repite esto para cada día hasta la última fecha solicitada. Por último devuelve la cantidad final.
  
 #### TIEMPO UTILIZADO
  
![image](https://github.com/Barbaspin/repositorio_Previtali_Sosa/assets/169061200/499a0c23-ba6c-4e1c-b0ea-1888c6a377d7)










    
