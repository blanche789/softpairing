package com.blanche.softpairing.util;

import com.blanche.softpairing.vo.Exercise;

import java.util.*;

/**
 * @Auther:Blanche
 * @Date:2019/10/12
 * @Description:com.blanche.softpairing.util
 * @version:1.0
 */
public class Generate {
    public static final String[] operatorArr = {"+", "-", "*", "÷"};
    Auxiliary auxiliary = new Auxiliary();
    Calculator calculator = new Calculator();

    public Exercise generateInterger(int qid, int numRange) {
        Random random = new Random();
        Exercise exercise = new Exercise();
        int operatorNum = random.nextInt(3) + 1;
        int[] operatorIndex = auxiliary.indexArray(operatorNum , 4);
        int[] operands = new int[operatorNum + 1];
        for (int i = 0; i < operands.length; i++) {
            operands[i] = random.nextInt(numRange);
        }
        String formula = auxiliary.splicingFormula(operatorIndex, operands, operatorNum);

        //计算出答案
        int answer = calculator.caculate(formula);
        if (answer > 0) {
            String answerStr = String.valueOf(answer);
            exercise.setQid(qid);
            exercise.setExercise(formula);
            exercise.setAnswer(answerStr);
        } else {
            return generateInterger(qid, numRange);
        }
        return exercise;
    }

    public Exercise generateFraction(int qid,int numRange) {
        Random random = new Random();
        Exercise exercise = new Exercise();
        int operatorNum = random.nextInt(2) + 1;
        int[] operatorIndex = auxiliary.indexArray(operatorNum , 2);

        //生成一个分数
        int[] fraction = auxiliary.primeNumber(numRange);
        int x = fraction[0];
        int y = fraction[1];
        String properFraction = auxiliary.properFraction(x, y);
        String s = properFraction;
        for (int i = 0; i < operatorNum; i++) {
            //生成下个个分数
            int[] nextFraction = auxiliary.primeNumber(numRange);
            int nextx = nextFraction[0];
            int nexty = nextFraction[1];

            if (operatorArr[operatorIndex[i]].equals("+")) {
                x = x * nexty + nextx * y;
                y = y * nexty;
            }else {
                int count = 0;
                while (x * nexty - nextx * y < 0) {
                    count++;
                    nextFraction = auxiliary.primeNumber(numRange);
                    nextx = nextFraction[0];
                    nexty = nextFraction[1];
                    if (count == 5) {
                        nextx = x - 1;
                        nexty = y;
                    }
                }

                x = x * nexty - nextx * y;
                y = y * nexty;
            }
            String nextProperFraction = auxiliary.properFraction(nextx, nexty);
            s += operatorArr[operatorIndex[i]] + nextProperFraction;
        }

        int divisor = auxiliary.commonDivisor(x, y);
        if (divisor != 1) {
            x /= divisor;
            y /= divisor;
        }
        String finalProperFraction = auxiliary.properFraction(x, y);
        s += "=";
        exercise.setQid(qid);
        exercise.setExercise(s);
        exercise.setAnswer(finalProperFraction);

        return exercise;
    }
}
