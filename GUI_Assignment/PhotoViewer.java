import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Homework 4 
 * Jennifer Khuu, jtk2eh 
 * 
 * Sources: Lecture Notes, Big Java book, TA Help, Google Images, Stack Overflow
 */
public class PhotoViewer extends JFrame {

    int index;
    JLabel centerLabel;
    ButtonModel m1, m2, m3, m4, m5;

    private FlowLayout layout = new FlowLayout();

    // instance variable imageLibrary
    private PhotographContainer imageLibrary;

    // getter for imageLibrary
    public PhotographContainer getImageLibrary() {
        return this.imageLibrary;
    }

    // setter for imageLibrary
    public void setImageLibrary(PhotographContainer library) {
        this.imageLibrary = library;
    }

    // main method testing
    public static void main(String[] args) {
        PhotoViewer myViewer = new PhotoViewer();

        String imageDirectory = "images/";

        Photograph p1 = new Photograph("blue sky", imageDirectory + "blue sky.jpg", "2015-11-08", 5);
        p1.loadImageData(imageDirectory + "blue sky.jpg");

        Photograph p2 = new Photograph("green sky", imageDirectory + "green sky.jpg", "2016-11-08", 4);

        p2.loadImageData(imageDirectory + "green sky.jpg");

        Photograph p3 = new Photograph("moon", imageDirectory + "moon.jpg", "2017-11-08", 3);
        p3.loadImageData(imageDirectory + "moon.jpg");

        Photograph p4 = new Photograph("purple mountain", imageDirectory + "purple mountain.jpg", "2018-11-08", 2);
        p4.loadImageData(imageDirectory + "purple mountain.jpg");

        Photograph p5 = new Photograph("red sun", imageDirectory + "red sun.jpg", "2019-11-08", 1);
        p5.loadImageData(imageDirectory + "red sun.jpg");

        myViewer.setImageLibrary(new PhotoLibrary("Test Library", 1));
        myViewer.getImageLibrary().addPhoto(p1);
        myViewer.getImageLibrary().addPhoto(p2);
        myViewer.getImageLibrary().addPhoto(p3);
        myViewer.getImageLibrary().addPhoto(p4);
        myViewer.getImageLibrary().addPhoto(p5);

        Collections.sort(myViewer.getImageLibrary().getPhotos(), new CompareByDate());

        javax.swing.SwingUtilities.invokeLater(() -> myViewer.createAndShowGUI(myViewer));
    }

