<html>
<head>

</head>
<body>
<?php
    
    
    $server="db518907232.db.1and1.com";
    $login="dbo518907232";
    $pass="costafernandez1989";
    $database="db518907232";
    
    mysql_connect ("$mysql_server","$mysql_login","$mysql_pass") or die ("Error en la conexión");
    
    // seleccionamos la BD y hacemos consulta
    mysql_select_db ("$database") or die ("No entra en la base de datos");
    
    $consulta = "SELECT * FROM formulario";
    $resposta = mysql_query ($consulta); // li he sustituit aquesta part de la consulta ", $conexion" del final de dins el parentesis
    
    // bucle para poner resultados
    
    while ($filabd = mysql_fetch_row($resposta)){
        
        echo $filabd[0]." ".$filabd[1]." ".$filabd[2]." ".$filabd[3]."<p>";
        
    }     ?>
<body>
</html>