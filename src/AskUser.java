import java.awt.BorderLayout;
import java.awt.FlowLayout;
 
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
 
public class AskUser extends JDialog {
 
        private final JPanel contentPanel = new JPanel();
        static int answer =0;
        /**
         * Launch the application.
         */
        public static void main(String[] args) {
                try {
                        AskUser dialog = new AskUser();
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setVisible(true);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
 
        /**
         * Create the dialog.
         */
        public AskUser() {
                setBounds(100, 100, 450, 300);
                getContentPane().setLayout(new BorderLayout());
                contentPanel.setLayout(new FlowLayout());
                contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                getContentPane().add(contentPanel, BorderLayout.CENTER);
                {
                        JPanel buttonPane = new JPanel();
                        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
                        getContentPane().add(buttonPane, BorderLayout.SOUTH);
                        {
                                JButton enlargeButton = new JButton("Enlarge");
                                enlargeButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent arg0) {
                                                answer = 1;
                                        }
                                });
                                enlargeButton.setActionCommand("Enlarge");
                                buttonPane.add(enlargeButton);
                                getRootPane().setDefaultButton(enlargeButton);
                        }
                        {
                                JButton shrinkButton = new JButton("Shrink");
                                shrinkButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                                answer = 2;
                                        }
                                });
                                shrinkButton.setActionCommand("Shrink");
                                buttonPane.add(shrinkButton);
                        }
                }
        }
        static int returnAnswer()
        {
                return answer;
               
        }
 
}