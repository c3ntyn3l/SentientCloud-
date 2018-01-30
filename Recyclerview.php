<?php

require "conn.php";

$query="SELECT * FROM project WHERE ID>0 ORDER by ID DESC";

$result = mysqli_query($con,$query) or die("Error in Selecting " . mysqli_error($con));
$emp=array();
while($row=mysqli_fetch_assoc($result))
{
	$emp['emp'][] = $row;
}

echo json_encode($emp);

echo end($emp);
mysqli_close($con);

?>