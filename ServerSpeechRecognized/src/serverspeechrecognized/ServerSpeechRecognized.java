package serverspeechrecognized;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class ServerSpeechRecognized {
  
    public static void main(String[] args) {
        
          HashMap<String, Integer> commands  = new HashMap<String, Integer>() {{
                put("идти", 87);
                put("стоп", 87);
                put("налево", 65);
                put("назад", 83);
                put("направо",68);
          }};            
         try{
             Robot rb=new Robot();
             ServerSocket ss=new ServerSocket(9000);
             System.out.println("Ожидание подключения");
             
             while(true){
                Socket socket = ss.accept();
                System.out.println("Совершено подключение");
                InputStream sin=socket.getInputStream();
                DataInputStream in=new DataInputStream(sin); 
                String line=in.readUTF();
                
                for(Map.Entry<String,Integer> item : commands.entrySet()){
                    if(line.equals(item.getKey())){   
                        switch(item.getKey()){
                            case "идти":
                                rb.keyPress((int) item.getValue());
                                break;
                            case "стоп":
                                rb.keyRelease((int) item.getValue());
                                break;
                            default:
                                rb.keyPress((int) item.getValue());
                                rb.keyRelease((int) item.getValue());
                                break;
                        }
                    }
                }
                
                
             }
     
         }catch(Exception ex){
             ex.printStackTrace();
         }    
    }
    
    
}
