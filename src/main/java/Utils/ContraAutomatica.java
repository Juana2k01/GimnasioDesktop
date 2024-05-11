package Utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ContraAutomatica {




    public static String generarContraseña() {

        List <String> caracteres = new ArrayList<>();
        Random random = new Random();


        for (int i = 0; i < 7; i++) {
            int tipoCaracter = random.nextInt(3);
            if (tipoCaracter == 0) {
                caracteres.add(obtenercaracter(0));
            } else if (tipoCaracter == 1) {
                caracteres.add(obtenercaracter(1));
            } else {
                caracteres.add(obtenercaracter(2));
            }
        }


        caracteres.add(obtenercaracter(3));


        Collections.shuffle(caracteres);


        StringBuilder contraseña = new StringBuilder();
        for (String caracter : caracteres) {
            contraseña.append(caracter);
        }

        return contraseña.toString();
    }

    public static String obtenercaracter(int numero) {
        String[] numeros = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] letrasmin = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String[] letrasMAY = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] simbolos = {"@", ",", ".", "_"};

        Random random = new Random();
        if (numero == 0) return numeros[random.nextInt(numeros.length)];
        else if (numero == 1) return letrasmin[random.nextInt(letrasmin.length)];
        else if (numero == 2) return letrasMAY[random.nextInt(letrasMAY.length)];
        else return simbolos[random.nextInt(simbolos.length)];
    }

    public static String generarHashSHA256(String contraseña) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = digest.digest(contraseña.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
