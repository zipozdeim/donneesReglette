package data.reglette;

public enum questions {
	Q1("Conditions de travail"),
	Q2("Motivation au travail"),
	Q3("Stress lié au travail"),
	Q4("Fatigue due au travail"),
	Q5("Niveau de pression"),
	Q6("Méthodes de travail"),
	Q7("Equilibre pro - perso");

	private String libelle="";

	//Constructeur
	questions(String libelle){
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle; 
	}

	public String toString() {
		return libelle; 
	}

}
