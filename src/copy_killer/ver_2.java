package copy_killer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class ver_2 extends JPanel {

	private JButton Add_File_Btn = new JButton("Add File");
	private JButton Clear_Btn = new JButton("Clear");
	private JButton Copy_Check_Btn = new JButton("Copy Check");
	
	private JPanel Compare_Panel = new JPanel();
	private JPanel Compare_inner_Panel = new JPanel();
	private JScrollPane Compare_Scroll;
	
	private JFileChooser fileChooser = new JFileChooser("C:\\Users\\user\\Desktop\\test_data");
	
	String[] Compare_File = new String[50];
	
	public JPanel[] Add_Panel = new JPanel[50];
	public JLabel[] Code_Name = new JLabel[50];
	public JLabel[] Copy_Value = new JLabel[50];
	public JButton[] Code_Btn = new JButton[50];
	public JButton[] Copy_Code_Btn = new JButton[50];
	public JButton[] Delete_Btn = new JButton[50];	
	
	public int count = 0;
	public int panel_y = 0;
	
	public StringBuffer[] Compare_List = new StringBuffer[50];
	public String[] Compare_String = new String[50];
	
	public int[] COPY_VALUE = new int[50];
	
	public JPanel Color_Pan[] = new JPanel[50];
	public JLabel Text_Lab[] = new JLabel[50];
	
	public int[][][] cpy_index;
	public int[] k = new int[50];
	
	public ver_2() {
		this.setSize(1036, 688);
		this.setBackground(new Color(59,23,11));
		this.setLayout(null);
		
		Add_File_Btn.setBackground(new Color(59, 23, 11));
		Add_File_Btn.setForeground(new Color(219, 169, 1));
		Add_File_Btn.setBorder(new LineBorder(new Color(219, 169, 1)));
		Add_File_Btn.setFocusPainted(false);
		
		Clear_Btn.setBackground(new Color(59, 23, 11));
		Clear_Btn.setForeground(new Color(219, 169, 1));
		Clear_Btn.setBorder(new LineBorder(new Color(219, 169, 1)));
		Clear_Btn.setFocusPainted(false);
		
		Copy_Check_Btn.setBackground(new Color(59, 23, 11));
		Copy_Check_Btn.setForeground(new Color(219, 169, 1));
		Copy_Check_Btn.setBorder(new LineBorder(new Color(219, 169, 1)));
		Copy_Check_Btn.setFocusPainted(false);
		
		this.add(Add_File_Btn);
		this.add(Clear_Btn);
		this.add(Copy_Check_Btn);
		
		Add_File_Btn.setBounds(20, 20, 100, 30);
		Clear_Btn.setBounds(170, 20, 100, 30);
		Copy_Check_Btn.setBounds(320, 20, 100, 30);
		
		// compare area		
		TitledBorder compare_title = new TitledBorder(new LineBorder(new Color(219, 169, 1)),"COMPARE");
		compare_title.setTitleColor(new Color(219, 169, 1));
		
		Compare_Panel.setBorder(compare_title);
		Compare_Panel.setOpaque(false);
		Compare_Panel.setLayout(new BorderLayout());
		this.add(Compare_Panel);
		Compare_Panel.setBounds(20, 70, 990, 600);
		
		// compare scroll area
		Compare_inner_Panel.setLayout(null);
		Compare_inner_Panel.setOpaque(false);
		Compare_inner_Panel.setBackground(new Color(59, 23, 11));
		Compare_inner_Panel.setBorder(null);
		Compare_inner_Panel.setPreferredSize(new Dimension(990, 3000));
		
		Compare_Scroll = new JScrollPane(Compare_inner_Panel);
		
		Compare_Scroll.setOpaque(false);
		Compare_Scroll.getViewport().setBackground(new Color(59, 23, 11));
		Compare_Scroll.setBorder(null);
		Compare_Scroll.getVerticalScrollBar().setUI(new ScrollBarUI_2());
		
		Compare_Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		Compare_Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Compare_Panel.add(Compare_Scroll);
		
        Compare_Panel_init();
		
		// btn - actionListener
		Add_File_Btn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
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
						
						Add_Panel[i].setBounds(0, panel_y, 950, 50);
					
						Compare_List[i] = new StringBuffer();
						convert_Code(Compare_File[i], Compare_List[i]);
						Compare_String[i] = new String();
						Compare_String[i] = Compare_List[i].toString();
						
						panel_y += 50;
		            }
		            count = i;
		            System.out.println("count : " + count + "\n");
		        }
			}
		});
		Clear_Btn.addActionListener(new ActionListener(){

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
		
		Copy_Check_Btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(count <= 0)
					JOptionPane.showMessageDialog(null, "Plear add code file.", "None File", JOptionPane.WARNING_MESSAGE);
				else
				{
					for(int i = 0 ; i < count ; i++)
					{
						COPY_VALUE[i] = 0;
						for(int j = 0 ; j < count ; j++)
						{
							if(j == i)
								continue;
							else
							{
								int temp;
								temp = Smith_Waterman_ALGO(Compare_String[i], Compare_String[j], i, j);
								
								// 가장 큰 값 저장
								if(temp > COPY_VALUE[i])
								{
									COPY_VALUE[i] = temp;
									k[i] = j;
								}
							}
						}
						Copy_Value[i].setText(String.valueOf(String.format("%02d", COPY_VALUE[i])) + 
								"%                     " + String.format("%2d", k[i]));
						
						Text_Lab[i].setText(String.valueOf(String.format("%02d", COPY_VALUE[i])) + 
								"%                     " + String.format("%2d", k[i]));
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
		
		setVisible(true);
	}
	
	public void Compare_Panel_init()
	{
		cpy_index = new int[50][][];
		for(int panel_count = 0, y = 0 ; panel_count < 50 ; panel_count++, y -= 50)
		{
			cpy_index[panel_count] = new int[50][];
			
			Color_Pan[panel_count] = new JPanel();
			Text_Lab[panel_count] = new JLabel();
			
			Add_Panel[panel_count] = new JPanel();
			Add_Panel[panel_count].setBackground(new Color(59,23,11));
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
					
					Copy_Code_Print(temp_file, temp, k[temp]);
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
	
	public void Copy_Code_Print(File print_File, int index, int index_2)
	{
		StyleContext context= new StyleContext();
		StyledDocument document = new DefaultStyledDocument(context);
		JTextPane code = new JTextPane(document);
		
		JScrollPane code_scroll = new JScrollPane(code);
		code_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		code_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		code.setEditable(false);
		
		javax.swing.text.Style st = context.getStyle(StyleContext.DEFAULT_STYLE);
		SimpleAttributeSet att = new SimpleAttributeSet();
		
		JFrame code_output_frame = new JFrame(print_File.getPath());
		
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
		
		for(int i = 0 ; i < t_cnt ; i++)
		{
			if(cpy_index[index][index_2][i] == 1)
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
	
	public void Code_print(File print_File){
		String line;
		
		JTextArea code = new JTextArea();
		JScrollPane code_scroll = new JScrollPane(code);
		code_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		code_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		code.setTabSize(3);
		
		try {
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
	void convert_Code(String FileName, StringBuffer Code_List)
	{
		Code_List.append(' ');
		
		try {
			FileInputStream read_file = new FileInputStream(FileName);
			InputStreamReader input_read = new InputStreamReader(read_file);
			
			try {
				int temp;
				while( (temp = input_read.read() ) != -1)
				{
					if( ((char)temp == '\t') || ((char)temp == '\n') || ((char)temp == '\r'))
						temp = 32;
										
					Code_List.append((char)temp);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(Code_List.toString());
	}
	
	int Smith_Waterman_ALGO(String Answer_Str, String Compare_Str, int index, int index_2)
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
		
		cpy_index[index][index_2] = new int[Answer_Size];
		
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
                               
                        cpy_index[index][index_2][max_x] = 1;
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
                                
                        cpy_index[index][index_2][max_x] = 1;
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
		
		int Copy_Value = 0;
		int Copy_Count = 0;
		
		for(int i = 0 ; i < Match_seq.length() ; i++)
		{
			if(Match_seq.charAt(i) == '|')
				Copy_Count++;
		}
		
		Copy_Value = (int)(Copy_Count / (float)Answer_Size * 100f); 
		
		return Copy_Value;
	}
}


class ScrollBarUI_2 extends BasicScrollBarUI {
    protected void configureScrollBarColors() {
        thumbRect.width = 10;
        trackRect.width = 10;
        
        thumbColor = new Color(59, 23, 11);
        thumbDarkShadowColor = new Color(59, 23, 11);
        thumbHighlightColor = new Color(59, 23, 11);
        thumbLightShadowColor = new Color(59, 23, 11);
        trackColor = new Color(157, 122, 8);
        trackHighlightColor = new Color(59, 23, 11);
    }

    protected JButton createDecreaseButton(int orientation) {
        JButton button = new BasicArrowButton(orientation);
        button.setBackground(new Color(59, 23, 11));
        button.setForeground(new Color(157, 122, 8));
        button.setBorder(new LineBorder(new Color(157, 122, 8)));
        return button;
    }

    protected JButton createIncreaseButton(int orientation) {
        JButton button = new BasicArrowButton(orientation);
        button.setBackground(new Color(59, 23, 11));
        button.setForeground(new Color(157, 122, 8));
        button.setBorder(new LineBorder(new Color(157, 122, 8)));
        return button;
    }
    
    protected Dimension getMaximumThumbSize() {
        return new Dimension(10, 20);
    }
}