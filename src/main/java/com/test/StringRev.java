package com.test;

/**
 * 实现字符床反转
 *
 * @author zhangxuan
 * @date 2018/12/5
 * @time 10:19
 */

public class StringRev {

    /**
     * 字符串反转
     *
     * @param originStr 输入字符串
     * @return 返回反转字符串
     */
    public String reverse(String originStr) {
        if (originStr.length() <= 1 || originStr == null) {
            return originStr;
        }
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

    public static void main(String[] args) {
        StringRev stringRev = new StringRev();
        String string = "1234567";
        //截取第X个字符
        System.out.println(string.substring(1));
        //取第X个字符，从0开始
        System.out.println(string.charAt(0));

        System.out.println(stringRev.reverse(string));
        System.out.println(stringRev.rev(string));
    }

    /**
     * 字符串反转
     * 递归形式实现
     * @param str
     * @return
     */
    public String rev(String str) {
        if (str == null || str.length()<=1){
            return str;
        }
        return rev(str.substring(1))+str.charAt(0);
    }
}
