<?php
  require_once "gomuluVT.php";
  $gomulu  = new Gomulu();

  $response = "";
  $yon = 0;
  if(isset($_GET["YON"])){
    $key = $_GET["YON"];
    if($key == "SAG"){
      $yon = 1;
      if($gomulu->yonBelirle($yon)){
        $response = "SAG";
      }else{
        $response = "HATA";
      }
    }else{
      $yon = 0;
      if($gomulu->yonBelirle($yon)){
        $response = "SOL";
      }else{
        $response = "HATA";
      }
    }
    echo json_encode($response);
  }else{
    $cevap = $gomulu->yonBul();
    echo json_encode($cevap);
  }
 ?>
