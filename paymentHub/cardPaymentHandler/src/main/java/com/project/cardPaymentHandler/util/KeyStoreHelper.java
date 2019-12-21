package com.project.cardPaymentHandler.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class KeyStoreHelper {

	
	
	//////////////////////**
	
private KeyStore keyStore;
	
	public KeyStoreHelper() {
		try {
			Security.addProvider(new BouncyCastleProvider());
			keyStore = KeyStore.getInstance("JKS", "SUN");
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}
	
    public Certificate readCertificate(String keyStorePass, String alias) {
		try {
			
			if(keyStore.isCertificateEntry(alias)) {
				Certificate cert = keyStore.getCertificate(alias);
				return cert;
			}
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return null;
	}
    
//    /**
//	 * Ucitava sertifikate is KS fajla
//	 */
//    public ArrayList<Certificate> readCertificates(String keyStoreFile, String keyStorePass) {
//			ArrayList<Certificate> listaC = new ArrayList<Certificate>();
//			String[] lista = new String[] {"eureka", "zuul", "requesthandler"};
//			//ucitavamo podatke
//			for(String a : lista) {
//				Certificate c = readCertificate(keyStoreFile, keyStorePass, a);
//				if(c != null) {
//					listaC.add(c);
//				}
//			}
//			return listaC;
//		
//	}
	
	public void loadKeyStore(String fileName, char[] password) {
		try {
			if(fileName != null) {
				keyStore.load(new FileInputStream(fileName), password);
			} else {
				//Ako je cilj kreirati novi KeyStore poziva se i dalje load, pri cemu je prvi parametar null
				keyStore.load(null, password);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveKeyStore(String fileName, char[] password) {
		try {
			keyStore.store(new FileOutputStream(fileName), password);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
									//koji ovde tacno ide kljuc, sa cim mora biti kompatibilan(onim u subject data???)
//	public void write(String alias, PrivateKey privateKey, char[] password, Certificate certificate) {
//		try {
//	
//			keyStore.setKeyEntry(alias, privateKey, password, new Certificate[] {certificate});
//		} catch (KeyStoreException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void write(String alias, PrivateKey key, char[] password, Certificate cer) {
	try {
		keyStore.setKeyEntry(alias, key, password, null);
	} catch (KeyStoreException e) {
		e.printStackTrace();
	}
}
	
	/**
	 * Ucitava privatni kljuc is KS fajla
	 */
	public PrivateKey readPrivateKey(String keyStorePass, String alias, String pass) {
		try {
			if(keyStore.isKeyEntry(alias)) {
				PrivateKey pk = (PrivateKey) keyStore.getKey(alias, pass.toCharArray());
				return pk;
			}
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] encrypt(byte[] input, PublicKey key) {
		try {			
			//Kada se definise sifra potrebno je navesti njenu konfiguraciju, sto u ovom slucaju ukljucuje:
			//	- Algoritam koji se koristi (RSA)
			//	- Rezim rada tog algoritma (ECB)
			//	- Strategija za popunjavanje poslednjeg bloka (PKCS1Padding)
			//	- Provajdera kriptografskih funckionalnosti (BC)
			Cipher rsaCipherEnc = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
			//inicijalizacija za sifrovanje
			rsaCipherEnc.init(Cipher.ENCRYPT_MODE, key);
			
			//sifrovanje
			byte[] cipherText = rsaCipherEnc.doFinal(input);
			return cipherText;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] decrypt(byte[] cipherText, PrivateKey key) {
		try {			
			Cipher rsaCipherDec = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
			//inicijalizacija za desifrovanje
			rsaCipherDec.init(Cipher.DECRYPT_MODE, key);
			
			//desifrovanje
			byte[] plainText = rsaCipherDec.doFinal(cipherText);
			return plainText;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
