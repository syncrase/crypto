package rsa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Vector;

public class RSAEncryptor {
	private BigInteger n;
	private BigInteger e;

	public RSAEncryptor(BigInteger n, BigInteger e) {
		this.n = n;
		this.e = e;
	}

	public BigInteger encrypt(BigInteger x) {
		
		/************************************************************
		 * Insert the code of Exercise 10b below this comment!
		 ************************************************************/
		
		
		
		return PublicKeyCryptoToolbox.modExp(x, e, n);
	}

	public BigInteger stringToBigInteger(String s) {
		BigInteger b = BigInteger.ONE;

		try {
			for (byte i : s.getBytes("UTF-8")) {
				b = b.multiply(BigInteger.valueOf(255));
				b = b.add(BigInteger.valueOf(i));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return b;
	}

	public Vector<BigInteger> encrypt(String x) {
		String s;
		Vector<BigInteger> v = new Vector<BigInteger>();
		int len = (n.bitLength() / 8)-1;

		do {
			if (x.length() <= len) {
				s = x;
				x = "";
			} else {
				s = x.substring(0, len);
				//System.out.println(s);
				x = x.substring(len);
			}
			v.add(encrypt(stringToBigInteger(s)));
		} while (x.length() > 0);
		return v;
	}
}
