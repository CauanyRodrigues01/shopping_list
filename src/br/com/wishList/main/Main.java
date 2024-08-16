package br.com.wishList.main;

public class Main {
	public static void main(String args[]) {
		
		User cauany = new User("cauany rodrigues", "cacau21", "cauany@gmail.com", "1234");
		User duda = new User("duda", "dudinha", "dudy@gmail.com", "1234");
		
		Wish bolsa = new Wish("bolsa", "lindaa", "Acessório", "https", 39.9, "https:/github.com", cauany);
		Wish garrafa = new Wish("garrafa", "1 litro", "Utencílios", "https", 39.9, "https:/github.com", cauany);
		Wish tigela = new Wish("tigela", "500g", "Utencílios", "https", 39.9, "https:/github.com", duda);
		
		duda.addWish(garrafa);
		duda.addWish(tigela);
		cauany.addWish(bolsa);
		
		System.out.println(cauany);
		System.out.println(cauany.getWishList());
		System.out.println(duda);
		System.out.println(duda.getWishList());
		System.out.println("teste em outra maquina");
		
		
		System.out.println("Teste PC de JV");
		System.out.println("Teste2 PC de JV");
	}
	
}

