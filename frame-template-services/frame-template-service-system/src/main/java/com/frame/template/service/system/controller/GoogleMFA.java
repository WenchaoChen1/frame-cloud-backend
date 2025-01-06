//package com.frame.template.service.system.controller;
//
//
//import com.gstdev.cloud.authenticator.qr.google.GoogleAuthenticator;
//import com.gstdev.cloud.authenticator.qr.google.GoogleAuthenticatorKey;
//import com.gstdev.cloud.authenticator.qr.google.GoogleAuthenticatorQRGenerator;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/mfa")
//public class GoogleMFA {
//
//
//    public static void main(String[] args) throws Exception {
//        GoogleAuthenticator gAuth = new GoogleAuthenticator();
//        // 生成一个新的密钥
//        GoogleAuthenticatorKey key = gAuth.createCredentials();
//        String secret = key.getKey();
//        System.out.println("Secret: " + secret);
//
//        // 生成TOTP二维码URL (可以用二维码生成器生成二维码图片)
//        String qrCodeUrl = GoogleAuthenticatorQRGenerator.getOtpAuthURL("MyApp", "user@example.com", key);
//        System.out.println("QR Code URL: " + qrCodeUrl);
//
//        // 生成当前的TOTP代码
////        int code = gAuth.getTotpPassword(secret);
//        int code = gAuth.getTotpPassword("BRF23ZDOFIBUT6ZCR4EYCQS2ONZJ7HO4");
//        System.out.println("Current OTP: " + code);
//
//        // 验证TOTP代码
//        boolean isCodeValid = gAuth.authorize("BRF23ZDOFIBUT6ZCR4EYCQS2ONZJ7HO4", code);
//        System.out.println("Is code valid? " + isCodeValid);
//
////        System.out.println("Current OTP: " + generateQRCodeBase64("ALBAGBDBKPWUQUGL"));
////        System.out.println("Current OTP: " + generateQRCodeBase64("otpauth%3A%2F%2Ftotp%2FMyApp%3Auser%40example.com%3Fsecret%3D6XCO4T77RYHYC62T%26issuer%3DMyApp%26algorithm%3DSHA1%26digits%3D6%26period%3D30&size=200x200&ecc=M&margin=0"));
//        String totpUri = "otpauth://totp/MyApp:user@example.com?secret=BRF23ZDOFIBUT6ZCR4EYCQS2ONZJ7HO4&issuer=MyApp&algorithm=SHA1&digits=6&period=30";
//        int size = 200;
//
//        try {
//            String base64QRCode = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURLBase64(totpUri, size,size);
//            System.out.println("Base64 QR code: " + base64QRCode);
//        } catch (Exception e) {
//            System.err.println("Could not generate QR code: " + e.getMessage());
//        }
//    }
//
//
//}
