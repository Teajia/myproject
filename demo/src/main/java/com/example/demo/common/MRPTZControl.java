/*
 * MRNetSDK.java
 *
 * Created on 2019-02-28
 * 
 * @author zhufeng
 */
package com.example.demo.common;

import com.example.demo.common.DateTimeUnits;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.NativeLongByReference;

import com.example.demo.common.MRNetSdk;

public class MRPTZControl {

	static MRNetSdk mrNetSdk = MRNetSdk.INSTANCE;
	
	
	//初始化明日摄像头设备
	public void InitPTZ() {
		
		 int initStu = mrNetSdk.MRNSDK_Init(1);
         if (initStu != 0)
         {
             System.out.println("初始化失败！");
         } else {
        	 
        	 System.out.println("初始化成功！"); 
         }
	}
	
	
	//登录云台设备，控制云台设备
	public void PTZControl(String IP, int IPort, String userName,String passWord,
			String deviceId, int timeOut, int chanel, String mrPtzcmd,byte byValue,Pointer pExtParam) {
		
	        int controlStu = mrNetSdk.MRNSDK_PtzControl(IP, IPort, userName, passWord, deviceId, timeOut, chanel, mrPtzcmd, byValue, pExtParam);

	        if (controlStu != 0)
	        {
	        	
	           System.out.println("云台控制失败");
	        } else {
	        	
	           System.out.println("云台控制成功");
	        }
	        
	}
	
	//对云台设备进行视频预览，设置视频窗口(将视频窗口置为NULL)，视频流开启开始
		public void PTZLiveSingle(NativeLongByReference pHandle,String szIP,int wPort,int protocol,String szUserName,
				 String szPassword,String szDeviceID,String szStreamName,int nTimeoutMs) {
			
			int liveSingleStu = mrNetSdk.MRNSDK_LiveSingle(pHandle, szIP, wPort, protocol, szUserName, 
					szPassword, szDeviceID, szStreamName, nTimeoutMs);
			if (liveSingleStu != 0)
	        {
	        	
	           System.out.println("摄像头预览开始失败");
	        } else {
	        	
	           System.out.println("摄像头预览开始成功");
	        }
		}
	
	
	//对云台设备进行视频预览后，设置视频窗口(将视频窗口置为NULL)，视频流开启开始
	public void PTZVideoStreamStart(NativeLong m_lHandle) {
		
		
		//int videoWndStu = mrNetSdk.MRNSDK_Stream_SetVideoWnd(m_lHandle, 0, null);

		int streamStartStu = mrNetSdk.MRNSDK_Stream_Start(m_lHandle);

		if (streamStartStu != 0)
	        {
	        	
	           System.out.println("摄像头视频流开始失败");
	        } else {
	        	
	           System.out.println("摄像头视频流开始成功");
	        }
		
	}
	
	//对云台设备进行视频预览后，设置视频窗口(将视频窗口置为NULL)，视频流开启开始后停止
	public void PTZVideoStreamStop(NativeLong m_lHandle) {
			
		int streamStopStu = mrNetSdk.MRNSDK_Stream_Stop(m_lHandle);
			
		if (streamStopStu != 0)
		    {
		        	
		        System.out.println("摄像头视频流停止失败");
		    } else {
		        	
		    	System.out.println("摄像头视频流停止成功");
		    }
	}
	
	//对云台设备进行视频预览后，设置视频窗口(将视频窗口置为NULL)，视频流开启开始后进行截图
	public String CapPicture(NativeLong m_lHandle,String imgAddr) {
		
		String pictureUrl = imgAddr + DateTimeUnits.getNowMs() + ".jpg";
		int capJpegStu = mrNetSdk.MRNSDK_Stream_CapJpeg(m_lHandle, 0, 100, pictureUrl);
				
		if (capJpegStu != 0)
			{
			      	
			    System.out.println("摄像头截图失败");
			} else {
			        	
				System.out.println("摄像头截图成功");
			}
		return pictureUrl;
	}
		
