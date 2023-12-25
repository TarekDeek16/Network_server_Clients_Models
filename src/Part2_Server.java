import com.sun.management.OperatingSystemMXBean;
import com.sun.management.UnixOperatingSystemMXBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/*
   @author tarek dar aldeek
   github: TarekDeek16
   Dec 17, 2023
*/
public class Part2_Server {
    static final String[] students = {"1210485", "1201603", "1202251"};
    public static void main(String[] args) {
        int port = 9955;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("server listening on port " + port); // BufferedReader b1r = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                try (Socket socket = serverSocket.accept();
                     PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())))
                {
                    String studentID= br.readLine();
                    if(isValidStudent(studentID)){
                        System.out.println("OS will lock screen after 10 seconds");
                        pr.println("OS will lock screen after 10 seconds");
                        Thread.sleep(10000); // in milli seconds
                        lockScreen();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void lockScreen() {
        try {
            // Using rundll32 to lock the Windows workstation
            Runtime.getRuntime().exec("rundll32 user32.dll,LockWorkStation");
        } catch (IOException e) {
            System.out.println("Error locking screen on Windows: " + e.getMessage());
        }
    }


    private static boolean isValidStudent(String studentID) {
        for (int i = 0; i < students.length; i++) {
            if(studentID.compareTo(students[i])==0)
                return true;
        }
        return  false;
    }
}
