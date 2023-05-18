import static java.lang.System.*;
import java.util.Scanner;

public class TimerDemo{
	static String ANSWER = "abcd";
	static final MINUTE = 60;
	static final HOUR = MINUTE * MINUTE;

	public static void main(String[] args){
		Timer timer = new Timer();
		Boolean Correct = false;
		String MyAnswer;
		try(Scanner input = new Scanner(System.in)){
			while(!Correct){
				out.println("What String value do I have: ");
				MyAnswer = input.nextLine();
				if(MyAnswer.length() != ANSWER.length()){
					out.println("Given answer isn't the righ length");
					continue;
				}
				if(!(ANSWER.equals(MyAnswer))){
					//int wait = 1 * 60 * 60;
					int wait = (1 * 60 * 60) + 68;
					out.println("Incorrect");
					out.println();
					while(wait > 0){
						Thread.sleep(1000);
						--wait;
						//out.print("\r"+String.format("%04d" ,--wait));
						//out.print("\r"+timer.completeFormat(wait));
						out.print("\r"+timer.simpleFormat(wait));
					}
					out.println();
				}else{
					Correct = true;
					out.println("Correct!");
					out.println("Anwer is "+MyAnswer);
				}
			}
		}catch(InterruptedException iex){
			iex.printStackTrace();
		}
	}
}

class Timer{
	public String completeFormat(int time){
		return formatHours(time)+formatMinutes(time)+formatSeconds(time);
	}
	
	public String simpleFormat(int time){
		String separator = ":";
		String output = formatTime(time/(HOUR), separator)
			+formatTime( ( (time % (HOUR) ) /MINUTE ), separator)
			+formatTime(time%MINUTE , "");
		return output;
	}
	
	public String formatHours(int time){
		String label = " Hours ";
		return formatTime(time/(HOUR), label);
	}
	
	public String formatMinutes(int time){
		String label = " Minutes ";
		return formatTime((time%(HOUR))/MINUTE, label);
	}
	
	public String formatSeconds(int time){
		String label = " Seconds ";
		return formatTime(time%MINUTE, label);
	}
	
	public String formatTime(int time, String label){
		String formatted = String.format("%02d", time) + label;
		return formatted;
	}
}
