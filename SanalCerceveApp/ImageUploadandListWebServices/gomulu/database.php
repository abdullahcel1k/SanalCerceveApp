<?php


/**
* Database sorgularını her seferinde yazmak yerinde burada oluşturduğumuz dinamik database sınıfı
*/
class Database extends Pdo
{
	public function construct($dsn, $user, $password)
	{
		parent::__construct($dsn, $user, $password);
           
	}

	public function select($tableName,$whichValues = "*",$where = "1",$fetchMode = "fetchAll"){

		$sql = "SELECT " .$whichValues. " FROM " . $tableName . " WHERE " . $where;
		$array = $this->query($sql)->{$fetchMode}(PDO::FETCH_ASSOC);
		return $array;
	}

	public function delete($tableName,$where){
		$sql = "DELETE FROM " .$tableName . "  WHERE " .$where;
		return $this->query($sql);
	}

	
 	public function insert($tableName,$tableValues,$values){
	//	$values = addcslashes($values);
		$sql = "INSERT INTO " . $tableName . "(" . $tableValues . ") VALUES (" . $values .")";
		
		return $this->query($sql);


	}

    
	public function update($tableName,$setValues,$where){

		$sql = "UPDATE " .$tableName . " SET " . $setValues . " WHERE " .$where;
	
		

	}

	public function updateArray($tableName,$array,$where){
		$i = 0;
		$sql = "UPDATE " . $tableName . " SET ";
		foreach ($array as $key => $value) {
			if($value != ""){
				if($i == 0)
					$sql .= "" . $key . " = '" . $value . "'";
				else
					$sql .= "," . $key . " = '" . $value . "'";
			}
			$i++;
		}

		$sql .= " WHERE " . $where;
		return $this->query($sql);

	} //Array şeklinde update

	
    public function insertArray($tableName, $data){

        $fieldKeys = implode(",", array_keys($data));
        $fieldValues = ":" . implode(", :", array_keys($data));
        $sql = "INSERT INTO $tableName($fieldKeys) VALUES($fieldValues)";
      
        $sth = $this->prepare($sql);
        foreach ($data as $key => $value) {
            $sth->bindValue(":$key", $value);
        }
        if($sth->execute())
        	return true;
        else
        	return false;

    } //Array şeklinde insert

	public function callProcedure($sql)
	{	
		
		return $this->query($sql)->fetchAll(PDO::FETCH_ASSOC);
	}

	
}

?>
