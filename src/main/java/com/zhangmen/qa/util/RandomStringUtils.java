package com.zhangmen.qa.util;

public class RandomStringUtils {

    /**
     * count 创建一个随机字符串，其长度是指定的字符数,字符将从参数的字母数字字符集中选择，如参数所示。
     * letters true,生成的字符串可以包括字母字符
     * numbers true,生成的字符串可以包含数字字符
     */
    public static String random(int count, boolean letters, boolean numbers){
        String random = RandomStringUtils.random(count, true, false);
        return random;
    }

    /**
     * 创建一个随机字符串，其长度是指定的字符数。
     * 将从所有字符集中选择字符
     */
    public static String random(int count){
        String random = RandomStringUtils.random(count);
       return random;
    }
    /**
     * 创建一个随机字符串，其长度是指定的字符数。
     * 字符将从字符串指定的字符集中选择，不能为空。如果NULL，则使用所有字符集。
     */
    public static String random(int count, String chars){
       String  random = RandomStringUtils.random(count, chars);
       return random;
    }
    /**
     * 创建一个随机字符串，其长度是指定的字符数,字符将从参数的字母数字字符集中选择，如参数所示。
     * count:计算创建的随机字符长度
     * start:字符集在开始时的位置
     * end:字符集在结束前的位置，必须大于65
     * letters true,生成的字符串可以包括字母字符
     * numbers true,生成的字符串可以包含数字字符
     *
     */
    public static String random(int count, int start,int end,boolean letters, boolean numbers){
       return  RandomStringUtils.random(count, start, end, true, true);
    }
    /**
     * 产生一个长度为指定的随机字符串的字符数，字符将从拉丁字母（a-z、A-Z的选择）。
     * count:创建随机字符串的长度
     */

    public static String randomAlphabetic(int count){
        return  RandomStringUtils.randomAlphabetic(count);
    }
    /**
     * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,，字符将从拉丁字母（a-z、A-Z的选择）。
     * minLengthInclusive ：要生成的字符串的包含最小长度
     * maxLengthExclusive ：要生成的字符串的包含最大长度
     */

    public static String randomAlphabetic(int minLengthInclusive, int maxLengthExclusive){
        return RandomStringUtils.randomAlphabetic(minLengthInclusive, maxLengthExclusive);
    }
    /**
     * 创建一个随机字符串，其长度是指定的字符数，字符将从拉丁字母（a-z、A-Z）和数字0-9中选择。
     * count ：创建的随机数长度
     */
    public static String randomAlphanumeric(int count){
        return   RandomStringUtils.randomAlphanumeric(count);
    }
    /**
     * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,字符将从拉丁字母（a-z、A-Z）和数字0-9中选择。
     * minLengthInclusive ：要生成的字符串的包含最小长度
     * maxLengthExclusive ：要生成的字符串的包含最大长度
     *
     */
    public static String randomAlphanumeric(int minLengthInclusive,int maxLengthExclusive){
        return   RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthExclusive);

    }
    /**
     * 创建一个随机字符串，其长度是指定的字符数，字符将从ASCII值介于32到126之间的字符集中选择（包括）
     * count:随机字符串的长度
     */
    public static String randomAscii(int count){
        return   RandomStringUtils.randomAscii(count);
    }
    /**
     * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,字符将从ASCII值介于32到126之间的字符集中选择（包括）
     * minLengthInclusive ：要生成的字符串的包含最小长度
     * maxLengthExclusive ：要生成的字符串的包含最大长度
     */
    public static String randomAscii(int minLengthInclusive, int maxLengthExclusive){
        return RandomStringUtils.randomAscii(minLengthInclusive,maxLengthExclusive);
    }
    /**
     * 创建一个随机字符串，其长度是指定的字符数，将从数字字符集中选择字符。
     * count:生成随机数的长度
     */
    public static String randomNumeric(int count){
        return   RandomStringUtils.randomNumeric(count);
    }
    /**
     * 创建一个随机字符串，其长度介于包含最小值和最大最大值之间,将从数字字符集中选择字符.
     * minLengthInclusive, 要生成的字符串的包含最小长度
     * maxLengthExclusive 要生成的字符串的包含最大长度
     */
    public static String randomNumeric(int minLengthInclusive, int maxLengthExclusive){
        return RandomStringUtils.randomNumeric(minLengthInclusive,maxLengthExclusive);
    }

    /***
     * 随机生成字符串
     * @param length,传入长度
     * @return
     */
    public static String usingMath(int length) {
        String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String allCharacters = alphabetsInLowerCase + alphabetsInUpperCase + numbers;
        StringBuffer randomString = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int)(Math.random() * allCharacters.length());
            randomString.append(allCharacters.charAt(randomIndex));
        }
        return randomString.toString();
    }











}
