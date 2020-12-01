package jar;

import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;
public class Logger {

	public static void main(String[] args) {
		
		try {
			Subscriber subscriber = new Subscriber();
			subscriber.subscribeToMessages("General");
			subscriber.subscribeToMessages("Error");
			subscriber.subscribeToMessages("Confirmation");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	protected void writeFile(String data, String type) {
		
		String file = "";

		switch(type) {

			case "General":
				file = "../src/main/java/jar/Log.txt";
				break;
			
			case "Error":
				file = "../src/main/java/jar/Error.txt";
				break;
			
			case "Confirmation":
				file = "../src/main/java/jar/Confirmation.txt";
				break;
			
			default:
				System.out.println("Please choose an approperiate topic.");
		}

		if(file != null){
			
			try (FileWriter FileWriter = new FileWriter(file, true)) {
 
				FileWriter.write(data + "\n");
				FileWriter.flush();
				FileWriter.close();
	 
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		} else {
			System.out.print("Did not enter anything into a file.");
		}
	}

	/* 
	Basic Function that works for now, not implemented
	Different files for different messages yet. 
	*/
	protected void log(String data){
         
        //Write JSON file
        try (FileWriter file = new FileWriter("../src/main/java/jar/Log.txt", true)) {
 
            file.write(data + "\n");
            file.flush();
            file.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
