/**
 * Copyright (c) 2005,2006 Polkomtel S.A. All Rights Reserved.
 *
 * This source code file is CONFIDENTIAL and PROPRIETARY
 * information of Polkomtel S.A. not intended for public
 * distribution. Use is subject to license terms.
 *
 * $Id: PasswordHash.java 1167 2007-01-04 14:15:52Z sylwester.lachiewicz $
 */
package aes256.szyfrowanie;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Obliczanie hash hasla
 */
public class Hash {
    /**
     * Algorytm hashujacy: SHA1
     */
    public static String HASH_ALGORITHM_SHA1 = "SHA1";
    /**
     * Message digest
     */
    private MessageDigest messageDigest;

    public Hash() {
        try {
            messageDigest = MessageDigest.getInstance(HASH_ALGORITHM_SHA1);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        }
    }

    /**
     * Obliczenie hash funkcja hasujaca SHA1 w postaci Hex
     *
     * @param password haslo
     * @return digest w postaci HEX
     */
    public String getHashHex(String password) {
        try {
            return new String(Hex.encodeHex(messageDigest.digest(password.getBytes("UTF8"))));
        } catch (UnsupportedEncodingException e) {
            System.err.print(e);
            return null;
        }
    }

    /**
     * Obliczenie hash funkcja odhasujaca SHA1 w postaci Hex
     *
     * @param password haslo
     * @return digest w postaci HEX
     */
    public String getDeHashHex(String password) {
        try {
            return new String(Hex.decodeHex(password.toCharArray()));
        } catch (DecoderException e) {
            System.err.print(e);
            return null;
        }
    }

    /**
     * Test digest dla hasla "matrix"
     *
     * @param args
     */
    public static void main(String[] args) {
        String password = "test";
        System.out.println("pass:hash => |" + password + "| " + new Hash().getHashHex(password));
        String hashHex = new Hash().getHashHex(password);
        System.out.println("hash:pass => |" + password + "| " + new Hash().getDeHashHex("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3"));
    }

}
