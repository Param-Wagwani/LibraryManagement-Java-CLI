public class Student extends Member {
	public Student(String name, int memberId){
		super(name, memberId);
	}

	public int getFinePerDay(){ return 5; }
	public int getThresholdDays() { return 7; }

	public String toString(){
		return "Student: [Name: " + this.getName() + ", Member ID: " + this.getMemberId() + "]";
	}
}
