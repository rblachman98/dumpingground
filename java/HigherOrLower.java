import static java.lang.System.*;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class HigherOrLower{
	static HigherLowerLogic highlow;
	public static void main(String... args){
		Scanner input = new Scanner(System.in);
		highlow = new HigherLowerLogic();
		highlow.startNew();
		while(! highlow.getAttemptsLeft().equals(0) ){
			out.println("Remaining attempts: "+highlow.getAttemptsLeft());
			out.print("Insert a whole number between "+highlow.getLowest()+" and "+highlow.getHighest()+": ");
			try{
				if(procesInput(Integer.parseInt(input.nextLine()))){
					out.println("Correct! The anwer was "+highlow.getHiddenNumber());
					out.println("Took "+ (highlow.getAttempts() - 1) +" attempts.");
					break;
				};
			}catch(NumberFormatException ex){
				out.println("Insert a whole number, please!");
			}
		}
		highlow.getAnswerSheet().forEach((attempt, answered) -> out.println("Attempt "+attempt+": "+answered));
		out.println("""

			Want to try again?[Y/N]""");
		String answer = input.nextLine().toUpperCase();
		if(answer.contains("Y")){
			main();
		}else{
			out.println("Thank you for playing!");
		}
	}

	static Boolean procesInput(Integer input){
		Boolean correctAnswer = highlow.checkAnswer(input);
		String HowFarOf = highlow.HigherOrLower(input); 
		final String FAILED = "Failed";
		if(! correctAnswer ){
			if(! HowFarOf.contains(FAILED)){
				out.print("Go ");
			}
			out.println(HowFarOf);
		}
		return correctAnswer;
	}
}

class HigherLowerLogic{
	Integer HiddenNumber, LowestNumber, HighestNumber, Attempt, MaxAttempts;
	final Integer START_ATTEMPT = 1;
	final String GO_HIGHER = "higher", GO_LOWER = "lower";
	RandomGenerator rangen;
	Map<Integer, Integer> AttemptMap;

	public HigherLowerLogic(){
		AttemptMap = new HashMap<Integer, Integer>();
		Attempt = START_ATTEMPT ;
		setLowest(0);
		setHighest(10);
		setMaxAttempts(4);
		rangen = RandomGenerator.getDefault();
	}

	void startNew(){
		Attempt = START_ATTEMPT ;
		AttemptMap.clear();
		HiddenNumber = rangen.nextInt(LowestNumber, HighestNumber+1);
	}

	Boolean checkAnswer(Integer userinput){
		Boolean RightAnswer = false;
		if(userinput.equals(HiddenNumber)){
			RightAnswer = true;
		}
		AttemptMap.put(Attempt++, userinput);
		return RightAnswer;
	}

	String HigherOrLower(Integer userinput){
		StringBuilder higherorlower = new StringBuilder();
		final Integer HIGHER_THAN_ANSWER = 1;
		final Integer LOWER_THAN_ANSWER = -1;
		if(getAttemptsLeft().equals(0)){
			higherorlower.append("Failed\n");
			higherorlower.append("The answer was: "+HiddenNumber);
		}else if( userinput.compareTo(HiddenNumber) == HIGHER_THAN_ANSWER ){
			higherorlower.append(GO_LOWER);
		}else if( userinput.compareTo(HiddenNumber) == LOWER_THAN_ANSWER ){
			higherorlower.append(GO_HIGHER);
		}
		//out.println(HiddenNumber);
		return higherorlower.toString().trim();
	}

	Map<Integer, Integer> getAnswerSheet(){
		return AttemptMap;
	}


	Integer getAttemptsLeft(){
		return MaxAttempts - Attempt;
	}

	Integer getAttempts(){
		return Attempt;
	}

	Integer getHiddenNumber(){
		return HiddenNumber;
	}

	Integer getHighest(){
		return HighestNumber;
	}

	Integer getLowest(){
		return LowestNumber;
	}
	void setHighest(Integer newhighest){
		HighestNumber = newhighest;
	}

	void setLowest(Integer newlowest){
		LowestNumber = newlowest;
	}

	void setMaxAttempts(Integer maxAttempts){
		this.MaxAttempts = maxAttempts+1;
	}

	Map<String, Integer> getBounds(){
		Map<String, Integer> bounds = new HashMap<>();
		bounds.put("highest", HighestNumber);
		bounds.put("lowest", LowestNumber);
		return bounds;
	}
}
