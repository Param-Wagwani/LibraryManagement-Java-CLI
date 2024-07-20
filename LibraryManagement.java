import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

public class LibraryManagement{
	private List<Book> books = new ArrayList<>();
	private List<Member> members = new ArrayList<>();
	private List<Transaction> transactions = new ArrayList<>();

	private static final String BOOKS_FILE = "books.txt";
	private static final String MEMBERS_FILE = "members.txt";
	private static final String TRANSACTIONS_FILE = "transactions.txt";

	public void addBook(Book book){
		books.add(book);
		saveBooks();
	}

	public void addMember(Member member){
		members.add(member);
		saveMembers();
	}

	public void addTransaction(Transaction transaction){
		transactions.add(transaction);
		saveTransactions();
	}

	public void saveBooks(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE));
			oos.writeObject(books);
			oos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void saveMembers(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBERS_FILE));
			oos.writeObject(members);
			oos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void saveTransactions(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTIONS_FILE));
			oos.writeObject(transactions);
			oos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadBooks(){
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE));
			books = (List<Book>) ois.readObject();
			ois.close();
		}catch(IOException e){
			System.out.println("Creating inital data for books...");

			books.add(new Book("Complete Reference", "Schildit", 1001));
			books.add(new Book("Data Structures Using C", "Tannebaum", 1002));
			books.add(new Book("Operating Systems", "William Stallings", 1003));
			
			saveBooks();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadMembers(){
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEMBERS_FILE));
			members = (List<Member>) ois.readObject();
			ois.close();
		}catch(IOException e){
			System.out.println("Creating inital data for Members...");

			members.add(new Student("John Doe", 2001));
			members.add(new Student("Bob Williams", 2002));
			members.add(new Student("Carol Doe", 2003));
			
			saveMembers();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadTransactions(){
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTIONS_FILE));
			transactions = (List<Transaction>) ois.readObject();
			ois.close();
		}catch(IOException e){
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		// catch(IOException | ClassNotFoundException e)
	}

	public void printBooks(){
		System.out.println("------ Books ------");
		for(Book book: books){
			System.out.println(book);
		}

		// for(int i = 0; i < books.size(); i++){
		// 	Book book = books.get(i);
		// 	//todo
		// }
	}

	public void printMembers(){
		System.out.println("------ Members ------ ");
		for(Member member : members){
			System.out.println(member);
		}		
	}

	public void printTransactions(){
		if(transactions.size() > 0){
			System.out.println("------ Transactions ------");
			for(Transaction transaction: transactions){
				System.out.println(transaction);
			}
		}
	}

	public void issueBook(int memberId, int bookId) throws Exception{
		Member member = null;
		for(Member m : members){
			if(m.getMemberId() == memberId){
				member = m;
				break;
			}
		}

		if(member == null){
			throw new Exception("Member with " + memberId + " not found!");
		}

		Book book = null;
		for(Book b: books){
			if(b.getBookId() == bookId){
				book = b;
				break;
			}
		}

		if(book == null){
			throw new Exception("Book with " + bookId + " not found!");
		}

		// I have found member and book.
		// we need to create a transaction for the same!
		transactions.add(new Transaction(book, member));
		saveTransactions();
	}

	public void returnBook(int memberId, int bookId, Date returnDate) throws Exception{
		Member member = null;

		for(Member m : members){
			if(m.getMemberId() == memberId){
				member = m;
				break;
			}
		}

		if(member == null){
			throw new Exception("Member with " + memberId + " not found!");
		}

		Book book = null;
		for(Book b : books){
			if(b.getBookId() == bookId){
				book = b;
				break;
			}
		}

		if(book == null){
			throw new Exception("Book with " + bookId + " not found!");
		}

		Transaction transaction = null;

		for(Transaction t: transactions){
			if(t.getMember().getMemberId() == memberId && t.getBook().getBookId() == bookId && t.getReturnDate() == null){
				transaction = t;
				break;
			}
		}

		if(transaction == null){
			throw new Exception("Transaction with " + bookId + " and " + memberId + " not found!");
		}

		System.out.println("Calculating fine...");
		Transaction.calculateFine(member, transaction.getIssueDate(), returnDate);

		transaction.setReturnDate(returnDate);
		saveTransactions();
	}

	public static void main(String[] args) throws Exception {
    	LibraryManagement libMgmt = new LibraryManagement();
    	libMgmt.loadBooks();
    	libMgmt.loadMembers();
    	libMgmt.loadTransactions();

    	Scanner scanner = new Scanner(System.in);

    	int choice;

    	while(true) {
    		System.out.println("1. Issue Book");
    		System.out.println("2. Return Book");
    		System.out.println("3. Print Members");
    		System.out.println("4. Print Books");
    		System.out.println("5. Print Transactions");
    		System.out.println("6. Exit");
    		System.out.println("Enter your choice: ");
    		choice = scanner.nextInt();
    		scanner.nextLine();

    		switch(choice) {
	    		case 1:
	    			System.out.println("Enter member id: ");
	    			int memberId = scanner.nextInt();
	    			scanner.nextLine();
	    			System.out.println("Enter Book Id");
	    			int bookId = scanner.nextInt();
	    			scanner.nextLine();
	    			try {
	    				libMgmt.issueBook(memberId, bookId);
	    			} catch(Exception e) {
	    				e.printStackTrace();
	    			}
    				break;
	    		case 2:
	    			System.out.println("Enter member id: ");
	    			memberId = scanner.nextInt();
	    			scanner.nextLine();
	    			System.out.println("Enter Book Id");
	    			bookId = scanner.nextInt();
	    			scanner.nextLine();
	    			System.out.println("Enter Return Date (yyyy-mm-dd)");
	    			String returnDateString = scanner.nextLine();
	    			try {
	    				Date returnDate = Util.parseDate(returnDateString);
	    				libMgmt.returnBook(memberId, bookId, returnDate);
	    			} catch(Exception e) {
	    				e.printStackTrace();
	    			}
	    			break;
	    		case 3:
	    			libMgmt.printMembers();
	    			break;
	    		case 4:
	    			libMgmt.printBooks();
	    			break;
	    		case 5:
	    			libMgmt.printTransactions();
	    			break;
	    		case 6:
	    			System.exit(0);
    		}
    	}

    }
}