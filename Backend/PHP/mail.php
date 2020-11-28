<?php
   use PHPMailer\PHPMailer\PHPMailer; 
   use PHPMailer\PHPMailer\Exception; 
  header('Content-Type:application/json');
  header('Access-Control-Allow-Origin: *');
  header('Access-Control-Allow-Method: POST');


$data= json_decode(file_get_contents("php://input"),true);
require 'vendor/autoload.php'; 
$mail = new PHPMailer(true); 
$con=mysqli_connect('localhost','root','','map_data');
    if($con==false)
    {
      echo("Connection is failed");
    }
     try { 
        
		 $m=$data["mail"];
         $rand=rand(100000,999999);     
          
        $sql="SELECT  `f_name`, `password`, `email` FROM `user` WHERE email='{$m}'";
		$result=mysqli_query($con,$sql) ;
		if($result)
		{
			
		if ($result->num_rows > 0){
         $sql="UPDATE `user` SET Password='$rand' WHERE Email='$m'";
		 
         $run=mysqli_query($con,$sql)or die("Na ho paya");

         $mail->isSMTP();                                             
         $mail->Host       = 'smtp.gmail.com;';                     
         $mail->SMTPAuth   = true;                              
         $mail->Username   = 'patelpruthvi00006@gmail.com';                  
         $mail->Password   = 'pruthvi0007';                         
         $mail->SMTPSecure = 'tls';                               
         $mail->Port       = 587;   
       
         $mail->setFrom('from@gfg.com', 'pruthvi');            
         $mail->addAddress($m); 
        
         $mail->isHTML(true);                                   
         $mail->Subject = 'Password Change'; 
         $mail->Body    = 'your one time password is <b>'.$rand.'</b> Click on <a href="file:///C:/Users/Pruthvi/Desktop/MAP%20INOVATIVE/map/forgot_password.html"> Click</a> to Reset yout password'; 
         $mail->AltBody = 'Body in plain text for non-HTML mail clients'; 
         $mail->send(); 
          echo json_encode(array('message' => 'Kindly Check Your Mail !!','status' => true));   
		}
		else
			echo json_encode(array('message' => 'Plese Enter Email which connected to the site !!','status' => true));
		}
		else
		echo json_encode(array('message' => 'Plese Enter Email which connected to the site !!','status' => true));
     } 
     catch (Exception $e) { 
        echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}"; 
     } 
   


   
?>