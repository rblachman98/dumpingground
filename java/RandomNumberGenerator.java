import java.util.stream.*;

public class RandomNumberGenerator{
	public static void main(String[] args){
		int AmountOutput = 20;
		int LessThan = 10;

		Stream.generate(() -> 1)
			.limit( AmountOutput )
			.forEach(x -> 
			System.out.println( (int) (Math.random() * LessThan  )  
				)
			);
	}
}
