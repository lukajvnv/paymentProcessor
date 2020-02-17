package com.project.cardPaymentService.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

import com.project.cardPaymentService.util.Base64Utility;

@Service
public class AuthorizationService {
	
	private static final Random RANDOM = new SecureRandom();
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;
	
	/*
	 * private byte[] hashPassword(String password, byte[] salt) { PBEKeySpec spec =
	 * new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
	 * //Arrays.fill(password.toCharArray(), Character.MIN_VALUE); //ideja da se
	 * "obrise" pass [ali mora u niz kast...] try { SecretKeyFactory skf =
	 * SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256"); return
	 * skf.generateSecret(spec).getEncoded(); } catch (NoSuchAlgorithmException |
	 * InvalidKeySpecException e) { throw new
	 * AssertionError("Error while hashing a password: " + e.getMessage(), e); }
	 * finally { spec.clearPassword(); } }
	 * 
	 * public byte[] generateSalt() { byte[] salt = new byte[16];
	 * RANDOM.nextBytes(salt); return salt; }
	 * 
	 * public boolean authenticate(String attemptedPassword, byte[] storedPassword,
	 * byte[] salt) { byte[] hashedAttempedPassword =
	 * hashPassword(attemptedPassword, salt); return
	 * Arrays.equals(hashedAttempedPassword, storedPassword); }
	 */
	
	private String hashPassword(String password, String salt) {
		PBEKeySpec spec = null;
        try {
			byte[] bytedSalt = Base64Utility.decode(salt);
			 spec = new PBEKeySpec(password.toCharArray(), bytedSalt, ITERATIONS, KEY_LENGTH);
	        //Arrays.fill(password.toCharArray(), Character.MIN_VALUE); //ideja da se "obrise" pass [ali mora u niz kast...]
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] bytedHash = skf.generateSecret(spec).getEncoded();
            return Base64Utility.encode(bytedHash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
            spec.clearPassword();
        }
		return "";
	}
	
	public String generateSalt() {
		byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64Utility.encode(salt);
	}
	
	/*
	 * public boolean authenticate(String attemptedPassword, byte[] storedPassword,
	 * byte[] salt) { byte[] hashedAttempedPassword =
	 * hashPassword(attemptedPassword, salt); return
	 * Arrays.equals(hashedAttempedPassword, storedPassword); }
	 */
	
	public boolean authenticate(String attemptedPassword, String storedPassword, String salt) {
		String attempedPassword = hashPassword(attemptedPassword, salt);
		return attempedPassword.equals(storedPassword);
	}
	
	public void generateSensitiveData() {
		//String[] passs = new String[] {"casopisA", "casopisB", "casopisC", "casopisD", "kupacA"};
		// String[] passs = new String[] {"kupacB", "kupacC", "kupacD"};
		String[] passs = new String[] {"5512365555555", "5512365444555", "4512365653214568", 
				"4864236653214568", "5864236444555", "5864236555555"};

		for(String pass: passs) {
			String salt = generateSalt();
			String hash = hashPassword(pass, salt);
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			System.out.println("pass:" + pass);
			System.out.println("salt:" + salt);
			System.out.println("hash:" + hash);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
	}
	
	
}
