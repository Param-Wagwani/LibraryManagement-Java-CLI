public class Teacher extends Member {
	public Teacher(String name, int memberId){
		super(name, memberId);
	}

	public int getFinePerDay(){ return 10; }
	public int getThresholdDays() { return 30; }

	public String toString(){
		return "Teacher: [Name: " + this.getName() + ", Member ID: " + this.getMemberId() + "]";
	}
}
