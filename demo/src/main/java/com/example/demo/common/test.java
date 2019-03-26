package com.example.demo.common;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.NativeLongByReference;

public class test {
	public static void main(String args[]) throws InterruptedException {
		MRPTZControl res = new MRPTZControl();
		String IP = "192.168.4.46";
		int serverPort = 3000;
		String userName = "admin";
		String passWord = "lg123456";
		String szStreamName = "live/av0";
		
		NativeLong lStreamHandle = new NativeLong(0);
		NativeLongByReference pHandle = new NativeLongByReference(lStreamHandle);		
		
		String url = res.PtzControlDirctFocusPicture(IP, serverPort, userName, passWord, szStreamName);
		System.out.println(url);
		 
	}

}

