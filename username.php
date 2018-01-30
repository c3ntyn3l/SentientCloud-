<?php
$db_name="sentientdb";
$servername="localhost";
$username="root";
$password="";

$con=mysqli_connect($servername,$username,$password,$db_name);



$mysql_query="Select Email from login where Email like'123'";

$result = mysqli_query($con ,$mysql_query);
while($row=mysqli_fetch_assoc($result))
{
	$emp['emp'][]= $row;
}

echo json_encode($emp);
$con->close();
?>