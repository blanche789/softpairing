package com.blanche.softpairing.controller;

import com.blanche.softpairing.util.Generate;
import com.blanche.softpairing.vo.Command;
import com.blanche.softpairing.vo.Exercise;
import org.apache.coyote.Response;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
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
    //用来存放Exercise
    List<Exercise> list = new ArrayList<>();

    //声明Generate对象
    Generate generate;

    @RequestMapping("index")
    public String index() { //首页跳转
        return "/index.html";
    }

    @RequestMapping("download")
    @ResponseBody
    public void download(HttpServletResponse response,@RequestBody String kind) throws IOException {//下载功能
        response.setContentType("text/plain");

        StringBuilder builder = new StringBuilder();
        BufferedOutputStream bos = null;
        ServletOutputStream sos = null;

        //根据前端的下载类型来进行判别下载
        if (kind.equals("0")) { //下载问题
            response.addHeader("Content-Disposition", "attachment;filename="
                    + "Exercise" + ".txt");
            for (Exercise exercise : list) {
                builder.append(exercise.getExercise())
                        .append("\r\n");
            }
        } else {//下载答案
            response.addHeader("Content-Disposition", "attachment;filename="
                    + "answer" + ".txt");
            for (Exercise exercise : list) {
                builder.append(exercise.getAnswer())
                        .append("\r\n");
            }
        }
            String s = builder.toString();

            try {
                //响应输出流
                sos = response.getOutputStream();
                bos = new BufferedOutputStream(sos);
                bos.write(s.getBytes("UTF-8"));
                bos.close();
                sos.close();
            } catch (IOException e) {
            } finally {
                bos.close();
                sos.close();
            }
        }

    @RequestMapping(value = "/generate" , method = RequestMethod.POST)
    @ResponseBody()
    public List<Exercise> generate(@RequestBody Command command) {//command接收前端传递的指令
        generate = new Generate();
        int exerciseNum = command.getQuestionNum();
        int range = command.getNumericalRange();
        list = new ArrayList<>();
        Random random = new Random();
        int choose;
        for (int i = 0; i < exerciseNum; i++) { //根据题目数量进行迭代
            choose = random.nextInt(2);
            if (choose == 1) {
               list.add(generate.generateInterger(i,range));
            } else {
                list.add(generate.generateFraction(i,range));
            }
        }
        return list;
    }
}
