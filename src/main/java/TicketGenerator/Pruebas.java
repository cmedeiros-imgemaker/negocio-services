package TicketGenerator;

public class Pruebas {

	public static void main(String[] args) {

		// Texto
		String sTexto = "<ticket>TICKET_f3475e60b119a06110162d21e7bb7c32f341bfee</ticket>";
		// Texto que vamos a buscar
		String sTextoBuscado = "palabra";
		// Contador de ocurrencias
		int contador = 0;

		System.out.println(sTexto.substring(sTexto.indexOf("<ticket>") + 8,
				sTexto.indexOf("</ticket>")));

	}
	
	
	

}
