import static java.lang.System.*;

public class checkIfLeapYear{
	public static void main(String[] args){
		if(args.length == 0){
			out.println("Pass a year to this file");
			return;
		}
		printResult(Integer.valueOf(args[0]));
	}

	static boolean checkIsLeapYear(int year){
		boolean isLeapYear = false;

		if(year%4 == 0 ){
			if( (year%100 != 0) | (year%400 == 0) ){
				isLeapYear = true;
			}
		}
		return isLeapYear;
	}

	static void printResult(int year){
		boolean isLeap = checkIsLeapYear(year);
		out.println(year+" is a leap year: "+ isLeap);
	}
}
