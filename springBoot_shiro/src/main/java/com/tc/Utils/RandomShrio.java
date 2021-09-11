package com.tc.Utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

/**
 * @author 谭铖
 * @date 2021/9/8 14:36
 * 自动生成随机数
 */
public  class RandomShrio {
    public static String Shiroran(){
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }
}
