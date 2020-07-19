package com.ybj.myapplication.java.arithmetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by 杨阳洋 on 2020/7/10.
 *
 */
public class Lesson7_1 {

    public static void main(String [] args) throws Exception {

        new Lesson7_1().findChildString("goodgoogle","google");
        new Lesson7_1().findMaxChildString("goodgoogle","google");
            new Lesson7_1().reserveWord(" goo   dgo ogl e ");
    }
    //假设要从主串 s = "goodgoogle" 中找到 t = "google" 子串。
    public void findChildString(String parent,String child){
        //是否查找到子串的标识
        int isFind = 0;
        for (int i = 0 ; i < parent.length() ; i ++){
            if(parent.charAt(i) == child.charAt(0)) {
                int index = 0;
                for (int j = 0 ; j < child.length() ; j++){
                    if(parent.charAt(i + j) != child.charAt(j)) {
                        break;
                    }
                    index = j;
                }
                if(index == child.length() - 1) {
                    isFind = 1;
                }
            }
        }
        System.out.println(isFind);

    }
    //查找出两个字符串的最大公共字串。
    public void findMaxChildString(String parent,String child){
        String maxSubStr = "";
        int maxLength = 0;
        for (int i = 0 ; i < parent.length() ; i ++){
            for (int j = 0 ; j < child.length() ; j ++){
                if(parent.charAt(i) == child.charAt(j)) {
                    for (int m = i , n = j; m < parent.length() && n < child.length() ; m++,n++){
                        if(parent.charAt(m) != child.charAt(n)) {
                            break;
                        }
                        if(maxLength < m - i + 1) {
                            maxLength = m - i + 1;
                            maxSubStr = parent.substring(i,m + 1);
                        }
                    }
                }
            }
        }
        System.out.println(maxSubStr);
        System.out.println(maxLength);
    }

    /*翻转单词*/
    public void reserveWord(String data){
        data = data.trim();
        List<String> wordList = Arrays.asList(data.split("\\s+"));
        Collections.reverse(wordList);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < wordList.size() ; i++){
            if(i == wordList.size() - 1) {
                stringBuilder.append(wordList.get(i));
            }else{
                stringBuilder.append(wordList.get(i)+" ");
            }
        }
        System.out.println(stringBuilder.toString());
    }


}
