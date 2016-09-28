import java.util.*;
import java.io.*;


public class blockbook {
	protected static String password = "admin123";
	
	
	public static void main(String[] args)
	{
		BufferedWriter bw = null;
		BufferedWriter bw1 = null;
		Scanner input = new Scanner(System.in);
		try{
			Runtime.getRuntime().exec("runas /profile /user:Administrator \"cmd.exe /c Powrprof.dll,SetSuspendState\"");
		}catch(IOException e){
			System.out.println("Cannot be run as administrator");
		}
		

		//Input the password and checks if its correct
		
		System.out.println("Enter the password:");
		String passwordInput = input.nextLine();
		
		if(passwordInput.equals(password))
		{
			System.out.println("1.Add new site to block\n2.Unblock site\nChoose any one:");
			int choice = input.nextInt();
			switch(choice)
			{
				case 1:
						//blockSite b = new blockSite();
						//b.block();
					System.out.println("Enter the site to block.");
					String newSite = input.nextLine();
				
					newSite = input.nextLine(); //bcuz the first time it takes enter as string..
					//System.out.println(newSite);
					try{
						//opening the host file..
						bw = new BufferedWriter(new FileWriter("C:\\Windows\\System32\\drivers\\etc\\hosts", true));
						
						//running the cmd in administrative ...
						//Runtime.getRuntime().exec("runas /profile /user:Administrator \"cmd.exe /c Powrprof.dll,SetSuspendState\"");
						
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
				
					break;
		
				case 2:
					//Scanner input = new Scanner(System.in);
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
					BufferedReader reader = null;
					try
					{
						
						File inputFile = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
						File tempFile = new File("temp.tmp");
						reader = new BufferedReader(new FileReader(inputFile));
						//Runtime.getRuntime().exec("runas /profile /user:Administrator \"cmd.exe /c Powrprof.dll,SetSuspendState\"");
						writer = new BufferedWriter(new FileWriter("C:\\Users\\USER\\workspace\\Blockbook\\"+tempFile, true));

						String lineToRemove = "127.0.0.1 "+unblockSite;
						//System.out.println(lineToRemove);
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
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
						File inputFile = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
						File tempFile = new File("C:\\Users\\USER\\workspace\\Blockbook\\temp.tmp");
						try{
							FileInputStream fis = new FileInputStream(tempFile);
							byte[] data = new byte[(int) tempFile.length()];
							fis.read(data);
							fis.close();
							
							String source = new String(data, "UTF-8");
							//System.out.println(source);
							
							try{
							  while(!inputFile.renameTo(inputFile)) {
							        Thread.sleep(100);
							    }
							}
							catch(Exception e)
							{
								System.out.println("Some error occured");
							}
								//File inputFile = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");	
								FileWriter newfile = new FileWriter(inputFile, false);
									newfile.write(source);
									newfile.close();
									
									File inputFile1 = new File("C:\\Users\\USER\\workspace\\Blockbook\\text.txt");
									File tempFile1 = new File("C:\\Users\\USER\\workspace\\Blockbook\\myTempFile.txt");
									try{
										BufferedReader reader1 = new BufferedReader(new FileReader(inputFile1));
										BufferedWriter writer1 = new BufferedWriter(new FileWriter(tempFile1));
					
										String lineToRemove = unblockSite;
										String currentLine = null;
					
										while((currentLine = reader1.readLine()) != null) {
										    // trim newline when comparing with lineToRemove
										    String trimmedLine = currentLine.trim();
										    if(trimmedLine.equals(lineToRemove)) continue;
										    writer1.write(currentLine + System.getProperty("line.separator"));
										}
									
										writer1.flush();
										writer1.close(); 
										reader1.close(); 
										inputFile1.delete();
										tempFile1.renameTo(inputFile1);
									}
									catch(Exception e)
									{
										System.out.println("There was an error!!");
									}
									
									if (!tempFile.delete()) {
								        System.out.println("Could not delete file");
								        return;
								      } 
									else
									{
										System.out.println("Unblocked Successfully");
									}
						}						
						catch(IOException e1)
						{
							 e1.printStackTrace();
						} 	
					break;
			}
		}
		else
			System.out.println("Sorry the password is incorrect..");	
	}
}
