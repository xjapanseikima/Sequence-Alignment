import java.util.ArrayList;

public abstract class DP_table {
	static ArrayList<String> sequence1 = new ArrayList<String>();
	static ArrayList<String> sequence2 = new ArrayList<String>();
	String table[][];
	int row;
	 int column;
	int match, h, g, mismatch;
	int scoretable[][];
	protected String[][] statustable;
	String s1;
	String s2;

	DP_table() {
		sequence1 = main.sequence1;
		sequence2 = main.sequence2;
		match = main.match;
		mismatch = main.mismatch;
		g = main.g;
		h = main.h;
	}

	void setRowColumnNW() {
		row = sequence1.size() + 2;
		column = sequence2.size() + 2;
		table = new String[column][row];// 初始化第一個空格
		scoretable = new int[column][row];// 初始化第一個空格


		statustable = new String[column][row];
		scoretable[1][1] = 0;
		scoretable[1][1] = 0;

		statustable[1][1] = "diag";
		// System.out.println("" + row + " " + column);
		for (int i = 2; i < row; i++) {
			table[0][i] = sequence1.get(i - 2);// 行設定為s1 ,cuz [0][0] and [0][1] null
		}
		for (int q = 2; q < column; q++) {
			table[q][0] = sequence2.get(q - 2);// 列設定為s2,cuz [0][0] and [1][0] null
		}
		for (int i = 2; i < row; i++) {
			scoretable[1][i] = h + g * (i - 1);// cuz i not start from 0;
			statustable[1][i] = "gap";// opening gap
		}
		for (int i = 2; i < column; i++) {
			scoretable[i][1] = h + g * (i - 1);
			statustable[i][1] = "gap"; // opening gap
		}
	}

	void setRowColumnSW() {
		row = sequence1.size() + 2;
		column = sequence2.size() + 2;
		table = new String[column][row];// 初始化第一個空格
		scoretable = new int[column][row];// 初始化第一個空格
		statustable = new String[column][row];
		scoretable[1][1] = 0;
		statustable[1][1] = "diag";
		// System.out.println("" + row + " " + column);
		for (int i = 2; i < row; i++) {
			table[0][i] = sequence1.get(i - 2);// 行設定為s1 ,cuz [0][0] and [0][1] null
		}
		for (int q = 2; q < column; q++) {
			table[q][0] = sequence2.get(q - 2);// 列設定為s2,cuz [0][0] and [1][0] null
		}
		for (int i = 2; i < row; i++) {
			scoretable[1][i] = h + g * (i - 1);// cuz i not start from 0;
			if (scoretable[1][i] < 0) {
				scoretable[1][i] = 0;
			}
			statustable[1][i] = "gap";// opening gap
		}
		for (int i = 2; i < column; i++) {
			scoretable[i][1] = h + g * (i - 1);
			if (scoretable[i][1] < 0) {
				scoretable[i][1] = 0;
			}
			statustable[i][1] = "gap"; // opening gap
		}
	}

	public int get_column() {
		return this.column;
	}

	public int get_g() {
		return this.g;
	}

	public int get_h() {
		return this.h;
	}

	public int get_mismatch() {
		return this.mismatch;
	}

	public int get_match() {
		return this.match;
	}
}