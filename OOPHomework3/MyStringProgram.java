/*
	Had to compare a char primitive to null, but discovered that I could not in Java. 
	I'd like to give credit here: https://stackoverflow.com/questions/28416308/how-to-compare-a-char-with-a-null
	They helped me in substituting null with the '\0' character to use for comparisons. 
	
	Another special thanks to https://stackoverflow.com/questions/12192805/convert-an-integer-to-an-array-of-characters-java
	In the valueOf(Int) method I couldn't really find a way without the provided toCharArray(), 
	although I understand that that method is part of the String class. 
*/


public class MyStringProgram {
	static class MyString {
		/* Decided to keep the character array that I was provided with in the constructor. 
		Also, within the constructor, I managed to count the length of the MyString. 
		As the prompt said, I was allowed to use the String class constructor, and I did
		twice. Plus I kept the MyString as a String within the class, just in case. */
		private final char[] CharData ; 
		private final String StringData ; 
		private final int IntData ;
	
		public MyString (char[] chars) {
			int charsLength = 0; 
				try {
					do {	
						charsLength++; 
					}while ( chars[charsLength] != '\0' ); 
				} catch ( ArrayIndexOutOfBoundsException e ) {/* 
					It will attempt to look at the index after the last. 
					The try-catch block helps prevent that. */}; 
			IntData = charsLength; 
			StringData = new String (chars); 			//One of two times that I use the String class constructor. 
			CharData = chars; 
		}
		
		//Substring method. 
		public MyString substring (int begin, int end) {
			int newLength = end - begin; 
			int copyIndex = begin; 
			char[] newCharArr = new char[newLength]; 
			for (int i = 0; i < newLength; i++, copyIndex++) { 
				newCharArr[i] = this.CharData[copyIndex]; 
			}
			MyString NewMyString = new MyString(newCharArr); 
			return NewMyString; 
		}
		
		//Equals, or general comparison, method. 
		public boolean equals (MyString s) {
			boolean isSame = true; 
			for (int i = 0; i < this.length(); i++) {
				if (this.charAt(i) != s.charAt(i)) {
					isSame = false; 
					break; 			//No need for all the extra comparisons. 
				}
			}
			return isSame; 
		}
		
		/*Second time that I use the String class constructor. */
		public String toString () {
			String NewString = new String(CharData);  
			return NewString; 
		}
		
		/* Since I have stored the MyString contents as a Character Array, 
		I might as well use that to facilitate the charAt(Int) method. */
		public char charAt (int index) { 
			if ( index >= this.IntData) { return '\0'; } 	//Please see the information at the top for this explaination. 
			else { return CharData[index]; }			
		}		
		
		
		public static MyString valueOf (int i) {
			char[] tempchars = ("" + i).toCharArray();		//Please see the information at the top for this explaination. 
			MyString newMyString = new MyString ( tempchars ); 
			return newMyString; 
		}
		
		/* Decided that taking the decimal ASCII value of a character, and then
		manipulating that would be the easiest approach to the Upper/Lower case 
		methods. After all, the ASCII values for corresponding Upper-to-Lower case
		is just an offset of 32. The catch was checking that it was in range. */
		public MyString toUpperCase () {
			int charASCII ; 
			char[] tempchars = new char[IntData]; 
			for (int i = 0; i < this.IntData; i++) {
				charASCII = (int) CharData[i]; 
				if (charASCII >= 97 && charASCII <= 122) {
					charASCII = charASCII - 32; 
				}
				tempchars[i] = (char) charASCII; 
			}
			MyString newMyString = new MyString(tempchars);  
			return newMyString; 
		}
		
		public MyString toLowerCase () {
			int charASCII ; 
			char[] tempchars = new char[IntData]; 
			for (int i = 0; i < this.IntData; i++) {
				charASCII = (int) CharData[i]; 
				if (charASCII >= 65 && charASCII <= 90 ){
					charASCII = charASCII + 32; 
				}
				tempchars[i] = (char) charASCII; 
			}
			MyString newMyString = new MyString(tempchars);  
			return newMyString; 
		}
		
		public MyString getMyString () { return this; }	
		public int length () { return IntData; }
	}

	public static void main (String[] args){
        System.out.println("This is the beginning.");
		
		char[] chars0 = new char[]{'H', 'e', 'l', 'l', 'o'}; 
		
		char[] chars1 = new char[5]; 		
		for(int i = 0; i < 5; i++){
			chars1[i] = 'a'; 
		}
		
		char[] chars2 = new char[5]; 		
		for(int i = 0; i < 5; i++){
			chars2[i] = 'Z'; 
		}
				
		MyString str0 = new MyString( chars0 ); 
		MyString str1 = new MyString( chars1 );
		MyString str2 = new MyString( chars2 );
		MyString str3 = str0.substring(1, 3); 
		MyString str4 = str2.toUpperCase(); 
		MyString str5 = MyString.valueOf(78); 		
		MyString str6 = new MyString( chars1 ); 
		MyString str7 = str1; 
		System.out.println("MyString objects suscessfully declared and initialized. \n"); 
		
		System.out.println("Printing MyString objects and System hashcodes: "); 
		System.out.println("str0: " + str0 + " " + System.identityHashCode(str0)); 
		System.out.println("str1: " + str1 + " " + System.identityHashCode(str1)); 
		System.out.println("str2: " + str2 + " " + System.identityHashCode(str2)); 
		System.out.println("str3: " + str3 + " " + System.identityHashCode(str3)); 
		System.out.println("str4: " + str4 + " " + System.identityHashCode(str4)); 
		System.out.println("str5: " + str5 + " " + System.identityHashCode(str5)); 
		System.out.println("str6: " + str6 + " " + System.identityHashCode(str6)); 
		System.out.println("str7: " + str7 + " " + System.identityHashCode(str7)); 
		
		System.out.print("\nstr3: " + str3); 
		System.out.print(" was made using the "); 
		System.out.println("Substring method. "); 
		System.out.print("str3 has a length of "); 
		System.out.println(str3.length()); 
		
		System.out.println("\nComparing str1 to all others: "); 
		System.out.print("str1 to str0: "); 
		System.out.println(str1.equals(str0));
		System.out.print("str1 to str2: "); 
		System.out.println(str1.equals(str2)); 
		System.out.print("str1 to str3: "); 
		System.out.println(str1.equals(str3)); 
		System.out.print("str1 to str4: "); 
		System.out.println(str1.equals(str4)); 
		System.out.print("str1 to str5: "); 
		System.out.println(str1.equals(str5)); 
		System.out.print("str1 to str6: "); 
		System.out.println(str1.equals(str6)); 
		System.out.print("str1 to str7: "); 
		System.out.println(str1.equals(str7)); 
		
		System.out.println("\nShowing upper/lower case methods: "); 
		System.out.println("str0: " + str0); 
		System.out.print("str0 to lowercase: "); 
		System.out.println(str0.toLowerCase()); 
		System.out.print("str0 to uppercase: "); 
		System.out.println(str0.toUpperCase()); 
		
		System.out.println("\nThis is the end.");
		
    }
}




















