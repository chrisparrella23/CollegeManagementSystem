package model;

import java.io.Serializable;

public class MajorBag implements Serializable {
	private MiniCourseBag[] miniCourseBags;
	private int nElems;

	public MajorBag(int maxSize) {
		miniCourseBags = new MiniCourseBag[maxSize];
		nElems = 0;
	}

	public MiniCourseBag[] getMiniCourseBags() {
		return miniCourseBags;
	}

	public void setMiniCourseBags(MiniCourseBag[] miniCourseBags) {
		this.miniCourseBags = miniCourseBags;
	}

	public int getnElems() {
		return nElems;
	}
	
	public void insert(MiniCourseBag miniCourseBag) {
		miniCourseBags[nElems++] = miniCourseBag;
	}
	
	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(miniCourseBags[i]);
		}
		System.out.println();
	}
	
	public MiniCourseBag findByMajor(Major major) {
		for (int i = 0; i < nElems; i++) {
			if (miniCourseBags[i].getMajor() == Major.CSE) {
				return miniCourseBags[i];
			}
		}
		return null;
	}

	
}
