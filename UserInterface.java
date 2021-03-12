import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class UserInterface {
	
	public static void main(String[] args) throws IOException {

		final int NUM_ACCOUNT = 100;
		final  int NUM_USERS = 100;
		final int NUM_INTEREST= 100;
		 HashTable<UserAccount> userAccounts= new HashTable<>(NUM_ACCOUNT);
		 HashTable<User> userNames = new HashTable<>(NUM_USERS);
		 HashTable<Interest> interest = new HashTable<>(NUM_INTEREST);
		 ArrayList<BST<User>> NewFriendByInterest = new ArrayList<BST<User>>();
		 
		File file = new File("Prin.txt");
		//reading input file
		try {
			BufferedReader buff = new BufferedReader(new FileReader(file));
			String s = "";
			while ((s = buff.readLine()) != null) {
				System.out.println("Chengyun s: " + s);
				ArrayList<String> al = new ArrayList<>();

				if(!(buff.readLine().isEmpty())) {
					al.add(s);
				}
				else {
					break;
				} 
				String[] name = al.get(0).split(" ", 2);
				System.out.println("Chengyun name: " + name);
				int friendNum = Integer.parseInt(al.get(5));
				ArrayList<User> friendArray = new ArrayList<>();
				ArrayList<Interest> userInterest = new ArrayList<>();
				for(int i = 0; i < friendNum; i++) {
					String[] friendName = al.get(i + 6).split(" ", 2);
					User temp = new User(friendName[0], friendName[1]);
					friendArray.add(temp);
				}
				
				for(int i = 0; i < Integer.parseInt(al.get(6 + friendNum)); i++) {
					String[] temp = al.get(i + friendNum + 7).split(" ", 2);
					Interest inter = new Interest(temp[0], Integer.parseInt(temp[1]));				
				}
				
				User user = new User(name[0], name[1],al.get(1), al.get(2), Integer.parseInt(al.get(3)), al.get(4), friendArray, userInterest);
				System.out.println("Chengyun user: " + user);
				for(int i = 0; i < user.getInterest().length; i++) {
					Interest[] temp = user.getInterest();
					NewFriendByInterest.get(temp[i].getID()).insert(user, new NameComparator());
					if(!(interest.contains(temp[i]))){
						interest.put(temp[i]);
					}
				}
				UserAccount userAccount = new UserAccount(user);
				userNames.put(user);
				userAccounts.put(userAccount);
			}
			
	    buff.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("Chengyun userAccounts: " + userAccounts);
		//System.out.println("Chengyun userNames: " + userNames);
		//System.out.println("Chengyun NewFriendByInterest: " + NewFriendByInterest);
		

	}

	/**
	* Read all user's data from txt file 
	* @param File the data source 
	*/
	/**	
	public static void readIn(File file,  HashTable<UserAccount> userAccounts, HashTable<User> userNames) throws FileNotFoundException {		
		try {
			BufferedReader buff = new BufferedReader(new FileReader(file));
			String s = "";
			while ((s = buff.readLine()) != null) {
				ArrayList<String> al = new ArrayList<>();

				if(!(buff.readLine().isEmpty())) {
					al.add(s);
				}
				else {
					break;
				} 
				String[] name = al.get(0).split(" ", 2);
				int friendNum = Integer.parseInt(al.get(5)) * 5;
				ArrayList<User> friendArray = new ArrayList<>();
				ArrayList<Interest> userInterest = new ArrayList<>();
				for(int i = 0; i < friendNum; i = i + 5) {
					String[] friendName = al.get(i + 6).split(" ", 2);
					User temp = new User(friendName[0], friendName[1], al.get(i + 7),al.get(i + 8), Integer.parseInt(al.get(i + 9)), al.get(i + 10));
					friendArray.add(temp);
				}
				
				for(int i = 0; i < Integer.parseInt(al.get(4 + 1 + friendNum)); i++) {
					Interest inter = new Interest(al.get(Integer.getInteger(al.get(4 + 1 + friendNum + 1))));
					
				}
				
				User user = new User(name[0], name[1],al.get(1), al.get(2), Integer.parseInt(al.get(3)), al.get(4), friendArray, userInterest);
				for(int i = 0; i < user.getInterest().length; i++) {
					if()
				}
				UserAccount userAccount = new UserAccount(user);
				userNames.put(user);
				userAccounts.put(userAccount);
			}
			
	    buff.close();
		}catch (IOException e) {
			e.printStackTrace();
		}	
	}
*/
/**	
	public static void WriteFile(File file) {
		
	}
	
	/**
	* verify whether the input user is in the system, 
	* if not, build a new account for this user
	* @param hashTable users 
	*/
/**
	public static UserAccount login(HashTable<User> userNames, HashTable<UserAccount> userAccounts) {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your user name: ");
		String userName = input.nextLine();
		System.out.print("Please enter your password: ");
		String password = input.nextLine();
		User user = new User(userName, password);
		UserAccount userAccount = new UserAccount(user);
		if (!(userAccounts.contains(userAccount))) {
			System.out.println("\nWe don't have your account on file...\n");
			System.out.println("Let's create an account for you!");
			System.out.print("Enter your first name: ");
			String first = input.nextLine();
			System.out.print("Enter your last name: ");
			String last = input.nextLine();
			user.setFirstName(first);
			user.setLastName(last);
			userNames.put(user);
			userAccounts.put(userAccount);
		} else {
			userAccount = userAccounts.get(userAccount);
	}
		System.out.println("\nWelcome, " + user.getFirstName() + " " + user.getLastName() + "!\n");
		System.out.println(user);
		return userAccount;
	}
	
	public static void menu() {
		System.out.println("\nA. Login\n");
		System.out.println("B. View My Friends (has sub-menu)");
		System.out.println("C. Search for a New Friend (has sub-menu)");
		System.out.println("D. Get Friend Recommendations (has sub-menu)");
		System.out.println("E. Quit and Write Records to a File");
		System.out.println();
	}
	
	public static void OptionB(User user) {
		Scanner input = new Scanner(System.in);
		System.out.println("\nPlease select from the options below: \n");
		System.out.println("\na. View Friends by name\n");
		System.out.println("\nb. View a selected Friend's profile\n");	
		System.out.println("\nc. Remove a friend\n");
		System.out.print("Enter your choice: ");
		String in = input.nextLine();
		if(in.equals("a")) {
			user.printUserFriends();
		}
		else if(in.equals("b")) {
			System.out.print("Enter friend's name: ");
			String name = input.nextLine();
			System.out.println(user.getFriendByName(name));
		}
		if(in.equals("c")) {
			System.out.print("Enter friend's name: ");
			String name = input.nextLine();
			user.RemoveFriendByNamed(name);
		}
	}
	public static void optionC(User user, HashTable<User> userNames, HashTable<UserAccount> userAccount) {
		Scanner input = new Scanner(System.in);
		System.out.println("\nPlease select from the options below: \n");
		System.out.println("\na. Search by name\n");
		System.out.println("\nb. Search by interest\n");	
		System.out.print("Enter your choice: ");
		String in = input.nextLine();
		if(in.equals("a")) {
			System.out.println("\nplease enter a full name\n");
			String name = input.nextLine();
			User searchedFriend = new User(name);
			User addedFriend = userNames.get(searchedFriend);
			if(addedFriend != null && user.getFriendByName(name) == null) {
				System.out.println("\na. Add Friend\n");
				System.out.print("Enter your choice: ");
				String add = input.nextLine();
				if(add.equals("a")) {
					user.addFriend((addedFriend));
				}
			}
			else{
				System.out.println(name + " is not have a account.");
			}
			
		}
		if(in.equals("b")) {
			System.out.println("\nplease enter an interest\n");
			String interest = input.nextLine();
			Interest 
			
		}

	}
*/
}
