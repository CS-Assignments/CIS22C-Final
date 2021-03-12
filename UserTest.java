import java.util.ArrayList;

public class UserTest {
	public static void main(String[] args) {
		User user1 = new User("Mulan Hua");
		User user2 = new User("castal", "cindy");
		User user3 = new User("snow", "white", "bule dress", "January", 1, "book");
		Interest interest1 = new Interest("dancing");
		Interest interest2 = new Interest("painting");
		ArrayList<Interest> alInterest = new ArrayList<>();
		alInterest.add(interest1);
		alInterest.add(interest2);
		ArrayList<User> alUser = new ArrayList<>();
		User userFriend = new User("little redhood");
		alUser.add(userFriend);
		User user4 = new User("princess", "swarn", "clothes", "Monday", 2, "fairy tales", alUser, alInterest);
		
		System.out.println("firstName: "+ user4.getFirstName());
		System.out.println();
		System.out.println("lastName: "+ user4.getLastName());
		System.out.println();
		System.out.println("UserName: "+ user4.getUserName());
		System.out.println();
		System.out.println("userId: "+ user4.getUserId());
		System.out.println();
		System.out.println("city: " + user4.getCity());
		System.out.println();
		System.out.println("password: " + user4.getPassword());
		System.out.println();
		System.out.println("user: ");
		System.out.println(user4);
		System.out.println();
		System.out.println("getFriendByName(): \n" + user4.getFriendByName("little redhood"));
		System.out.println();
		System.out.println("getFriendByName(): \n" + user4.getFriendByName("swaon AAA"));
		System.out.println();
		System.out.println("getInterest(): " +user4.getInterest());
		System.out.println();
		user4.RemoveFriendByNamed("little redhood");
		System.out.println("getFriendByName(): " + user4.getFriendByName("little redhood"));
		System.out.println();
		User newFriend = new User("Poppy aaa");
		user4.addFriend(newFriend);
		System.out.println("getFriendByName(): \n" + user4.getFriendByName("Poppy aaa"));
		System.out.println();
		user4.printUserFriends();

	}
}
