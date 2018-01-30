<?php
require "conn.php";
$Email=$_POST["Email"];
$password=$_POST["Password"];

$mysql_query="Select * from login where Email like'$Email' and Password like '$password'";

$result = mysqli_query($con ,$mysql_query);

if(mysqli_num_rows($result) > 0)
{
	echo "Successful";
}
else
{
	echo "Invalid";
}
$con->close();
?>