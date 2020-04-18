package it.polito.tdp.quadrato.model;

import java.util.ArrayList;
import java.util.List;

public class RisolviQuadrato {
	private int N ; // lato del quadrato
	private int N2 ; // numero di caselle (N^2)
	private int magica ; // costante magica
	private List<List<Integer>>soluzione; 
	
	
	public RisolviQuadrato(int N) {
		this.N = N; 
		this.N2 = N*N; 
		this.magica = N*(N2+1)/2; 
		
	}
	
	public List<List<Integer>> quadrati() {
		List<Integer> parziale = new ArrayList<>();
		int livello = 0; 
		this.soluzione = new ArrayList<List<Integer>>(); 
		cerca(parziale, livello); 
		
		return this.soluzione;
		
	}
	
	
	private void cerca(List<Integer> parziale, int livello) {
		
		if(livello == N2) {
			//caso terminale 
			if(controlla(parziale)) {
				
				//System.out.println(parziale); 
				this.soluzione.add(new ArrayList<Integer>(parziale)); 
			}
			return; 
		}
		
		if(livello%N==0 && livello!=0) {
			if(!ControllaRiga(parziale, livello/N-1)); 
			return; 
		}
		
		
		for(int i= 0; i<=N2; i++) {
			if(!parziale.contains(i)) {
				parziale.add(i);
				cerca(parziale, livello+1);
				parziale.remove(parziale.size()-1); 
			}
		}
		
		
	}
	/**
	 * Verifica se una soluzione rispetta tutte le somme
	 * @param parziale
	 * @return
	 */
	private boolean controlla(List<Integer> parziale) {
		if(parziale.size()!=this.N*this.N)
			throw new IllegalArgumentException("Numero di elementi insufficiente") ;
		
		// Fai la somma delle righe
		for(int riga=0; riga<this.N; riga++) {
			int somma = 0 ;
			for(int col=0; col<this.N; col++) {
				somma += parziale.get(riga*this.N+col) ;
			}
			if(somma!=this.magica)
				return false ;
		}
		
		// Fai la somma delle colonne
		for(int col=0; col<this.N; col++) {
			int somma = 0 ;
			for(int riga=0; riga<this.N; riga++) {
				somma += parziale.get(riga*this.N+col) ;
			}
			if(somma!=this.magica)
				return false ;
		}
		
		// diagonale principale
		int somma = 0;
		for(int riga=0; riga<this.N; riga++) {
			somma += parziale.get(riga*this.N+riga) ;
		}
		if(somma!=this.magica)
			return false ;
		
		// diagonale inversa
		somma = 0;
		for(int riga=0; riga<this.N; riga++) {
			somma += parziale.get(riga*this.N+(this.N-1-riga)) ;
		}
		if(somma!=this.magica)
			return false ;

		return true ;
	}
	
	private boolean ControllaRiga(List<Integer> parziale, int riga ) {
	 int somma = 0; 
	 
	 for (int col=0; col<N;col++) {
		 somma+=parziale.get(riga*N+col);
		 
	 }return somma==magica; 
	}
}
