package com.ec.facilitator.base.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class AESHelper {

    private static final String ALGO = "AES";

    private static final byte[] keyValue =new byte[]
            {
                    'S', 'u', 'n', 'y', 'u', 'k', 'i','u', 's', 'c', 'c','b', 'n', 's', 't', 'd'
            };

    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes(StrUtil.CHARSET));
        String encryptedValue = DatatypeConverter.printBase64Binary(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = DatatypeConverter.parseBase64Binary(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue,StrUtil.CHARSET);
        return decryptedValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
}
