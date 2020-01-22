package com.project.cardPaymentHandler.util;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.sql.Timestamp;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project.cardPaymentHandler.model.BankInfo;
import com.project.cardPaymentHandler.model.SellerBankInfo;
import com.project.cardPaymentHandler.model.Tx;
import com.project.cardPaymentHandler.model.TxStatus;
import com.project.cardPaymentHandler.service.SymetricCryptography;
import com.project.cardPaymentHandler.service.UnityOfWork;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private UnityOfWork unityOfWork;
	
	@Autowired
	private SecretKey key;
	
	@Autowired
	private SymetricCryptography crypthoService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		createBankInfoAccount();
		createSellerInfoAccount();
		
		createTx();
		
		// proba();
	}
	
	private void proba() {
		KeyStoreHelper helper = new KeyStoreHelper();
		SymetricCryptography crypto = new SymetricCryptography();
		
		helper.loadKeyStore("F:\\FTN\\master V godina\\SEP\\keystore\\cardHandler.jks", "sepstore".toCharArray());
		SecretKey key = crypto.generateKey();
		PrivateKey privateKey= helper.readPrivateKey(/*"F:\\FTN\\master V godina\\SEP\\keystore\\cardHandler.jks",*/ "sepstore", "cardhandler", "sepstore");
		Certificate certificate = helper.readCertificate(/*"F:\\\\FTN\\\\master V godina\\\\SEP\\\\keystore\\\\cardHandler.jks", */ "sepstore", "cardhandlercer");
		
		System.out.println("\n===== Generisanje kljuca =====");
		String data = "Luka 2903 Jovanovic";
		System.out.println("Generisan kljuc: " + Base64Utility.encode(key.getEncoded()));
		byte[] encoded =  helper.encrypt(key.getEncoded(), certificate.getPublicKey());
		System.out.println("Enkriptovan kljuc: " + Base64Utility.encode(encoded));
		byte[] decoded = helper.decrypt(encoded, privateKey);
		SecretKey originalKey = new SecretKeySpec(decoded, 0, decoded.length, "AES");

		System.out.println("Dekriptovan klju: " + Base64Utility.encode(decoded));
		System.out.println("Dekriptovan kljuc[byte]: " + decoded);

	}
	
	private void createSellerInfoAccount() throws IOException {
//		byte[] pass1b = crypthoService.encrypt("casopisA", key);
//		byte[] pass2b = crypthoService.encrypt("casopisB", key);
//		byte[] pass3b = crypthoService.encrypt("casopisC", key);
//		
//		String passDec1 = new String(crypthoService.decrypt(pass1b, key));
//		String passDec2 = new String(crypthoService.decrypt(pass2b, key));
//		String passDec3 = new String(crypthoService.decrypt(pass3b, key));
//		
//		String pass1 = Base64Utility.encode(crypthoService.encrypt("casopisA", key));
//		String pass2 = Base64Utility.encode(crypthoService.encrypt("casopisB", key));
//		String pass3 = Base64Utility.encode(crypthoService.encrypt("casopisC", key));
//		
//		String check1 = new String( crypthoService.decrypt(Base64Utility.decode(pass1), key));
		
		byte[] pass1b = crypthoService.encrypt("casopisA");
		byte[] pass2b = crypthoService.encrypt("casopisB");
		byte[] pass3b = crypthoService.encrypt("casopisC");
		
		String passDec1 = new String(crypthoService.decrypt(pass1b));
		String passDec2 = new String(crypthoService.decrypt(pass2b));
		String passDec3 = new String(crypthoService.decrypt(pass3b));
		
		String pass1 = Base64Utility.encode(crypthoService.encrypt("casopisA"));
		String pass2 = Base64Utility.encode(crypthoService.encrypt("casopisB"));
		String pass3 = Base64Utility.encode(crypthoService.encrypt("casopisC"));
		
		String check1 = new String( crypthoService.decrypt(Base64Utility.decode(pass1)));
		
		// VAZNO: cardPaymentSHandler.SellerBankInfo.sellerIdentifier -> requestPaymentHandler.SellerInfo.sellerDBId
		SellerBankInfo sellerBankInfo1 = new SellerBankInfo(1L, "7457897912345", pass1, pass1, "https://localhost:4200/success", "https://localhost:4200/failed", "https://localhost:4200/error", "Casopis A");
		SellerBankInfo sellerBankInfo2 = new SellerBankInfo(2l, "7455632178954", pass2, pass2, "https://localhost:4200/success", "https://localhost:4200/failed", "https://localhost:4200/error", "Casopis B");
		SellerBankInfo sellerBankInfo3 = new SellerBankInfo(3l, "4565635558954", pass3, pass3, "https://localhost:4200/success", "https://localhost:4200/failed", "https://localhost:4200/error", "Casopis C");

		unityOfWork.getSellerBankInfoRepository().save(sellerBankInfo1);
		unityOfWork.getSellerBankInfoRepository().save(sellerBankInfo2);
		unityOfWork.getSellerBankInfoRepository().save(sellerBankInfo3);
	}
	
	
	

	
	private void createBankInfoAccount() {
		BankInfo bankInfo1 = new BankInfo("Banka Intesa", "745", "https://localhost:8841/card");
		BankInfo bankInfo2 = new BankInfo("Raifeissen banka", "456", "https://localhost:8851/card");
		
		unityOfWork.getBankInfoRepository().save(bankInfo1);
		unityOfWork.getBankInfoRepository().save(bankInfo2);
	}
	
	private void createTx() {
		@SuppressWarnings("deprecation")
		Timestamp timestamp1 = new Timestamp(120, 11, 19, 1, 20, 8, 1);
		long paymentId1 = 1234567896l;
		long merchantOrderId1 = 4561230258l;
		
		@SuppressWarnings("deprecation")
		Timestamp timestamp2 = new Timestamp(120, 0, 21, 1, 43, 8, 1);
		long paymentId2 = 1536987452l;
		long merchantOrderId2 = 3698527530l;
		
		@SuppressWarnings("deprecation")
		Timestamp timestamp3 = new Timestamp(120, 0, 21, 1, 47, 4, 1);
		long paymentId3 = 1237414582l;
		long merchantOrderId3 = 4522230258l;
		
		@SuppressWarnings("deprecation")
		Timestamp timestamp4 = new Timestamp(120, 0, 20, 19, 20, 4, 1);
		long paymentId4 = 4234567896l;
		long merchantOrderId4 = 4563330258l;
		
		Tx tx1 = new Tx(new Timestamp(System.currentTimeMillis()), TxStatus.UNKNOWN, 500f, "txDescription", paymentId1, "senderName", "senderAccountNum", "recieverName", "recieverAccountNum", timestamp1, merchantOrderId1, timestamp1, merchantOrderId1);
		Tx tx2 = new Tx(new Timestamp(System.currentTimeMillis()), TxStatus.ERROR, 500f, "txDescription", paymentId2, "senderName", "senderAccountNum", "recieverName", "recieverAccountNum", timestamp2, merchantOrderId2, timestamp2, merchantOrderId2);
		Tx tx3 = new Tx(new Timestamp(System.currentTimeMillis()), TxStatus.SUCCESS, 500f, "txDescription", paymentId3, "senderName", "senderAccountNum", "recieverName", "recieverAccountNum", timestamp3, merchantOrderId3, timestamp3, merchantOrderId3);
		Tx tx4 = new Tx(new Timestamp(System.currentTimeMillis()), TxStatus.UNKNOWN, 500f, "txDescription", paymentId4, "senderName", "senderAccountNum", "recieverName", "recieverAccountNum", timestamp4, merchantOrderId4, timestamp4, merchantOrderId4);
	
		unityOfWork.getTxRepository().save(tx1);
		unityOfWork.getTxRepository().save(tx2);
		unityOfWork.getTxRepository().save(tx3);
		unityOfWork.getTxRepository().save(tx4);	
	}

}
