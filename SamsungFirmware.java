package samsungfirmware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SamsungFirmware {
  /* 
  * author: Slam (2024)
  */  
  public static void main(String[] args) {
        // Configuración inicial
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el CSC:");
        String csc = scan.nextLine();
        System.out.println("Ingrese el modelo:");
        String model = scan.nextLine();
        String mUrl = String.format("https://fota-cloud-dn.ospserver.net/firmware/%s/%s/version.xml", csc, model);

        // Manejo de la conexión
        try {
            URL url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } else {
                System.out.println("Error en la conexión: " + connection.getResponseCode());
            }
        } catch (MalformedURLException ex) {
            System.out.println("URL incorrecta: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de IO: " + ex.getMessage());
        }
    }
}
