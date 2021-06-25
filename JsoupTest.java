import java.io.IOException;
import java.util.regex.PatternSyntaxException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JsoupTest {

	public static void main(String[] args) {

		try {
			String[] songs = { "https://genius.com/Bring-me-the-horizon-can-you-feel-my-heart-lyrics",
					"https://genius.com/Bring-me-the-horizon-the-house-of-wolves-lyrics",
					"https://genius.com/Bring-me-the-horizon-empire-let-them-sing-lyrics",
					"https://genius.com/Bring-me-the-horizon-sleepwalking-lyrics",
					"https://genius.com/Bring-me-the-horizon-go-to-hell-for-heavens-sake-lyrics",
					"https://genius.com/Bring-me-the-horizon-shadow-moses-lyrics",
					"https://genius.com/Bring-me-the-horizon-and-the-snakes-start-to-sing-lyrics",
					"https://genius.com/Bring-me-the-horizon-seen-it-all-before-lyrics",
					"https://genius.com/Bring-me-the-horizon-antivist-lyrics",
					"https://genius.com/Bring-me-the-horizon-crooked-young-lyrics",
					"https://genius.com/Bring-me-the-horizon-hospital-for-souls-lyrics" };
			
			ArrayList<String> allWords = new ArrayList<String>();

			for (String link : songs) {

				Document htmlDoc = Jsoup.connect(link).get();

				String html = htmlDoc.getElementsByClass("lyrics").toString();
				Document doc = Jsoup.parse(html);

				// this contains unfiltered lyrics
				String lyrics = doc.body().text();

				// separates word and puts into array
				String[] splitArray = lyrics.split("\\s+");
				

				// filters our special chars
				for (String a : splitArray) {

					if (!a.contains("[") && !a.contains("]")) {
						a = a.replaceAll("[^a-zA-Z0-9]", "");
						a = a.toLowerCase();
						allWords.add(a);
					}

				}
			}

			ArrayList<String> rememberedWords = new ArrayList<String>();
			boolean duplicate = false;

			System.out.println(allWords.size());
			
			Comparator<Word> compa = new Comp();
			ArrayList<Word> finalList = new ArrayList<Word>();
			
			for (String a : allWords) {

				duplicate = false;

				for (String b : rememberedWords) {
					if (b.equals(a)) {
						duplicate = true;
						break;
					}
				}

				// if word is not duplicate
				if (!duplicate) {
					rememberedWords.add(a);
					finalList.add (new Word(a, Collections.frequency(allWords,a)));
					//System.out.println("Frequency of " + "'" + a + "' is " + Collections.frequency(allWords, a));
				}

			}
			
			Collections.sort(finalList,compa);
			
			for ( Word x : finalList ) {
				System.out.println( x );
			}
			
			
			

		}

		catch (IOException e) {
			System.out.println("Invalid URL");
		}

		catch (PatternSyntaxException pse) {

		}

	}
}
