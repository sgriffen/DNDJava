package dnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtReadWrite {
	
	public static void read(ArrayList<String> names, ArrayList<Integer> health, ArrayList<Integer> id, ArrayList<Integer> speed, ArrayList<Boolean> dv, ArrayList<String> imagePath) throws FileNotFoundException
	{
			File f = new File("dnd.txt"); 
			Scanner scan = new Scanner(f);
			int i = 0;
			scan.nextLine();
			while(scan.hasNextLine())
			{
				String line = scan.nextLine();
				reader(names, health, id, speed, dv, imagePath, line, i);
				i++;
			}
	}

	public static void reader(ArrayList<String> names, ArrayList<Integer> health, ArrayList<Integer> id, ArrayList<Integer> speed, ArrayList<Boolean> dv, ArrayList<String> imagePath, String line, int i)
	{
		Scanner scan = new Scanner(line);
		if(names.size() > 0);
		{
			id.set(i, scan.nextInt());
			names.set(i, scan.next());
			health.set(i, scan.nextInt());
			speed.set(i, scan.nextInt());
			dv.set(i, scan.nextBoolean());
			imagePath.set(i, scan.next());
		}
		if(names.size() == 0)
		{
			id.add(scan.nextInt());
			names.add(scan.next());
			health.add(scan.nextInt());
			speed.add(scan.nextInt());
			dv.add(scan.nextBoolean());
			imagePath.add(scan.next());
		}
	}
	
	public static void write(ArrayList<String> names, ArrayList<Integer> health, ArrayList<Integer> id, ArrayList<Integer> speed, ArrayList<Boolean> dv, ArrayList<String> imagePath) throws FileNotFoundException
	{
		Scanner scan =  new Scanner(System.in);
	    File outFile = new File("dnd.txt");
	    PrintWriter out = new PrintWriter(outFile);
	    out.println("ID|Name|Health|Speed|Darkvision|ImagePath");
	    // Echo keyboard input out to the file.
	    for(int i = 0; i < names.size(); i++)
	    {
	      out.println(id.get(i) + " " + names.get(i) + " " + health.get(i) + " " + speed.get(i) + " " + dv.get(i) + " " + imagePath.get(i));
	    }
	    
	    System.out.println("Done");
	    out.close(); // Important: don't forget to close!
	}
	
	public static void create(ArrayList<String> names, ArrayList<Integer> health, ArrayList<Integer> id, ArrayList<Integer> speed, ArrayList<Boolean> dv, ArrayList<String> imagePath) throws FileNotFoundException
	{
		Boolean newCharacter = true;
		int ids = 1;
		Scanner scan =  new Scanner(System.in);
	    File outFile = new File("dnd.txt");
	    PrintWriter out = new PrintWriter(outFile);
	    while(newCharacter)
	    {
	    	id.add(ids);
	    	ids++;
	    	System.out.println("Enter Name: ");
	    	names.add(scan.next());
	    	System.out.println("Enter Health: ");
	    	health.add(scan.nextInt());
	    	System.out.println("Enter Speed: ");
	    	speed.add(scan.nextInt());
	    	System.out.println("Does the character have darkvision? Input yes or no");
	    	String a = scan.next();
	    	if(a.toLowerCase().equals("yes"))
	    	{
	    		dv.add(true);
	    	}
	    	else
	    	{
	    		dv.add(false);
	    	}
	    	System.out.println("Give the name of your image file");
	    	imagePath.add(scan.next());
	    	System.out.println("Would you like to add another character? Input yes or no");
	    	String ans = scan.next();
	    	if(ans.toLowerCase().equals("yes"))
	    	{
	    	}
	    	else
	    	{
	    		newCharacter = false;
	    	}
	    }
	    out.println("ID|Name|Health|Speed|Darkvision|ImagePath");
	    // Echo keyboard input out to the file.
	    for(int i = 0; i < names.size(); i++)
	    {
	      out.println(id.get(i) + " " + names.get(i) + " " + health.get(i) + " " + speed.get(i) + " " + dv.get(i) + " " + imagePath.get(i));
	    }
	    
	    System.out.println("Done");
	    out.close(); // Important: don't forget to close!
	}
}
