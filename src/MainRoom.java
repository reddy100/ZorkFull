import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
public class MainRoom {
	static Scanner darkly = new Scanner(System.in);
	static boolean roomFound = false;
	static int roomCount = 0;
	static ArrayList<String> itemsSeen = new ArrayList<String>();
	static int moneyStash = 0;
	static String[] roomNames = {"the Foyer", "the Front Room", "the Library", "the Kitchen", "the Dining room", "the Vault", "the Parlor", "the Secret room"};
	static HashMap <String, Integer> moneyMap = fillRooms(roomNames.length, 1000);

	
	private static HashMap<String, Integer> fillRooms(int numberOfRooms, int moneyRange)
	{
		HashMap <String, Integer> moneyMap = new HashMap<String, Integer>();
		for (int i=0;i<numberOfRooms-1;i++)
		{
			Random rand = new Random();
			int money = rand.nextInt(moneyRange+1);
			moneyMap.put(roomNames[i], money);
		}
		moneyMap.put(roomNames[roomNames.length-1], moneyRange);
		return moneyMap;
	}
	
	private static void roomDescribe(String where, String[] stuff, String directions)
	{
		roomCount++;
		System.out.println("You are in " + where);
		for (String thing : stuff) {
			System.out.println("You see " + thing);
		}
		if(!itemsSeen.contains(stuff[0]))					//only adding items the first time you see it
		{
			itemsSeen.add(stuff[0]);						//adding items seen to arraylist
		}
		int moneyInRoom = moneyMap.get(where);
		moneyStash+= moneyInRoom;						//picking up money and adding to stash
		moneyMap.put(where, 0);							//emptying money in room after picking it up
		System.out.println("You picked up "+moneyInRoom+" gold");
		System.out.println("Your money stash now contains: "+moneyStash+ " gold coins");
		System.out.println("You can go " + directions);
	}
	
	public static void room1()
	{
		String place = "the Foyer";
		String[] things = new String[1];
		things[0] = "a dead scorpion";
		String direct = " to the north (n) or the south (s)";
		
		do
		{
		roomDescribe(place, things, direct);
		
		String choice = darkly.next();
		
		if (choice.equals("n")) 
		{
			room2();
		}
		else if (choice.equals("s")) 
		{
			exitSequence();
		}
		} while (1==1);
	}
	
	public static void room2()
	{
		String place = "the Front Room";
		String[] things = new String[1];
		things[0] = "a piano";
		String direct = " to the east (e), the south (s) or the west (w)";
		
		do
		{
			roomDescribe(place, things, direct);
			
			String choice = darkly.next();
			
			if (choice.equals("s")) 
			{
				room1();
			} else if (choice.equals("e")) 
			{
				room4();
			} else if (choice.equals("w")) 
			{
				room3();
			}
		} while (1==1);
	}
	
	public static void room3()
	{
		String place = "the Library";
		String[] things = new String[1];
		things[0] = "spiders";
		String direct = " to the east (e) or the north (n)";
		
		do
		{
		roomDescribe(place, things, direct);
		
		String choice = darkly.next();
		
		if (choice.equals("n")) 
		{
			room5();
		}
		else if (choice.equals("e")) 
		{
			room2();
		}
		} while (1==1);
	}
	
	public static void room4()
	{
		String place = "the Kitchen";
		String[] things = new String[1];
		things[0] = "bats";
		String direct = " to the west (w) or the north (n)";
		
		do
		{
		roomDescribe(place, things, direct);
		
		String choice = darkly.next();
		
		if (choice.equals("n")) 
		{
			room7();
		}
		else if (choice.equals("w")) 
		{
			room2();
		}
		} while (1==1);
	}
	
	public static void room5()
	{
		String place = "the Dining room";
		String[] things = new String[1];
		things[0] = "dusty boxes";
		String direct = " to the south (s)" ;
		
		do
		{
		roomDescribe(place, things, direct);
		
		String choice = darkly.next();
		
		if (choice.equals("s")) 
		{
			room3();
		}
		} while (1==1);
	}
	
	public static void room6()
	{
		Random rand = new Random();
		int secretRoom = 1 + rand.nextInt(4);
		String place = "the Vault";
		String[] things = new String[1];
		things[0] = "3 walking skeleton";
		String direct = " to the east (e)";
		if(secretRoom==1 && roomFound == false)
		{
			roomFound=true;
		}
		if(roomFound)
		{
			direct+=" or the secret room(eS)";
		}
		do
		{
		roomDescribe(place, things, direct);
		
		String choice = darkly.next();
		
		if (choice.equals("e")) 
		{
			room7();
		}
		else if (roomFound && choice.equals("eS")) 
		{
			
			room8();
		}
		} while (1==1);
	}
	
	public static void room7()
	{
		String place = "the Parlor";
		String[] things = new String[1];
		things[0] = "a treasure chest";
		String direct = " to the west (w) or the south (s)";
		
		do
		{
		roomDescribe(place, things, direct);
		
		String choice = darkly.next();
		
		if (choice.equals("w")) 
		{
			room6();
		}
		else if (choice.equals("s")) 
		{
			room4();
		}
		} while (1==1);
	}
	
	public static void room8()
	{
		String place = "the Secret room";
		String[] things = new String[1];
		things[0] = "piles of gold";
		String direct = " to the west (w)";
		
		do
		{
		roomDescribe(place, things, direct);
		
		String choice = darkly.next();
		
		if (choice.equals("w")) 
		{
			room6();
		}
		} while (1==1);
	}
	
	public static void exitSequence()
	{
		Random rand = new Random();
		int ghost = 1 + rand.nextInt(4);
		System.out.println("You have exited the house");
		if(ghost==1)
		{
			System.out.println("You are being followed by a ghost");
		}
		roomCount--;
		printAtEnd(itemsSeen);
		System.exit(0);
	}
	
	//function that initiates the attributes to be printed when you exit the house
	public static void printAtEnd(ArrayList<String> items)
	{
		String rooms ="You have seen ";
		for (int j =0;j<items.size();j++)
		{
			if(j<items.size()-1)
			{
				rooms+=items.get(j)+", ";
			}
			else
			{
				rooms+="and "+items.get(j);
			}
		}
		System.out.println("You have walked through "+roomCount+" rooms");
		System.out.println(rooms);
		System.out.println("You have collected a total of "+moneyStash+" gold");
	}
	
	public static void main(String[] args) {
		room1();
	}

}

