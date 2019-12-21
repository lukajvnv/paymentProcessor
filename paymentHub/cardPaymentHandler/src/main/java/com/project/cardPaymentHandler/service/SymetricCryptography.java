package com.project.cardPaymentHandler.service;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cardPaymentHandler.util.Base64Utility;

@Service
public class SymetricCryptography {

	private static final SecureRandom RANDOM = new SecureRandom();
    private static final byte[] IV =
        { 16, 74, 71, -80, 32, 101, -47, 72, 117, -14, 0, -29, 70, 65, -12, 74 };

	@Autowired
	private SecretKey key;
    
	/*
	 * public void testIt() { SecretKey secretKey; String data =
	 * "Ovo su podaci koji se kriptuju simetricnim AES algoritmom, duzina podataka nije bitna, tj. AES moze da se koristi za proizvoljnu duzinu podataka"
	 * ;
	 * 
	 * System.out.println("===== Primer simetricne AES sifre =====");
	 * System.out.println("Podaci koji se sifruju: " + data);
	 * 
	 * System.out.println("\n===== Generisanje kljuca ====="); secretKey =
	 * generateKey(); System.out.println("Generisan kljuc: " +
	 * Base64Utility.encode(secretKey.getEncoded()));
	 * 
	 * System.out.println("\n===== Sifrovanje ====="); byte[] cipherText =
	 * encrypt(data); System.out.println("Sifrat: " +
	 * Base64Utility.encode(cipherText));
	 * 
	 * System.out.println("\n===== Desifrovanje ====="); byte[] plainText =
	 * decrypt(cipherText); System.out.println("Originalna poruka: " + new
	 * String(plainText)); }
	 */
	
	public SecretKey generateKey() {
		//TODO: Generisati i vratiti AES kljuc duzine koju diktira najbolja praksa
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256, RANDOM); // for example
			return keyGen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return null;
	}
	
	public byte[] encrypt(String plainText) {
		//TODO: Sifrovati otvoren tekst uz pomoc tajnog kljuca koristeci konfiguraciju AES algoritma koju diktira najbolja praksa
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			// AlgorithmParameterSpec paramSpec=new PBEParameterSpec(salt,iterationCount);
	        AlgorithmParameterSpec paramSpec=new IvParameterSpec(IV);    //KONF?????
			cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec, RANDOM);
	        byte[] encrypted = cipher.doFinal(plainText.getBytes());
			return encrypted;
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NoSuchPaddingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidAlgorithmParameterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return null;
	}
	
	public byte[] decrypt(byte[] cipherText) {
		//TODO: Desifrovati sifru uz pomoc tajnog kljuca koristeci konfiguraciju AES algoritma koju diktira najbolja praksa
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			// AlgorithmParameterSpec paramSpec=new PBEParameterSpec(salt,iterationCount);
	        AlgorithmParameterSpec paramSpec=new IvParameterSpec(IV);    //KONF?????
			cipher.init(Cipher.DECRYPT_MODE, key, paramSpec, RANDOM);
	        byte[] encrypted = cipher.doFinal(cipherText);
			return encrypted;
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NoSuchPaddingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidAlgorithmParameterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return null;
	}
	
	public String findInfo(String text) throws IOException {
		return new String( decrypt(Base64Utility.decode(text) ));
	}
}
