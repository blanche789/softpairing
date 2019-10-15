package com.blanche.softpairing.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Auther:Blanche
 * @Date:2019/10/13
 * @Description:com.blanche.softpairing.util
 * @version:1.0
 */
public class Calculator {

    public int caculate(String formula) {
        Auxiliary auxiliary = new Auxiliary();
        Map<String, Integer> map = new HashMap<>();
        map.put("(", 0);
        map.put("+", 1);
        map.put("-", 1);
        map.put("*", 2);
        map.put("÷", 2);

        //存放数字的栈
        Stack<Integer> integerStack = new Stack<>();
        //存放符号的栈
        Stack<String> operatorStack = new Stack<>();

        String trueFormula = formula.replaceAll(" ", "");

        for (int i = 0; i < trueFormula.length();) {
            StringBuilder stringBuilder = new StringBuilder();
            char c = trueFormula.charAt(i);
            while (Character.isDigit(c)) {
                stringBuilder.append(c);
                i++;
                if (i < trueFormula.length()) {
                    c = trueFormula.charAt(i);
                }else {
                    break;
                }
            }

            if (stringBuilder.length() == 0) {//表明当前所取的为符号
                String operator;
                switch (c) {
                    case '(':
                        operatorStack.push(String.valueOf(c));
                        break;
                    case ')': //右括号优先级最高，需进行计算
                        operator = operatorStack.pop();
                        while (!operatorStack.isEmpty() && !operator.equals("(")) {
                            int a = integerStack.pop();
                            int b = integerStack.pop();
                            int result = auxiliary.caculate(b, a, operator);
                            if (result < 0) {
                                return -1;
                            }
                            integerStack.push(result);
                            operator = operatorStack.pop();
                        }
                        break;
                    case '=':
                        while (!operatorStack.isEmpty()) {
                            while (!operatorStack.isEmpty()) {
                                operator = operatorStack.pop();
                                int a = integerStack.pop();
                                int b = integerStack.pop();
                                int result = auxiliary.caculate(b, a, operator);
                                if (result < 0) {
                                    return -1;
                                }
                                integerStack.push(result);
                            }
                        }
                        break;
                    default:
                        while (!operatorStack.isEmpty()) {
                            operator = operatorStack.pop();
                            if (map.get(operator) >= map.get(String.valueOf(c))) {
                                int a = integerStack.pop();
                                int b = integerStack.pop();
                                int result = auxiliary.caculate(b, a, operator);
                                if (result < 0) {
                                    return -1;
                                }
                                integerStack.push(result);
                            } else {
                                operatorStack.push(operator);
                                break;
                            }
                        }
                        operatorStack.push(String.valueOf(c));
                        break;
                }
            } else {
                integerStack.push(Integer.valueOf(stringBuilder.toString()));
                continue;
            }
            i++;
        }
        return integerStack.peek();
    }
}
