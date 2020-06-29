package choutuan;

import org.junit.Test;

public class testString {
    @Test
    public  void fun1(){
        String str=new String("%20123");
        System.out.println(str.substring(3,6));
    }
}
