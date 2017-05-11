package org.java.cerceve.sliderShow;

/*Slider şovu için hazırladığımız sınıfımız
 * 
 *	03.05.2017 Abdullah ÇELİK
 * */
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.java.cerceve.dataTransfer.MyImageArray;
import org.java.cerceve.main.SensorValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SlideShow extends JFrame{
	JLabel pic;
	JTextField resimSira;
    Timer tm;
    int x=0;
    
    SensorValue mySensor = new SensorValue();
    ArrayList<MyImageArray> imageArrays= new ArrayList<MyImageArray>();
    
    // kendi değişkenimiz olan MyImageArray nesnemizi burda türetiyoruz ve dataTrasnfer paketinde
    // web serviceden karşılayıp kaydettiğimiz verileri bu metoda yönlendirip slider şovumuz yapabilelim
	public SlideShow(ArrayList<MyImageArray> imagelist) {
		super("Java SliderShow");
		imageArrays = imagelist;
        pic=new JLabel();
        pic.setBounds(0,0,480,320);
        setImageShow(x); //resmi gelen position bilgisine göre işledik
        
        // 1000 = 1s olmak üzere zamana bağlı olarak slideri hareket ettiriyoruz 
        tm= new Timer(3000,new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
        	   setImageShow(x);
               // gelen değer sağ ise x++ sol ise x-- olarak yönlendircez
        	   // true gelmiş ise sağa dön yani x++
        	   if(sensorYon()){
        		   System.out.println(sensorYon());
        		   x++;
        		   if(x>=imageArrays.size())
                       x=0;
        	   }else {
        		   //false gelmiş ise değerimiz sola doğru hareket ettiriyoruz
        		   System.out.println(sensorYon());
        		   x--;
        		   if(x<0)
                       x=imageArrays.size()-1;
        	   }
        	   
               
           }
        });
        add(pic);
        tm.start();
        setLayout(null);
        setSize(480,320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	// position bilgisi alarak resmi frame içine ekleyen metot
	public void setImageShow(int position){
		MyImageArray imageArray = imageArrays.get(position);
        ImageIcon icon = new ImageIcon(imageArray.getYarismaciImage());
        Image img=icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(),pic.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon newImc=new ImageIcon(newImg);
        pic.setIcon(newImc);
    }
	
	// jsondan gelen veriyi okuyup sag yön ise "true" sol yön ise "false" dönderen değer
	public boolean sensorYon(){
		String jsonYon;
		boolean myYon = true;
		JSONParser parser = new JSONParser();
		jsonYon = mySensor.jsonSensor();
		
		try {
			JSONArray  jsonArr = (JSONArray) parser.parse(jsonYon.toString());
			System.out.println(jsonYon);
			
			JSONObject jsonObj = (JSONObject) jsonArr.get(0);
			jsonYon = (String) jsonObj.get("sliderYon");
			System.out.println(jsonYon);
			// gelen değer 1 ise sağa gitmesi gerekiyor yani true değer göndereceğiz
			if(jsonYon.equals("1")){
				myYon = true;
			}else{
				myYon = false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myYon;
	}
}