    private static void createAndShowGUI(PhotoViewer frame) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // what the 'x' button does
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        frame.setSize(width, height);
        // This will center the JFrame in the middle of the screen (optional)
        frame.setLocationRelativeTo(null);
        // Set up the content pane by calling another method "addComponentsToPane"
        frame.addComponentsToPane(frame.getContentPane()); // pass in the content pane
        // Display the window
        frame.setVisible(true); // so that all things show up, set visibility to "true"!
                                // NOT optional unless you wish to see nothing :)
    }

    private void addComponentsToPane(Container pane) {

        JPanel panel = new JPanel();
        panel.setLayout(layout);
        layout.setAlignment(FlowLayout.CENTER);
        JPanel panel1 = new JPanel();
        panel1.setLayout(layout);

        JPanel panel2 = new JPanel();
        panel2.setLayout(layout);

        JPanel panel3 = new JPanel();
        panel3.setLayout(layout);

        JPanel panel4 = new JPanel();
        panel4.setLayout(layout);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);

        ArrayList<Photograph> list = getImageLibrary().getPhotos();

        index = 0;
        centerLabel = center(list.get(index));

        JLabel[] panel2Labels = { thumbnail(list.get(0)), thumbnail(list.get(1)), thumbnail(list.get(2)), thumbnail(list.get(3)),
                thumbnail(list.get(4)), };

        panel1.add(centerLabel);
        for (JLabel label : panel2Labels) {
            panel2.add(label);

        }
        JButton previous = new JButton("Previous");
        JButton next = new JButton("Next");
        JButton date = new JButton("Date");
        JButton rating = new JButton("Rating");
        JButton caption = new JButton("Caption");

        JRadioButton one = new JRadioButton("1");
        JRadioButton two = new JRadioButton("2");
        JRadioButton three = new JRadioButton("3");
        JRadioButton four = new JRadioButton("4");
        JRadioButton five = new JRadioButton("5");
        ButtonGroup group = new ButtonGroup();
        group.add(one);
        group.add(two);
        group.add(three);
        group.add(four);
        group.add(five);
        m1 = one.getModel();
        m2 = two.getModel();
        m3 = three.getModel();
        m4 = four.getModel();
        m5 = five.getModel();

        // gives the rating of the current center image
        Photograph photo = list.get(index);
        int photoRating = photo.getRating();
        if (photoRating == 1) {

            group.setSelected((ButtonModel) m1, true);
        }
        if (photoRating == 2) {

            group.setSelected((ButtonModel) m2, true);
        }
        if (photoRating == 3) {

            group.setSelected((ButtonModel) m3, true);
        }
        if (photoRating == 4) {

            group.setSelected((ButtonModel) m4, true);
        }
        if (photoRating == 5) {

            group.setSelected((ButtonModel) m5, true);
        }

        // ButtonListener includes action for previous, next, date, caption, and rating buttons

        class ButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("click")) {
                    if (index == 0) {
                        index = list.size() - 1;
                        Photograph p = list.get(index);
                        centerLabel.setIcon(new ImageIcon(p.getImageData().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                    } else {
                        index--;
                        Photograph p = list.get(index);
                        centerLabel.setIcon(new ImageIcon(p.getImageData().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                    }
                    Photograph photo = list.get(index);
                    int photoRating = photo.getRating();
                    if (photoRating == 1) {

                        group.setSelected((ButtonModel) m1, true);
                    }
                    if (photoRating == 2) {

                        group.setSelected((ButtonModel) m2, true);
                    }
                    if (photoRating == 3) {

                        group.setSelected((ButtonModel) m3, true);
                    }
                    if (photoRating == 4) {

                        group.setSelected((ButtonModel) m4, true);
                    }
                    if (photoRating == 5) {

                        group.setSelected((ButtonModel) m5, true);
                    }

                }

                if (e.getActionCommand().contentEquals("forward click")) {
                    if (index == list.size() - 1) {
                        index = 0;
                        Photograph p = list.get(index);
                        centerLabel.setIcon(new ImageIcon(p.getImageData().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                    } else {
                        index++;
                        Photograph p = list.get(index);
                        centerLabel.setIcon(new ImageIcon(p.getImageData().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                    }
                    Photograph photo = list.get(index);
                    int photoRating = photo.getRating();
                    if (photoRating == 1) {

                        group.setSelected((ButtonModel) m1, true);
                    }
                    if (photoRating == 2) {

                        group.setSelected((ButtonModel) m2, true);
                    }
                    if (photoRating == 3) {

                        group.setSelected((ButtonModel) m3, true);
                    }
                    if (photoRating == 4) {

                        group.setSelected((ButtonModel) m4, true);
                    }
                    if (photoRating == 5) {

                        group.setSelected((ButtonModel) m5, true);
                    }
                }

                if (e.getActionCommand().equals("date")) {
                    Collections.sort(list, new CompareByDate());
                    index = 0;
                    centerLabel
                            .setIcon(new ImageIcon(list.get(0).getImageData().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                    for (int i = 0; i < panel2Labels.length; i++) {
                        Photograph p = list.get(i);
                        panel2Labels[i].setIcon(
                                new ImageIcon(list.get(i).getImageData().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                        panel2Labels[i].setText("<html>" + p.getCaption() + "<br>" + p.getDateTaken() + "<br>" + "Rating: "
                                + p.getRating() + "</html>");
                    }
                }
                if (e.getActionCommand().equals("rating")) {
                    Collections.sort(list, new CompareByRating());
                    index = 0;
                    centerLabel
                            .setIcon(new ImageIcon(list.get(0).getImageData().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                    for (int i = 0; i < panel2Labels.length; i++) {
                        Photograph p = list.get(i);
                        panel2Labels[i].setIcon(
                                new ImageIcon(list.get(i).getImageData().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                        panel2Labels[i].setText("<html>" + p.getCaption() + "<br>" + p.getDateTaken() + "<br>" + "Rating: "
                                + p.getRating() + "</html>");
                    }
                }
                if (e.getActionCommand().equals("caption")) {
                    Collections.sort(list, new CompareByCaption());
                    index = 0;
                    centerLabel
                            .setIcon(new ImageIcon(list.get(0).getImageData().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                    for (int i = 0; i < panel2Labels.length; i++) {
                        Photograph p = list.get(i);
                        panel2Labels[i].setIcon(
                                new ImageIcon(list.get(i).getImageData().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                        panel2Labels[i].setText("<html>" + p.getCaption() + "<br>" + p.getDateTaken() + "<br>" + "Rating: "
                                + p.getRating() + "</html>");
                    }

                }

            }
        }
        previous.setActionCommand("click");
        next.setActionCommand("forward click");
        date.setActionCommand("date");
        rating.setActionCommand("rating");
        caption.setActionCommand("caption");

        previous.addActionListener(new ButtonListener());
        next.addActionListener(new ButtonListener());
        date.addActionListener(new ButtonListener());
        rating.addActionListener(new ButtonListener());
        caption.addActionListener(new ButtonListener());

        panel3.add(previous);
        panel3.add(next);
        panel3.add(date);
        panel3.add(rating);
        panel3.add(caption);

        // MouseListener

        class Listener implements MouseListener {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                for (int i = 0; i < panel2Labels.length; i++) {
                    JLabel label2 = panel2Labels[i];
                    if (label == label2) {
                        Photograph p = imageLibrary.getPhotos().get(i);
                        index = i;
                        centerLabel.setIcon(new ImageIcon(p.getImageData().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                        Photograph photo = list.get(index);
                        int photoRating = photo.getRating();
                        if (photoRating == 1) {

                            group.setSelected((ButtonModel) m1, true);
                        }
                        if (photoRating == 2) {

                            group.setSelected((ButtonModel) m2, true);
                        }
                        if (photoRating == 3) {

                            group.setSelected((ButtonModel) m3, true);
                        }
                        if (photoRating == 4) {
                            group.setSelected((ButtonModel) m4, true);
                        }
                        if (photoRating == 5) {
                            group.setSelected((ButtonModel) m5, true);
                        }
                        break;
                    }
                }

            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub

            }

        }

        panel2.addMouseListener(new Listener());

        for (JLabel label : panel2Labels) {
            label.addMouseListener(new Listener());
        }

        one.setActionCommand("1");
        two.setActionCommand("2");
        three.setActionCommand("3");
        four.setActionCommand("4");
        five.setActionCommand("5");

        // RadioListeners for rating radioButtons
        class RadioListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("1")) {
                    group.setSelected(m1, true);
                    for (int i = 0; i < list.size(); i++) {
                        if (i == index) {
                            Photograph p = list.get(i);
                            p.setRating(1);
                        }
                    }
                    for (int i = 0; i < panel2Labels.length; i++) {
                        Photograph p = list.get(i);
                        panel2Labels[i].setIcon(
                                new ImageIcon(list.get(i).getImageData().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                        panel2Labels[i].setText("<html>" + p.getCaption() + "<br>" + p.getDateTaken() + "<br>" + "Rating: "
                                + p.getRating() + "</html>");
                    }
                }

            }

        }
        class RadioListener2 implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("2")) {
                    group.setSelected(m2, true);
                    for (int i = 0; i < list.size(); i++) {
                        if (i == index) {
                            Photograph p = list.get(i);
                            p.setRating(2);
                        }
                    }
                    for (int i = 0; i < panel2Labels.length; i++) {
                        Photograph p = list.get(i);
                        panel2Labels[i].setIcon(
                                new ImageIcon(list.get(i).getImageData().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                        panel2Labels[i].setText("<html>" + p.getCaption() + "<br>" + p.getDateTaken() + "<br>" + "Rating: "
                                + p.getRating() + "</html>");
                    }
                }
            }

        }
        class RadioListener3 implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("3")) {
                    group.setSelected(m3, true);
                    for (int i = 0; i < list.size(); i++) {
                        if (i == index) {
                            Photograph p = list.get(i);
                            p.setRating(3);
                        }
                    }
                    for (int i = 0; i < panel2Labels.length; i++) {
                        Photograph p = list.get(i);
                        panel2Labels[i].setIcon(
                                new ImageIcon(list.get(i).getImageData().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                        panel2Labels[i].setText("<html>" + p.getCaption() + "<br>" + p.getDateTaken() + "<br>" + "Rating: "
                                + p.getRating() + "</html>");
                    }
                }
            }
        }
        class RadioListener4 implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("4")) {
                    group.setSelected(m4, true);
                    for (int i = 0; i < list.size(); i++) {
                        if (i == index) {
                            Photograph p = list.get(i);
                            p.setRating(4);
                        }
                    }
                    for (int i = 0; i < panel2Labels.length; i++) {
                        Photograph p = list.get(i);
                        panel2Labels[i].setIcon(
                                new ImageIcon(list.get(i).getImageData().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                        panel2Labels[i].setText("<html>" + p.getCaption() + "<br>" + p.getDateTaken() + "<br>" + "Rating: "
                                + p.getRating() + "</html>");
                    }
                }
            }
        }
        class RadioListener5 implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("5")) {
                    group.setSelected(m5, true);
                    for (int i = 0; i < list.size(); i++) {
                        if (i == index) {
                            Photograph p = list.get(i);
                            p.setRating(5);
                        }
                    }
                    for (int i = 0; i < panel2Labels.length; i++) {
                        Photograph p = list.get(i);
                        panel2Labels[i].setIcon(
                                new ImageIcon(list.get(i).getImageData().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
                        panel2Labels[i].setText("<html>" + p.getCaption() + "<br>" + p.getDateTaken() + "<br>" + "Rating: "
                                + p.getRating() + "</html>");
                    }
                }
            }
        }

        panel4.add(one);
        panel4.add(two);
        panel4.add(three);
        panel4.add(four);
        panel4.add(five);
        one.addActionListener(new RadioListener());
        two.addActionListener(new RadioListener2());
        three.addActionListener(new RadioListener3());
        four.addActionListener(new RadioListener4());
        five.addActionListener(new RadioListener5());

        pane.add(panel);

    }

    // thumbnail
    private JLabel thumbnail(Photograph p) {

        try {
            String filename = p.getFilename();

            BufferedImage bi = ImageIO.read(new File(filename));
            Image i = bi.getScaledInstance(150, 150, Image.SCALE_DEFAULT);

            return new JLabel(
                    "<html>" + p.getCaption() + "<br>" + p.getDateTaken() + "<br>" + "Rating: " + p.getRating() + "</html>",
                    new ImageIcon(i), JLabel.LEFT);
        }

        catch (IOException e) {
            System.out.println("error");
            return null;
        }
    }

    // center
    private JLabel center(Photograph p) {

        try {
            String filename = p.getFilename();

            BufferedImage bi = ImageIO.read(new File(filename));
            Image i = bi.getScaledInstance(300, 300, Image.SCALE_DEFAULT);

            return new JLabel(new ImageIcon(i));
        } catch (IOException e) {
            System.out.println("photo file error");
            return null;
        }

    }

    // caption
    private JLabel caption(Photograph p) {
        JLabel label = new JLabel(
                "<html>" + p.getCaption() + "<br>" + p.getDateTaken() + "<br>" + "Rating: " + p.getRating() + "</html>");

        return label;
    }
}
