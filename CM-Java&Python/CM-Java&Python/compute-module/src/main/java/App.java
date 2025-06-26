import static spark.Spark.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import spark.Request;
import spark.Response;

public class App {
    private static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) {
        port(8080); // listen on localhost:8080
        post("/calculate", App::calculate);
    }
    // args = {
    //     "timestamp": event['timestamp'],
    //     "idNumber": event['idNumber'],
    //     "x": event['x'],
    //     "y": event['y'],
    //     "z": event['z'],
    //     "covariance": event['covariance']
    // }
    private static String calculate(Request req, Response res) {
        res.type("application/json");
        try {
            InputData data = mapper.readValue(req.body(), InputData.class);
            // String timestamp = req.queryParams("timestamp");
            // int idNumber = Integer.parseInt(req.queryParams("idNumber"));
            // int x = Integer.parseInt(req.queryParams("x"));
            // int y = Integer.parseInt(req.queryParams("y"));
            // int z = Integer.parseInt(req.queryParams("z"));
            // double[][] covariance = mapper.readValue(req.queryParams("covariance"), double[][].class);
            
            //double result = x * y * z + covariance[0][0];
            double result = data.x * data.y * data.z + data.covariance[0][0];
            String status = "success";
    
            OutputData out = new OutputData(data.timestamp, data.idNumber, status);
            return mapper.writeValueAsString(out);

        } catch (Exception e) {
            res.status(400);
            try {
                return mapper.writeValueAsString(Map.of("error", res.body()));
            } catch (Exception err) {
                return "{\"error\": \"Invalid input\"}";
            }
            
        }
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