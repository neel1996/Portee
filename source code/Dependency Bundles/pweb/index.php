<?php
	function crypto_rand_secure($min, $max)
	{
		$range = $max - $min;
		if ($range < 1) return $min; // not so random...
		$log = ceil(log($range, 2));
		$bytes = (int) ($log / 8) + 1; // length in bytes
		$bits = (int) $log + 1; // length in bits
		$filter = (int) (1 << $bits) - 1; // set all lower bits to 1
		do {
			$rnd = hexdec(bin2hex(openssl_random_pseudo_bytes($bytes)));
			$rnd = $rnd & $filter; // discard irrelevant bits
		} while ($rnd >= $range);
		return $min + $rnd;
	}

	function getToken($length)
	{
		$token = "";
		$codeAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		$codeAlphabet.= "abcdefghijklmnopqrstuvwxyz";
		$codeAlphabet.= "0123456789";
		$max = strlen($codeAlphabet) - 1;
		for ($i=0; $i < $length; $i++) {
			$token .= $codeAlphabet[crypto_rand_secure(0, $max)];
		}
		return $token;
	}
	
	ini_set('display_errors','off');



$name = $_POST['fname'];
$email = $_POST['email'];
$password = $_POST['pass'];
$mobile = $_POST['mob'];


	
	$db_host="127.0.0.1";
	$db_username="root";
	$db_pass="";
	$db_name="user";
	
	$con = mysqli_connect("$db_host","$db_username","$db_pass") or die("ERROR DB INI");
	mysqli_select_db($con,"$db_name") or die("ERROR DB CONNECT");
	
	date_default_timezone_set('Asia/Kolkata');

	if( $name != " " && $email != " " && $password != " " && $mobile != " " )
	{
		
		$sql = "SELECT * FROM usertab where email='$email'";
		
		$result = mysqli_query($con,$sql);
		
		$checkRow = mysqli_num_rows($result);
		
		
		if($checkRow > 0)
		{
			$errors['email'] = 'Email Already Exists';
		}
		
		else
		{
			$confirm_code = getToken(25);
		
			mysqli_query($con,"SELECT * from usertab where email='$email'");
			mysqli_query($con,"INSERT INTO usertab(name,email,password,mobile,key) values('$name','$email',$password,'$mobile','$confirm_code')");
			
			$data['success'] = false;
			$data['message'] = 'Thanks YO Yo...';
			
			mysqli_close($con);				 
		}
		
	}

?>