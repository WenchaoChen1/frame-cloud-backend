package com.frame.template.service.system.controller;


import com.gstdev.cloud.authenticator.qr.google.GoogleAuthenticator;
import com.gstdev.cloud.authenticator.qr.google.GoogleAuthenticatorKey;
import com.gstdev.cloud.authenticator.qr.google.GoogleAuthenticatorQRGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mfa")
public class GoogleMFA {


    public static void main(String[] args) throws Exception {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        // 生成一个新的密钥
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        String secret = key.getKey();
        System.out.println("Secret: " + secret);

        // 生成TOTP二维码URL (可以用二维码生成器生成二维码图片)
        String qrCodeUrl = GoogleAuthenticatorQRGenerator.getOtpAuthURL("MyApp", "user@example.com", key);
        System.out.println("QR Code URL: " + qrCodeUrl);

        // 生成当前的TOTP代码
//        int code = gAuth.getTotpPassword(secret);
        int code = gAuth.getTotpPassword("QVKRRGVJ4CNXQYKJOCZKBLUELYSBBTML");
        System.out.println("Current OTP: " + code);

        // 验证TOTP代码
        boolean isCodeValid = gAuth.authorize("QVKRRGVJ4CNXQYKJOCZKBLUELYSBBTML", code);
        System.out.println("Is code valid? " + isCodeValid);

//        System.out.println("Current OTP: " + generateQRCodeBase64("ALBAGBDBKPWUQUGL"));
//        System.out.println("Current OTP: " + generateQRCodeBase64("otpauth%3A%2F%2Ftotp%2FMyApp%3Auser%40example.com%3Fsecret%3D6XCO4T77RYHYC62T%26issuer%3DMyApp%26algorithm%3DSHA1%26digits%3D6%26period%3D30&size=200x200&ecc=M&margin=0"));

    }
//        /**
//         * 生成二维码
//         */
//        public static String generateQRCodeBase64(String content) throws WriterException, IOException {
//            // 生成二维码
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300);
//
//            // 将二维码写入字节流
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
//
//            // 转换为 Base64 编码字符串
//            byte[] qrCodeBytes = outputStream.toByteArray();
//            return Base64.getEncoder().encodeToString(qrCodeBytes);
//        }
}
