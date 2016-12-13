package indi.nut.myspring.test;

/**
 * Created by nut on 2016/12/13.
 */
public class HelloWorldServiceImpl implements HelloWorldService {

    private String text;



    @Override
    public void helloWorld() {
        System.out.println(text);
    }
}
