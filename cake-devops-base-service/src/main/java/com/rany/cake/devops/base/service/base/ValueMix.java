package com.rany.cake.devops.base.service.base;

import com.rany.cake.devops.base.domain.base.PropertiesConst;
import com.rany.cake.toolkit.lang.codec.Base62s;
import com.rany.cake.toolkit.lang.crypto.Signatures;
import com.rany.cake.toolkit.lang.crypto.enums.PaddingMode;
import com.rany.cake.toolkit.lang.crypto.enums.WorkingMode;
import com.rany.cake.toolkit.lang.crypto.symmetric.EcbSymmetric;
import com.rany.cake.toolkit.lang.crypto.symmetric.SymmetricBuilder;
import com.rany.cake.toolkit.lang.utils.Arrays1;
import com.rany.cake.toolkit.lang.utils.Strings;

import java.util.Arrays;
import java.util.Optional;

/**
 * 数据混入器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/4/14 23:27
 */
public class ValueMix {

    private static final EcbSymmetric ECB = SymmetricBuilder.aes()
            .workingMode(WorkingMode.ECB)
            .paddingMode(PaddingMode.PKCS5_PADDING)
            .generatorSecretKey(PropertiesConst.VALUE_MIX_SECRET_KEY)
            .buildEcb();

    private ValueMix() {
    }

    /**
     * 加密
     *
     * @param value 明文
     * @return 密文
     */
    public static String encrypt(String value) {
        if (value == null) {
            return null;
        }
        try {
            return ValueMix.ECB.encryptAsString(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 加密
     *
     * @param value 明文
     * @param key   key
     * @return 密文
     */
    public static String encrypt(String value, String key) {
        if (value == null) {
            return null;
        }
        try {
            return SymmetricBuilder.aes()
                    .workingMode(WorkingMode.ECB)
                    .paddingMode(PaddingMode.PKCS5_PADDING)
                    .generatorSecretKey(key)
                    .buildEcb()
                    .encryptAsString(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解密
     *
     * @param value 密文
     * @return 明文
     */
    public static String decrypt(String value) {
        if (value == null) {
            return null;
        }
        try {
            return ECB.decryptAsString(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解密
     *
     * @param value 密文
     * @param key   key
     * @return 明文
     */
    public static String decrypt(String value, String key) {
        if (value == null) {
            return null;
        }
        try {
            return SymmetricBuilder.aes()
                    .workingMode(WorkingMode.ECB)
                    .paddingMode(PaddingMode.PKCS5_PADDING)
                    .generatorSecretKey(key)
                    .buildEcb()
                    .decryptAsString(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 明文 -> ecb -> base62 -> 密文
     *
     * @param value value
     * @param key   key
     * @return 密文
     */
    public static String base62ecbEnc(String value, String key) {
        return Optional.ofNullable(value)
                .map(v -> encrypt(value, key))
                .map(Base62s::encode)
                .orElse(null);
    }

    /**
     * 密文 -> base62 -> ecb -> 明文
     *
     * @param value value
     * @param key   key
     * @return 明文
     */
    public static String base62ecbDec(String value, String key) {
        return Optional.ofNullable(value)
                .map(Base62s::decode)
                .map(v -> decrypt(v, key))
                .orElse(null);
    }

    /**
     * 密码签名
     *
     * @param password password
     * @param salt     盐
     * @return 密文
     */
    public static String encPassword(String password, String salt) {
        return Signatures.md5(password, salt, 3);
    }

    /**
     * 密码验签
     *
     * @param password password
     * @param salt     salt
     * @param sign     密码签名
     * @return 是否正确
     */
    public static boolean validPassword(String password, String salt, String sign) {
        return sign.equals(Signatures.md5(password, salt, 3));
    }

    /**
     * 创建登录token
     *
     * @param uid       uid
     * @param timestamp 时间戳
     * @return token
     */
    public static String createLoginToken(String uid, Long timestamp) {
        return ValueMix.base62ecbEnc(uid + "_" + timestamp, PropertiesConst.VALUE_MIX_SECRET_KEY);
    }

    /**
     * 检查loginToken是否合法
     *
     * @param token token
     * @return true合法
     */
    public static boolean validLoginToken(String token) {
        return ValueMix.base62ecbDec(token, PropertiesConst.VALUE_MIX_SECRET_KEY) != null;
    }

    /**
     * 获取loginToken的uid
     *
     * @param token token
     * @return uid
     */
    public static Long getLoginTokenUserId(String token) {
        return Optional.ofNullable(ValueMix.base62ecbDec(token, PropertiesConst.VALUE_MIX_SECRET_KEY))
                .map(s -> s.split("_"))
                .map(s -> s[0])
                .filter(Strings::isInteger)
                .map(Long::valueOf)
                .orElse(null);
    }

    /**
     * 获取loginToken的信息
     *
     * @param token token
     * @return [uid, loginTimestamp]
     */
    public static Long[] getLoginTokenInfo(String token) {
        return Optional.ofNullable(ValueMix.base62ecbDec(token, PropertiesConst.VALUE_MIX_SECRET_KEY))
                .map(s -> s.split("_"))
                .filter(s -> Arrays.stream(s).allMatch(Strings::isInteger))
                .map(s -> Arrays1.mapper(s, Long[]::new, Long::valueOf))
                .orElse(null);
    }

}
