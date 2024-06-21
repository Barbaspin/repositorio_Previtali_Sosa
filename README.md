BREVE DESCRIPCIóN DE LOS PROCESOS DE CARGA Y REALIZACION DE LOS REPORTES

PROCESOS DE CARGA

  La clase OrdenarCSV se encarga de leer los datos del archivo csv y separar cada fila del archivo como una canción diferente, con sus atributos correspondientes (nombre, artistas, etc). Al separar las canciones, estas se guardan en un hash que tiene como clave la fecha en la que se guardaron los datos de la cancion, y el dato que guarda es una lista que contiene las canciones antes mencionadas. Esta manera de ordenarla facilitar las consultas, ya que estas dependen de la fecha que se quiera consultar, y nos brinda una fácil obtención de las canciones.
  Para cargar los datos del csv, creamos instancias de Cancion, estas las creamos usando las filas del csv guardadas como un arrays ,separando cada value utilizando split("\",\""), el constructor se encarga de leer el array y crear la instancia.
  A la hora de guardarla se verifica la existencia de una lista con la clave fecha en el hash, si esta no existe se crea, y se le agrega la instancia de Cancion a la lista.


REALIZACIóN DE LOS REPORTES

  REPORTE TOP 10 PAIS FECHA
    Este reporte busca que la fecha indicada se encuentre en el hash y obtiene la lista de canciones en esa fecha. El reporte se encarga de buscar las canciones que se encuentren en el top 10 y que el país coincida con el país pedido. Para esto creamos un hash donde la clave es la posición. Para finalizar, la consulta busca en el hash las posiciones del ranking de manera creciente, y en cada paso muestra los datos solicitados de la canción.

  REPORTE AAA
    
