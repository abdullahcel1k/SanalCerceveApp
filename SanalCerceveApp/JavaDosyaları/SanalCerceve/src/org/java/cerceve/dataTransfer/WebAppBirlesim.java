package org.java.cerceve.dataTransfer;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/*
*Bu sınıfımız ile gelen verileri karşılayıp array'imize ekliyeceğiz
*
*	03.05.2017 Abdullah ÇELİK
*/
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/*
*Bu sınıfımız ile gelen verileri karşılayıp array'imize ekliyeceğiz
*
*	03.05.2017 Abdullah ÇELİK
*/
public class WebAppBirlesim {

	ArrayList<MyImageArray> imageArrays= new ArrayList<MyImageArray>();
	String jsonData;
	WebServiceClient web = new WebServiceClient();
	
	// gelen verileri internet bağlantısı problemi olduğu zamanlarda
	// çalışabilmesi için text dosyaya kaydedeceğiz bunun için input değişkenimizi oluşturduk
	Scanner input = new Scanner(System.in);
	File file = new File("imagelist.txt");
	
	// bu metot ile web serviceden gelen responsemizi işleyip array listimiz içerisinde kaydedeceğiz
	public ArrayList<MyImageArray> WebAppBirlesim() {
		jsonData = web.jsonData();
		try (PrintWriter writer = new PrintWriter(file)){
			writer.print(jsonData);
			//System.out.println(jsonData);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		
		// dosya başarı ile oluşturuldu ve devam etmek için enter'a basılamsını bekliyoruz
		System.out.println("Herhangi bir problem ile karşılaşmadık..\n\n Devam etmek için enter tuşuna basınız!");
		input.nextLine();
		try {
			input = new Scanner(file);
			StringBuilder jsonIn = new StringBuilder();
			while(input.hasNextLine()){
				jsonIn.append(input.nextLine());
			}
			System.out.println(jsonIn.toString());
			JSONParser parser = new JSONParser();
		
			JSONArray  jsonArr = (JSONArray) parser.parse(jsonIn.toString());
			
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject jsonObj = (JSONObject) jsonArr.get(i);
				MyImageArray images = new MyImageArray();
				String imageUrl = (String) jsonObj.get("img_name");
				//String nameIn = (String) jsonObj.get("name");
				String nameIn = "deneme";
				//String grupName = (String) jsonObj.get("groups");
				String grupName = "deneme";
				// imageler yükledigimiz isim ile kayıt edildiği için burada resimlerin bulunduğu
				// url adresini string birleştirme ile ekledik
				imageUrl = "http://erhankulekci.com/gomulu/images/"+imageUrl;
				System.out.println(imageUrl);
				images.setYarismaciName(nameIn);
				images.setYarismaciGrup(grupName);
				images.setYarismaciImage(imageloader(imageUrl));
				
				imageArrays.add(images);
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return imageArrays;
	}
	
	
	// urlsi verilen resmi image dosyasına dönüştüren metod
	public static Image imageloader(String url){
		Image image = null;
		URL urlURL;
        try {
        	urlURL = new URL(url);
            image = ImageIO.read(urlURL);  
        } catch (IOException e) {
        	e.printStackTrace();
        }
		return image;
	}
	
}

