package BruteForce;

import API.CaesarCipher;

public class Encryptor {

    public String encryptData(String inputStr, int shiftKey) {

        String encrypt = "";

        for(int i = 0; i < inputStr.length(); i++) {
            int position = CaesarCipher.SYMBOLS.indexOf(inputStr.charAt(i));

            int encryptPos = (position + shiftKey) % CaesarCipher.SYMBOLS.length();
            char encryptChar = CaesarCipher.SYMBOLS.charAt(encryptPos);

            encrypt += encryptChar;
        }
        return encrypt;
    }
}
