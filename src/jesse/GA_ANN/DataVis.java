/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesse.GA_ANN;

import java.awt.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;



/**
 *
 * @author xuhao
 */
public class DataVis extends javax.swing.JPanel {

    /**
     * Creates new form DataVis
     */

    NumberAxis dateaxis;
    public XYSeries total[];
    int lineid=0;
    public int getNext()
    {
        lineid++;
        if(lineid>10)
        {
            this.clear();
            lineid=1;
        }
        return lineid-1;
    }
    public XYSeriesCollection XYSeriescollection;
    XYLineAndShapeRenderer xylineandshaperenderer;
    JFreeChart createChart()//Here Input MillSecond
    {
        total = new XYSeries[10];
        XYSeriescollection = new XYSeriesCollection();
        for(int i=0;i<10;i++)
        {
            total[i]=new XYSeries(i);
            XYSeriescollection.addSeries(total[i]);
        }

        dateaxis = new NumberAxis("Time");
        NumberAxis Conaxis = new NumberAxis("z坐标");
        dateaxis.setAutoRange(true);
        dateaxis.setLowerMargin(0.0D);
        dateaxis.setUpperMargin(0.0D);
        dateaxis.setTickLabelsVisible(true);
        xylineandshaperenderer= new XYLineAndShapeRenderer(true, false);
        xylineandshaperenderer.setSeriesPaint(0, Color.green);
        xylineandshaperenderer.setSeriesStroke(0, new BasicStroke(1F, 0, 2));
        xylineandshaperenderer.setFillPaint(new Color(30,30,220),true );

        Conaxis.setRange(-1,1);
        Conaxis.setAutoRange(true);
        Conaxis.setAutoRangeIncludesZero(false);

        XYPlot xyplot = new XYPlot(XYSeriescollection, dateaxis, Conaxis, xylineandshaperenderer);
        JFreeChart jfreechart = new JFreeChart("time-z", new Font("Arial", 1, 24), xyplot, true);
        ChartUtilities.applyCurrentTheme(jfreechart);
        ChartPanel chartpanel = new ChartPanel(jfreechart, true);
        chartpanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createLineBorder(Color.black)));
        return jfreechart;
    }

    void addData(int k,double a,double b)
    {
        total[k].add(a,b);
    }

    public DataVis()
    {
        initComponents();
        JFreeChart jct=createChart();
        this.setLayout(new FlowLayout());
        this.add(new ChartPanel(jct));
        this.setVisible(true);
    }
    public void clear()
    {
        lineid=0;
        for(int i=0;i<total.length;i++)
            total[i].clear();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
