import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class NeedlemanWunsch_alg extends DP_table {
	String NWtable[][];
	int row, column;
	int match, h, g, mismatch;
	int scoretable[][];
	int count_mis = 0;
	int temp;
	boolean openinggap = false;
	int gapcount, opengapcount = 0;
	int countmatch = 0;// 算match的次數
	String seq1, seq2, seq3;
	StringBuffer buff = new StringBuffer();
	StringBuffer buff2 = new StringBuffer();
	StringBuffer buff3 = new StringBuffer();
	Cell cell;


	/*
	 * 先比字 在比分數
	 * 
	 */
	void NWalg() {
		cell = new Cell(super.row, super.column);
		System.out.println("row是" + (super.row - 2));
		System.out.println("column 是" + (super.column - 2));
		System.out.println("Calculating...");
		cell.initalize();
		for (int i = 2; i < super.row; i++) {
			for (int j = 2; j < super.column; j++) {
				if (NWtable[0][i].equals(NWtable[j][0])) {
					temp = match;
				} else {
					temp = mismatch;
				}
				cell.diascore[j][i] = MaxValue(cell.diascore[j - 1][i - 1], cell.colscore[j - 1][i - 1],
						cell.rowscore[j - 1][i - 1])+ temp ;
				cell.colscore[j][i] = MaxValue(cell.diascore[j-1][i ] + g + h, cell.colscore[j-1][i ] + g,
						cell.rowscore[j-1][i] + g + h);
				cell.rowscore[j][i] = MaxValue(cell.diascore[j ][i-1] + h + g, cell.colscore[j ][i-1] + h + g,
						cell.rowscore[j ][i-1] + g);
				cell.currentscore[j][i] = MaxValue(cell.diascore[j][i], cell.colscore[j][i], cell.rowscore[j][i]);

			}
		}
	}

	private int MaxValue(int a, int b, int c) {
		// TODO Auto-generated method stub
		if (a >= b && a >= c) {
			return a;
		}
		if (b > a && b >=c) {
			return b;
		}
		else {
			return c;
		}
			
		
	}

	void printTable() {
		for (int j = 2; j < super.column; j++) {
			for (int i = 2; i < super.row; i++)
				System.out.print(cell.currentscore[j][i] + "	");
			System.out.println("");
		}
		// System.out.print("seq.chartAt" + seq1.charAt(1));
		System.out.println(cell.currentscore[super.column - 1][super.row - 1]);

	}

	void Traceback() {
		int j = super.column - 1;// 最後一格的row
		int i = super.row - 1;// 最後一格的row
		int d_traceback;
		int c_traceback;
		int r_traceback;
		while (true) {
			if (i == 2 || j == 2) {
				if(NWtable[j][0].equals(NWtable[0][i])) {
					buff = buff.append(NWtable[j][0]);
					buff2 = buff2.append(NWtable[0][i]);
					buff3 = buff3.append("|");
				}
				else {
					buff = buff.append(NWtable[j][0]);
					buff2 = buff2.append(NWtable[0][i]);
					buff3 = buff3.append(".");
				}
				break;
			}
			d_traceback = cell.currentscore[j - 1][i - 1];
			c_traceback = cell.currentscore[j - 1][i];
			r_traceback = cell.currentscore[j][i - 1];
			System.out.println("	d" +cell.diascore[j][i-1]+"c " + cell.colscore[j][i-1]);
			System.out.println("	r" +cell.rowscore[j][i-1]+"cu " + cell.currentscore[j][i-1]);
			System.out.println(cell.currentscore[j][i-1]+" "+cell.currentscore[j-1][i-1]+" "+cell.currentscore[j-1][i]);


			System.out.println("	d" +cell.diascore[j][i]+"c " + cell.colscore[j][i]);
			System.out.println("	r" +cell.rowscore[j][i]+"cu " + cell.currentscore[j][i]);
			System.out.println("i是多少"+i+"  j是多少 "+j);

			
			if (d_traceback >= c_traceback && d_traceback >= r_traceback) {
				if(cell.currentscore[j][i]==cell.currentscore[j-1][i-1]+match) {
					buff = buff.append(NWtable[j][0]);
					buff2 = buff2.append(NWtable[0][i]);
					buff3 = buff3.append("|");
					i = i - 1;
					j = j - 1;
					countmatch = countmatch + 1;
				}
				else  {
					buff = buff.append(NWtable[j][0]);
					buff2 = buff2.append(NWtable[0][i]);
					buff3 = buff3.append(".");
					i = i - 1;
					j = j - 1;
					count_mis = count_mis + 1;
				}
		}

			if (c_traceback >=d_traceback && c_traceback >= r_traceback) {

					if(cell.currentscore[j-1][i]==cell.currentscore[j-1][i]+g+h) {
						buff = buff.append(NWtable[j][0]);
						buff2 = buff2.append("-");
						buff3 = buff3.append(" ");
						j = j - 1;
						gapcount = gapcount + 1;
						opengapcount = opengapcount + 1;
					}
					else  {
						buff=buff.append(NWtable[j][0]);
						buff2=buff2.append("-");
						buff3=buff3.append(" ");
						j=j-1;
						gapcount=gapcount+1;
						
					}
				}
				if (r_traceback >= d_traceback && r_traceback >=c_traceback) {

					System.out.println(cell.currentscore[j][i]);
					System.out.println(r_traceback);

					if(cell.currentscore[j][i-1]==cell.currentscore[j][i-1]+g+h) {
						buff = buff.append("-");
						buff2 = buff2.append(NWtable[0][i]);
						buff3 = buff3.append(" ");
						i = i - 1;
						gapcount = gapcount + 1;
						opengapcount = opengapcount + 1;
					}
					else {
						buff = buff.append("-");
						buff2 = buff2.append(NWtable[0][i]);
						buff3 = buff3.append(" ");
						i = i - 1;
						gapcount = gapcount + 1;						
					}
					
				}
				
					if(i<=2&&j>2) {
						i = 3;
						j = j - 1;
					}
					if (j<=2&&i>2) {
						j = 3;
						//i = i - 1;	
				}
			}
		}

	void printanswer() {
		seq1 = buff.reverse().toString();
		seq2 = buff2.reverse().toString();
		seq3 = buff3.reverse().toString();
		System.out.println("sequence 1 = " + seq1);
		System.out.println("sequence 3 = " + seq3);
		System.out.println("sequence 2 = " + seq2);

		System.out.println("highest score = " + cell.currentscore[super.column - 1][super.row - 1]);
		System.out.println("長度為" + seq1.length());
		System.out.println("match為" + countmatch);
		System.out.println("gap為" + gapcount);
		System.out.println("mismatch為" + count_mis);
		System.out.println("opening gap count 為" + opengapcount);
	}

	void set_Table() {
		super.setRowColumnNW();
		this.column = super.get_column();
		this.NWtable = super.table;
		this.h = super.get_h();
		this.g = super.get_g();
		this.match = super.get_match();
		this.mismatch = super.get_mismatch();
		this.scoretable = super.scoretable;
	}

	public void printtxt() {
		try {
			File writename = new File("NeedlemanWunsch_output.txt");
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write("INPUT:\n" + "******\r\n"); // \r\n即为换行
			out.write("\nS1>\r\n");
			for (int i = 0; i <= sequence1.size() - 1; i++) {
				out.write(sequence1.get(i));
			}
			out.write(" ");
			out.write("\r\nS2>\r\n");
			for (int i = 0; i <= sequence2.size() - 1; i++) {
				out.write(sequence2.get(i));
			}
			out.write("\r\n");
			out.write("\nOUTPUT:\n" + "******");
			out.write("\r\n");
			out.write("\nScores: match =" + match + "," + " mismatch = " + ", h =" + h + " g =" + g);
			out.write("\r\n");
			out.write("\nSequence 1 = \"s1\", length =" + sequence1.size());
			out.write("\nSequence 2 = \"s2\", length =" + sequence2.size());
			out.write("\r\n");
			int i = 0;
			while (true) {
				out.write("\r\n");
				out.write("S1	" + (i + 1) + "		" + seq1.substring(i, i + 60) + "  " + (i + 60) + "\r\n");
				out.write("			" + seq3.substring(i, i + 60) + "  " + "\r\n");
				out.write("S2	" + (i + 1) + "		" + seq2.substring(i, i + 60) + "  " + (i + 60) + "\r\n");
				out.write("\r\n");
				i = i + 60;
				if (i + 60 > seq1.length() && i + 60 > seq3.length() && i + 60 > seq2.length()) {
					out.write("S1	" + (i + 1) + "		" + seq1.substring(i, seq1.length()) + "  " + (seq1.length())
							+ "\r\n");
					out.write("			" + seq3.substring(i, seq3.length()) + "		" + "\r\n");
					out.write("S2	" + (i + 1) + "		" + seq2.substring(i, seq2.length()) + "  " + (seq2.length())
							+ "\r\n");
					break;
				}
			}
			out.write("Report: \r\n");
			out.write("Global optimal score = " + cell.currentscore[super.column - 1][super.row - 1] + "\r\n");
			out.write("Number of:  matches = " + countmatch + ", mismatches = " + count_mis + ", opening gaps ="
					+ opengapcount + ", gaps =" + gapcount + "\r\n");
			out.write("Identities =" + countmatch + "/" + seq1.length() + "(" + (float) countmatch / seq1.length() + ")"
					+ ", Gaps = " + gapcount + "/" + seq1.length() + "(" + (float) gapcount / seq1.length() + ")");
			// +", Gaps = "+14/125 (11%) );
			// for(int i=1;seq1.length())
			// out.write("S1 "+i);
			out.flush(); // 把缓存区内容压入文件
			out.close(); // 最后记得关闭文件
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void printhighest() {
		System.out.println(cell.currentscore[super.column - 1][super.row - 1]);
	}
}
