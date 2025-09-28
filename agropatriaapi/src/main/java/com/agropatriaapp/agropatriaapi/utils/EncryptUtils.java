package com.agropatriaapp.agropatriaapi.utils;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.ScryptFunction;
import com.password4j.types.Bcrypt;

public class EncryptUtils {
  private static String secret = "z5V+2Hg9sDtzQ3mJQb0L1X1gq2TjT6o0JQOTf8Y8YCM=";
  private static String salt = "lFz2Q5g0n6u2V9xRZK9QGg==";

  public static String encriptarContrasena(String contrasena) {
    ScryptFunction scrypt = ScryptFunction.getInstance(32768, 8, 1);

    Hash hash = Password.hash(contrasena)
        .addPepper(EncryptUtils.secret)
        .addSalt(EncryptUtils.salt)
        .with(scrypt);

    return hash.getResult();
  }

  public static boolean verificarContrasena(String contrasena, String contrasenaDB){
    ScryptFunction scrypt = ScryptFunction.getInstance(32768, 8, 1);

    boolean verified = Password.check(contrasena, contrasenaDB)
                           .addPepper(secret)
                           .addSalt(EncryptUtils.salt)
                           .with(scrypt);
       return verified;                   
  }
    

}
