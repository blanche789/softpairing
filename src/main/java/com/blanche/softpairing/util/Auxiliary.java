package com.blanche.softpairing.util;

import java.util.Random;

/**
 * @Auther:Blanche
 * @Date:2019/10/13
 * @Description:com.blanche.softpairing.util
 * @version:1.0
 */
public class Auxiliary {
    Random random = new Random();
    public int commonDivisor(int numerator, int denominator) { //生成最大公约数
        int num = 1;
        for (int i = 1; i <= numerator; i++) {
            if (numerator % i == 0 && denominator % i == 0) {
                num = i;
            }
        }
        return num;
    }

    public int[] indexArray(int operatorNum , int totalOperator) { //生成运算符匹配的索引
        Random random = new Random();
        int[] operatorIndex = new int[operatorNum];
        for (int i = 0; i < operatorNum; i++) {
            operatorIndex[i] = random.nextInt(totalOperator);
        }
        return operatorIndex;
    }

    public String splicingFormula(int[] operatorIndex, int[] operands, int operatorNum) {//将运算符与操作数连接

        //判断式子形式的标志
        int tag = random.nextInt(2);
        StringBuilder stringBuilder = new StringBuilder();
        switch (operatorNum) {
            case 1:
                //a+b的式子
                stringBuilder.append(operands[0])
                        .append(" ")
                        .append(Generate.operatorArr[operatorIndex[0]])
                        .append(" ")
                        .append(operands[1])
                        .append(" ")
                        .append("=")
                        .append(" ");
                break;
            case 2:
                if (tag == 0) { //a+b+c的式子
                    stringBuilder.append(operands[0])
                            .append(" ")
                            .append(Generate.operatorArr[operatorIndex[0]])
                            .append(" ")
                            .append(operands[1])
                            .append(" ")
                            .append(Generate.operatorArr[operatorIndex[1]])
                            .append(" ")
                            .append(operands[2])
                            .append(" ")
                            .append("=")
                            .append(" ");
                }else { //a+(b+c)的式子
                    stringBuilder.append(operands[0])
                            .append(" ")
                            .append(Generate.operatorArr[operatorIndex[0]])
                            .append(" ")
                            .append("(")
                            .append(" ")
                            .append(operands[1])
                            .append(" ")
                            .append(Generate.operatorArr[operatorIndex[1]])
                            .append(" ")
                            .append(operands[2])
                            .append(" ")
                            .append(")")
                            .append(" ")
                            .append("=")
                            .append(" ");
                }
                break;
            case 3:
                if (tag == 0) {//a+b+c+d的式子
                    stringBuilder.append(operands[0])
                            .append(" ")
                            .append(Generate.operatorArr[operatorIndex[0]])
                            .append(" ")
                            .append(operands[1])
                            .append(" ")
                            .append(Generate.operatorArr[operatorIndex[1]])
                            .append(" ")
                            .append(operands[2])
                            .append(" ")
                            .append(Generate.operatorArr[operatorIndex[2]])
                            .append(" ")
                            .append(operands[3])
                            .append(" ")
                            .append("=")
                            .append(" ");
                } else { //a+((b+c)+d)的式子
                    stringBuilder.append(operands[0])
                            .append(" ")
                            .append(Generate.operatorArr[operatorIndex[0]])
                            .append(" ")
                            .append("((")
                            .append(" ")
                            .append(operands[1])
                            .append(" ")
                            .append(Generate.operatorArr[operatorIndex[1]])
                            .append(" ")
                            .append(operands[2])
                            .append(" ")
                            .append(")")
                            .append(" ")
                            .append(Generate.operatorArr[operatorIndex[2]])
                            .append(" ")
                            .append(operands[3])
                            .append(" ")
                            .append(")")
                            .append(" ")
                            .append("=")
                            .append(" ");
                }
                break;
        }
        return stringBuilder.toString();
    }


    //生成互质的两个数，分别作为分子分母
    public int[] primeNumber(int numRange) {
        int x = random.nextInt(numRange) + 1;
        int y = random.nextInt(numRange) + 1;
        int[] primeNumArr = new int[2];
        int divisor = commonDivisor(x, y);
        if (divisor != 1) {
            x = x / divisor;
            y = y / divisor;
        }
        primeNumArr[0] = x;
        primeNumArr[1] = y;
        return primeNumArr;
    }

    //转换为真分数
    public String properFraction(int x, int y) {
        String properFraction;
        if (x > y) {
            if (y != 1) {
                int n = x / y;
                x = x - n * y;
                properFraction = n + "'" + x + "/" + y;
            } else {
                properFraction = String.valueOf(x);
            }
        } else if (x == y) {
           properFraction = String.valueOf(1);
        }else {
            properFraction = x + "/" + y;
        }
        return properFraction;
    }

    public int caculate(int a, int b, String operator) {//计算两个整数
        char operatorChar = operator.charAt(0);
        int result = 0;
        switch (operatorChar) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '÷':
                if (b == 0) {
                    result = -1;
                } else if (a % b != 0) {
                    result = -2;
                }else
                    result = a / b;
                break;
        }
        return result;
    }
}
