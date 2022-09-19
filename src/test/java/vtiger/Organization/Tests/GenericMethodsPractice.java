package vtiger.Organization.Tests;

public class GenericMethodsPractice {
	//caller or calling methods
			public static void main(String[] args) {
			 int sum = GenericMethodsPractice.add(50,50);
			 System.out.println(sum);
				
			}
			//called method
			//public static void add()
			{
				int a=50;
				int b=20;
				int c=a+b;
				System.out.println(c);
				
			}
			
			//Called method
			
			public static int add(int a,int b)
			{
				int c=a+b;
				return c;
			}
			

		
		//To call multiple times we use parameterised method. by using parameterised we pass the values in caller /calling method
		//and from that we
}
