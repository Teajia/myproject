/*
 * MRNetSDK.java
 *
 * Created on 2019-02-28
 * 
 * @author zhufeng
 */


package com.example.demo.common;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
//import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.NativeLongByReference;
import com.sun.jna.win32.StdCallLibrary;

import com.example.demo.common.MRNetSdk.MRAVSTREAM_T;


//SDK接口说明,MRNetSdk.dll
public interface MRNetSdk extends StdCallLibrary {

	MRNetSdk INSTANCE = (MRNetSdk) Native.loadLibrary("mrptzdll\\MRNetSdk",
			MRNetSdk.class);
	
    /***宏定义***/
    //常量

    /**********************云台控制命令 begin*************************/
    public static final String MR_PTZCMD_LEFT_START = "left_start";	/* 左开始 命令*/
    public static final String MR_PTZCMD_LEFT_STOP = "left_stop";	/* 左停止命令 */
    public static final String MR_PTZCMD_LEFTUP_START = "leftup_start";	/* 左上开始命令 */
    public static final String MR_PTZCMD_LEFTUP_STOP = "leftup_stop";	/* 左上停止命令 */
    public static final String MR_PTZCMD_LEFTDOWN_START = "leftdown_start";	/* 左下开始命令*/
    public static final String MR_PTZCMD_LEFTDOWN_STOP = "leftdown_stop";	/* 左下停止命令 */
    public static final String MR_PTZCMD_RIGHT_START = "right_start";	/* 右开始命令 */
    public static final String MR_PTZCMD_RIGHT_STOP = "right_stop";	/* 右停止命令 */
    public static final String MR_PTZCMD_RIGHTUP_START = "rightup_start";	/* 右上开始命令 */
    public static final String MR_PTZCMD_RIGHTUP_STOP = "rightup_stop";	/* 右上停止命令 */
    public static final String MR_PTZCMD_RIGHTDOWN_START = "rightdown_start"; /* 右下开始命令 */
    public static final String MR_PTZCMD_RIGHTDOWN_STOP = "rightdown_stop"; /* 右下停止命令 */
    public static final String MR_PTZCMD_UP_START = "up_start"; /* 向上开始命令 */
    public static final String MR_PTZCMD_UP_STOP = "up_stop"; /* 向上停止命令 */
    public static final String MR_PTZCMD_DOWN_START = "down_start";	/* 向下开始命令 */
    public static final String MR_PTZCMD_DOWN_STOP = "down_stop";	/* 向下停止命令 */
    public static final String MR_PTZCMD_FOCUS_ADD_START = "focusadd_start";	/* 光圈开始变大命令*/
    public static final String MR_PTZCMD_FOCUS_ADD_STOP = "focusadd_stop";	/* 光圈停止变大命令*/
    public static final String MR_PTZCMD_FOCUS_DEC_START = "focusdec_start";	/* 光圈开始减小命令*/
    public static final String MR_PTZCMD_FOCUS_DEC_STOP = "focusdec_stop";	/* 光圈停止变小命令 */
    public static final String MR_PTZCMD_ZOOM_ADD_START = "zoomadd_start";	/* 焦距开始变大命令 */
    public static final String MR_PTZCMD_ZOOM_ADD_STOP = "zoomadd_stop";	/* 焦距停止变大命令 */
    public static final String MR_PTZCMD_ZOOM_DEC_START = "zoomdec_start";	/* 焦距开始减小命令 */
    public static final String MR_PTZCMD_ZOOM_DEC_STOP = "zoomdec_stop";	/* 焦距停止变小命令*/
    public static final String MR_PTZCMD_QUERY_POS = "query_pos";	/* 查询当前位置*/
    public static final String MR_PTZCMD_GO_HOME = "go_home";	/* 走HOME位置，角度和焦距回到原始位置*/

    /**********************云台控制命令 end*************************/

   //定义结构体MRAVSTREAM_T
    public static class MRAVSTREAM_T extends Structure {
        public int id;
        public int duration;
        public int codec_type;
        public int codec_id;
        
        public int nWidth;
        public int nHeight;
        public int nFrameRate;
        public int nBitRate;
        public int nSampleRate;
        public int nSampleBits;
        public int nChannel;
        public int framenum;
        public String extradata;
        public int extradata_size;
       
    }

    
 /***API函数声明,详细说明见API手册***/

 //设备进行初始化   
 int MRNSDK_Init(int i);
 
 //对摄像头开始预览
 int MRNSDK_LiveSingle(NativeLongByReference pHandle,String szIP,int wPort,int protocol,String szUserName,
		 String szPassword,String szDeviceID,String szStreamName,int nTimeoutMs);
 
//获取摄像头流数
 int MRNSDK_Stream_GetNums(NativeLong lStreamHandle);
 
//获取摄像头流信息
 int MRNSDK_Stream_GetStream(NativeLong lStreamHandle, int nb_Videos,MRAVSTREAM_T stream);
 
//对摄像头开始预览窗口进行设置，设置为NULL
 //int MRNSDK_Stream_SetVideoWnd(NativeLong lStreamHandle,int nb_Videos,HWND pWnd);
 
//启动摄像头流
 int MRNSDK_Stream_Start(NativeLong lStreamHandle);
 
//停止摄像头流
 int MRNSDK_Stream_Stop(NativeLong lStreamHandle);
 
//摄像头抓拍
 int MRNSDK_Stream_CapJpeg(NativeLong lStreamHandle,int nIndex,int nQuality,String szFileName);
 
//摄像头云台控制
 int MRNSDK_PtzControl(String IP,int IPort,String userName,String passWord,String deviceId,int timeOut,int chanel,String mrPtzcmd,byte byValue,Pointer pExtParam);

}




