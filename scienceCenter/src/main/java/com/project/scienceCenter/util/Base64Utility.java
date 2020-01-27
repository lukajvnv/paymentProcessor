package com.project.scienceCenter.util;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Utility {
	//pre korisceno
	 //Pomocna funkcija za enkodovanje bajtova u string
//	 public static String encode(byte[] data){
//		 BASE64Encoder encoder = new BASE64Encoder();
//		 return encoder.encode(data);
//	 }
//	 
//	 //Pomocna funkcija za dekodovanje stringa u bajt niz
//	 public static byte[] decode(String base64Data) throws IOException{
//		 BASE64Decoder decoder = new BASE64Decoder();
//		 return decoder.decodeBuffer(base64Data);
//	 }
	 
	 //updateovano
	//Pomocna funkcija za enkodovanje bajtova u string
		 public static String encode(byte[] data){
			Encoder encoder = Base64.getEncoder();
			 return encoder.encodeToString(data);
		 }
		 
		 //Pomocna funkcija za dekodovanje stringa u bajt niz
		 public static byte[] decode(String base64Data) throws IOException{
			 Decoder decoder = Base64.getDecoder();
			 return decoder.decode(base64Data);
		 }
}
