package model;

public class College {
	private PeopleBag peopleBag;
	private TextbookBag textbookBag;
	private MasterCourseBag masterCourseBag;

	public College(int peopleBagSize, int textbookBagSize, int masterCourseBagSize) {
		super();
		peopleBag = new PeopleBag(peopleBagSize);
		textbookBag = new TextbookBag(textbookBagSize);
		masterCourseBag = new MasterCourseBag(masterCourseBagSize);
	}

	public PeopleBag getPeopleBag() {
		return peopleBag;
	}

	public void setPeopleBag(PeopleBag peopleBag) {
		this.peopleBag = peopleBag;
	}

	public TextbookBag getTextbookBag() {
		return textbookBag;
	}

	public void setTextbookBag(TextbookBag textbookBag) {
		this.textbookBag = textbookBag;
	}

	public MasterCourseBag getMasterCourseBag() {
		return masterCourseBag;
	}

	public void setMasterCourseBag(MasterCourseBag masterCourseBag) {
		this.masterCourseBag = masterCourseBag;
	}

}
