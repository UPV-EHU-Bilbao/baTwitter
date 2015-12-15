package twitterGraphs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import graph.HirugarrenLehioa;
import twitter4j.TwitterException;

public class RadioButtonDemo extends JPanel
                             implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9184258634987688752L;
	static String fav = "Favorito";
    static String rt= "ReTweet";
    static String nirea= "Nire Tweet-ak";
    static String jarraitzaileak="Jarraitzaileak";
    static String jarraituak = "Jarraituak";
    static String exportatu= "Exportatu Datuak";
    private Osoa gurasoa;
    
    public RadioButtonDemo(Osoa guraso) {
        super(new BorderLayout());
        this.gurasoa = guraso;
        //Create the radio buttons.
        JRadioButton favButton = new JRadioButton(fav);
        favButton.setMnemonic(KeyEvent.VK_B);
        favButton.setActionCommand(fav);

        JRadioButton rtButton = new JRadioButton(rt);
        rtButton.setMnemonic(KeyEvent.VK_C);
        rtButton.setActionCommand(rt);

        JRadioButton nireaButton = new JRadioButton(nirea);
        nireaButton.setMnemonic(KeyEvent.VK_D);
        nireaButton.setActionCommand(nirea);

        JRadioButton tzaileButton = new JRadioButton(jarraitzaileak);
        tzaileButton.setMnemonic(KeyEvent.VK_R);
        tzaileButton.setActionCommand(jarraitzaileak);

        JRadioButton tuakButton = new JRadioButton(jarraituak);
        tuakButton.setMnemonic(KeyEvent.VK_P);
        tuakButton.setActionCommand(jarraituak);

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(nireaButton);
        group.add(tzaileButton);
        group.add(tuakButton);
        group.add(favButton);
        group.add(rtButton);

        //Register a listener for the radio buttons.
        favButton.addActionListener(this);
        rtButton.addActionListener(this);
        nireaButton.addActionListener(this);
        tzaileButton.addActionListener(this);
        tuakButton.addActionListener(this);

        JButton ex1= new JButton(exportatu);
        ex1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					new ExpFrame().gorde();
					
				}catch(Exception e){
					
				}
        		
			}
		});       
       JButton atzera= new JButton("Atzera");
       
        atzera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					guraso.setVisible(false);
					guraso.dispose();
					
				}catch(Exception e){
					
				}
        		
			}
		}); 
    

        
      
        //Put the radio buttons in a column in a panel.
        //JPanel radioPanel = new JPanel(new GridLayout(1, 0));
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, 1));
       
        
        radioPanel.add(favButton);
        radioPanel.add(rtButton);
        radioPanel.add(nireaButton);
        radioPanel.add(tzaileButton);
        radioPanel.add(tuakButton);
    

        add(radioPanel, BorderLayout.LINE_START);
        
       
        radioPanel.add(ex1);
        radioPanel.add(atzera);


        //entzule bereziak
        
        //add(table, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    /** Listens to the radio buttons. */
    @Override
	public void actionPerformed(ActionEvent e) {
       // picture.setIcon(createImageIcon(  e.getActionCommand()));
		/*TableDemo taula = new TableDemo(e.getActionCommand().toString());
    	JFrame frame = new JFrame("TableDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		taula.setOpaque(true);
		frame.setContentPane(taula);
		frame.pack();
		frame.setVisible(true);*/
    	
    	try {
			gurasoa.bistaratu(e.getActionCommand());
		} catch (IllegalStateException | SQLException | TwitterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	//System.out.println(e.getActionCommand());
    }


    
    
  
}
 
