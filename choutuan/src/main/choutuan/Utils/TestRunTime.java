package Utils;

public class TestRunTime {
    private static long start=0;
    private static  long timeUse=0;
    public static void start(){
        start= System.currentTimeMillis();
    }
    public static  String  getTimeUse(){
       return  "耗费"+(System.currentTimeMillis()-start)/1000+"s";
    }
}
