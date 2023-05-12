public class MilitaryToStandardTime{
	static String TimeOfDay;

	enum DAY{
		AM("a.m."), PM("p.m.");
		
		String ampm;

		DAY(String ampm){
			this.ampm = ampm;
		}
	}
	
	public static void main(String... args){
		TimeOfDay = DAY.AM.name();
		makeNonMilitary(args);
	}

	static void makeNonMilitary(String... time){
		try{
			int hours = Integer.parseInt(time[0]);
			int minutes = Integer.parseInt(time[1]);
			hours = getNonMilitaryHours(hours);
			validateMinutes(minutes);
			if(hours == 12 && minutes > 0){
				TimeOfDay = DAY.PM.name();
			}
			System.out.println("Given time is: " + constructTimeString(hours, minutes) + " " + TimeOfDay);
		}catch(NumberFormatException notNumber){
			System.out.println("NOT A NUMBER!!!!!!");
		}catch(IllegalArgumentException notHour){
			System.out.println(notHour.getMessage());
		}catch(ArrayIndexOutOfBoundsException noargument){
			System.out.println("NO HOUR AND/OR MINUTES GIVEN!!!");
		}
	};

	private static int getNonMilitaryHours(int hours) throws IllegalArgumentException{
		if(hours < 13){
			return hours;
		}else if(hours > 13 && hours < 25){
			if(hours == 24){
				return 0;
			}
			int NewHours = hours - 12;
			TimeOfDay = DAY.PM.name();
			return NewHours;
		}else{
			throw new IllegalArgumentException("NOT A VALID HOUR!!!!");
		}
	}

	private static void validateMinutes(int minutes) throws IllegalArgumentException{
		if( (minutes >= 60) || (minutes < 0) ){
			throw new IllegalArgumentException("NOT A VALID MINUTE!!!");
		}
	}
	
	private static String constructTimeString(int hours, int minutes){
		String hourString = "";
		String minuteString = "";

		// Mistakes and inexperience must be shown
		/*
		if( (hours < 10) | (minutes < 10)){
			if(hours < 10){
				hourString = "0"+hours;
			}else{
				hourString = String.valueOf(hours);
			}
			if(minutes < 10){
				minuteString = "0"+minutes;
			}else{
				minuteString = String.valueOf(minutes);
			}
		}else{
			hourString = String.valueOf(hours);
			minuteString = String.valueOf(minutes);
		}
		*/

		// code snippet from above comment in two statements
		hourString  = String.format("%02d", hours);
		minuteString  = String.format("%02d", minutes);

		return hourString+":"+minuteString;
	}
}
