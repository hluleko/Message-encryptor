
package za.ac.tut.message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Messages {
    private String theMassage;
 private String fileName;

    public Messages( String fileName) {
                this.fileName = fileName;
    }
 
    

    public String getTheMassage() {
        return theMassage;
    }

    public String getFileName() {
        return fileName;
    }

   

    public void setTheMassage(String theMassage) {
        this.theMassage = theMassage;
    }
 public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public String toString() {
        return "Messages{" + "theMassage=" + theMassage + '}';
    }
   
    public void writeToFile(String data) throws IOException
    {
        FileWriter fw = new FileWriter(fileName,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data);
        bw.close();
        fw.close();
    }
    public String readFromFile()throws FileNotFoundException,IOException {
        String data = "",line;
        
        FileReader fr=new FileReader(fileName);
        BufferedReader br=new BufferedReader(fr);
        line=br.readLine();
        while(line !=null)
        {
            data=data+line+'\n';
            line=br.readLine();
        }
        br.close();
        fr.close();
        return data;
    }
    
    
}

    

