package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JFrame;
//import java.util.Random;
//import javax.swing.JFrame;
import javax.swing.JPanel;

//import javax.swing.SwingUtilities;

public class Grapher extends JPanel {

	private static final long serialVersionUID = 1L;
	// private int width = 800;
	// private int heigth = 400;
	private int padding = 25;
	private int labelPadding = 25;
	private Color lineColor;
	private static Color defaultColor = new Color(44, 102, 230, 180);
	private Color pointColor = new Color(100, 100, 100, 180);
	private Color gridColor = new Color(200, 200, 200, 200);
	private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	private int pointWidth = 4;
	private int numberDivisions = 10;
	private ArrayList<Double> yscores;
	private ArrayList<Double> xscores;

	public Grapher(ArrayList<Double> xscores, ArrayList<Double> yscores,
			Color color) {

		if (xscores != null && (xscores.size() != yscores.size())) {
			return;
		}

		this.yscores = yscores;
		this.xscores = xscores;
		this.lineColor = color;
	}

	public Grapher(ArrayList<Double> xscores, ArrayList<Double> yscores) {
		this(xscores, yscores, defaultColor);
	}

	public Grapher(ArrayList<Double> yscores, Color color) {
		this(null, yscores, color);
	}

	public Grapher(ArrayList<Double> yscores) {
		this(null, yscores, defaultColor);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		double xScale = ((double) getWidth() - (2 * padding) - labelPadding)
				/ (xscores == null ? yscores.size() - 1 : getMaxXScore()
						- getMinXScore());
		double yScale = ((double) getHeight() - 2 * padding - labelPadding)
				/ (getMaxYScore() - getMinYScore());

		ArrayList<Point> graphPoints = new ArrayList<>();
		for (int i = 0; i < yscores.size(); i++) {
			int x1 = (int) ((xscores == null ? i : xscores.get(i)
					- getMinXScore())
					* xScale + padding + labelPadding);
			int y1 = (int) ((getMaxYScore() - yscores.get(i)) * yScale + padding);
			graphPoints.add(new Point(x1, y1));
		}

		// draw white background
		g2.setColor(Color.WHITE);
		g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding)
				- labelPadding, getHeight() - 2 * padding - labelPadding);
		g2.setColor(Color.BLACK);

		// create hatch marks and grid lines for y axis.
		for (int i = 0; i < numberDivisions + 1; i++) {
			int x0 = padding + labelPadding;
			int x1 = pointWidth + padding + labelPadding;
			int y0 = getHeight()
					- ((i * (getHeight() - padding * 2 - labelPadding))
							/ numberDivisions + padding + labelPadding);
			int y1 = y0;
			if (yscores.size() > 0) {
				g2.setColor(gridColor);
				g2.drawLine(padding + labelPadding + 1 + pointWidth, y0,
						getWidth() - padding, y1);
				g2.setColor(Color.BLACK);
				String yLabel = ((int) ((getMinYScore() + (getMaxYScore() - getMinYScore())
						* ((i * 1.0) / numberDivisions)) * 100))
						/ 100.0 + "";
				FontMetrics metrics = g2.getFontMetrics();
				int labelWidth = metrics.stringWidth(yLabel);
				g2.drawString(yLabel, x0 - labelWidth - 5,
						y0 + (metrics.getHeight() / 2) - 3);
			}
			g2.drawLine(x0, y0, x1, y1);
		}

		// and for x axis
		if (xscores != null) {
			for (int i = 0; i < xscores.size() + 1; i++) {

				int x0 = (i * (getWidth() - padding * 2 - labelPadding))
						/ numberDivisions + padding + labelPadding;
				int x1 = x0;
				int y0 = getHeight() - padding - labelPadding;
				int y1 = y0 - pointWidth;

				g2.setColor(gridColor);
				g2.drawLine(x0, getHeight() - padding - labelPadding - 1
						- pointWidth, x1, padding);
				g2.setColor(Color.BLACK);
				String xLabel = ((int) ((getMinXScore() + (getMaxXScore() - getMinXScore())
						* ((double) i / (double) numberDivisions)) * 100.0))
						/ 100.0 + "";
				FontMetrics metrics = g2.getFontMetrics();
				int labelWidth = metrics.stringWidth(xLabel);
				g2.drawString(xLabel, x0 - labelWidth / 2,
						y0 + metrics.getHeight() + 3);

				g2.drawLine(x0, y0, x1, y1);

			}
		} else {
			for (int i = 0; i < yscores.size(); i++) {
				if (yscores.size() > 1) {
					int x0 = i * (getWidth() - padding * 2 - labelPadding)
							/ (yscores.size() - 1) + padding + labelPadding;
					int x1 = x0;
					int y0 = getHeight() - padding - labelPadding;
					int y1 = y0 - pointWidth;
					if ((i % ((int) ((yscores.size() / 20.0)) + 1)) == 0) {
						g2.setColor(gridColor);
						g2.drawLine(x0, getHeight() - padding - labelPadding
								- 1 - pointWidth, x1, padding);
						g2.setColor(Color.BLACK);
						String xLabel = i + "";
						FontMetrics metrics = g2.getFontMetrics();
						int labelWidth = metrics.stringWidth(xLabel);
						g2.drawString(xLabel, x0 - labelWidth / 2,
								y0 + metrics.getHeight() + 3);
					}
					g2.drawLine(x0, y0, x1, y1);
				}
			}
		}

		// create x and y axes
		g2.drawLine(padding + labelPadding, getHeight() - padding
				- labelPadding, padding + labelPadding, padding);
		g2.drawLine(padding + labelPadding, getHeight() - padding
				- labelPadding, getWidth() - padding, getHeight() - padding
				- labelPadding);

		Stroke oldStroke = g2.getStroke();
		g2.setColor(lineColor);
		g2.setStroke(GRAPH_STROKE);
		for (int i = 0; i < graphPoints.size() - 1; i++) {
			int x1 = graphPoints.get(i).x;
			int y1 = graphPoints.get(i).y;
			int x2 = graphPoints.get(i + 1).x;
			int y2 = graphPoints.get(i + 1).y;
			g2.drawLine(x1, y1, x2, y2);
		}

		g2.setStroke(oldStroke);
		g2.setColor(pointColor);
		for (int i = 0; i < graphPoints.size(); i++) {
			int x = graphPoints.get(i).x - pointWidth / 2;
			int y = graphPoints.get(i).y - pointWidth / 2;
			int ovalW = pointWidth;
			int ovalH = pointWidth;
			g2.fillOval(x, y, ovalW, ovalH);
		}
	}

	public void display(String name) {
		JFrame frame = new JFrame(name);
		setPreferredSize(new Dimension(800, 600));
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	// @Override
	// public Dimension getPreferredSize() {
	// return new Dimension(width, heigth);
	// }
	private double getMinYScore() {
		double minScore = Double.MAX_VALUE;
		for (Double score : yscores) {
			minScore = Math.min(minScore, score);
		}
		return minScore;
	}

	private double getMaxYScore() {
		double maxScore = Double.MIN_VALUE;
		for (Double score : yscores) {
			maxScore = Math.max(maxScore, score);
		}
		return maxScore;
	}

	private double getMinXScore() {
		double minScore = Double.MAX_VALUE;
		for (Double score : xscores) {
			minScore = Math.min(minScore, score);
		}
		return minScore;
	}

	private double getMaxXScore() {
		double maxScore = Double.MIN_VALUE;
		for (Double score : xscores) {
			maxScore = Math.max(maxScore, score);
		}
		return maxScore;
	}

	public void setScores(ArrayList<Double> xscores, ArrayList<Double> yscores) {
		this.yscores = yscores;
		this.xscores = xscores;
		invalidate();
		this.repaint();
	}

	public void setScores(ArrayList<Double> yscores) {
		this.yscores = yscores;
		this.xscores = null;
		invalidate();
		this.repaint();
	}

	public ArrayList<Double> getYScores() {
		return yscores;
	}

	public ArrayList<Double> getXScores() {
		return xscores;
	}
}