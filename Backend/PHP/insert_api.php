<?php
header('Content-Type:application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Method: POST');


$data= json_decode(file_get_contents("php://input"),true);
$con= mysqli_connect("localhost","root","","map_data") or die("Connection Failed");
$fname=$data['name'];
$pass=$data['pass'];
$mail=$data['email'];

$sql="INSERT INTO `user`(`f_name`, `password`, `email`) VALUES ('{$fname}','{$pass}','{$mail}')";
$result=mysqli_query($con,$sql) ;
if($result)
{
	echo json_encode(array('message' => 'Registration Successfully !!','status' => true));
}
else
	echo json_encode(array('message' => 'Your Email already Registered !!','status' => false));



?>