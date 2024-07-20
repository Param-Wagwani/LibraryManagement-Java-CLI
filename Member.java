import java.io.Serializable;

public class Member implements Serializable {
    private String name;
    private int memberId;

    public Member(String name,int memberId){
        this.name = name;
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    
public int getFinePerDay(){ return 0; }
public int getThresholdDays() { return 0; }

}
