<?php
header('Content-Type:application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Method: POST');


$data= json_decode(file_get_contents("php://input"),true);
$con= mysqli_connect("localhost","root","","map_data") or die("Connection Failed");
$fname=$data['uname'];
$pass=$data['pass'];
$type=$data['t'];

$sql="SELECT * FROM `user` ";
$result=mysqli_query($con,$sql) or die("SQL failed");
echo json_encode($result);
?>