package com.flower.core.utils;

import java.io.Serializable;

public class Data implements Serializable{
	private static final long serialVersionUID=1L;
	private Boolean notIsUserCode;
	private Boolean allIsRight;
	public Boolean getNotIsUserCode() {
		return notIsUserCode;
	}
	public void setNotIsUserCode(Boolean notIsUserCode) {
		this.notIsUserCode = notIsUserCode;
	}
	public Boolean getAllIsRight() {
		return allIsRight;
	}
	public void setAllIsRight(Boolean allIsRight) {
		this.allIsRight = allIsRight;
	}
	
	
	
	
}
