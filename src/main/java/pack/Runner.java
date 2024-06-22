package pack;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext context1 = new ClassPathXmlApplicationContext("config.xml");
        Task task1 = context1.getBean("task1", Task.class);
        task1.executeSubTask();
    }
}
