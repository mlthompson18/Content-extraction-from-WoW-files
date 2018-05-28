/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wowavatarhistory;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marie
 */
public class WoWAvatarHistory {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        // Create an array to hold the files to crawl through
        File[] myFiles = new File("C:\\Users\\Marie\\Desktop\\WOWstats\\wowah\\WoWAH\\2006").listFiles();
        
        
        try {
            //create a file for the data to be written to
            FileWriter fw = new FileWriter("C:\\Users\\Marie\\Desktop\\WOWstats\\wowah\\WoWAH\\2008\\WoW_Data_2006.csv");
            PrintWriter pw = new PrintWriter(fw);

            // read the files and get the data
            readFiles(pw, myFiles);

            pw.close();

        } catch (IOException ex) {
            Logger.getLogger(WoWAvatarHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void readFiles(PrintWriter pw, File[] files){
        for(File file : files){
            if (file.isDirectory()){
                //writeLines(pw, "Directory: " + file.getName());
                readFiles(pw, file.listFiles());
                
            } else{
                //writeLines(pw,"File: " + file.getName());
                getData(pw, file);

            }
            
        }
        
    }
    
    public static void writeLines(PrintWriter pw, String toWrite){
           
            pw.println(toWrite);
           
    }
    
    public static void getData(PrintWriter pw,File file){
        
        
        // Read each textfile and get the string between the { }
        try {
            Scanner scanner = new Scanner(new FileInputStream(file));
            
            boolean tokenFound = false;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.equals("Persistent_Storage = {")){
                    tokenFound = true;
                }else if(line.equals("}")){
                    tokenFound = false;
                }
                
                if((tokenFound) && (!line.equals("Persistent_Storage = {"))){
                    writeLines(pw, line);
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WoWAvatarHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
