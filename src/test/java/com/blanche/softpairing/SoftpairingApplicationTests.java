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
        Command command = new Command();
        command.setQuestionNum(10000);
        command.setNumericalRange(7);
        ExerciseController exerciseController = new ExerciseController();
        List<Exercise> generate = exerciseController.generate(command);
        long start = System.currentTimeMillis();
        for (Exercise exercise : generate) {
            System.out.println( exercise.getExercise() + exercise.getAnswer());
        }
        long end = System.currentTimeMillis();
        long time = end - start;

        System.out.println("生成10000道题目时间：" +String.valueOf(time) + "ms");

    }

}
