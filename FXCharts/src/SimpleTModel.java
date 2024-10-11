import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javax.swing.table.AbstractTableModel;

public class SimpleTModel extends AbstractTableModel {
    private static ObservableList<BarChart.Series> bcData;
    private final String []   names={"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private final Integer[][]  data={{ 23,45,56,77,60 },
                                     { 56,77,47,88,37 } };
    
    @Override
    public int getRowCount() {
        return data.length;
    }
    @Override
    public int getColumnCount() {
        return names.length;
    }
    @Override
    public Object getValueAt(int i, int j) {
        return data[i][j];
    }
    @Override
    public String getColumnName(int i) {
        return names[i];
    }
    @Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }
    @Override
    public Class<?> getColumnClass(int j) {
        return getValueAt(0, j).getClass();
    }
    @Override
    public void setValueAt(Object val, int i, int j) {
        data[i][j]=(int)val;
        fireTableCellUpdated(i, j);
    }
    
    public ObservableList<BarChart.Series> getBarChartData() {
        if (bcData == null) {
            bcData = FXCollections.<BarChart.Series>observableArrayList();
            for (int row = 0; row < getRowCount(); row++) {
                ObservableList<BarChart.Data> series =
                    FXCollections.<BarChart.Data>observableArrayList();
                for (int column = 0; column < getColumnCount(); column++) {
                    series.add(new BarChart.Data(getColumnName(column),
                    getValueAt(row, column)));
                }
                bcData.add(new BarChart.Series(series));
            }
        }
        return bcData;
    }
}
