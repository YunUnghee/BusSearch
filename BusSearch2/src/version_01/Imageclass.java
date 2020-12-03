package version_01;

import java.awt.Image;
import java.io.File;

public class Imageclass {
	File src;
	Image img;
	String name1;
	private int name;

	public Imageclass(String name) {
		name1 = name;
		this.name = Integer.valueOf(name);
	}

	public File getImage() {
		try {

			switch (name) {
			case 100:
				src = new File(Main.class.getResource("../Image/100.png").toURI());
				break;
			case 101:
				src = new File(Main.class.getResource("../Image/101.png").toURI());
				break;
			case 103:
				src = new File(Main.class.getResource("../Image/103.png").toURI());
				break;
			case 200:
				src = new File(Main.class.getResource("../Image/200.png").toURI());
				break;
			case 220:
				src = new File(Main.class.getResource("../Image/220.png").toURI());
				break;
			case 9999:
				src = new File(Main.class.getResource("../Image/default.png").toURI());
				break;

			}
		} catch (Exception e) {
		}
		return src;
	}

}
