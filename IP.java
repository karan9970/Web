import java.net.*;
import java.io.*;
import java.util.Scanner;
class IP{
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of website");
		String web = sc.nextLine();
		try{
			InetAddress ip =InetAddress.getByName(web);
			// ip.getByName(web);
			System.out.println("The ip is"+ip);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}

	
	
