<?php
require_once "image.php";

$image = new image();
$response = array();
$response = $image->getImage();
echo json_encode($response);

?>
