BREVE DESCRIPCION DE LOS PROCESOS DE CARGA Y REALIZACION DE LOS REPORTES

PROCESOS DE CARGA


  La clase OrdenarCSV se encarga de leer los datos del csv y ordenar los datos en un hash con clave fecha y value una lista de canciones.
  Esta manera de ordenarla nos brinda una facil obtencion de las canciones y tambien es util para facilitar la realizacion de las consultas.
  Para cargar los datos del csv, creamos instancias de Cancion, estas las creamos usando las filas del csv guardadas como un arrays ,separando cada value 
  utilizando split("\",\""), el constructor se encarga de leer el array y crear la instancia.
  A la hora de guardarla se verifica la existencia de una lista con la clave fecha en el hash, si esta no existe se crea, y se le agrega la instancia de Cancion a la lista.
  
      
