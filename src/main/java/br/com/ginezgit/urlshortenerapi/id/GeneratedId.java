package br.com.ginezgit.urlshortenerapi.id;

public class GeneratedId {

	private final String generatedId;
	private final int generationSeed;
	
	public GeneratedId(String generatedId, int generationSeed) {
		this.generatedId = generatedId;
		this.generationSeed = generationSeed;
	}
	
	public String getGeneratedId() {
		return generatedId;
	}
	public int getGenerationSeed() {
		return generationSeed;
	}
	
	
	
}
