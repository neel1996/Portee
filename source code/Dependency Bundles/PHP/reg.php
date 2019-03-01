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



$name = $_POST['fname'];
$email = $_POST['email'];
$password = $_POST['pass'];
$mobile = $_POST['mob'];

$len = (int) strlen($email);
$len_1 = (int) strlen($mobile);
$mel = (int) ($len + $len_1);


	
	$db_host="localhost";
	$db_username="root";
	$db_pass="";
	$db_name="user";
	
	$con = mysqli_connect("$db_host","$db_username","$db_pass") or die("ERROR DB INI");
	mysqli_select_db($con,"$db_name") or die("ERROR DB CONNECT");
	//$res = mysql_query("SELECT * FROM usertab WHERE id='1'");
	//echo $res;
	$confirm_code = getToken($mel);
	$newcr = getToken(6);
	//mysqli_query($con,"INSERT INTO malt(name,email,password,mobile,secure) VALUES('$name','$email','$password','$mobile','$confirm_code')");
	mysqli_query($con,"INSERT INTO usertab(name,email,password,mobile,key_1,share_key,mac) VALUES('$name','$email','$password','$mobile','$confirm_code','$newcr','')");
	echo $mel.$name.$email.$mobile.$confirm_code;
	
	
	//exec('set path=C:\Program Files\Java\jre1.8.0_121\bin');
	//java -cp com.mysql.jdbc_5.1.5.jar;. Core asasas asasda sdfsdf asdfasda asdas
	
	$que = 'java -cp com.mysql.jdbc_5.1.5.jar;. Core '.$email.' '.$mobile.' '.$newcr.' '.$name.' '.$confirm_code.'';
	system($que, $output);
	echo $output;

	
	echo $newcr;
	

?>