package web.Common.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * Created by Administrator on 2017/10/19.
 */
public class Aes {

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     * Java 6支持PKCS5Padding填充方式
     * Bouncy Castle支持PKCS7Padding填充方式
     */
    private static final String ALGORITHM_TAG = "AES/CBC/PKCS7Padding";

    private static final String ALGORITHM_MODEL = "BC";

    static {
        //如果是PKCS7Padding填充方式，则必须加上下面这行
        Security.addProvider(new BouncyCastleProvider());
    }

    private final static String sKey = "academyosciences";
    private final static String ivParameter = "academyosciences";

    private Cipher cipher;
    private byte[] key;
    private byte[] iv;

    public Aes(){
        setKey(sKey.getBytes());
        setIv(ivParameter.getBytes());
        try {
            cipher = Cipher.getInstance(ALGORITHM_TAG, ALGORITHM_MODEL);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    // 加密
    public byte[] encrypt(byte[] sSrc) throws Exception {

        SecretKeySpec skeySpec = new SecretKeySpec(getKey(), ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(getIv());//使用CBC模式，需要一个向量iv，可增加加密算法的强度

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted = cipher.doFinal(sSrc);

        return encrypted;
    }

    // 解密
    public byte[] decrypt(byte[] sSrc) throws Exception {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(getKey(), ALGORITHM);

            IvParameterSpec iv = new IvParameterSpec(getIv());

            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(sSrc);

            return original;

        } catch (Exception ex) {
            return null;
        }
    }
}
