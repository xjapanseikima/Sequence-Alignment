
public class Cell {
	public int row;
	public int col;
	public int currentscore[][];
	public int diascore[][];
	public int rowscore[][];
	public int colscore[][];
	public String diastatus[][];
	public String rowstatus[][];
	public String colstatus[][];
	public String currentstatus[][];
	int match, h, g, mismatch;

	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		match = main.match;
		mismatch = main.mismatch;
		g = main.g;
		h = main.h;
	}

	void initalize() {
		currentscore = new int[col][row];
		diascore = new int[col][row];
		rowscore = new int[col][row];
		colscore = new int[col][row];
//		diascore[1][1] = 0;
//		rowscore[1][1] = 0;
//		colscore[1][1] = 0;

		for (int i = 2; i < row; i++) {
			rowscore[1][i] = h + g * (i-1);
			diascore[1][i] = -2000000000;
			colscore[1][i] = -2000000000;

		}
		for (int j = 2; j < col; j++) {
			colscore[j][1] = h + g * (j-1 );
			diascore[j][1] = -2000000000;
			rowscore[j][1] = -2000000000;

		}
	}

	void SWinitalize() {
		currentscore = new int[col][row];
		diascore = new int[col][row];
		rowscore = new int[col][row];
		colscore = new int[col][row];
		diascore[1][1] = 0;
		rowscore[1][1] = 0;
		colscore[1][1] = 0;

		for (int i = 2; i < row; i++) {
			rowscore[1][i] = 0;
			diascore[1][i] = 0;
			colscore[1][i] = 0;

		}
		for (int j = 2; j < col; j++) {
			colscore[j][1] = 0;
			diascore[j][1] = 0;
			rowscore[j][1] = 0;

		}
	}

	void setcolscore(int j, int i, int score) {
		this.colscore[j][i] = score;
	}

	void setdiascore(int j, int i, int score) {
		this.diascore[j][i] = score;
	}

	void setrowscore(int j, int i, int score) {
		this.rowscore[j][i] = score;
	}

}
