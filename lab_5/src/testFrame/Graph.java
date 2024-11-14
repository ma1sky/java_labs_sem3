package testFrame;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Graph extends JPanel {
    protected void paintComponent(Graphics g, ArrayList<Long> arrayListAddTimes, ArrayList<Long> arrayListRemoveTimes, ArrayList<Long> hashMapAddTimes,ArrayList<Long> hashMapRemoveTimes, ArrayList<Integer> sizes) {
        super.paintComponent(g);
        drawGraph(g, arrayListAddTimes, Color.RED, "ArrayList Add", sizes);
        drawGraph(g, arrayListRemoveTimes, Color.BLUE, "ArrayList Remove", sizes);
        drawGraph(g, hashMapAddTimes, Color.GREEN, "HashMap Add", sizes);
        drawGraph(g, hashMapRemoveTimes, Color.ORANGE, "HashMap Remove", sizes);

        drawYAxis(g, arrayListAddTimes, arrayListRemoveTimes, hashMapAddTimes, hashMapRemoveTimes);
    }

    private void drawYAxis(Graphics g, ArrayList<Long> arrayListAddTimes, ArrayList<Long> arrayListRemoveTimes, ArrayList<Long> hashMapAddTimes,ArrayList<Long> hashMapRemoveTimes) {
        int baseY = 350; // Базовая Y координата для оси X
        int maxHeight = 300; // Высота графика
        long maxTime = Math.max(Math.max(arrayListAddTimes.stream().max(Long::compare).orElse(1L),
                        arrayListRemoveTimes.stream().max(Long::compare).orElse(1L)),
                Math.max(hashMapAddTimes.stream().max(Long::compare).orElse(1L),
                        hashMapRemoveTimes.stream().max(Long::compare).orElse(1L)));

        // Рисуем вертикальную ось
        g.drawLine(50, 50, 50, baseY);
        for (int i = 0; i <= 5; i++) {
            int yPos = baseY - (i * maxHeight / 5);
            g.drawLine(45, yPos, 55, yPos); // Маленькие линии для отметок
            g.drawString((maxTime / 5 * i) + " ns", 10, yPos + 5); // Метки времени
        }
        g.drawString("Time (ns)", 10, 30); // Надпись для оси Y
    }

    private void drawGraph(Graphics g, List<Long> times, Color color, String title, ArrayList<Integer> sizes) {
        g.setColor(color);
        g.drawString(title, 60, 50 + (color == Color.RED ? 0 : color == Color.BLUE ? 20 : color == Color.GREEN ? 40 : 60));

        int maxHeight = 1800; // Высота графика
        int graphWidth = 960; // Ширина графика
        int barWidth = graphWidth / sizes.size();

        // Нормализация времени для отображения
        long maxTime = times.stream().max(Long::compare).orElse(1L);

        // Рисуем линии и выводим время
        int prevX = 50 + barWidth / 2;
        int prevY = 350 - (int) ((times.get(0) * maxHeight) / maxTime);

        // Выводим время для первой точки
        g.drawString(times.get(0) + " ns", prevX + 5, prevY - 5);

        for (int i = 1; i < times.size(); i++) {
            int xPos = 50 + i * barWidth + barWidth / 2;
            int yPos = 350 - (int) ((times.get(i) * maxHeight) / maxTime);
            g.drawLine(prevX, prevY, xPos, yPos);
            prevX = xPos;
            prevY = yPos;

            // Выводим время для текущей точки
            g.drawString(times.get(i) + " ns", xPos + 5, yPos - 5);
        }

        // Ось X
        g.setColor(Color.BLACK);
        for (int i = 0; i < sizes.size(); i++) {
            g.drawString(String.valueOf(sizes.get(i)), 50 + i * barWidth + barWidth / 2, 370);
        }
    }

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000};


        JFrame frame = new JFrame("Collection Performance Chart");
        Graph chart = new Graph();
        frame.add(chart);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}