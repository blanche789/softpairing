package com.blanche.softpairing.controller;

import com.blanche.softpairing.util.Generate;
import com.blanche.softpairing.vo.Command;
import com.blanche.softpairing.vo.Exercise;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther:Blanche
 * @Date:2019/10/11
 * @Description:com.blanche.softpairing.controller
 * @version:1.0
 */
@Controller
public class ExerciseController {
//    @Configuration
//    public class WebConfig extends WebMvcConfigurationSupport {
//        @Override
//        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        }
//
//    }

    Generate generate;
//    @RequestMapping("test")
//    @ResponseBody
//    public Map<String, String> test() {
//        Map<String,String> map = new HashMap<>();
//        map.put("blanche", "jay");
//        map.put("jack", "ma");
//        return map;
//    }

    @RequestMapping("index")
    public String index() { //首页跳转
        return "/index.html";
    }

    @RequestMapping(value = "/generate" , method = RequestMethod.POST)
    @ResponseBody()
    public List<Exercise> generate(@RequestBody Command command) {
        generate = new Generate();
        int exerciseNum = command.getQuestionNum();
        int range = command.getNumericalRange();
        List<Exercise> list = new ArrayList<>();
        Random random = new Random();
        int choose;
        for (int i = 0; i < exerciseNum; i++) {
            choose = random.nextInt(2);
            if (choose == 1) {
               list.add(generate.generateInterger(i,range));
            } else {
                list.add(generate.generateFraction(i,range));
            }
        }
//        Exercise exercise = new Exercise();
//        exercise.setQid(1);
//        exercise.setExercise("test");
//        exercise.setAnswer("testAnswer");
//        list.add(exercise);
//        list.add(generate.generateFraction(1,range));
        return list;
    }
}
