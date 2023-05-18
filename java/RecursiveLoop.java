public class RecursiveLoop{
	static int loop;

	public static void main(String[] args){
		loop = 0;
		recurse();
	}

	static void recurse(){
		loop++;
		System.out.print("A");
		System.out.print(loop);
		if(loop > 50){
			System.exit(0);
		}
		recurse();
	}
}
