import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class unblockSite {
	public synchronized void unblock()
	{
		Scanner input = new Scanner(System.in);
		try
		{
			BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\USER\\workspace\\Blockbook\\text.txt"));
			String sites = null;
			while((sites = br1.readLine())!=null)
			{
				System.out.println(sites);
			}
			br1.close();
		}
		catch(IOException e){
			System.out.println("There was an error while reading unblock sites.");
		}
		System.out.println("Enter the site you want to unblock.");
		String unblockSite  = input.nextLine();
		unblockSite = input.nextLine();
		BufferedWriter writer = null;
		
		try
		{
			File inputFile = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
			File tempFile = new File("temp.tmp");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			//Runtime.getRuntime().exec("runas /profile /user:Administrator \"cmd.exe /c Powrprof.dll,SetSuspendState\"");
			writer = new BufferedWriter(new FileWriter("C:\\Users\\USER\\workspace\\Blockbook\\"+tempFile, true));

			String lineToRemove = "127.0.0.1 "+unblockSite;
			System.out.println(lineToRemove);
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove)) continue;
			    writer.write(currentLine);
			    writer.newLine();
			}
			writer.flush();
			writer.close(); 
			reader.close(); 
				
				FileInputStream fis = new FileInputStream(tempFile);
				byte[] data = new byte[(int) tempFile.length()];
				fis.read(data);
				fis.close();
				
				String source = new String(data, "UTF-8");
				try
				{
					//File inputFile1 = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
					FileWriter newfile = new FileWriter(inputFile, false);
					newfile.write(source);
					newfile.close();
					
					if (!tempFile.delete()) {
				        System.out.println("Could not delete file");
				        return;
				      } 
			}
			catch(Exception e)
			{
				e.printStackTrace();
			} 
		}
		catch(IOException e1)
		{
			 e1.printStackTrace();
		} 
	}
}
