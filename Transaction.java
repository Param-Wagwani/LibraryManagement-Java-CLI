import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable{
	private static final long serialVersionUID = 1L;

	private Book book;
	private Member member;
	private Date issueDate;
	private Date returnDate;

	public Transaction(Book book, Member member){
		this(book, member, new Date(), null);
	}

	public Transaction(Book book, Member member, Date issueDate){
		this(book, member, issueDate, null);	
	}

	public Transaction(Book book, Member member, Date issueDate, Date returnDate){
		this.book = book;
		this.member = member;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	public Book getBook() { return this.book; }
	public Member getMember() { return this.member; }
	public Date getIssueDate() { return this.issueDate; }
	public Date getReturnDate() { return this.returnDate; }

	public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

	public void calculateFine() { 
		Transaction.calculateFine(this.member, this.issueDate, new Date());
	}

	public static void calculateFine(Member member, Date issueDate, Date returnDate){
		int finePerDay;
		int fineAfterNumOfDays;
		// if(member instanceof Student){
		// 	finePerDay = STUDENT_FINE_PER_DAY;
		// 	fineAfterNumOfDays = STUDENT_FINE_PER_THRESHOLD_DAYS;
		// }else if(member instanceof Teacher){
		// 	finePerDay = TEACHER_FINE_PER_DAY;
		// 	fineAfterNumOfDays = TEACHER_FINE_PER_THRESHOLD_DAYS;
		// }

		finePerDay = member.getFinePerDay();
		fineAfterNumOfDays = member.getThresholdDays();	
	
		int diffDays = getNumOfDays(issueDate, returnDate);

		int totalFine = 0;

		if(diffDays > fineAfterNumOfDays){
			totalFine = (diffDays - fineAfterNumOfDays) * finePerDay;
		}

		System.out.println("Total Fine: " + totalFine);
	}

	public String getStatus(){
		if(this.returnDate != null){
			return "Completed";
		}

		int numOfDays = getNumOfDays(new Date());
		if(numOfDays > member.getThresholdDays()){
			return "Late";
		}
		
		return "Pending";
	}	

	public String toString(){
		return "Begin Transaction \n"+
		book + "\n" +
		member + "\n" + 
		issueDate + "\n" + 
		returnDate + "\n" +
		getStatus() +  "\nEnd Transaction";
	} 

	private int getNumOfDays(Date returnDate){
		return getNumOfDays(this.issueDate, returnDate);
	}

	

	private static int getNumOfDays(Date issueDate, Date returnDate){	
		long diff = returnDate.getTime() - issueDate.getTime();
		int diffDays = Util.getNumOfDaysFromMillis(diff);
		return diffDays;
	}
}