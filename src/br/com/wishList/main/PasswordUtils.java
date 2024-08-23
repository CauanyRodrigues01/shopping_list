package br.com.wishList.main;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {

	private String passwordTreated;

	/**
	 * @param inputPassword
	 */
	public PasswordUtils(String inputPassword) {
		
		try {
			String originalPassword = inputPassword;
			String generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);
			passwordTreated = generatedSecuredPasswordHash;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("O algoritmo especificado não está disponível: " + e.getMessage());
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			System.out.println("Especificação da chave inválida: " + e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * Gera um hash seguro para a senha fornecida usando o algoritmo PBKDF2.
	 * 
	 * @param password A senha original em texto claro.
	 * @return O hash gerado, incluindo o número de iterações, o sal, e o hash.
	 * @throws NoSuchAlgorithmException Se o algoritmo especificado não for encontrado.
	 * @throws InvalidKeySpecException Se a especificação da chave for inválida.
	 */
	private static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();
		
		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}
	/**
	 * Gera um array de bytes para o sal usnado um algoritmo seguro.
	 * @return Um array de bytes representando o sal.
	 * @throws NoSuchAlgorithmException Se o algortimo especificado não for encontrado.
	 */
	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
	/**
	 * Converte um array de bytes em uma representação hexadecimal.
	 * @param array O array de bytes a ser convertido.
	 * @return Uma string representando o array em hexadecimal.
	 * @throws NoSuchAlgorithmException Se o algoritmo especificado não for encontrado.
	 */
	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		
		int paddingLength = (array.length * 2) - hex.length();
		if(paddingLength > 0) {
			return String.format("%0"  +paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}
	/**
	 * Valida uma senha comparando o hash armazenado com o hash da senha fornecida.
	 * 
	 * @param originalPassword A senha original em texto claro.
	 * @param storedPassword O hash da senha armazenada, incluindo iterações, sal, e hash.
	 * @return true se a senha for válida, false caso contrário.
	 * @throws NoSuchAlgorithmException Se o algoritmo especificado não for encontrado.
	 * @throws InvalidKeySpecException Se a especificação da chave for inválida.
	 */
	protected boolean validatePassword(String inputPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = this.passwordTreated.split(":");
		int iterations = Integer.parseInt(parts[0]);
		
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);
		
		PBEKeySpec spec = new PBEKeySpec(inputPassword.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();
		
		int diff = hash.length ^ testHash.length;
		for(int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}
	/**
	 * Converte uma string hexadecimal em um array de bytes.
	 *
	 * @param hex A string hexadecimal a ser convertida em um array de bytes.
	 * @return Um array de bytes que representa a conversão da string hexadecimal.
	 * @throws NoSuchAlgorithmException Se o algoritmo utilizado para gerar a conversão não for encontrado.
	 */
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for(int i = 0; i < bytes.length ;i++) {
			bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
	
}
