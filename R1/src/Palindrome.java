// Palindrome.java
// Class: cs200

public class Palindrome extends Stack {

	private void generatePalindromeRecursive(String s,int index)
	{
		// first print the character, then recurse on the next character index
		System.out.print(s.charAt(index));
		if(index<s.length()-1)
		{
			// question: will index++ or ++index work on the method call below?
			generatePalindromeRecursive(s,index+1);
		}
		System.out.print(s.charAt(index));
		
	}
	
	public void generatePalindrome(String s) {
		// generate palindrome by concatenating the reverse of s to s
		//generatePalindromeRecursive(s,0);

		int len = s.length();
		String returnString = "";
		Stack stack = new Stack();

		//Push each char to the top of the stack
		for(int i = 0; i < len; i++)
			stack.push(s.charAt(i));
		
		//Pop the top to a new string
		for(int i = 0; i < len; i++)
			returnString += stack.pop();

		System.out.println(s + returnString);		
	}
	
	public static void main(String[] args) {
	    Palindrome p = new Palindrome();
		p.generatePalindrome("ab");

	}

	

}
