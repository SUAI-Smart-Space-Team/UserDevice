import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Gui {
    private JFrame frame = new JFrame("Messenger");
    private JTextArea dialogWindow = new JTextArea(20, 30);
    private JTextArea serviceWindow = new JTextArea(10, 21);
    private JTextArea statusWindow = new JTextArea(10, 21);
    private JButton buttonStartServer = new JButton("Turn On");
    private JButton buttonStopServer = new JButton("Turn Off");
    private JPanel panelButtons = new JPanel();
    private Application server;

    public Gui(Application server) {
        this.server = server;
    }

    //method, which initialize graphics interface
    protected void initFrameServer() {
        dialogWindow.setEditable(false);
        dialogWindow.setLineWrap(true);
        frame.add(new JScrollPane(dialogWindow), BorderLayout.CENTER);
        JScrollPane serviceInfo = new JScrollPane(serviceWindow);
        serviceInfo.add(statusWindow);
        frame.add(new JScrollPane(serviceInfo), BorderLayout.EAST);
        panelButtons.add(buttonStartServer);
        panelButtons.add(buttonStopServer);
        frame.add(panelButtons, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null); // sets window to the centre of display
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //processing class for closing window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                server.stopServer();
                System.exit(0);
            }
        });
        frame.setVisible(true);

        buttonStartServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.startServer();
            }
        });
        buttonStopServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopServer();
            }
        });
    }

    //method, which add new message to message's area
    public void refreshDialogWindow(String serviceMessage) {
        dialogWindow.append(serviceMessage);
    }

    //method, which add new message to service area
    public void refreshServiceWindow(String serviceMessage) {
        serviceWindow.append(serviceMessage);
    }

    //method, which add new message to status area
    public void refreshStatusWindow(String serviceMessage) {
        statusWindow.append(serviceMessage);
    }
}
