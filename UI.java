package dini;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.RootPaneContainer;

import dini.Coordinator;
import dini.Table;
import dini.UI;
import java.awt.Color;

class UI extends JPanel {
    private final Coordinator c;
    private final Table t;

    private final JRootPane root;
    private static final int externalBorder = 6;

    private static final int stopped = 0;
    private static final int running = 1;
    private static final int paused = 2;

    private int state = stopped;
    public UI(RootPaneContainer pane, Coordinator C, Table T,
              boolean isApplet) {
        final UI u = this;
        c = C;
        t = T;

        final JPanel b = new JPanel();   // button panel
        b.setBackground(Color.PINK);
        b.setForeground(Color.CYAN);

        final JButton runButton = new JButton("Run");
        runButton.setBackground(Color.MAGENTA);
        runButton.setForeground(Color.DARK_GRAY);
        final JButton pauseButton = new JButton("Pause");
        pauseButton.setBackground(Color.MAGENTA);
        pauseButton.setForeground(Color.DARK_GRAY);
        final JButton resetButton = new JButton("Reset");
        resetButton.setForeground(Color.DARK_GRAY);
        resetButton.setBackground(Color.MAGENTA);
        final JButton quitButton = new JButton("Quit");

        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.resume();
                root.setDefaultButton(pauseButton);
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t.pause();
                root.setDefaultButton(runButton);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t.reset();
                root.setDefaultButton(runButton);
            }
        });
        if (!isApplet) {
            quitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }

        // put the buttons into the button panel:
        b.setLayout(new FlowLayout());
        b.add(runButton);
        b.add(pauseButton);
        b.add(resetButton);
        if (!isApplet) {
            b.add(quitButton);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(
            externalBorder, externalBorder, externalBorder, externalBorder));
        add(t);
        add(b);

        pane.getContentPane().add(this);
        root = getRootPane();
        root.setDefaultButton(runButton);
    }
}
