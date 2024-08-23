package br.com.wishList.main;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
	public static void main(String args[]) throws WishExtantException, NoSuchAlgorithmException,
    UnsupportedEncodingException  {
		
		User cauany = new User("cauany rodrigues", "cacau21", "cauany@gmail.com", "1234");
		User duda = new User("duda", "dudinha", "dudy@gmail.com", "1234");
		User bruna = new User("bruna", "bruzky", "bruna@gmail.com", "1234");
		
		Wish bolsa = new Wish("bolsa", "lindaa", "Acessório", "https", 39.9, "https:/github.com", cauany);
		Wish garrafa = new Wish("garrafa", "1 litro", "Utencílios", "https", 39.9, "https:/github.com", cauany);
		Wish tigela = new Wish("tigela", "500g", "Utencílios", "https", 39.9, "https:/github.com", duda);
		
		// tigela pertence a duda, não pode remover de cauany
		try {
			cauany.removeWish(tigela);
		} catch (WishInUseException e) {
			System.out.println("tigela pertence a duda, não pode remover de cauany");
			e.printStackTrace();
		}
		
		// tigela pertence a duda, não pode ser adicionada a cauany
		try {
			cauany.addWish(tigela);
		} catch (WishInUseException e) {
			System.out.println("tigela pertence a duda, não pode ser adicionada a cauany");
			e.printStackTrace();
		} catch (WishExtantException e) {
			e.printStackTrace();
		}
		
		// garrafa já pertence a cauany, não pode ser adicionada mais uma vez
		try {
			cauany.addWish(garrafa);
		} catch (WishInUseException e) {
			System.out.println("tigela pertence a duda, não pode ser adicionada a cauany");
			e.printStackTrace();
		} catch (WishExtantException e) {
			System.out.println("garrafa já pertence a cauany, não pode ser adicionada mais uma vez");
			e.printStackTrace();
		}
		
		System.out.println(cauany);
		System.out.println("Lista de desejos de cauay: " + cauany.getWishListView());
		System.out.println(duda);
		System.out.println(duda.getWishListView());
		System.out.println(cauany.getWishSize());
		System.out.println(bruna.getWishSize());
		cauany.clearUserWish();
		System.out.println("Cauany tem: " + cauany.getWishSize());
		System.out.println(cauany.toString());
		System.out.println(duda.toString());
		System.out.println(bruna.toString());
		
		System.out.println(cauany.verifyPassword("1234"));

		
	} // fim do método main
} // fim da classe main

