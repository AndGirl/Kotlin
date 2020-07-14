package com.ybj.myapplication.java.arithmetic;

import java.util.Stack;

/**
 * Created by 杨阳洋 on 2020/7/10.
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。有效字符串需满足：
 * 左括号必须与相同类型的右括号匹配，左括号必须以正确的顺序匹配。例如，{ [ ( ) ( ) ] }
 * 是合法的，而 { ( [ ) ] } 是非法的。
 */
public class Lesson5_1 {

    public static void main(String [] args){
        String s = "{[()()]}";
        System.out.println(isLegal(s));
    }

    private static int isLeft(char c){
        if(c == '{' || c=='(' || c =='[') {
            return 1;
        }else{
            return 2;
        }
    }

    private static int isPair(char p, char curr) {
        if ((p == '{' && curr == '}') || (p == '[' && curr == ']') || (p == '(' && curr == ')')) {
            return 1;
        } else {
            return 0;
        }
    }

    private static String isLegal(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0 ; i < s.length() ; i++){
            char curr = s.charAt(i);
            if(isLeft(curr) == 1) {
                stack.push(curr);
            }else{
                if(stack.empty()) {
                    return "非法";
                }
                char p = stack.pop();
                if(isPair(p,curr) == 0) {
                    return "非法";
                }
            }
        }

        if(stack.empty()) {
            return "合法";
        }else{
            return "非法";
        }
    }

}
