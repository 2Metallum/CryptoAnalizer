package API;

import BruteForce.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
public class CaesarCipher {
    public static final String SYMBOLS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзи йклмнопрстуфхцчшщъыьэюя.,\":-!?";
    private static Encryptor encryptor;
    private static Decryptor decryptor;
    private static Set<String> inputText;
    private static Set<String> outputText;
    private static int key;
    private static boolean flagOfOperation;

    public static void main(String[] args){
        init();

        try {
            Scanner scanner = new Scanner(System.in);
            BufferedReader readerEncrypt = Files.newBufferedReader(Path.of("src/CaesarInfo/Caesar.txt"));
            Files.deleteIfExists(Path.of("src/CaesarInfo/Results/resultOfEncrypt.txt"));
            BufferedWriter writerEncrypt = Files.newBufferedWriter(Files.createFile(Path.of("src/CaesarInfo/Results/resultOfEncrypt.txt")));

            BufferedReader readerDecrypt = Files.newBufferedReader(Path.of("src/CaesarInfo/Cipher.txt"));
            Files.deleteIfExists(Path.of("src/CaesarInfo/Results/resultOfDecrypt.txt"));
            BufferedWriter writerDecrypt = Files.newBufferedWriter(Files.createFile(Path.of("src/CaesarInfo/Results/resultOfDecrypt.txt")));

            System.out.println("Выбери одну из операций : \n(1) Зашифровать файл\n(2) Расшифровать файл");
            flagOfOperation = chooseOperation(scanner.nextInt());

            if(flagOfOperation) {

                System.out.println("Введи любое число от 1 до 41");
                initKey((scanner.nextInt()));
                encryptFile(readerEncrypt, writerEncrypt, key);

            } else {
                int key = decryptFile(readerDecrypt, writerDecrypt);
                System.out.println("Ключом к файлу было число : " + key);
            }
            scanner.close();
            readerEncrypt.close();
            readerDecrypt.close();
            writerDecrypt.close();
            writerEncrypt.close();

            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    private static boolean chooseOperation(int flag) {
        if(flag == 1) {
            return true;
        } else if(flag == 2) {
            return false;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static void encryptFile(BufferedReader reader, BufferedWriter writer, int key) throws IOException {

        while(reader.ready()) {
            outputText.add(encryptor.encryptData(reader.readLine(), key));
        }
        for(var str : outputText) {

            writer.write(str);
            writer.flush();
        }
    }
    private static int decryptFile(BufferedReader reader, BufferedWriter writer) throws IOException {
        StringBuilder sb = new StringBuilder();
        int key = -1;
        while(reader.ready()) {
            sb.append(reader.readLine());
        }
        for(int i = 1; i < CaesarCipher.SYMBOLS.length(); i++) {

            String string = decryptor.decryptData(sb.toString(), i);
            int countOfSpaces = 0;
            String[] countOfStrings = string.split(" ");

            for (int j = 0; j < string.length(); j++) {
                if(j < countOfStrings.length) {
                    if(countOfStrings[j].length() > 17) break;
                }
                if(string.charAt(j) == ' ') {
                    countOfSpaces++;
                }
            }
            if(countOfStrings.length == countOfSpaces + 1 && (countOfStrings.length != 1 && countOfSpaces != 0)) {
                key = i;
                break;
            }
        }
        writer.write(decryptor.decryptData(sb.toString(), key));
        writer.flush();
        return key;
    }
    private static void init() {
        inputText = new LinkedHashSet<>();
        outputText = new LinkedHashSet<>();
        encryptor = new Encryptor();
        decryptor = new Decryptor();

    }
    private static void initKey(int value) {
        if(value < 1 || value > 41)
            throw new IndexOutOfBoundsException();
        key = value;
    }
}
