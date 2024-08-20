package br.com.wishList.main;

public class Main {
	public static void main(String args[]) {
		
		User cauany = new User("cauany rodrigues", "cacau21", "cauany@gmail.com", "1234");
		User duda = new User("duda", "dudinha", "dudy@gmail.com", "1234");
		User bruna = new User("bruna", "bruzky", "bruna@gmail.com", "1234");
		
		Wish bolsa = new Wish("bolsa", "lindaa", "Acessório", "https", 39.9, "https:/github.com", cauany);
		Wish garrafa = new Wish("garrafa", "1 litro", "Utencílios", "https", 39.9, "https:/github.com", cauany);
		Wish tigela = new Wish("tigela", "500g", "Utencílios", "https", 39.9, "https:/github.com", duda);
		
		duda.addWish(garrafa);
		duda.addWish(tigela);
		cauany.addWish(bolsa); // Não é adicionado pq cauany já tem bolsa
		
		System.out.println(cauany);
		System.out.println("Lista de desejos de cauay: " + cauany.getWishListView());
		System.out.println(duda);
		System.out.println(duda.getWishListView());
		System.out.println(cauany.getWishListSize());
		System.out.println(bruna.getWishListSize());
		cauany.clearWishList();
		System.out.println("Cauany tem: " + cauany.getWishListSize());
	}
	
}

