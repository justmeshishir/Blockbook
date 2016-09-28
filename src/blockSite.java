import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;


public class blockSite {
	Scanner input = new Scanner(System.in);
	BufferedWriter bw = null;
	BufferedWriter bw1 = null;
	//input the site to block..
	public void block()
	{
		System.out.println("Enter the site to block.");
		String newSite = input.nextLine();
	
		newSite = input.nextLine(); //bcuz the first time it takes enter as string..
		//System.out.println(newSite);
		try{
			//opening the host file..
			bw = new BufferedWriter(new FileWriter("C:\\Windows\\System32\\drivers\\etc\\hosts", true));
			
			//running the cmd in administrative ...
			Runtime.getRuntime().exec("runas /profile /user:Administrator \"cmd.exe /c Powrprof.dll,SetSuspendState\"");
			
			//updating the file by adding the site detail..
			bw.write("\n127.0.0.1 "+newSite);
			bw.newLine();
			bw.flush();
			bw.close();
			try{
				bw1 = new BufferedWriter(new FileWriter("C:\\Users\\USER\\workspace\\Blockbook\\text.txt", true));
				bw1.write(newSite);
				bw1.newLine();
				bw1.flush();
				bw1.close();
				System.out.println("Blocked successfully!!");
			}
			catch(Exception e){
				System.out.println("There is an error..");
			}
		}
		catch(Exception e){
			System.out.println("There is an error..");
		}
	}
}

