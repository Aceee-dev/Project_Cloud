
package servercloudhandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

public class TestClient {
    
    private static Socket socket;
 
    public static void main(String args[])
    {
        try
        {
            while(true){
                String host = "localhost";
                int port = 5000;
                InetAddress address = InetAddress.getByName(host);
                socket = new Socket(address, port);
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String message = br.readLine();
                System.out.println("Message received from the server : " +message);

                port = Integer.parseInt(message);
                socket.close();

                socket = new Socket(address, port);
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                message = br.readLine();
                System.out.println("Message received from the server : " +message);

                if(message.equals("done")){
                    break;
                }
                
                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                MatrixOperation mat = (MatrixOperation) inStream.readObject();
                mat.multiply();

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(mat);
                outputStream.flush();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

