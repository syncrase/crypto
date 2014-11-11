package rsa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 
 * RSA Decryption
 *
 */
public class RSADecryptor {
	/// Prime p
	private BigInteger p;
	/// Prime q
	private BigInteger q;
	/// Multiplicative inverse of q modulo p
	private BigInteger qm1;
	/// Module n  
	private BigInteger n;
	private BigInteger d;
	
	public RSADecryptor(BigInteger p, BigInteger q, BigInteger d) {
		this.p = p;
		this.q = q;
		this.qm1 = q.modInverse(p);
		this.n = p.multiply(q);
		this.d = d;
	}

	public BigInteger decrypt(BigInteger y) {

		/************************************************************
		 * Insert the code of Exercise 10b below this comment!
		 ************************************************************/
		
		BigInteger a = PublicKeyCryptoToolbox.modExp(y, d, p);
		BigInteger b = PublicKeyCryptoToolbox.modExp(y, d, q);
		BigInteger x = (((a.subtract(b)).multiply((PublicKeyCryptoToolbox.extendedEuclid(q, p).getInverse()).mod(p))).mod(p)).multiply(q).add(b);
		
		return x;
	}
	
	public String bigIntegerToString(BigInteger b) {
		BigInteger i;
		Vector<Byte> v = new Vector<Byte>();
		
		while (b.compareTo(BigInteger.ONE)>0) {
			i = b.mod(BigInteger.valueOf(255));
			b = b.divide(BigInteger.valueOf(255));
			v.add(i.byteValue());
		}

		byte[] a = new byte[v.size()];
		
		for (int j=0; j<v.size(); j++) {
			a[j]=v.get(v.size()-1-j);
		}
		try {
			return new String(a, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String decrypt(Vector<BigInteger> v) {
		String s="";
		BigInteger p;
		
		for (BigInteger b : v) {
			//System.out.println(b);
			p = decrypt(b);
			//System.out.println(bigIntegerToString(p));
			s = s + bigIntegerToString(p);
		}
		
		return s;
	}

}
