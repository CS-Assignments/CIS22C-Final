/** Course Project CS 22c
*Autor: Chengyun Li
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class UserInterface {
	
	public static void main(String[] args) throws IOException {

		final int NUM_ACCOUNT = 30;
		final  int NUM_USERS = 30;
		final int NUM_INTEREST= 20;
		HashTable<UserAccount> userAccounts= new HashTable<>(NUM_ACCOUNT);
		HashTable<User> userNames = new HashTable<>(NUM_USERS);
		HashTable<Interest> interest = new HashTable<>(NUM_INTEREST);
		ArrayList<BST<User>> NewFriendByInterest = new ArrayList<BST<User>>();
		ArrayList<String> outputNames = new ArrayList<>();
		for(int i = 0; i < NUM_INTEREST; i++) {
			NewFriendByInterest.add(new BST<User>());
		}
		 
		File file = new File("Princesses.txt");
		//reading input file
		try {
			Scanner input = new Scanner(file);
			while (input.hasNextLine()) { 
				String[] str = new String[35];
				for(int i = 0; i < 35; i++) {
					if (input.hasNextLine()) {
						str[i] = input.nextLine();
						if (str[i].isEmpty()) {
							break;
						}
					}
				}
				outputNames.add(str[0]);
				String[] name = str[0].split(" ", 2);
				int friendNum = Integer.parseInt(str[5]);
				ArrayList<User> friendArray = new ArrayList<>();
				ArrayList<Interest> userInterest = new ArrayList<>();
				for(int h = 0; h < friendNum; h++) {
					User temp = new User(str[h + 6]);
					friendArray.add(temp);
				}
					
				for(int j = 0; j < Integer.parseInt(str[6 + friendNum]); j++) {
					
					String[] temp = str[j + friendNum + 7].split("#", 2);
					if(temp.length != 2) {
						System.out.println("error: size of interest" + temp.length);
					}
					Interest inter = new Interest(temp[0], Integer.parseInt(temp[1]));
					
					userInterest.add(inter);
				}
				User user = new User(name[0], name[1],str[1], str[2], str[3], Integer.parseInt(str[4]),  friendArray, userInterest);
				Interest[] temp = user.getInterest();
				for(int i = 0; i < temp.length; i++) {
				}
				for(int k = 0; k < temp.length; k++) {
					int index = temp[k].getID();
					NewFriendByInterest.get(index).insert(user, new NameComparator());					
					if(!(interest.contains(temp[k]))){
						interest.put(temp[k]);
					}
				}
				UserAccount userAccount = new UserAccount(user);
				userNames.put(user);
				userAccounts.put(userAccount);	
		}	
		input.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		writeFile("updatedPrincesses.txt", userNames, outputNames);
	}
	
    public static void writeFile(String fileName, HashTable<User> userNames, ArrayList<String> outputNames){
        try {
            PrintWriter out = new PrintWriter(fileName);
            for(int i = 0; i < outputNames.size(); i++) {
            	User user = new User(outputNames.get(i));
            	User outUser = userNames.get(user);
            	String name = outUser.getFirstName() + " " + outUser.getLastName();
            	out.println(name);
            	String userName = outUser.getUserName();
            	out.println(userName);
            	String password = outUser.getPassword();
            	out.println(password);
            	String city = outUser.getCity();
            	out.println(city);
            	int userId = outUser.getUserId();
            	out.println(userId);
            	int numFriends = outUser.getNumFriends();
            	out.println(numFriends);
            	// add name of User' friend
            	String friendNames = outUser.getAllFriendName();
            	out.print(friendNames);
            	
            	int numInterests = outUser.getNumInterests();
            	out.println(numInterests);
            	String interests = "";
            	for(int j = 0; j < outUser.getInterest().length; j++) {
            	 	interests += outUser.getInterest()[j].getName() + "#" + outUser.getInterest()[j].getID() + "\n";
            	}
            	out.print(interests);
            	out.println("");
            }
            
           // System.out.println(userNames.g);
            out.flush();
            out.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
 /**   
	public static void menu() {
		System.out.println("\nA. Login\n");
		System.out.println("B. View My Friends (has sub-menu)");
		System.out.println("C. Search for a New Friend (has sub-menu)");
		System.out.println("D. Get Friend Recommendations (has sub-menu)");
		System.out.println("E. Quit and Write Records to a File");
		System.out.println();
	}
*/
	public static void optionD(User user, HashTable<User> userNames, HashTable<UserAccount> userAccount) {
		
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
