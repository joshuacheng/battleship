public class Point {
	/* Instance variables */
    private int columnIndex;
    private int rowIndex;

	/**
	 * Constructor
	 */
    public Point(int row, int column) {
        columnIndex = column;
		rowIndex = row;
    }
    
	/**
	 * Getter
	 */
    public int getColumnIndex() {
        return columnIndex;
    }
    
	/**
	 * Getter
	 */
    public int getRowIndex() {
        return rowIndex;
    }
}
