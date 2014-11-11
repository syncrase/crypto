package rsa;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class PublicKeyCryptoToolbox {
	// / Secure pseudo random number generator
	SecureRandom prng;

	public PublicKeyCryptoToolbox() {
		// Create a new prng instance
		prng = new SecureRandom();
	}

	public static EEAResult extendedEuclid(BigInteger a, BigInteger b) {

		/************************************************************
		 * Insert the code of Exercise 6a below this comment!
		 ************************************************************/
		BigInteger d = null;
		BigInteger x = BigInteger.ONE;
		BigInteger y = BigInteger.ZERO;
		BigInteger buffer;
		//This tabl will contain values of a/b at each step of the algorithm
		ArrayList<BigInteger> tab = new ArrayList<BigInteger>();
		EEAResult eeaResult = new EEAResult(a, b, d, x, y);

		System.out.println("Apply the extended euclidean algorithm to "+a+" and "+b);
		while (b.compareTo(BigInteger.ZERO) != 0) {// If b = 0 the first part of
													// the algorithm is conclude
			tab.add(a.divide(b));
			buffer = a;//Memory of a
			a = b;//Change a
			b = buffer.mod(b);// Change b
			
		}
		eeaResult.setD(a);

		int tabSize = tab.size();
		for (int i = 0; i < tabSize; i++) {
			buffer = y;
			y = x.subtract(tab.get(tabSize - i - 1).multiply(y));//Compute y
			x = buffer;
			// System.out.println(" x et y = "+x+" et "+y);
		}

		eeaResult.setX(x);
		eeaResult.setY(y);

		return eeaResult;

	}

	static public BigInteger modExp(BigInteger a, BigInteger b, BigInteger m) {

		/************************************************************
		 * Insert the code of Exercise 7a below this comment!
		 ************************************************************/

		BigInteger d = BigInteger.ONE;
		BigInteger c = BigInteger.ZERO;
		for (int i = b.bitLength() - 1; i >= 0; i--) {
			c = c.multiply(new BigInteger("2"));//c=2*c
			d = (d.multiply(d)).mod(m);//d = d*dmod(m)
			if (b.testBit(i) == true) {
				c = c.add(BigInteger.ONE);//c=c+1
				d = (d.multiply(a)).mod(m);//d = d*amod(m)
			}
		}

		return d;//return the result of a.pow(b).mod(m)
	}

	public BigInteger randomInteger(int bit_length) {

		/************************************************************
		 * Insert the code of Exercise 7a below this comment!
		 ************************************************************/
		//return a random integer with a length of bit_length bits
		return new BigInteger(bit_length, prng);
	}

	public BigInteger randomInteger(BigInteger n) {

		/************************************************************
		 * Insert the code of Exercise 7b below this comment!
		 ************************************************************/
		// compute the bit length of n
		int l = n.bitLength();
		// System.out.println("Bit length = "+l);
		BigInteger r;
		do {
			// generate a l-bit random number
			r = randomInteger(l);
			// System.out.println("Big integer generated = "+r);
		} while (!(r.compareTo(BigInteger.ONE) > 0) || !(r.compareTo(n) < 0));

		return r;//Return a random integer which is inf to n and with a lentgh of n.bitLength
	}

	public boolean witness(BigInteger a, BigInteger n) {

		/************************************************************
		 * Insert the code of Exercise 8a below this comment!
		 ************************************************************/
		BigInteger x;
		// n-1
		BigInteger nMinusOne = n.subtract(BigInteger.ONE);
		BigInteger d = BigInteger.ONE;
		for (int i = nMinusOne.bitLength() - 1; i >= 0; i--) {
			x = d;
			d = (d.multiply(d)).mod(n);
			if ((d.compareTo(BigInteger.ONE) == 0)
					&& (x.compareTo(BigInteger.ONE) != 0)
					&& (x.compareTo(nMinusOne) != 0)) {
				return true;
			}
			if (nMinusOne.testBit(i) == true) {
				d = (d.multiply(a)).mod(n);
			}
		}
		if (d.compareTo(BigInteger.ONE) != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param n
	 * @param s
	 * @return true if n is a composite and false if n is a prime number
	 */
	public boolean millerRabinTest(BigInteger n, int s) {

		/************************************************************
		 * Insert the code of Exercise 8b below this comment!
		 ************************************************************/
		BigInteger a;
		for (int i = 0; i < s; i++) {
			a = randomInteger(n.subtract(BigInteger.ONE));
			if (witness(a, n) == true) {
				return false;// n is composite
			}
		}
		return true;// n is prime
	}

	public BigInteger randomPrime(int bit_length, int s) {

		/************************************************************
		 * Insert the code of Exercise 8d below this comment!
		 ************************************************************/
		
		BigInteger primeRandomNumber;
		do{
			primeRandomNumber = randomInteger(bit_length);//generate a random number
		}while(!millerRabinTest(primeRandomNumber, s));//while it's not prime
		return primeRandomNumber;//Return a generated random prime number
	}

}
