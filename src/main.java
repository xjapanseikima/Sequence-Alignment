import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

public class main {
	static int match;
	static int mismatch;
	static int h;
	static int g;
	static String inputFile = "";
	static String configFilepath = "src/parameters.config";
	static String option;// default
	static String array[];// test
	static String str = null;
	static ArrayList<String> sequence1 = new ArrayList<String>();
	static ArrayList<String> sequence2 = new ArrayList<String>();

	public static void main(String args[]) throws IOException {
		inputFile = args[0];//src/input.fasta 
		option = args[1];//0 or 1
		configFilepath = args[2];//src/parameters.config
		// readingDNAsequqnce(inputFile);
		readingDNAsequqnce(inputFile);
		readconfig(configFilepath);
		if (option.equals("0")) {
			NeedlemanWunsch_alg x1 = new NeedlemanWunsch_alg();
			x1.set_Table();
			x1.NWalg();
			x1.printhighest();
			x1.Traceback();
			//x1.printTable();
			x1.printanswer();
			x1.printtxt();
		}
		if (option.equals("1")) {
			SmithWaterman_alg x2 = new SmithWaterman_alg();
			x2.set_Table();
			x2.SWalg();
			x2.Max();
			//x2.printTable();
			x2.Traceback();
			x2.printanswer();
			x2.printtxt();
		}
	}

	private static void printSequencq1() {
		// TODO Auto-generated method stub
		System.out.println("Sequence1");
		for (int i = 0; i <= sequence1.size() - 1; i++) {
			System.out.print(sequence1.get(i));
		}
		System.out.println();
	}

	private static void printSequencq2() {
		// TODO Auto-generated method stub
		System.out.println("Sequence2");
		for (int i = 0; i <= sequence2.size() - 1; i++) {
			System.out.print(sequence2.get(i));
		}
		System.out.println();
	}

	private static void readingDNAsequqnce(String IF) throws FileNotFoundException {
		InputStream file = new FileInputStream(IF);
		BufferedReader reader = null;
		int n = 0;// 第一次讀到“>”
		try {
			reader = new BufferedReader(new InputStreamReader(file));
			while (true) {
				str = reader.readLine();// 跳過一行
				if (str != null) {
					if (str.contains(">")) {
						n = n + 1;
					} else {
						int i = 0;
						for (i = 0; i < str.length(); i++) {
							String[] tempstring = new String[str.length()];
							tempstring = str.split("");
							// System.out.print(tempstring[i]);
							if (n == 1) {
								sequence1.add(tempstring[i]);
							}
							if (n == 2) {
								sequence2.add(tempstring[i]);
								// System.out.print(sequence2.get(i));
							}
						}
					}
				} else
					break;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.print(Arrays.deepToString(sequence1.get(1)));
	}

	private static void readconfig(String cf) throws IOException {
		Properties properties = new Properties();
		InputStream in = new FileInputStream(cf);
		try {
			properties.load(in);
			match = Integer.parseInt(properties.getProperty("match").trim());
			mismatch = Integer.parseInt(properties.getProperty("mismatch").trim());
			h = Integer.parseInt(properties.getProperty("h").trim());
			g = Integer.parseInt(properties.getProperty("g").trim());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Wrong Input File");
		}
	}
}
