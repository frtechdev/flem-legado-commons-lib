package br.org.flem.fwe.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 *
 * @author dbbarreto
 */
public class CriptografiaUtil {

    private static final byte[] chaveAES = new byte[] { 0x02, 0x03, 0x04, 0x02, 0x03, 0x04, 0x08, 0x08, 0x12, 0x12, 0x00, 0x23, 0x17, 0x01, 0x09, 0x15};

    /**
     * M�todo para criptografar strings utilizando o algoritimo MD5.
     * @param String a ser criptografada
     * @return 
     */
    public static String stringToMD5(String string) {
        String retorno = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            BigInteger hash = new BigInteger( 1, md.digest() );
            retorno =  hash.toString( 16 );
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return retorno;
    }

    public static String criptografaAES(String string) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(chaveAES, "AES");

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(string.getBytes());
        return new BASE64Encoder().encode(encrypted);
    }

    public static String decriptografaAES(String string) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(chaveAES, "AES");

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        
        byte[] original = cipher.doFinal(new BASE64Decoder().decodeBuffer(string));
        return new String(original);

    }

    private static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(new CriptografiaUtil().stringToMD5("Ketylla159623"));
    }
}
