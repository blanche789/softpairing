package com.blanche.softpairing;

import com.blanche.softpairing.controller.ExerciseController;
import com.blanche.softpairing.util.Auxiliary;
import com.blanche.softpairing.util.Generate;
import com.blanche.softpairing.vo.Command;
import com.blanche.softpairing.vo.Exercise;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftpairingApplicationTests {

    @Test
    public void contextLoads() {
//        int result;
//        for (int i = 0; i < 1000; i++) {
////            result = (int)(1*Math.random()+1);
//            result = Math.random() >= 0.5 ? 1 : 0;
//            System.out.println(result);
//            String str = "test";
//          String test =   str + result;
//        }
//        System.out.println(5%10);
//        Generate generate = new Generate();
//        List<String> list = generate.genericQuestion(100, 20);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//        Auxiliary commonDivisor = new Auxiliary();
//        int divisor = commonDivisor.commonDivisor(5, 20);
//        System.out.println(divisor);
//        Auxiliary auxiliary = new Auxiliary();
//        String s = auxiliary.properFraction(7, 5);
//        System.out.println(s);
        Command command = new Command();
        command.setQuestionNum(10);
        command.setNumericalRange(7);
        ExerciseController exerciseController = new ExerciseController();
        List<Exercise> generate = exerciseController.generate(command);
        for (Exercise exercise : generate) {
            System.out.println( exercise.getExercise() + exercise.getAnswer());
        }
//        Generate generate1 = new Generate();
//        for (int i = 0; i < 100; i++) {
//            String s = generate1.generateInterger(1, 6);
//            System.out.println(s);
//        }
    }

}
