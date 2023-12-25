import javax.crypto.spec.PSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/*
   @author tarek dar aldeek
   github: TarekDeek16
   Dec 17, 2023
*/

public class Part2_Client {
    public static void main(String[] args) {
        int port = 9955;
        try(Socket socket = new Socket("localhost",port);
            PrintWriter pr = new PrintWriter(socket.getOutputStream());
            BufferedReader brFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader brFromClient = new BufferedReader(new InputStreamReader(System.in))
        ){
            System.out.println(socket.getLocalPort());
            String studentID = brFromClient.readLine();
            pr.println(studentID);
            pr.flush();
            System.out.println(brFromSocket.readLine());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
