<?php
require_once "config.php";
/**
 *
 */
class connect
{
  private $conn;
  function __construct()
  {
    # code...
  }

  public function baglan(){
    try {
      $conn = new PDO("mysql:hostname=".DB_HOST."; dbname=". DB_DATABASE. "; charset=utf8;", DB_USER, DB_PASSWORD);
    } catch (PDOException $e) {
      die($e->getMessage());
    }
    return $conn;
  }
}


?>
