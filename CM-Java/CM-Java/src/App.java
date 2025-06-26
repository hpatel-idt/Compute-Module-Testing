import com.palantir.computemodules.functions.Context;
import com.palantir.computemodules.ComputeModule;

public class App {

    public static void main(String[] args) {

        ComputeModule.builder()
                .add(App::hello, String.class, String.class, "hello")
                .add(App::calculate, InputData.class, OutputData.class, "calculate")
                .build()
                .start();
    }

    static String hello(Context context, String name) {
        return "hello " + name;
    }
    static OutputData calculate(Context context, InputData data){
        double res = data.x * data.y * data.z * data.covariance[0][0];
        String status = "success";
        return new OutputData(data.timestamp, data.idNumber, status);
    }
    public static class InputData {
        public String timestamp;
        public int idNumber;
        public double x, y, z;
        public double[][] covariance;
    }
    public static class OutputData {
        public String timestamp;
        public int idNumber;
        public String message;
        
        public OutputData(String timestamp, int idNumber, String message) {
        this.timestamp = timestamp;
        this.idNumber = idNumber;
        this.message = message;
        }
    }
}