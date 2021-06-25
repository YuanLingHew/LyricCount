
public class Word {
	
	private String name;
	private int count;

	public Word ( String Name, int Count ) {
		this.name= Name;
		this.count = Count;
	}
	
	public int getCount () {
		return this.count;
	}
	
	@Override
	public String toString() {
		return "Frequency of '" + this.name + "' is " + this.count;
	}
}
