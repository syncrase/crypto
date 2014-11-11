package rsa;

import java.math.BigInteger;
import java.util.ArrayList;

public class PKCTExercise {

	void eeaResultExercise() {

		System.out.println("\nExercise 5:");
		System.out.println("===========\n");

		/************************************************************
		 * Insert the code of Exercise 5a+b below this comment!
		 ************************************************************/
		BigInteger a = new BigInteger("8002109");
		BigInteger b = new BigInteger("7186131");
		BigInteger d = new BigInteger("1");
		BigInteger x = new BigInteger("-2996671");
		BigInteger y = new BigInteger("3336940");
		EEAResult eeaResult = new EEAResult(a, b, d, x, y);
		System.out.println("result : "+eeaResult.toString());
		//If a isn't invertible modulo b, the method modInverse cannot return any result
		try {
			System.out.println("inverse is : " + eeaResult.getInverse());
		} catch (Exception e) {
			System.out.println("a isn't invertible modulo b ");
		}
	}

	void euclidExercise() {

		System.out.println("\nExercise 6:");
		System.out.println("===========\n");

		/************************************************************
		 * Insert the code of Exercise 6b+c below this comment!
		 ************************************************************/
		BigInteger a = new BigInteger("7019544");
		BigInteger b = new BigInteger("8135112");
		System.out.println("Given a and b, respectively equals to " + a
				+ " and " + b);
		System.out.println("After apply the extended euclidean algorithm : "
				+ PublicKeyCryptoToolbox.extendedEuclid(a, b).toString());

		// Compute the gcd 7186131 and 8002109
		a = new BigInteger("7186131");
		b = new BigInteger("8002109");
		System.out.println("Now, given a and b, respectively equals to " + a
				+ " and " + b);
		EEAResult eeaResult = PublicKeyCryptoToolbox.extendedEuclid(a, b);
		System.out.println("After apply the extended euclidean algorithm : "
				+ eeaResult.toString());

		try {
			System.out.println("inverse is : " + eeaResult.getInverse());
		} catch (Exception e) {
			System.out.println("a isn't invertible modulo b ");
		}
	}

	void modExpExercise() {
		System.out.println("\nExercise 7:");
		System.out.println("===========\n");

		/************************************************************
		 * Insert the code of Exercise 6b+c below this comment!
		 ************************************************************/

		BigInteger a = new BigInteger("17");
		BigInteger b = new BigInteger("1005");
		BigInteger m = new BigInteger("230");
		System.out.println("17^1005 modulo 230 with my method = "
				+ PublicKeyCryptoToolbox.modExp(a, b, m));
		System.out.println("17^1005 modulo 230 with modPow method = "
				+ a.modPow(b, m));
	}

	void randomNumbers() {
		System.out.println("\nExercise 8:");
		System.out.println("===========\n");

		/************************************************************
		 * Insert the code of Exercise 8c below this comment!
		 ************************************************************/
		PublicKeyCryptoToolbox pkct = new PublicKeyCryptoToolbox();
		int bit_length = 64;
		BigInteger b = pkct.randomInteger(bit_length);
		System.out.println(" Generated big integer (bit length = " + bit_length
				+ ") : " + b);

		System.out.println(" random integer under n=" + b + " : "
				+ pkct.randomInteger(b));

		System.out
				.println("Generate 20 random numbers between 1 and 102030405060708090");
		for (int i = 0; i < 20; i++) {
			System.out.println((i + 1) + ") \t"
					+ pkct.randomInteger(new BigInteger("102030405060708090")));
		}
	}

	void primalityTest() {
		System.out.println("\nExercise 9:");
		System.out.println("===========\n");

		/************************************************************
		 * Insert the code of Exercise 9c+e below this comment!
		 ************************************************************/
		// ArrayList<BigInteger>
		// BigInteger[]
		ArrayList<BigInteger> bigIntArray = new ArrayList<BigInteger>();
		// = {
		bigIntArray
				.add(new BigInteger(
						"343232674978653231166402657365997144371953839307928119227511"));
		bigIntArray
				.add(new BigInteger(
						"667984267564412673929015509827448340743034959781814076053617"));
		bigIntArray
				.add(new BigInteger(
						"902857742149935096180418505174605673479122931367283811478172"));
		bigIntArray
				.add(new BigInteger(
						"408025803078911998315951562970145017384911797981108589419277"));
		bigIntArray
				.add(new BigInteger(
						"1040747016400791716218800060097121047453800566864795676123313"));
		bigIntArray
				.add(new BigInteger(
						"341920262248211364330159957004187372102128507551704555404569"));
		bigIntArray
				.add(new BigInteger(
						"880723572255844606588685481136407927962444382553394348261623"));
		bigIntArray
				.add(new BigInteger(
						"1130242628975018265380102543215055338361897468448588898970126"));
		// };
		PublicKeyCryptoToolbox pkct = new PublicKeyCryptoToolbox();
		for (BigInteger bi : bigIntArray) {
			System.out.println(bi + " is prime ? \t"
					+ pkct.millerRabinTest(bi, 100));
		}

		System.out.println("\n\nGenerate a random prime with 128, 256, 512, and 1024 bits.");
		int length = 128;
		
		for (int i = 0; i < 4; i++) {
			System.out.println("random prime of length "+length+" : "+pkct.randomPrime(length, 100));
			length = length * 2;
		}
	}

	void run() {
		/*
		 * Uncomment the method after completion of the respective exercise!
		 */
		eeaResultExercise();
		euclidExercise();
		modExpExercise();
		randomNumbers();
		primalityTest();
	}

	public static void main(String[] args) {

		PKCTExercise m = new PKCTExercise();
		m.run();
	}

}
