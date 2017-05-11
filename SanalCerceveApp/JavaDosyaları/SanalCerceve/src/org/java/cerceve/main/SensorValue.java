package org.java.cerceve.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*Bu sınıf ile sensörümüzden gelen değeri okuyup slider şovumuza göndereceğiz
 * 
 *	  06.05.2017 Abdullah ÇELİK
 * */
public class SensorValue {
	
	// url adresinden sensorden gelen json değerini çektiğimiz metot
		public String jsonSensor(){
			String output = null; // response return için değişken
			BufferedReader br;	// response dan okunan değeri saklamak için br türettik
			try {
				  //web service bağlanacağımız adresi tanımladık
			      URL url = new URL("http://abdullahcelik.com.tr/gomulu/gomuluservice.php");
			      //web service bağlantısını kurduk
			      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			      conn.setRequestMethod("GET"); //bağlantı tipini get/post belirttik
			      conn.setRequestProperty("Accept", "application/json"); // karşıladığımız datanın json fromatında olduğunu berlittik
			      
			      //responsede gelen mesaj 200 değilse bi hata var demek
			      if (conn.getResponseCode() != 200) {
			        throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
			      }
			      
			      // br ile okuduğumuz değeri karşıladık
			      br = new BufferedReader(new InputStreamReader(
			          conn.getInputStream()
			      ));
			      // br de karşıladığımız değeri metodumuzun dönüş tipine uyması için stringe çevirdik
			      output = br.readLine();
			      
			      // işlerimiz bittikden sonra bağlantıyı kapattık
			      conn.disconnect();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			// response String olarak geri döndürdük
			return output; 
		}
}
