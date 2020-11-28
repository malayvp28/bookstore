<?php
 header('Content-Type:application/json');
 header('Access-Control-Allow-Origin: *');
 header('Access-Control-Allow-Method: POST');


$data= json_decode(file_get_contents("php://input"),true);
$con= mysqli_connect("localhost","root","","map_data") or die("Connection Failed");
$pass=$data['pass'];
$rpass=$data['rpass'];
$otp=$data['otp'];
$mail=$data['email'];
$sql="SELECT  `f_name`, `password`, `email` FROM `user` WHERE email='{$mail}' and password='{$otp}' ";
$result=mysqli_query($con,$sql) ;
$username="";
if($result)
{
	if ($result->num_rows > 0){
		
		$sql="UPDATE `user` SET `Password`='{$pass}' WHERE `email`='{$mail}'";
        $result=mysqli_query($con,$sql) ;
		if($result)
		{
			echo json_encode(array('message' => 'Your Password`Updated !!','status' => true));	
		}
		else
			echo json_encode(array('message' => 'Failed !!','status' => false));	
		
		
	}
	else
	 echo json_encode(array('message' => 'Enter the valid OTP !!','status' => false));	
}
else
	echo json_encode(array('message' => 'Failed','status' => false));



?>