import java.io.IOException;
import java.util.regex.PatternSyntaxException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Album {

	private String name, artist, geniusPage;
	private String[] trackList;

	public Album(String Name, String Art) {
		this.name = Name;
		this.artist = Art;
		this.geniusPage = "https://genius.com/albums/" + convertHelper(this.artist) + "/" + convertHelper(this.name);

		try {
			Document htmlDoc = Jsoup.connect(this.geniusPage).get();

			String html = htmlDoc.getElementsByClass("chart_row-content-title").toString();
			Document doc = Jsoup.parse(html);

			// this contains unfiltered tracklist
			String tracks = doc.body().text() + " ";

			// separates tracks and puts into array
			this.trackList = tracks.split(" Lyrics ");

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String[] getTracklist() {
		return this.trackList;
	}

	public int getTracklistSize() {
		return this.trackList.length;
	}

	public String getArtist() {
		return this.artist;
	}

	public String getAlbumName() {
		return this.name;
	}

	private static String convertHelper(String str) {
		str = str.substring(0, 1).toUpperCase() + str.substring(1);
		str = str.replaceAll(" ", "-");

		return str;
	}

	public static void test() {

		try {
			// gets full html
			Document htmlDoc = Jsoup.connect(
					"https://genius.com/artists/Architects")
					.get();
			//System.out.println( "htmlDoc: " + htmlDoc);
			System.out.println();
			
			// <h2 class = "sheesh">..............</h2>
			String albumName = htmlDoc.getElementsByClass("act-show cont-artists snarly").toString();
			System.out.println( "albumName: " + albumName);
			System.out.println();
			
			// not needed
			Document doc = Jsoup.parse(albumName);
			//System.out.println( "doc: " + doc);
			System.out.println();

			// text
			String tracks = doc.body().text();
			//System.out.println( "tracks: " + tracks);
			System.out.println();
			

			// separates tracks and puts into array
			//this.trackList = tracks.split(" Lyrics ");

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
