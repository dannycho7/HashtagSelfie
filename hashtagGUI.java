import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;

import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import javax.swing.border.*;

public class hashtagGUI extends JFrame implements ActionListener {
    static private final String newline = "\n";

    JPanel c1 = new JPanel();

    File file;

    JTextArea log;
    JFileChooser fc;

    JMenuBar menuBar;
    JMenu fileManager;
    JMenuItem Open;
    JMenuItem Save;

    private int colIndex = 0;
    private int rowIndex = 0;

    private double zoomFactor;

    private JScrollPane scrollPane;

    Picture picture;

    private ImageDisplay imageDisplay;

    JButton filter1 = new JButton("DannyFilter");
    JButton filter2 = new JButton("Exposure");
    JButton filter3 = new JButton("Alpha");
    JButton filter4 = new JButton("Gamma");
    JButton filter5 = new JButton("Sepia");
    JButton filter6 = new JButton("Saturate");
    JButton filter7 = new JButton("Negate");
    JButton filter8 = new JButton("Grayscale");
    JButton filter9 = new JButton("Invert");
    JButton filter10 = new JButton("Postertize");
    JButton filter11 = new JButton("Contrast");
    JButton filter12 = new JButton("Subtract");
    JButton filter13 = new JButton("Fill");

    public static void main(String[] args) {
        new hashtagGUI();
    }

    public void AddMenuBar() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        fileManager = new JMenu("File");
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        fileManager.add(Open);
        fileManager.add(Save);

        Open.addActionListener(this);
        Save.addActionListener(this);

        menuBar.add(fileManager);
    }

    public hashtagGUI() {
        super("#Selfie");

        c1.setLayout(new GridLayout(13, 1));  
        setSize(700, 800);

        this.getContentPane().setLayout(new BorderLayout()); // use border layout
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // when close stop

        AddMenuBar();

        JButton[] filterlist = {filter1, filter2, filter3, filter4, filter5, filter6, 
                filter7, filter8, filter9, filter10, filter11, filter12, filter13};
        for (JButton listener : filterlist) {
            listener.addActionListener(this);
            listener.setPreferredSize(new Dimension(100, 70));
            c1.add(listener, BorderLayout.WEST);
        }

        fc = new JFileChooser();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(c1, BorderLayout.WEST);
        setVisible(true);  
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == Open) {
            int returnVal = fc.showOpenDialog(hashtagGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
            }
            BufferedImage image = null;
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {}
            picture = new Picture(image);
            picture.setName(file.getName());
            createAndInitScrollingImage();
        }
        
        //Saving to selected Directory
        if (source == Save) {
            int returnVal = fc.showSaveDialog(hashtagGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                try {
                    String filename = selectedFile.getCanonicalPath();
                    if (!filename.endsWith(".png")) {
                        selectedFile = new File(filename + ".png");
                    }
                    ImageIO.write(picture.getBufferedImage(), "png", selectedFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Button Stuff
        if (picture != null) {
            if (source == filter1) {
                picture.DannyFilter(0.7,35); 
            }
            
            if (source == filter2) {
                picture.brighten(10);
            }
            
            if (source == filter3) {
                picture.alpha(10);
            }
            
            if (source == filter4) {
                picture.gamma(1.05);
            }
            
            if (source == filter5) {
                picture.sepia();
            }
            
            if (source == filter6) {
                picture.saturate(1.5);
            }
            
            if (source == filter7) {
                picture.negate();
            }
            
            if (source == filter8) {
                picture.grayscale(1);
            }
            
            if (source == filter9) {
                picture.invert();
            }
            
            if (source == filter10) {
                picture.postertize(5);
            }
            
            if (source == filter11) {
                picture.contrast(5);
            }
            
            if (source == filter12) {
                picture.subtract(4, 4, 4);
            }
            
            if (source == filter13) {
                picture.fill(5, 3, 98);
            }
            createAndInitScrollingImage();
        }

        //System.out.println(file.getName());
        this.revalidate();
    } 

    /**
     * Create and initialize the scrolling image
     */
    private void createAndInitScrollingImage() {
        if (scrollPane != null) {
            this.remove(scrollPane);
        }
        scrollPane = new JScrollPane();

        BufferedImage bimg = picture.getBufferedImage();
        imageDisplay = new ImageDisplay(bimg);
        scrollPane.setViewportView(imageDisplay);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}