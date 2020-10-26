package acs.tasklist;

// Inherits from Other class, is an object that stores info for an upcoming event
public class EventO extends OtherO{
    String loc, time;
    public void setLoc(String input){
        this.loc = input;
    }
    public void setTime(String input){
        this.time = input;
    }
    @Override
    public String formatInfo(){
        return "Due: "+duedate+"\n"+"Description: "+desc+"\n"+"Location: "+loc+"\n"+"Time: "+time+"\n";
    }
    @Override
    public char getID(){
        return 'E';
    }
}
