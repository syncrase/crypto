package bigint;

import java.math.BigInteger;
import java.security.SecureRandom;

public class BigIntegerExercise {

	public BigIntegerExercise() {
	}

	void expression() {

		System.out.println("\nExercise 2:");
		System.out.println("===========\n");

		/************************************************************
		 * Insert the code of Exercise 2 below this comment!
		 ************************************************************/
//		(a * b - 4)/c + ((d * d) - (a - b))
//		a = 512, b = 102, c = 3, and d = 761,
		BigInteger a = new BigInteger("512");
		BigInteger b = new BigInteger("102");
		BigInteger c = new BigInteger("3");
		BigInteger d = new BigInteger("761");
		
//		((a.multiply(b).subtract(new BigInteger("4"))).divide(c)).add(d.multiply(d).subtract(a.subtract(b)));
		System.out.println("\nBigNumber returned : "+((a.multiply(b).subtract(new BigInteger("4"))).divide(c)).add(d.multiply(d).subtract(a.subtract(b))));
		
	}

	void comparison() {
		System.out.println("\nExercise 3:");
		System.out.println("===========\n");

		/************************************************************
		 * Insert the code of Exercise 3 below this comment!
		 ************************************************************/
		BigInteger a = new BigInteger("781");
		BigInteger b = new BigInteger("12891");
		//Print results of all comparaisons
		System.out.println("a = "+a+" and b = "+b);
		System.out.println("______________________\n");
		System.out.println("a < b ? \t"+(a.compareTo(b)<0));
		System.out.println("a <= b ? \t"+(a.compareTo(b)<=0));
		System.out.println("a = b ? \t"+(a.compareTo(b)==0)); 	
		System.out.println("a >= b ? \t"+(a.compareTo(b)>=0));
		System.out.println("a > b ? \t"+(a.compareTo(b)>0));
		System.out.println("a != b ? \t"+(a.compareTo(b)!=0));
	}

	void randomNumbers() {
		System.out.println("\nExercise 4:");
		System.out.println("===========\n");

		/************************************************************
		 * Insert the code of Exercise 4 below this comment!
		 ************************************************************/
		SecureRandom prng = new SecureRandom();
		//Generated random integer with a certain length
		int length = 64;
		for(int i = 1 ; i<7 ; i++){
			System.out.println(length+" bit random integer : "+new BigInteger(length, prng));
			length=length*2;
		}
	}

	public void run() {
		/*
		 * Uncomment the method after the implementation!
		 */
		expression();
		comparison();
		randomNumbers();
	}

	public static void main(String[] args) {
		BigIntegerExercise m = new BigIntegerExercise();

		m.run();
	}

}
