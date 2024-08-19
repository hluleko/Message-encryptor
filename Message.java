
package message.tut.za;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import za.ac.tut.encryption.MessageEncryption;
import za.ac.tut.message.Messages;


public class Message extends JFrame{
    
    private JTextArea jtxtareaone;
    private JTextArea jtxtareatwo;
    private JScrollPane jscrollone;
    private JScrollPane jscrolltwo;
    private JMenuBar jmenubar;
    private JMenu  menu;
    private JMenuItem jmOpenfile,jmEnctpmessage,jmSaveenctpmessage,jmClear,jmExit;
    
    private JPanel headingpanel;
    private JPanel txtAreaonepanel;
    private JPanel txtareatwopanel;
    private JPanel scrollone;
    private JPanel scrolltwo;
    private JPanel mainpanel;
    private JLabel heading;

    public Message() {
        
        mainpanel=new JPanel(new BorderLayout());
        headingpanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        heading=new JLabel("Message Encrytyptor",JLabel.CENTER);
        heading.setFont(new Font(Font.SERIF,Font.ITALIC +Font.BOLD,25));
        heading.setForeground(Color.BLUE);
        heading.setBorder(new BevelBorder(BevelBorder.RAISED));
        
        headingpanel.add(heading);
       
        
        jmenubar=new JMenuBar();
           menu=new  JMenu("File");    
        jmenubar.add(menu);
        
        jmOpenfile=new JMenuItem("Open file");
       jmOpenfile.addActionListener(new jMenuOpenfileEvent());
        menu.add(jmOpenfile);
        
        jmEnctpmessage=new JMenuItem("Encrypt message");
        jmEnctpmessage.addActionListener(new jMenuEnctpmessageEvent());
        menu.add(jmEnctpmessage);
        
        jmSaveenctpmessage=new JMenuItem("Save encrtpted message");
        jmSaveenctpmessage.addActionListener(new jMenuSaveenctpmessageEven());
        menu.add(jmSaveenctpmessage);
        
        jmClear=new JMenuItem("Clear");
        jmClear.addActionListener(new jMenuClearEvent());
        menu.add(jmClear);
        
        jmExit=new JMenuItem("Exit");
        jmExit.addActionListener(new jMenuExitEvent());
        menu.add(jmExit);
        
       
        txtAreaonepanel=new JPanel();
        txtAreaonepanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1),"Plain message"));
        jtxtareaone=new JTextArea(15,20);
        jtxtareaone.setLineWrap(true);
       jscrollone=new JScrollPane(jtxtareaone,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        txtAreaonepanel.add(jscrollone);
        
        
        txtareatwopanel=new JPanel();
        txtareatwopanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1),"Encrypted message"));
        jtxtareatwo=new JTextArea(15,20);
        jtxtareatwo.setLineWrap(true);
       jscrolltwo=new JScrollPane(jtxtareatwo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       
       
        txtareatwopanel.add(jscrolltwo);
         mainpanel.add(headingpanel,BorderLayout.NORTH);
          mainpanel.add(txtAreaonepanel,BorderLayout.WEST);
       mainpanel.add(txtareatwopanel,BorderLayout.EAST);
       
        add(mainpanel);
      
       setTitle("Secure Messages");
        setJMenuBar(jmenubar);
        setSize(500,550);
       /* setLayout(new GridLayout(2,1));*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pack();
        setVisible(true);
         }
         private class jMenuOpenfileEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
             JFileChooser jfc=new JFileChooser();
         int returnval=jfc.showOpenDialog(getRootPane());
         if (returnval==JFileChooser.APPROVE_OPTION)
         {
             String fileName=jfc.getSelectedFile().getAbsolutePath();
             JOptionPane.showMessageDialog(null,fileName);
             Messages fm=new Messages(fileName);
             try
             {
             String line=fm.readFromFile();
             jtxtareaone.setText(line);
             }
             catch(FileNotFoundException ex)
             {
             JOptionPane.showMessageDialog(getRootPane(),ex.getMessage());
             }
             catch(IOException ex)
             {
             JOptionPane.showMessageDialog(getRootPane(),ex.getMessage());
             }
         }
          
        }
         }
         
        private class jMenuEnctpmessageEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           MessageEncryption me=new MessageEncryption();
           
        String plainText=jtxtareaone.getText();
        String encryptedText=me.encryp(plainText,3);
        jtxtareatwo.setText(encryptedText);
        }
        
    }
       
     private class jMenuSaveenctpmessageEven implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
         String data = jtxtareatwo.getText();
            Messages fm = new Messages("one.txt");
            try {
                fm.writeToFile(data);
                 JOptionPane.showMessageDialog(getRootPane(), "Saved to the file"); 
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(getRootPane(), ex.getMessage());
            }
            
          
        }
         
     }
    
     private class jMenuClearEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jtxtareaone.setText("");
          jtxtareatwo.setText("");
        }
    
     }
      private class jMenuExitEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
          System.exit(0);
        }
      }
}
