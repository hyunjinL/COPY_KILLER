package copy_killer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Style;
import javax.print.attribute.AttributeSet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class ver_1 extends JPanel {

	private JPanel Answer_Panel = new JPanel();
	private JLabel Answer_Label = new JLabel();
	private JButton Answer_Add_btn = new JButton("Add File");
	private JButton Answer_Code_btn = new JButton("Code");
	private JButton Answer_clear_btn = new JButton("Clear");
	private JButton Copy_Check_btn = new JButton("Copy Check");
	
	private JButton compare_Add_btn = new JButton("Add File");
	private JButton compare_Clear_btn = new JButton("Clear");	
	
	private JFileChooser fileChooser = new JFileChooser();
	public File Answer_File;
	
	private StringBuffer Answer_List = new StringBuffer();
	private String Answer_String = new String();
	
	private JPanel Compare_Panel = new JPanel();
	private JPanel Compare_inner_Panel = new JPanel();
	private JScrollPane Compare_Scroll;
	
	public JPanel[] Add_Panel = new JPanel[50];
	public JLabel[] Code_Name = new JLabel[50];
	public JLabel[] Copy_Value = new JLabel[50];
	public JButton[] Code_Btn = new JButton[50];
	public JButton[] Copy_Code_Btn = new JButton[50];
	public JButton[] Delete_Btn = new JButton[50];	
	
	public int count = 0;
	public int panel_y = 0;
	
	String[] Compare_File = new String[50];
	public StringBuffer[] Compare_List = new StringBuffer[50];
	public String[] Compare_String = new String[50];
	
	public int[] COPY_VALUE = new int[50];
	
	public JPanel Color_Pan[] = new JPanel[50];
	public JLabel Text_Lab[] = new JLabel[50];
	//test
	public int[][] cpy_index;
	
	public ver_1() {
		
		this.setBackground(new Color(157, 122, 8));
		this.setSize(1036, 688);
		this.setLayout(null);
		
		TitledBorder answer_title = new TitledBorder(new LineBorder(new Color(59, 23, 11)),"ANSWER");
		answer_title.setTitleColor(new Color(59, 23, 11));
		
		Answer_Panel.setBorder(answer_title);
		Answer_Panel.setOpaque(false);
		Answer_Panel.setLayout(null);
		Answer_Panel.setBounds(20, 20, 990, 100);
		
		Answer_Label.setBorder(null);
		Answer_Label.setOpaque(true);
		Answer_Label.setBackground(Color.white);
				
		Answer_Add_btn.setBackground(Color.white);
		Answer_Code_btn.setBackground(Color.white);
		Copy_Check_btn.setBackground(Color.white);
		Answer_clear_btn.setBackground(Color.white);
		Answer_Add_btn.setFocusPainted(false);
		Answer_Code_btn.setFocusPainted(false);
		Copy_Check_btn.setFocusPainted(false);
		Answer_clear_btn.setFocusPainted(false);
		
		Answer_Panel.add(Answer_Label);
		Answer_Panel.add(Answer_Add_btn);
		Answer_Panel.add(Answer_Code_btn);
		Answer_Panel.add(Answer_clear_btn);
		Answer_Panel.add(Copy_Check_btn);
				
		Answer_Label.setBounds(20, 37, 350, 30);
		Answer_Add_btn.setBounds(390, 37, 100, 30);
		Answer_Code_btn.setBounds(510, 37, 100, 30);
		Answer_clear_btn.setBounds(630, 37, 100, 30);
		Copy_Check_btn.setBounds(750, 37, 120, 30);
		
		// extensions to be filtered
        FileNameExtensionFilter filter = new FileNameExtensionFilter("c 파일", "c");
        // add extensions to be filtered
        fileChooser.addChoosableFileFilter(filter);
        
        filter = new FileNameExtensionFilter("c++ 파일", "cpp");
        fileChooser.addChoosableFileFilter(filter);
        
        filter = new FileNameExtensionFilter("java 파일", "java");
        fileChooser.addChoosableFileFilter(filter);
		// push answer_Add_btn -> execute
		Answer_Add_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				       
		        // file open dialog
		        int result = fileChooser.showOpenDialog(null);
		         
		        // APPROVE_OPTION -> normal open, 0
		        if (result == JFileChooser.APPROVE_OPTION) {
		            // return file path that choose
		            Answer_File = fileChooser.getSelectedFile();
		             
		            // change label to path
		            Answer_Label.setText(Answer_File.getPath());
		            
		            // code convert function execute
		            convert_Code(Answer_File.getPath(), Answer_List);
		            Answer_String = Answer_List.toString();
		            
		            System.out.println(Answer_File);
		        }
			}
		});
		
		Answer_Code_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// do not add the correct answer file, a warning message will be displayed.
				if(Answer_File == null)
					JOptionPane.showMessageDialog(null, "Plear add code file.", "None File", JOptionPane.WARNING_MESSAGE);
				else
					Code_print(Answer_File);
					
			}
		});
		
		Answer_clear_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Answer_File == null)
					JOptionPane.showMessageDialog(null, "Plear add code file.", "None File", JOptionPane.WARNING_MESSAGE);
				else
				{
					Answer_File = null;
					Answer_Label.setText(null);
				}
			}
		});
		
		Copy_Check_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Answer_File == null)
					JOptionPane.showMessageDialog(null, "Plear add code file.", "None File", JOptionPane.WARNING_MESSAGE);
				else
				{
					for(int i = 0 ; i < count ; i++)
					{
						COPY_VALUE[i] = Smith_Waterman_ALGO(Answer_String, Compare_String[i], i);
						Copy_Value[i].setText(String.valueOf(COPY_VALUE[i]) + "%");
						
						Text_Lab[i].setText(String.valueOf(COPY_VALUE[i]) + "%");
						Text_Lab[i].setBackground(null);
						Text_Lab[i].setForeground(Color.BLACK);
						Text_Lab[i].setAlignmentX(LEFT_ALIGNMENT);
						
						Copy_Value[i].add(Text_Lab[i]);
						Text_Lab[i].setBounds(0,0,100,30);
						
						Color_Pan[i].setBackground(new Color(158, 0, 0));
						Copy_Value[i].add(Color_Pan[i]);
						Color_Pan[i].setBounds(0, 0, COPY_VALUE[i], 30);
					}
				}
			}
		});
		
		// compare area
		compare_Add_btn.setBackground(Color.WHITE);
		compare_Add_btn.setFocusPainted(false);
		compare_Clear_btn.setBackground(Color.WHITE);
		compare_Clear_btn.setFocusPainted(false);
		
		this.add(compare_Add_btn);
		this.add(compare_Clear_btn);
		
		compare_Add_btn.setBounds(410, 140, 100, 30);
		compare_Clear_btn.setBounds(530, 140, 100, 30);
		
		TitledBorder compare_title = new TitledBorder(new LineBorder(new Color(59, 23, 11)),"COMPARE");
		compare_title.setTitleColor(new Color(59, 23, 11));
		
		Compare_Panel.setBorder(compare_title);
		Compare_Panel.setOpaque(false);
		Compare_Panel.setLayout(new BorderLayout());
		Compare_Panel.setBounds(20, 180, 990, 480);

		// set the panel layout for the ScrollPane to GridBagLayout
		Compare_inner_Panel.setLayout(null);
		Compare_inner_Panel.setOpaque(false);
		Compare_inner_Panel.setBackground(new Color(157, 122, 8));
		Compare_inner_Panel.setBorder(null);
		Compare_inner_Panel.setPreferredSize(new Dimension(990, 3000));
		
		Compare_Scroll = new JScrollPane(Compare_inner_Panel);
		
		// JScrollPane GUI
		Compare_Scroll.setOpaque(false);
		Compare_Scroll.getViewport().setBackground(new Color(157, 122, 8));
		Compare_Scroll.setBorder(null);
		Compare_Scroll.getVerticalScrollBar().setUI(new ScrollBarUI());
		
		// vertical -> generate scrolls only when needed, horizental -> no scroll
		Compare_Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		Compare_Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Compare_Panel.add(Compare_Scroll);
        // added panel init
        Compare_Panel_init();
        
        // add compare file
        compare_Add_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// if Answer_File is not added, the comparison target file can not be added
				if(Answer_File == null)
					JOptionPane.showMessageDialog(null, "Plear add answer code file.", 
							"None File", JOptionPane.WARNING_MESSAGE);
				else
				{
			        // multiple choose 
			        fileChooser.setMultiSelectionEnabled(true);
			        int result = fileChooser.showOpenDialog(null);
			        
			        if (result == JFileChooser.APPROVE_OPTION) {
			            File Compare_Files[] = fileChooser.getSelectedFiles();
			            
			            int i;
			            for(i = 0 + count ; i < Compare_Files.length + count ; i++)
			            {
			            	Code_Name[i].setText(Compare_Files[i-count].getPath());
			            	Compare_File[i] = Compare_Files[i-count].getPath();
			            	
							Compare_inner_Panel.add(Add_Panel[i]);
							// add panel
							Add_Panel[i].setBounds(0, panel_y, 950, 50);
						
							Compare_List[i] = new StringBuffer();
							convert_Code(Compare_File[i], Compare_List[i]);
							Compare_String[i] = new String();
							Compare_String[i] = Compare_List[i].toString();
							
							panel_y += 50;
			            }
			            count = i;
			        }
				}
			}
        });
        
        // all compare file remove
        compare_Clear_btn.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		for(int i = 0 ; i < count && panel_y > 0; i++)
        		{
        			Code_Name[i].setText("");
        			Text_Lab[i].setText("");
        			Color_Pan[i].setBounds(0, -panel_y, 100, 30);
        			Compare_File[i] = "";
        			Copy_Value[i].setText("");
        			Add_Panel[i].setBounds(0, -panel_y, 950, 50);
        			
        			panel_y -= 50;
        		}
        		count = 0;
        	}
        });
        
		this.add(Answer_Panel);
		this.add(Compare_Panel);
	}
	
	public void Compare_Panel_init()
	{
		cpy_index = new int[50][];
		for(int panel_count = 0, y = 0 ; panel_count < 50 ; panel_count++, y -= 50)
		{
			Color_Pan[panel_count] = new JPanel();
			Text_Lab[panel_count] = new JLabel();
			
			Add_Panel[panel_count] = new JPanel();
			Add_Panel[panel_count].setBackground(new Color(157, 122, 8));
			Add_Panel[panel_count].setSize(955, 50);
			Add_Panel[panel_count].setLayout(null);
		
			Code_Name[panel_count] = new JLabel();
			Code_Name[panel_count].setBorder(null);
			Code_Name[panel_count].setOpaque(true);
			Code_Name[panel_count].setBackground(Color.white);
			
			Copy_Value[panel_count] = new JLabel();
			Copy_Value[panel_count].setBorder(null);
			Copy_Value[panel_count].setOpaque(true);
			Copy_Value[panel_count].setBackground(Color.white);
			
			Code_Btn[panel_count] = new JButton("Code");
			Copy_Code_Btn[panel_count] = new JButton("Copy Code");
			Delete_Btn[panel_count] = new JButton("Delete");
			
			Code_Btn[panel_count].setBackground(Color.white);
			Copy_Code_Btn[panel_count].setBackground(Color.white);
			Delete_Btn[panel_count].setBackground(Color.white);
			
			Code_Btn[panel_count].setFocusPainted(false);
			Copy_Code_Btn[panel_count].setFocusPainted(false);
			Delete_Btn[panel_count].setFocusPainted(false);
			
			Add_Panel[panel_count].add(Code_Name[panel_count]);
			Add_Panel[panel_count].add(Copy_Value[panel_count]);
			Add_Panel[panel_count].add(Code_Btn[panel_count]);
			Add_Panel[panel_count].add(Copy_Code_Btn[panel_count]);
			Add_Panel[panel_count].add(Delete_Btn[panel_count]);
					
			Code_Name[panel_count].setBounds(15, 0, 350, 30);
			Copy_Value[panel_count].setBounds(385, 0, 100, 30);
			Code_Btn[panel_count].setBounds(505, 0, 100, 30);
			Copy_Code_Btn[panel_count].setBounds(625, 0, 100, 30);
			Delete_Btn[panel_count].setBounds(745, 0, 120, 30);
			
			Code_Btn[panel_count].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					// identify which button was clicked
					for(int i = 0 ; i < 50 ; i++)
					{
						if(e.getSource().equals(Code_Btn[i]))
						{
							File temp_file = new File(Compare_File[i]);
							Code_print(temp_file);
						}
					}
				}
			});
			
			Copy_Code_Btn[panel_count].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int temp = 0;
					for(int i = 0 ; i < 50 ; i++)
					{
						if(e.getSource().equals(Copy_Code_Btn[i]))
							temp = i;
					}
					
					File temp_file = new File(Compare_File[temp]);
					
					Copy_Code_Print(temp_file, temp);
				}
			});
			
			Delete_Btn[panel_count].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					int temp = 0;
					for(int i = 0 ; i < 50 ; i++)
					{
						if(e.getSource().equals(Delete_Btn[i]))
							temp = i;
					}
					
					Copy_Value[temp].setText("");
					Code_Name[temp].setText("");
					Compare_File[temp] = "";
					
        			Add_Panel[temp].setBounds(0, -5000, 950, 50);
				}
			});
			
			Add_Panel[panel_count].setBounds(-2000, y, 955, 50);
		}
	}
		
	// print code function
	public void Code_print(File print_File){
		String line;
		
		JTextArea code = new JTextArea();
		JScrollPane code_scroll = new JScrollPane(code);
		code_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		code_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		code.setTabSize(3);
		
		try {
			// file input output
			FileReader read_file = new FileReader(print_File);
			BufferedReader buf_read = new BufferedReader(read_file);
			
			try {
				while((line = buf_read.readLine()) != null)
				{
					code.append(line + "\n");
				}
				buf_read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		JFrame code_output_frame = new JFrame(print_File.getPath());
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		code_output_frame.add(code_scroll);
		
		code_output_frame.setSize(500, 500);
		code_output_frame.setLocation(screenSize.width/2 - 256, screenSize.height/2 - 192);
		code_output_frame.setVisible(true);
	}
	
	// copy code print function
	public void Copy_Code_Print(File print_File, int index)
	{
		StyleContext context= new StyleContext();
		StyledDocument document = new DefaultStyledDocument(context);
		JTextPane code = new JTextPane(document);
		
		JScrollPane code_scroll = new JScrollPane(code);
		code_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		code_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// change disable
		code.setEditable(false);
		
		javax.swing.text.Style st = context.getStyle(StyleContext.DEFAULT_STYLE);
		SimpleAttributeSet att = new SimpleAttributeSet();
		
		JFrame code_output_frame = new JFrame(print_File.getPath());
		
		// read file and assign string
		String t_str = new String();
		int t_cnt = 0;
		try {
			FileInputStream read_file = new FileInputStream(print_File);
			InputStreamReader input_read = new InputStreamReader(read_file);
			
			try {
				int temp;
				while( (temp = input_read.read() ) != -1)
				{
					t_str += (char)temp;
					t_cnt ++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// if the index is set to 1 after performing the algorithm and comparing with the stored index
		// output to screen after color conversion of string at index
		for(int i = 0 ; i < t_cnt ; i++)
		{
			if(cpy_index[index][i] == 1)
			{
				StyleConstants.setForeground(st, Color.red);
				try {
					document.insertString(i, t_str.substring(i, i+1), st);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				StyleConstants.setForeground(st, Color.BLACK);
			}
			else
			{
				try {
					document.insertString(i, t_str.substring(i, i+1), st);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		}
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		code_output_frame.add(code_scroll);
		
		code_output_frame.setSize(500, 500);
		code_output_frame.setLocation(screenSize.width/2 - 256, screenSize.height/2 - 192);
		code_output_frame.setVisible(true);
	}

	// code convert function
	public void convert_Code(String FileName, StringBuffer Code_List)
	{
		// for smith-waterman, first space insert 
		Code_List.append(' ');
		
		try {
			FileInputStream read_file = new FileInputStream(FileName);
			InputStreamReader input_read = new InputStreamReader(read_file);
			
			try {
				int temp;
				while( (temp = input_read.read() ) != -1)
				{
					// \t, \n, \r -> change space
					if( ((char)temp == '\t') || ((char)temp == '\n') || ((char)temp == '\r'))
						temp = 32;
										
					Code_List.append((char)temp);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(Code_List.toString());
	}
	
	// smith-waterman algorithm function
	int Smith_Waterman_ALGO(String Answer_Str, String Compare_Str, int index)
	{
		final int MATCH = 2;
		final int MISMATCH = -1;
		final int GAP = -1;
		
		final int Answer_Size = Answer_Str.length();
		final int Compare_Size = Compare_Str.length();
		int[][] Matrix = new int[Compare_Size][Answer_Size];
		
		int Max_Value = 0;
		int max_x = 0;
		int max_y = 0;
		
		cpy_index[index] = new int[Compare_Size];
		
		// init matrix
		for(int i = 0 ; i < Compare_Size ; i++)
		{
			for(int j = 0 ; j < Answer_Size ; j++)
				Matrix[i][j] = 0;
		}
		
		// make matrix
        for(int x = 1 ; x < Answer_Size ; x++)
        {
                for(int y = 1 ; y < Compare_Size ; y++)
                {
                        int weight = 0;
                        int deletion = 0;

                        if(Answer_Str.charAt(x) == Compare_Str.charAt(y))
                        {
                                if(weight > Matrix[y-1][x-1]+MATCH)
                                        weight = weight;
                                else
                                        weight = Matrix[y-1][x-1] + MATCH;

                                if(Matrix[y-1][x]+GAP > Matrix[y][x-1]+GAP)
                                        deletion = Matrix[y-1][x] + GAP;
                                else
                                        deletion = Matrix[y][x-1] + GAP;

                                if(weight > deletion)
                                        weight = weight;
                                else
                                        weight = deletion;

                                Matrix[y][x] = weight;
                        }
                        else
                        {
                                if(weight > Matrix[y-1][x-1]+MISMATCH)
                                        weight = weight;
                                else
                                        weight = Matrix[y-1][x-1] + MISMATCH;

                                if(Matrix[y-1][x]+GAP > Matrix[y][x-1]+GAP)
                                        deletion = Matrix[y-1][x] + GAP;
                                else
                                        deletion = Matrix[y][x-1] + GAP;

                                if(weight > deletion)
                                        weight = weight;
                                else
                                        weight = deletion;

                                Matrix[y][x] = weight;
                        }
                }
        }
		
        // find max value
        for(int x = 1 ; x < Answer_Size ; x++)
        {
                for(int y = 1 ; y < Compare_Size ; y++)
                {
                        if(Max_Value < Matrix[y][x])
                        {
                                Max_Value = Matrix[y][x];
                                max_x = x;
                                max_y = y;
                        }
                }
        }

        System.out.println(Max_Value + " : " + max_x + ", " + max_y);
        
        // backtrace
        StringBuffer Answer_result = new StringBuffer();
        StringBuffer Compare_result = new StringBuffer();
        StringBuffer Match_seq = new StringBuffer();
        
        int flag = 0;
        while(max_x > 0 && max_y > 0)
        {
        	if(Matrix[max_y][max_x-1] == 0 && Matrix[max_y-1][max_x] == 0 && Matrix[max_y-1][max_x-1] == 0 && flag == 1)
        	{
        		Match_seq.append(' ');
        		Answer_result.append('-');
        		Compare_result.append('-');
        		max_x--;
        		max_y--;
        	}
        	else
        	{
            	if(Matrix[max_y][max_x-1] == 0 && Matrix[max_y-1][max_x] == 0 && Matrix[max_y-1][max_x-1] == 0 && flag == 0)
            		flag = 1;
            	
        		if(Matrix[max_y][max_x-1] >= Matrix[max_y-1][max_x])
        		{
        			if(Matrix[max_y-1][max_x-1] >= Matrix[max_y][max_x-1])
                    {
        				Match_seq.append('|');
        				Answer_result.append(Answer_Str.charAt(max_x));
                        Compare_result.append(Compare_Str.charAt(max_y));
                        max_x--;
                        max_y--;
                               
                        cpy_index[index][max_y] = 1;
                    }
                    else
                    {
                    	Match_seq.append(' ');
                    	Answer_result.append(Answer_Str.charAt(max_x));
                    	Compare_result.append('-');
                    	max_x--;
                    }
                }
                else
                {
                    if(Matrix[max_y-1][max_x-1] >= Matrix[max_y-1][max_x])
                    {
                       	Match_seq.append('|');
                       	Answer_result.append(Answer_Str.charAt(max_x));
                       	Compare_result.append(Compare_Str.charAt(max_y));
                        max_x--;
                        max_y--;
                                
                        cpy_index[index][max_y] = 1;
                    }
                    else
                    {
                    	Match_seq.append(' ');
                    	Answer_result.append('-');
                    	Compare_result.append(Compare_Str.charAt(max_y));
                        max_y--;
                    }
                }
        	}
        }
		
		// copy value 
		int Copy_Value = 0;
		int Copy_Count = 0;
		
		for(int i = 0 ; i < Match_seq.length() ; i++)
		{
			if(Match_seq.charAt(i) == '|')
				Copy_Count++;
		}
		
		Copy_Value = (int)(Copy_Count / (float)Compare_Size * 100f); 
		
		return Copy_Value;
	}
}

// resetting the Scrollbar GUI
class ScrollBarUI extends BasicScrollBarUI {
    protected void configureScrollBarColors() {
        thumbRect.width = 10;
        trackRect.width = 10;
        
        thumbColor = new Color(157, 122, 8);
        thumbDarkShadowColor = new Color(157, 122, 8);
        thumbHighlightColor = new Color(157, 122, 8);
        thumbLightShadowColor = new Color(157, 122, 8);
        trackColor = new Color(59, 23, 11);
        trackHighlightColor = new Color(157, 122, 8);
    }

    // specify the color of the arrow buttons on top of the ScrollBar
    protected JButton createDecreaseButton(int orientation) {
        JButton button = new BasicArrowButton(orientation);
        button.setBackground(new Color(157, 122, 8));
        button.setForeground(new Color(157, 122, 8));
        button.setBorder(new LineBorder(new Color(59, 23, 11)));
        return button;
    }

    // Arrow-shaped button color at the bottom of ScrollBar
    protected JButton createIncreaseButton(int orientation) {
        JButton button = new BasicArrowButton(orientation);
        button.setBackground(new Color(157, 122, 8));
        button.setForeground(new Color(157, 122, 8));
        button.setBorder(new LineBorder(new Color(59, 23, 11)));
        return button;
    }
    
    protected Dimension getMaximumThumbSize() {
        return new Dimension(10, 20);
    }
}