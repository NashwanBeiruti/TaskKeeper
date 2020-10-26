package acs.tasklist;

// Inherits from OtherO class, stores info about an upcoming homework assignment
public class HomeworkO extends OtherO{
    String subject;
    public void setSubject(String input){
        this.subject = input;
    }
    @Override
    public String formatInfo(){
        return "Due: "+duedate+"\n"+"Description: "+desc+"\n"+"Subject: "+subject+"\n";
    }
    @Override
    public char getID(){
        return 'H';
    }
}
