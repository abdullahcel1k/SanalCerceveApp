<?php


/**
 *
 */
class Gomulu
{
  private $conn;

  function __construct()
  {
    require_once 'connect.php';

    $db = new connect();
    $this->conn = $db->baglan();
  }

  public function yonBelirle($yon){
    $stmt = $this->conn->prepare("UPDATE gomulu SET sliderYon = :booleanYon WHERE id = 0");
    $result = $stmt->execute(array(':booleanYon' => $yon));
    return $result;
  }

  public function yonBul(){
    $stmt = $this->conn->prepare("SELECT sliderYon FROM gomulu");
    $stmt->execute();
    $result = $stmt->fetchAll();
    return $result;
  }
}
