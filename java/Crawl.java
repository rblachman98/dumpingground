import static java.lang.System.*;

//a text crawl

public class Crawl{
	static String chars;
	static char c;
	static int crawlspeed = 10;
	public static void main(String... args) throws Exception{
		initialize();
		while( c != 'z'+1){
			if( c > 'Z' && c < 'a'){
				c++;
				continue;
			}
			if(c == 'a'){
				out.print("\r"+" ".repeat(chars.length()));
			}
			out.print("\r"+(chars = chars +(char) (c++)));
			Thread.sleep(crawlspeed);
		}
		//crawlForwardOnly(chars);
		crawlForwardThenBack(chars);
		main();
	}

	static void crawlForwardOnly(String chars) throws Exception{
		StringBuilder sb = new StringBuilder(chars);
		int place = 0;
		while(! sb.toString().isBlank()){
			sb.replace(place++, place, " ");
			out.print("\r"+sb);
			Thread.sleep(crawlspeed);
		}
	}

	static void crawlForwardThenBack(String chars) throws Exception{
		StringBuilder sb = new StringBuilder(chars);
		int place = sb.length();
		while(! sb.toString().isBlank()){
			sb.replace(--place, place+1, " ");
			//sb.replace(25, 26, " ");
			out.print("\r"+sb);
			Thread.sleep(crawlspeed);
		}

	}
	
	static void initialize(){
		c = 'A';
		chars = "";
	}
}
