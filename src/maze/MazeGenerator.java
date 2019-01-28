package maze;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;

public class MazeGenerator {
	
private static Drawing drawPanel2;

//Main method invokes GUI creation in a separate thread
public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() 
			{
				buildGUI();
			}
			
		});

	} //main


//GUI builder method
public static void buildGUI() {
	
	//setting up the frame
	JFrame frame1 = new JFrame("Maze Generator");
	frame1.setVisible(true);
	frame1.setMinimumSize(new Dimension(580,620));
	frame1.setResizable(false);
	frame1.setLocationRelativeTo(null);
	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//creating layout manager
	GridBagLayout gbl = new GridBagLayout();
	frame1.setLayout(gbl);
	
	/* Layout constraints */
	
	GridBagConstraints gbc_scroll1 = new GridBagConstraints();
	gbc_scroll1.gridx = 0;
	gbc_scroll1.gridy = 0;
	gbc_scroll1.gridwidth = 5;
	gbc_scroll1.gridheight = 1;
	gbc_scroll1.insets = new Insets(1,1,10,1);
	gbc_scroll1.fill = GridBagConstraints.BOTH;
	
	GridBagConstraints gbc_label1 = new GridBagConstraints();
	gbc_label1.gridx = 0;
	gbc_label1.gridy = 1;
	gbc_label1.gridwidth = 1;
	gbc_label1.gridheight = 1;
	gbc_label1.insets = new Insets(10,0,0,0);
	gbc_label1.anchor = GridBagConstraints.BASELINE_LEADING;
	
	GridBagConstraints gbc_textfield1 = new GridBagConstraints();
	gbc_textfield1.gridx = 1;
	gbc_textfield1.gridy = 1;
	gbc_textfield1.gridwidth = 1;
	gbc_textfield1.gridheight = 1;
	gbc_textfield1.insets = new Insets(10,0,0,80);
	
	GridBagConstraints gbc_button1 = new GridBagConstraints();
	gbc_button1.gridx = 2;
	gbc_button1.gridy = 1;
	gbc_button1.gridwidth = 1;
	gbc_button1.gridheight = 1;
	gbc_button1.insets = new Insets(10,1,1,1);
	
	GridBagConstraints gbc_button2 = new GridBagConstraints();
	gbc_button2.gridx = 3;
	gbc_button2.gridy = 1;
	gbc_button2.gridwidth = 1;
	gbc_button2.gridheight = 1;
	gbc_button2.insets = new Insets(10,10,1,1);
	
	GridBagConstraints gbc_button3 = new GridBagConstraints();
	gbc_button3.gridx = 4;
	gbc_button3.gridy = 1;
	gbc_button3.gridwidth = 1;
	gbc_button3.gridheight = 1;
	gbc_button3.insets = new Insets(10,60,1,1);
	gbc_button3.anchor = GridBagConstraints.BASELINE_TRAILING;
	
	/* Components */
	
	//component instantiation
	JLabel label1 = new JLabel();
	JTextField textfield1 = new JTextField();
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton button3 = new JButton();
	JScrollPane scrollPane = new JScrollPane();
	JDialog quitMenu = new JDialog();
	
	//adding components to frame
	frame1.add(label1, gbc_label1);
	frame1.add(scrollPane, gbc_scroll1);
	frame1.add(textfield1, gbc_textfield1);
	frame1.add(button1, gbc_button1);
	frame1.add(button2, gbc_button2);
	frame1.add(button3, gbc_button3);
		
	/* ScrollPane */
	scrollPane.setAutoscrolls(true);
	scrollPane.setPreferredSize(new Dimension(500,500));
	
	/* Label */
	label1.setText("Size: ");
	label1.setPreferredSize(new Dimension(30,10));
	label1.setMinimumSize(new Dimension(30,10));
	
	/* Textfield */
	textfield1.setPreferredSize(new Dimension(80,20));
	textfield1.setMinimumSize(new Dimension(80,20));
	textfield1.grabFocus();
	
	/* Generate button */
	button1.setText("Generate");
	button1.setVisible(true);	

	/* Save button */
	button2.setText("Save");
	button2.setEnabled(false);
	button2.setVisible(true);
	
	/* Exit button */
	button3.setText("Exit");
	button3.setSize(new Dimension(80,40));
	button3.setVisible(true);	
	
	/* Event Handlers */
		
	button1.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				Drawing drawPanel = new Drawing(textfield1);
				drawPanel.setBackground(Color.WHITE);
				scrollPane.getViewport().add(drawPanel);
				button2.setEnabled(true);
				
				drawPanel2 = drawPanel;
				
					} 
				}
			);//button1 event handler (Generate)
	
	button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(new FileNameExtensionFilter("*.jpg, *.png", "jpg", "png"));
				
				jfc.setDialogTitle("Save maze as image");
				int saveResult = jfc.showSaveDialog(button2);
				
				BufferedImage componentImage = new BufferedImage(drawPanel2.getWidth(), drawPanel2.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = componentImage.createGraphics();
				drawPanel2.paint(g2d);
				File imageFile = jfc.getSelectedFile();
				File imageFileJPG = new File(imageFile.toString()+".jpg");
				
				try 
				{
					if (saveResult == JFileChooser.APPROVE_OPTION) 
					{
						ImageIO.write(componentImage, "jpg", imageFileJPG);
					}
					
				}
				catch (IOException ioe) 
				{
					JOptionPane.showMessageDialog(jfc, "I/O Error!");
				}
				
					}//AE
				}
			);//button2 event handler (Save)
	
	button3.addActionListener (new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ae) {
							
			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit program", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (response == JOptionPane.YES_OPTION) 
			{
				System.exit(0);
			}
			else if (response == JOptionPane.NO_OPTION)
			{
				quitMenu.setVisible(false);
			}
			else if (response == JOptionPane.CANCEL_OPTION) 
			{
				quitMenu.setVisible(false);
			}				
					}
				}
			);//button3 event handler (Exit)
	
	
} //buildGUI method - code all GUI elements inside this method !!!
	
} //MazeGenerator - main class


