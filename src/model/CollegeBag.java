package model;

public class CollegeBag {
	private College[] colleges;
	private int nElems;
	
	public CollegeBag(int maxSize) {
		colleges = new College[maxSize];
		nElems = 0;
	}
}
