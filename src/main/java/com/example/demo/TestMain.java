package com.example.demo;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class TestMain {

    public static void main(String[] args) {
        String serverName = "192.168.20.137"; // Replace with your FTP server
        int port = 2222; // Specify the port number
        String userName = "admin"; // Replace with your username
        String password = "admin"; // Replace with your password
        String filePath = "/ftp_test/new_directory/1817_FRONT_SIDE.jpeg"; // Path to the file to retrieve

        FTPClient ftp = new FTPClient();

        try {
            ftp.enterLocalPassiveMode(); // Use passive mode
            ftp.connect(serverName, port); // Connect to the server with the specified port
            ftp.login(userName, password);
            int reply = ftp.getReplyCode();
            System.out.println("Reply code: " + reply);

            if (FTPReply.isPositiveCompletion(reply)) {
                // Retrieve the file as an InputStream
                InputStream inputStream = ftp.retrieveFileStream(filePath);
                if (inputStream != null) {
                    // Read the InputStream into a byte array
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    // Convert the byte array to Base64
                    byte[] fileBytes = outputStream.toByteArray();
                    String base64String = Base64.getEncoder().encodeToString(fileBytes);

                    // Print the Base64 string to the console
                    System.out.println("Base64 of the file: " + base64String);

                    // Close the InputStream
                    inputStream.close();
                } else {
                    System.out.println("Failed to retrieve the file. Reply code: " + ftp.getReplyCode());
                }
            } else {
                System.out.println("Failed to connect to the FTP server. Reply code: " + reply);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}