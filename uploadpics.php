<?php
require "conn.php";
header('Content-type :bitmap; charset=utf-8');

	if(isset($_POST['encoded_string']))
	{
		$encoded_string=$_POST['encoded_string'];
		$imgname=$_POST['imgname'];

		$decoded_string = base64_decode($encoded_string);

		$path='images/'.$imgname;

		$file=fopen($path, 'wb');

		$is_written=fwrite($file,$decoded_string);
		fclose($file);

		if($is_written>0)
		{
			$query="INSERT INTO photos(name,path)values($imgname,$path)";
			$result = mysqli_query($con ,$query);
		}

		if($result)
		{
			echo "Success";
		}
		else
		{
			echo "Failed";
		}

		mysqli_close($con);
	}
?>