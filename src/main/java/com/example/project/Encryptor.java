package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        if (messageLen == 0) {
            return 1;
        }
        return (int) Math.ceil((double) messageLen/rows);
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        int column = determineColumns(message.length(), rows);
        int length = message.length(); 
        String arr[][] = new String[rows][column]; 
        int index = -1; 
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < column; j++){
                index++; 
                if (index > length - 1){
                    arr[i][j] = "="; 
                } else {
                    arr[i][j] = message.substring(index, index+1); 
                }
            }
        }
        return arr;
    }

    public static String encryptMessage(String message, int rows){
        String[][] arr = generateEncryptArray(message, rows);
        int columns = determineColumns(message.length(), rows);
        String encrypted = ""; 
        for (int i = columns - 1; i >= 0; i--){
            for (int j = 0; j < rows; j++){
                encrypted+=arr[j][i]; 
            }
        }
        return encrypted;
        
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String decrypted = ""; 
        int ind = 0; 
        String[][] arr = new String[rows][determineColumns(encryptedMessage.length(), rows)];
        for (int i = determineColumns(encryptedMessage.length(), rows) - 1; i >=0; i--){
            for(int j = 0; j < rows; j++){
                if (ind < encryptedMessage.length()){
                    arr[j][i] = encryptedMessage.substring(ind, ind+1);
                    ind++;
                } else {
                    arr[j][i] = ""; 
                }
            }
        }
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[0].length; j++){
                if (!arr[i][j].equals("=")){
                    decrypted+=arr[i][j]; 
                }
                
            }
        }
        return decrypted;
    }
}