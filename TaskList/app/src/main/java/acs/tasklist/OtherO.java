package acs.tasklist;

// Super class, all other task classes inherit from this
public class OtherO {
    String desc;
    String duedate;

    public void setDesc(String input){
        this.desc = input;
    }

    public void setDueDate(String input){
        this.duedate = input;
    }

    public String formatInfo(){
        return "Due: "+duedate+"\n"+"Description: "+desc+"\n";
    }

    public char getID(){
        return 'O';
    }
}
