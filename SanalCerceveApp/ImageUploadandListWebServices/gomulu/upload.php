<?php
/**
*
*/
require_once "database.php";

class img_upload
{

	private $db;

	public function __construct()
	{
		$this->db = new database("mysql:host=localhost;dbname=****;charset=utf8","***","sifre");
		if(!$this->db){
			echo "404 Error";
			die();
		}
	}

	public function imageUpload(){

		$allow = array("jpg", "jpeg", "gif", "png");


			$todir = 'images/';

			if ( !!$_FILES['file']['tmp_name'] ) // is the file uploaded yet?
			{
				echo $_FILES['file']['name'];
			    $info = explode('.', strtolower( $_FILES['file']['name']) ); // whats the extension of the file

				if ( in_array( end($info), $allow) ) // is this file allowed
				{
				    if ( move_uploaded_file( $_FILES['file']['tmp_name'], $todir . basename($_FILES['file']['name'] ) ) )
				    {
				            return basename($_FILES['file']['name']);
				    }
				}
				else
				{
				    echo "hata";
				        // error this file ext is not allowed
				}
			}
	}


	public function add(){
		$image = $this->imageUpload();
		unset($_FILES);
		$this->db->insertArray("image", [
				"img_name" => $image
			]);
		header('Location: http://erhankulekci.com/gomulu/upload_view.php');
	}


}

$run = new img_upload();
$run->add();




?>
