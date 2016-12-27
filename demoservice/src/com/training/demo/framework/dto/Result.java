package com.training.demo.framework.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {
	public Result() {
		clearProperties();
	}

	private boolean state = false;
	private String msgCode = "";
	private String msgDesc = "";
	private ArrayList<Long> longResult = new ArrayList<Long>();
	private ArrayList<String> stringResult = new ArrayList<String>();

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

	public ArrayList<Long> getLongResult() {
		return longResult;
	}

	public void setLongResult(ArrayList<Long> longResult) {
		this.longResult = longResult;
	}

	public ArrayList<String> getStringResult() {
		return stringResult;
	}

	public void setStringResult(ArrayList<String> stringResult) {
		this.stringResult = stringResult;
	}

	private void clearProperties() {
		state = false;
		msgCode = "";
		msgDesc = "";
		longResult = new ArrayList<Long>();
		stringResult = new ArrayList<String>();
	}
}
