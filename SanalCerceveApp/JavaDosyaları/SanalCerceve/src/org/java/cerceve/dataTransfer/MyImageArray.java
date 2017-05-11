package org.java.cerceve.dataTransfer;

/*bu sınıfımızda web serviceden gelen verileri saklayabileceğimiz yeni bir değişken oluşturduk
 * bu değişkenimizde image urlsini string olarak işliyeceğiz
 * 
 * 03.05.2017 Abdullah ÇELİK
 * */
import java.awt.Image;

public class MyImageArray {
	
	private String yarismaciName;
	
	private String yarismaciGrup;
	
	//private String yarismaciImage;
	
	private Image yarismaciImage;
	
	public MyImageArray() {
	}
	
	public MyImageArray(String yarismaciName, String yarismaciGrup, Image yarismaciImage){
	     super();
	     this.setYarismaciName(yarismaciName);
	     this.setYarismaciGrup(yarismaciGrup);
	     this.setYarismaciImage(yarismaciImage);

	 }

	public Image getYarismaciImage() {
		return yarismaciImage;
	}

	public void setYarismaciImage(Image yarismaciImage) {
		this.yarismaciImage = yarismaciImage;
	}

	public String getYarismaciGrup() {
		return yarismaciGrup;
	}

	public void setYarismaciGrup(String yarismaciGrup) {
		this.yarismaciGrup = yarismaciGrup;
	}

	public String getYarismaciName() {
		return yarismaciName;
	}

	public void setYarismaciName(String yarismaciName) {
		this.yarismaciName = yarismaciName;
	}
}