	//控制云台设备的焦距和方向,截图
	public String PtzControlDirctFocusPicture(String IP, int IPort, String userName,String passWord,String szStreamName) {
		
		String imgAddr = "D:\\";
		String picUrl = null;
		NativeLong lStreamHandle = new NativeLong(0);
		NativeLongByReference pHandle = new NativeLongByReference(lStreamHandle);
		
		try {
			
			InitPTZ();
			PTZLiveSingle(pHandle,IP,IPort,0,userName,passWord,null,szStreamName,2000);
			PTZVideoStreamStart(pHandle.getValue());
			
			//每次进行云台控制和截图时，对摄像头位置和焦距进行复原，回到初始化位置
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_GO_HOME,(byte)100,null);
			
			//摄像头向下偏转一定角度，然后停止，抓拍
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_DOWN_START,(byte)100,null);
			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_DOWN_STOP,(byte)100,null);
			Thread.sleep(1000);
			String p1 = CapPicture(pHandle.getValue(),imgAddr);
			picUrl += ";" + p1;
			
			//摄像头向左偏转一定角度，然后停止，抓拍
			Thread.sleep(1000);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_LEFT_START,(byte)100,null);
			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_LEFT_STOP,(byte)100,null);
			Thread.sleep(1000);
			String p2 = CapPicture(pHandle.getValue(),imgAddr);
			picUrl += ";" + p2;
			//摄像头向右偏转一定角度，然后停止，抓拍
			Thread.sleep(1000);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_RIGHT_START,(byte)100,null);
			Thread.sleep(1000);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_RIGHT_STOP,(byte)100,null);
			Thread.sleep(1000);
			String p3  = CapPicture(pHandle.getValue(),imgAddr);
			picUrl += ";" + p3;
			//摄像头向右偏转停止抓拍后，进行变焦，抓拍
			Thread.sleep(1000);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_ZOOM_ADD_START,(byte)100,null);
			Thread.sleep(1000);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_ZOOM_ADD_STOP,(byte)100,null);
			Thread.sleep(1000);
			String p4 = CapPicture(pHandle.getValue(),imgAddr);  
			picUrl += ";" + p4;
			//摄像头回到初始位置，摄像头向下偏转一定角度，然后停止，再进行变焦，抓拍
			Thread.sleep(1000);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_GO_HOME,(byte)100,null);

			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_DOWN_START,(byte)100,null);
			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_DOWN_STOP,(byte)100,null);
			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_ZOOM_ADD_START,(byte)100,null);
			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_ZOOM_ADD_STOP,(byte)100,null);
			Thread.sleep(1000);
			String p5 = CapPicture(pHandle.getValue(),imgAddr); 
			picUrl += ";" + p5;
			//摄像头回到初始位置，摄像头向下偏转一定角度，然后停止，向左一定角度，再进行变焦，抓拍
			Thread.sleep(1000);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_GO_HOME,(byte)100,null);
			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_DOWN_START,(byte)100,null);
			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_DOWN_STOP,(byte)100,null);
			
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_LEFT_START,(byte)100,null);
			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_LEFT_STOP,(byte)100,null);
			
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_ZOOM_ADD_START,(byte)100,null);
			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_ZOOM_ADD_STOP,(byte)100,null);
			Thread.sleep(1000);
			String p6 = CapPicture(pHandle.getValue(),imgAddr); 

			picUrl += ";" + p6;
			//摄像头回到初始化位置，并摄像头调节到一定角度，不抓拍，再断流
			Thread.sleep(1000);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_GO_HOME,(byte)100,null);
			Thread.sleep(500);

			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_DOWN_START,(byte)100,null);
			Thread.sleep(500);
			PTZControl(IP,IPort,userName,passWord,null,1000,0,MRNetSdk.MR_PTZCMD_DOWN_STOP,(byte)100,null);
				
			PTZVideoStreamStop(pHandle.getValue());
				
		} catch (Exception e) {
				
		}
			
		return picUrl;
	}
}
