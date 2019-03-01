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
		$codeAlphabet = "12345678901235748964139875";
		$codeAlphabet.= "57898741203256987410236554";
		$codeAlphabet.= "0123456789";
		$max = strlen($codeAlphabet) - 1;
		for ($i=0; $i < $length; $i++) {
			$token .= $codeAlphabet[crypto_rand_secure(0, $max)];
		}
		return $token;
	}
	
	//ini_set('display_errors','off');



$email = $_GET['email'];
$mobile = $_GET['mobile'];

$len = (int) strlen($email);
$len_1 = (int) strlen($mobile);
$mel = (int) ($len + $len_1);


	
	$db_host="localhost";
	$db_username="root";
	$db_pass="";
	$db_name="user";
	
	$con = mysqli_connect("$db_host","$db_username","$db_pass") or die("ERROR DB INI");
	mysqli_select_db($con,"$db_name") or die("ERROR DB CONNECT");
	$confirm_code = getToken($mel);
	
	$res = mysqli_query($con,"SELECT key_1 FROM usertab WHERE email='$email'");
	$fin = mysqli_fetch_array($res);
	$val = $fin['key_1'];
	echo $val.'<br>'.$confirm_code;
	
	
	mysqli_query($con,"UPDATE core SET prev_key='$val',cur_key='$confirm_code' WHERE enc_email='$email'"); 
	echo 'complete';
	
	//echo $mel.$name.$email.$mobile.$confirm_code;
	

?>