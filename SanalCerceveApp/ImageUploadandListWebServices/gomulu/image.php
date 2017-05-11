<?php
require_once "database.php";

/**
Üniversite verilerini çekmek için yazılan webservice dosyası.
**/
class image
{
	private $db;
	private $json;
	function __construct()
	{
		$this->db = new Database("mysql:host=localhost;dbname=*****;charset=utf8","***","***");
		if(!$this->db){
			echo "404 Error";
			die();
		}
	}

	public function getImage(){
		$response = $this->db->query("SELECT img_name FROM image")->fetchAll(PDO::FETCH_ASSOC);
		//$response = $this->db->select("university",);
		//$this->json["image"] = $response;
		return $response;
	}

}

?>
