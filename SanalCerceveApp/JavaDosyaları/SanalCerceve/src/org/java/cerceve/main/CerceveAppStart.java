package org.java.cerceve.main;

/*Yapıları tetiklediğimiz sınıfımız bu sınıf ile tetiği çekiyoruz :)
 * 
 *	  03.05.2017 Abdullah ÇELİK
 * */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import org.java.cerceve.dataTransfer.MyImageArray;
import org.java.cerceve.dataTransfer.WebAppBirlesim;
import org.java.cerceve.dataTransfer.WebServiceClient;
import org.java.cerceve.sliderShow.SlideShow;

public class CerceveAppStart {
	static Timer tm;
	static ArrayList<MyImageArray> imageArray;
	static WebServiceClient webServiceClient;
	static String uz;
	static WebAppBirlesim appBirlesim = new WebAppBirlesim();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		imageArray =  appBirlesim.WebAppBirlesim();
		SlideShow slideShow = new SlideShow(imageArray);
	}
}