class Drawing extends JPanel 
{
	
	int mazeSize;
	
	public Drawing(JTextField jtf) 
	{
		try {
		this.mazeSize = Integer.parseInt(jtf.getText());
		}
		
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(this, "ERROR!  Invalid size value!");
		}
	} //constructor
	
    public Dimension getPreferredSize() {
        return new Dimension((mazeSize*10)+1,(mazeSize*10)+1);
    } //getPreferredSize - this method is used by the scroll pane to adjust its own size automatically
	
	public void drawMaze (Graphics g) 
	{
		Grid grid = new Grid();
		grid.generate(mazeSize);
		grid.createMaze(grid);
		
		g.setColor(new Color(0,128,0));
		
		for (int i = 0; i < grid.cells.length; i++) {
	        //west
	        if (grid.cells[i].wallW == true)
	        {
	        g.drawLine((grid.cells[i].x*10), (grid.cells[i].y*10), (grid.cells[i].x*10)+10, (grid.cells[i].y*10));
	        }
	        //south
	        if (grid.cells[i].wallS == true)
	        {
	        g.drawLine((grid.cells[i].x*10)+10, (grid.cells[i].y*10), (grid.cells[i].x*10)+10, (grid.cells[i].y*10)+10);
	        }
	        //east
	        if (grid.cells[i].wallE == true)
	        {
	        g.drawLine((grid.cells[i].x*10)+10, (grid.cells[i].y*10)+10, (grid.cells[i].x*10), (grid.cells[i].y*10)+10);
	        }
	        //north
	        if (grid.cells[i].wallN == true)
	        {
	        g.drawLine((grid.cells[i].x*10), (grid.cells[i].y*10)+10, (grid.cells[i].x*10), (grid.cells[i].y*10));
	        }   
	    } //for
		
	} //drawMaze
    
	@Override
    public void paintComponent(Graphics g) 
    {
    	
    	super.paintComponent(g);
    	drawMaze(g);    	
		
		
    }//paintComponent() @Override method

}// DrawingClass