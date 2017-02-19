package com.fr.here.util;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by shli on 2016-04-19.
 * 加密工具类
 */
public class EncryptUtil {
    private static final String DES_NAME = "DESede";
    private static final String spKey = "zzb_app_by_frsoft_123";  //用于通过MD5生成密钥
    private static final String CharsetName = "UTF-8";
    /**
     * @param paramString      加密字符
     * @param paramArrayOfByte 秘钥    24
     * @throws Exception
     */
    public static byte[] desEncrypt(String paramString, byte[] paramArrayOfByte) throws Exception {
        Cipher localCipher = Cipher.getInstance(DES_NAME);
        SecretKeySpec secretKeySpec = new SecretKeySpec(paramArrayOfByte, DES_NAME);
        localCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return localCipher.doFinal(paramString.getBytes(CharsetName));
    }

    /**
     * @param param            解密字节
     * @param paramArrayOfByte 秘钥  24
     * @throws Exception
     */
    public static String desDecrypt(byte[] param, byte[] paramArrayOfByte) throws Exception {
        Cipher localCipher = Cipher.getInstance(DES_NAME);
        SecretKeySpec secretKeySpec = new SecretKeySpec(paramArrayOfByte, DES_NAME);
        localCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return new String(localCipher.doFinal(param));
    }

    /**
     * @param paramString1 字符串
     * @param paramString2 秘钥 大于24
     * @throws Exception
     */
    public static byte[] encryptWithKey(String paramString1, String paramString2) throws Exception {
        if (paramString2.length() > 24) {
            paramString2 = paramString2.substring(0, 24);
        }
        return desEncrypt(paramString1, paramString2.getBytes(CharsetName));
    }

    /**
     * @param paramString1 字节码
     * @param paramString2 秘钥 大于24
     * @throws Exception
     */
    public static String decryptWithKey(byte[] paramString1, String paramString2) throws Exception {
        if (paramString2.length() > 24) {
            paramString2 = paramString2.substring(0, 24);
        }
        return desDecrypt(paramString1, paramString2.getBytes(CharsetName));
    }

    /**
     * string 的MD5
     * @param string 字符串
     */
    public static String getMd5(String string) {
        return getMd5(string.getBytes());
    }

    /**
     * 获得MD5 串
     * @param paramArrayOfByte byte
     * @return MD5
     */
    public static String getMd5(byte[] paramArrayOfByte) {
        try {
            return bytesToHexString(getMd5byte(paramArrayOfByte));
        } catch (Exception localException) {
            throw new RuntimeException(localException);
        }
    }

    /**
     * 获得MD5 串
     * @param paramArrayOfByte byte
     * @return byte
     */
    public static byte[] getMd5byte(byte[] paramArrayOfByte) {
        try {
            MessageDigest localMessageDigest;
            (localMessageDigest = MessageDigest.getInstance("MD5")).reset();
            localMessageDigest.update(paramArrayOfByte);
            return localMessageDigest.digest();
        } catch (Exception localException) {
            throw new RuntimeException(localException);
        }
    }


    /**
     * 将字节转为HEXSTring
     * @param bytes 字节
     * @return String
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 获得文件的MD5
     *
     * @param paramString 文件路径
     * @return MD5串
     */
    public static String getFileMd5(String paramString) {
        byte[] arrayOfByte = new byte[65536];
        int i = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(paramString);
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            while ((i = fileInputStream.read(arrayOfByte)) > 0) {
                localMessageDigest.update(arrayOfByte, 0, i);
            }
            fileInputStream.close();
            return bytesToHexString(localMessageDigest.digest());
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }


    /**
     * 将HEXString转为byte
     *
     * @param paramString HEXString
     * @return byte
     * @throws Exception
     */
    public static byte[] hexStringToByte(String paramString) throws Exception {
        byte[] arrayOfByte = new byte[paramString.length() / 2];
        for (int i = 0; i < arrayOfByte.length; i++)
            arrayOfByte[i] = ((byte) (0xFF & Integer.parseInt(
                    paramString.substring(i << 1, (i << 1) + 2), 16)));
        return arrayOfByte;
    }


    /**
     * @param paramInt 位数
     * @return 随机生成的paramInt位字符串
     */
    public static String getRandomString(int paramInt) {
        Random localRandom = new Random();
        StringBuilder localStringBuffer = new StringBuilder();
        int j = 0;
        while (j < paramInt) {
            int i;
            while ((((i = Math.abs(localRandom.nextInt()) % 94 + 32) < 48) || (i > 57)) && ((i < 65) || (i > 90)) && ((i < 97) || (i > 122))) {

            }
            localStringBuffer.append((char) i);
            j++;
        }
        return localStringBuffer.toString();
    }


    /**
     * 得到DESede的密钥匙
     * 根据根据需要，如密钥匙为24个字节，md5加密出来的是16个字节，因此后面补8个字节的0
     *
     * @param spKey 原始的SPKEY
     * @return byte[] 指定加密方式为md5后的byte[]
     */
    public static byte[] getEnKey(String spKey) {
        byte[] desKey = null;
        try {
            byte[] desKey1 = getMd5byte(spKey.getBytes(CharsetName));
            desKey = new byte[24];
            int i = 0;
            while (i < desKey1.length && i < 24) {
                desKey[i] = desKey1[i];
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desKey;
    }
    
    public static byte[] getDefaultEnKey(){
    	return getEnKey(spKey);
    }

}
