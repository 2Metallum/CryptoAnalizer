package BruteForce;

import API.CaesarCipher;

public class Decryptor {
    public String decryptData(String outputStr, int shiftKey) {

        String decrypt = "";

        for(int i = 0; i < outputStr.length(); i++) {
            int position = CaesarCipher.SYMBOLS.indexOf(outputStr.charAt(i));

            int decryptPos = (position - shiftKey) % CaesarCipher.SYMBOLS.length();
            if(decryptPos < 0)  {
                decryptPos = CaesarCipher.SYMBOLS.length() + decryptPos;
            }
            char decryptChar = CaesarCipher.SYMBOLS.charAt(decryptPos);

            decrypt += decryptChar;
        }

        return decrypt;
    }
}
