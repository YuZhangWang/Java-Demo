package com.wbhz.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class FlowerAction {
	private String flowerName;
	private File flowerPic;
	private String flowerPicContentType;
	private String flowerPicFileName;
	public String getFlowerName() {
		return flowerName;
	}
	public void setFlowerName(String flowerName) {
		this.flowerName = flowerName;
	}
	public File getFlowerPic() {
		return flowerPic;
	}
	public void setFlowerPic(File flowerPic) {
		this.flowerPic = flowerPic;
	}
	public String getFlowerPicContentType() {
		return flowerPicContentType;
	}
	public void setFlowerPicContentType(String flowerPicContentType) {
		this.flowerPicContentType = flowerPicContentType;
	}
	public String getFlowerPicFileName() {
		return flowerPicFileName;
	}
	public void setFlowerPicFileName(String flowerPicFileName) {
		this.flowerPicFileName = flowerPicFileName;
	}
	
	//文件上传的功能
	public String upload(){
		System.out.println("flowerName:"+flowerName);
		String path = ServletActionContext.getServletContext().getRealPath("/images");
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();//如果文件夹不存在，创建文件夹
		}
		try {
			FileUtils.copyFile(flowerPic, new File(file, flowerPicFileName));//文件复制
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
}
