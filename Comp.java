import java.util.Comparator;

public class Comp implements Comparator<Word> {

	@Override
	public int compare(Word o1, Word o2) {
		
		if ( o1.getCount() < o2.getCount()) {
			return 1;
		}
		
		if ( o1.getCount() == o2.getCount()) {
			return 0;
		}
		
		return -1;
		
	}

}
