// Abstract κλάση που δημιουργεί γραφήματα
// Αν αλλάζει κάτι για κάποιο γράφημα σε υποκλάση γίνεται Override
// η σχετική μέθοδος

package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import service.DateRange;

public abstract class Graph {
    private DateRange dateRange;
    private final String title;
    private final String xAxisLabel;
    private final String yAxisLabel;

    public Graph(String title, String xAxisLabel, String yAxisLabel) {
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
    }

    public DateRange getDateRange() {
        return dateRange;
    }
    
    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }
    
    // Δημιουργεί κι επιστρέφει γράφημα για την συχνότητα αριθμών
    public ChartPanel create() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new Dimension(750, 650));

        return chartPanel;
    }
    
    // Δημιουργεί το dataset
    public CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Διαβάζει τα δεδομένα
        Map<Integer, Long> numbers = getData();

        // Γεμίζει το dataset
        for (Map.Entry<Integer, Long> number : numbers.entrySet()) {
            dataset.addValue(number.getValue(), "Αριθμός", Integer.toString(number.getKey()));
        }

        return dataset;
    }

    // Δημιουργεί το τελικό γράφημα
    private JFreeChart createChart(final CategoryDataset dataset) {

        // Δημιουργία του γραφήματος με τα κατάλληλα properties
        final JFreeChart chart = ChartFactory.createBarChart3D(
                title, // chart title
                yAxisLabel, // domain axis label
                xAxisLabel, // range axis label
                dataset, // data
                PlotOrientation.HORIZONTAL, // orientation
                false, // include legend
                true, // tooltips
                false // urls
        );

        // Καθορίζεται η εμφάνιση του γραφήματος
        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        // Δημιουργείται ο τύπος του γραφήματος που θέλουμε
        final BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
        renderer.setDrawBarOutline(true);

        return chart;
    }
    
    abstract Map<Integer, Long> getData();
}
