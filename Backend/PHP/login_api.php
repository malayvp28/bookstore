<?php
header('Content-Type:application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Method: POST');


$data= json_decode(file_get_contents("php://input"),true);
$con= mysqli_connect("localhost","root","","map_data") or die("Connection Failed");
$fname=$data['uname'];
$pass=$data['pass'];
$type=$data['t'];
if($type == "User"){
$sql="SELECT  `f_name`, `password`, `email` FROM `user` WHERE email='{$fname}' and password='{$pass}'";
$result=mysqli_query($con,$sql) ;
$username="";
if($result)
{
	if ($result->num_rows > 0){
	
	
	while($row = $result->fetch_assoc()){
    $username=$row["f_name"];}
 
	echo json_encode(array('message' => 'Logging Successfully !!','status' => true , 'user' => $username));
	}
	else
		echo json_encode(array('message' => 'Incorrect Password !!','status' => false));
}
else
	echo json_encode(array('message' => 'Service Unavailable !!','status' => false));

}
else
{
	
$sql="SELECT  `aname`, `password`, `email` FROM `admin` WHERE email='{$fname}' and password='{$pass}'";
$result=mysqli_query($con,$sql) or die("SQL failed");
if($result)
{
	if ($result->num_rows > 0){
		while($row = $result->fetch_assoc()){
    $username=$row["aname"];}

	echo json_encode(array('message' => 'Logging Successfully !!','status' => true , 'user' => $username));
	}
	else
		echo json_encode(array('message' => 'Incorrect Password !!','status' => false));
}
else
	echo json_encode(array('message' => 'Service Unavailable !!','status' => false));

	
}
?>