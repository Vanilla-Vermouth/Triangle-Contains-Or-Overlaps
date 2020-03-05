// If it reports an error, please do this:
// Windows -> Preferences -> Java -> Compiler -> Errors/Warnings ->
// Deprecated and trstricted API -> Forbidden reference (access rules): -> change to 'warning'

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * Created by Zechen Wang on 2015/5/19.
 */

public class TriangleHandle {
	private JFrame frame = new JFrame("User_Interface");
	private JLabel lab1;
	private JLabel lab2;
	private JLabel lab3;
	private JLabel info1;
	private JLabel info2;
	private JLabel info3;
	private JTextField text11 = new JTextField();
	private JTextField text12 = new JTextField();
	private JTextField text21 = new JTextField();
	private JTextField text22 = new JTextField();
	private JTextField text31 = new JTextField();
	private JTextField text32 = new JTextField();
	private JButton button11 = new JButton("ok");
	private JButton button12 = new JButton("clear");
	private JButton button21 = new JButton("ok");
	private JButton button22 = new JButton("clear");
	private JButton button31 = new JButton("ok");
	private JButton button32 = new JButton("clear");
	private Font fn = new Font("Times New Roman", Font.BOLD, 18);
	
	
	public TriangleHandle() {
		
		Container cont = frame.getContentPane();
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		
		
		//pan1
		info1 = new JLabel("Result: ");
		info1.setBounds(40, 60, 1300, 100);
		info1.setFont(fn);
		final JPanel pan1 = new JPanel();
		lab1 = new JLabel("Triangle(example: 0,0,45,345,456,34)");
		lab2 = new JLabel("contains ?");
		lab3 = new JLabel("Dot(example: 100,100)");
		lab1.setBounds(40, 0, 300, 100);
		lab1.setFont(fn);
		lab2.setBounds(600, 0, 100, 100);
		lab2.setFont(fn);
		lab3.setBounds(700, 0, 180, 100);
		lab3.setFont(fn);
		text11.setBounds(340, 25, 240, 50);
		text11.setFont(fn);
		text12.setBounds(880, 25, 150, 50);
		text12.setFont(fn);
		button11.setBounds(1050, 25, 80, 50);
		button11.setFont(fn);
		button12.setBounds(1150, 25, 100, 50);
		button12.setFont(fn);
		pan1.setLayout(null);
		pan1.add(lab1);
		pan1.add(lab2);
		pan1.add(lab3);
		pan1.add(text11);
		pan1.add(text12);
		pan1.add(button11);
		pan1.add(button12);
		pan1.add(info1);
		
		
		text11.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					button11.doClick();
				}
			}
		});
		
		text12.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					button11.doClick();
				}
			}
		});
		
		
		button11.addActionListener(new ActionListener() {
			                           @Override
			                           public void actionPerformed(ActionEvent e) {
				                           if (e.getSource() == button11) {
					                           String str1 = text11.getText();
					                           String[] input11 = str1.split(",");
					                           String str2 = text12.getText();
					                           String[] input12 = str2.split(",");
					                           if (input11.length >= 6 && input12.length >= 2) {
						                           try {
							                           final double x1 = Double.parseDouble(input11[0].trim());
							                           final double y1 = Double.parseDouble(input11[1].trim());
							                           final double x2 = Double.parseDouble(input11[2].trim());
							                           final double y2 = Double.parseDouble(input11[3].trim());
							                           final double x3 = Double.parseDouble(input11[4].trim());
							                           final double y3 = Double.parseDouble(input11[5].trim());
							                           final double dotx = Double.parseDouble(input12[0].trim());
							                           final double doty = Double.parseDouble(input12[1].trim());
							                           Triangle2D t = new Triangle2D(x1, y1, x2, y2, x3, y3);
							                           info1.setText(t.contains(dotx, doty) ? "Result: contains" : "Result: not contains");
							
							                           //PaintDemo
							                           class Inner extends JComponent {
								                           public void paintComponent(Graphics g) {     //0,0,45,345,456,34
									                           Graphics2D g2 = (Graphics2D) g;
									                           g2.draw(new Line2D.Double(0, 0, 1100, 0));//X
									                           g2.draw(new Line2D.Double(0, 0, 0, 400));//Y
									                           //->X
									                           for (int x = 1090; x < 1096; x++) {
										                           g2.drawString("·", x + 4, 1101 - x);
									                           }
									                           g2.drawString("X", 1110, 10);
									                           //->Y
									                           for (int x = 0; x < 6; x++) {
										                           g2.drawString("·", x, 405 - x);
									                           }
									                           g2.drawString("Y", 0, 418);
									                           //triangle
									                           g2.draw(new Line2D.Double(x1, y1, x2, y2));
									                           g2.draw(new Line2D.Double(x1, y1, x3, y3));
									                           g2.draw(new Line2D.Double(x2, y2, x3, y3));
									                           //dot
									                           Ellipse2D circle = new Ellipse2D.Double();
									                           circle.setFrameFromCenter((int) dotx, (int) doty, (int) dotx + 3, (int) doty + 3);
									                           g2.fill(circle);
								                           }
							                           }
							                           Inner in = new Inner();
							                           in.setBounds(30, 150, 10000, 10000);
							                           pan1.add(in);
							                           pan1.repaint();
							
							
						                           } catch (Exception ex) {
							                           info1.setText("********************************************************* Invalid Input *********************************************************");
						                           }
						
						
					                           } else {
						                           info1.setText("********************************************************* Invalid Input *********************************************************");
					                           }
				                           }
			                           }
		                           }
		
		);
		
		button12.addActionListener(new ActionListener() {
			                           @Override
			                           public void actionPerformed(ActionEvent e) {
				                           if (e.getSource() == button12) {
//                                               text11.setText("");
//                                               text12.setText("");
					                           info1.setText("Result: ");
					                           lab1 = new JLabel("Triangle(example: 0,0,45,345,456,34)");
					                           lab2 = new JLabel("contains ?");
					                           lab3 = new JLabel("Dot(example: 100,100)");
					                           lab1.setBounds(40, 0, 300, 100);
					                           lab1.setFont(fn);
					                           lab2.setBounds(600, 0, 100, 100);
					                           lab2.setFont(fn);
					                           lab3.setBounds(700, 0, 180, 100);
					                           lab3.setFont(fn);
					                           pan1.removeAll();
					                           pan1.setLayout(null);
					                           pan1.add(lab1);
					                           pan1.add(lab2);
					                           pan1.add(lab3);
					                           pan1.add(text11);
					                           pan1.add(text12);
					                           pan1.add(button11);
					                           pan1.add(button12);
					                           pan1.add(info1);
					                           pan1.repaint();
				                           }
			                           }
		                           }
		
		);
		
		//pan2
		info2 = new JLabel("Result: ");
		info2.setBounds(40, 60, 1300, 100);
		info2.setFont(fn);
		final JPanel pan2 = new JPanel();
		lab1 = new JLabel("Triangle1(example: 0,0,45,345,456,34)");
		lab2 = new JLabel("contains ?");
		lab3 = new JLabel("       Triangle2");
		
		lab1.setBounds(40, 0, 300, 100);
		lab1.setFont(fn);
		lab2.setBounds(600, 0, 100, 100);
		lab2.setFont(fn);
		lab3.setBounds(700, 0, 180, 100);
		lab3.setFont(fn);
		text21.setBounds(340, 25, 240, 50);
		text21.setFont(fn);
		text22.setBounds(880, 25, 150, 50);
		text22.setFont(fn);
		button21.setBounds(1050, 25, 80, 50);
		button21.setFont(fn);
		button22.setBounds(1150, 25, 100, 50);
		button22.setFont(fn);
		pan2.setLayout(null);
		pan2.add(lab1);
		pan2.add(lab2);
		pan2.add(lab3);
		pan2.add(text21);
		pan2.add(text22);
		pan2.add(button21);
		pan2.add(button22);
		pan2.add(info2);
		
		text21.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					button21.doClick();
				}
			}
		});
		
		text22.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					button21.doClick();
				}
			}
		});
		
		button21.addActionListener(new ActionListener() {
			                           @Override
			                           public void actionPerformed(ActionEvent e) {
				                           if (e.getSource() == button21) {
					                           String str1 = text21.getText();
					                           String[] input11 = str1.split(",");
					                           String str2 = text22.getText();
					                           String[] input12 = str2.split(",");
					                           if (input11.length >= 6 && input12.length >= 6) {
						                           try {
							                           final double t1x1 = Double.parseDouble(input11[0].trim());
							                           final double t1y1 = Double.parseDouble(input11[1].trim());
							                           final double t1x2 = Double.parseDouble(input11[2].trim());
							                           final double t1y2 = Double.parseDouble(input11[3].trim());
							                           final double t1x3 = Double.parseDouble(input11[4].trim());
							                           final double t1y3 = Double.parseDouble(input11[5].trim());
							                           final double t2x1 = Double.parseDouble(input12[0].trim());
							                           final double t2y1 = Double.parseDouble(input12[1].trim());
							                           final double t2x2 = Double.parseDouble(input12[2].trim());
							                           final double t2y2 = Double.parseDouble(input12[3].trim());
							                           final double t2x3 = Double.parseDouble(input12[4].trim());
							                           final double t2y3 = Double.parseDouble(input12[5].trim());
							                           final Triangle2D t1 = new Triangle2D(t1x1, t1y1, t1x2, t1y2, t1x3, t1y3);
							                           Triangle2D t2 = new Triangle2D(t2x1, t2y1, t2x2, t2y2, t2x3, t2y3);
							                           info2.setText(t1.contains(t2) ? "Result: contains" : "Result: not contains");
							
							                           //PaintDemo
							                           class Inner extends JComponent {
								                           public void paintComponent(Graphics g) {     //0,0,45,345,456,34
									                           Graphics2D g2 = (Graphics2D) g;
									                           g2.draw(new Line2D.Double(0, 0, 1100, 0));//X
									                           g2.draw(new Line2D.Double(0, 0, 0, 400));//Y
									                           //->X
									                           for (int x = 1090; x < 1096; x++) {
										                           g2.drawString("·", x + 4, 1101 - x);
									                           }
									                           g2.drawString("X", 1110, 10);
									                           //->Y
									                           for (int x = 0; x < 6; x++) {
										                           g2.drawString("·", x, 405 - x);
									                           }
									                           g2.drawString("Y", 0, 418);
									                           //triangle
									                           g2.draw(new Line2D.Double(t1x1, t1y1, t1x2, t1y2));
									                           g2.draw(new Line2D.Double(t1x1, t1y1, t1x3, t1y3));
									                           g2.draw(new Line2D.Double(t1x2, t1y2, t1x3, t1y3));
									                           g2.draw(new Line2D.Double(t2x1, t2y1, t2x2, t2y2));
									                           g2.draw(new Line2D.Double(t2x1, t2y1, t2x3, t2y3));
									                           g2.draw(new Line2D.Double(t2x2, t2y2, t2x3, t2y3));
								                           }
							                           }
							                           Inner in = new Inner();
							                           in.setBounds(30, 150, 10000, 10000);
							                           pan2.add(in);
							                           pan2.repaint();
							
							
						                           } catch (Exception ex) {
							                           info2.setText("********************************************************* Invalid Input *********************************************************");
						                           }
						
						
					                           } else {
						                           info2.setText("********************************************************* Invalid Input *********************************************************");
					                           }
				                           }
				
			                           }
		                           }
		
		);
		
		button22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button22) {
//                    text21.setText("");
//                    text22.setText("");
					info2.setText("Result: ");
					lab1 = new JLabel("Triangle1(example: 0,0,45,345,456,34)");
					lab2 = new JLabel("contains ?");
					lab3 = new JLabel("       Triangle2");
					lab1.setBounds(40, 0, 300, 100);
					lab1.setFont(fn);
					lab2.setBounds(600, 0, 100, 100);
					lab2.setFont(fn);
					lab3.setBounds(700, 0, 180, 100);
					lab3.setFont(fn);
					pan2.removeAll();
					pan2.setLayout(null);
					pan2.add(lab1);
					pan2.add(lab2);
					pan2.add(lab3);
					pan2.add(text21);
					pan2.add(text22);
					pan2.add(button21);
					pan2.add(button22);
					pan2.add(info2);
					pan2.repaint();
				}
			}
		});
		
		//pan3
		info3 = new JLabel("Result: ");
		info3.setBounds(40, 60, 1300, 100);
		info3.setFont(fn);
		final JPanel pan3 = new JPanel();
		lab1 = new JLabel("Triangle1(example: 0,0,45,345,456,34)");
		lab2 = new JLabel("overlaps ?");
		lab3 = new JLabel("       Triangle2");
		
		lab1.setBounds(40, 0, 300, 100);
		lab1.setFont(fn);
		lab2.setBounds(600, 0, 100, 100);
		lab2.setFont(fn);
		lab3.setBounds(700, 0, 180, 100);
		lab3.setFont(fn);
		text31.setBounds(340, 25, 240, 50);
		text31.setFont(fn);
		text32.setBounds(880, 25, 150, 50);
		text32.setFont(fn);
		button31.setBounds(1050, 25, 80, 50);
		button31.setFont(fn);
		button32.setBounds(1150, 25, 100, 50);
		button32.setFont(fn);
		pan3.setLayout(null);
		pan3.add(lab1);
		pan3.add(lab2);
		pan3.add(lab3);
		pan3.add(text31);
		pan3.add(text32);
		pan3.add(button31);
		pan3.add(button32);
		pan3.add(info3);
		
		text31.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					button31.doClick();
				}
			}
		});
		
		text32.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					button31.doClick();
				}
			}
		});
		
		button31.addActionListener(new ActionListener() {
			                           @Override
			                           public void actionPerformed(ActionEvent e) {
				                           if (e.getSource() == button31) {
					                           String str1 = text31.getText();
					                           String[] input11 = str1.split(",");
					                           String str2 = text32.getText();
					                           String[] input12 = str2.split(",");
					                           if (input11.length >= 6 && input12.length >= 6) {
						                           try {
							                           final double t1x1 = Double.parseDouble(input11[0].trim());
							                           final double t1y1 = Double.parseDouble(input11[1].trim());
							                           final double t1x2 = Double.parseDouble(input11[2].trim());
							                           final double t1y2 = Double.parseDouble(input11[3].trim());
							                           final double t1x3 = Double.parseDouble(input11[4].trim());
							                           final double t1y3 = Double.parseDouble(input11[5].trim());
							                           final double t2x1 = Double.parseDouble(input12[0].trim());
							                           final double t2y1 = Double.parseDouble(input12[1].trim());
							                           final double t2x2 = Double.parseDouble(input12[2].trim());
							                           final double t2y2 = Double.parseDouble(input12[3].trim());
							                           final double t2x3 = Double.parseDouble(input12[4].trim());
							                           final double t2y3 = Double.parseDouble(input12[5].trim());
							                           final Triangle2D t1 = new Triangle2D(t1x1, t1y1, t1x2, t1y2, t1x3, t1y3);
							                           Triangle2D t2 = new Triangle2D(t2x1, t2y1, t2x2, t2y2, t2x3, t2y3);
							                           info3.setText(t1.overlaps(t2) ? "Result: overlaps" : "Result: not overlaps");
							
							                           //PaintDemo
							                           class Inner extends JComponent {
								                           public void paintComponent(Graphics g) {     //0,0,45,345,456,34
									                           Graphics2D g2 = (Graphics2D) g;
									                           g2.draw(new Line2D.Double(0, 0, 1100, 0));//X
									                           g2.draw(new Line2D.Double(0, 0, 0, 400));//Y
									                           //->X
									                           for (int x = 1090; x < 1096; x++) {
										                           g2.drawString("·", x + 4, 1101 - x);
									                           }
									                           g2.drawString("X", 1110, 10);
									                           //->Y
									                           for (int x = 0; x < 6; x++) {
										                           g2.drawString("·", x, 405 - x);
									                           }
									                           g2.drawString("Y", 0, 418);
									                           //triangle
									                           g2.draw(new Line2D.Double(t1x1, t1y1, t1x2, t1y2));
									                           g2.draw(new Line2D.Double(t1x1, t1y1, t1x3, t1y3));
									                           g2.draw(new Line2D.Double(t1x2, t1y2, t1x3, t1y3));
									                           g2.draw(new Line2D.Double(t2x1, t2y1, t2x2, t2y2));
									                           g2.draw(new Line2D.Double(t2x1, t2y1, t2x3, t2y3));
									                           g2.draw(new Line2D.Double(t2x2, t2y2, t2x3, t2y3));
								                           }
							                           }
							                           Inner in = new Inner();
							                           in.setBounds(30, 150, 10000, 10000);
							                           pan3.add(in);
							                           pan3.repaint();
							
							
						                           } catch (Exception ex) {
							                           info3.setText("********************************************************* Invalid Input *********************************************************");
						                           }
						
						
					                           } else {
						                           info3.setText("********************************************************* Invalid Input *********************************************************");
					                           }
				                           }
				
			                           }
		                           }
		
		);
		
		button32.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button32) {
//                    text31.setText("");
//                    text32.setText("");
					info3.setText("Result: ");
					lab1 = new JLabel("Triangle1(example: 0,0,45,345,456,34)");
					lab2 = new JLabel("overlaps ?");
					lab3 = new JLabel("       Triangle2");
					lab1.setBounds(40, 0, 300, 100);
					lab1.setFont(fn);
					lab2.setBounds(600, 0, 100, 100);
					lab2.setFont(fn);
					lab3.setBounds(700, 0, 180, 100);
					lab3.setFont(fn);
					pan3.removeAll();
					pan3.setLayout(null);
					pan3.add(lab1);
					pan3.add(lab2);
					pan3.add(lab3);
					pan3.add(text31);
					pan3.add(text32);
					pan3.add(button31);
					pan3.add(button32);
					pan3.add(info3);
					pan3.repaint();
				}
			}
		});
		
		tab.addTab("   Test 1   ", pan1);
		tab.addTab("   Test 2   ", pan2);
		tab.addTab("   Test 3   ", pan3);
		tab.setFont(fn);
		cont.add(tab);
		frame.setSize(1300, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.setVisible(true);
		
		
	}
}

class LogInHandle {
	private JFrame frame = new JFrame("Welcome to Java");
	private JButton submit = new JButton("submit");
	private JButton reset = new JButton("reset");
	private JLabel nameLab = new JLabel("Name: ");
	private JLabel passLab = new JLabel("Password: ");
	private JLabel infoLab = new JLabel("Login System");
	private JTextField nameText = new JTextField(10);
	private JPasswordField passText = new JPasswordField();
	private Font newFn = new Font("Times New Roman", Font.BOLD, 18);
	
	public LogInHandle() {
		final Font fn = new Font("Seriel", Font.ITALIC + Font.BOLD, 28);
		infoLab.setFont(fn);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == submit) {
					String tname = nameText.getText();
					String tpass = new String(passText.getPassword());
					LoginCheck log = new LoginCheck(tname, tpass);
					try {
						if (log.validate()) {
							frame.dispose();
//                            Thread.sleep(1000);
							new TriangleHandle();
						} else {
							infoLab.setText("Wrong Password");
							passText.setText("");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == reset) {
					nameText.setText("");
					passText.setText("");
					infoLab.setText("Login System");
				}
			}
		});
		
		nameText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					KeyboardFocusManager.
							getCurrentKeyboardFocusManager().focusNextComponent(passLab);
				}
			}
		});
		
		passText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					submit.doClick();
				}
			}
		});
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
		frame.setLayout(null);
		nameLab.setBounds(180, 150, 80, 30);
		nameLab.setFont(newFn);
		passLab.setBounds(180, 200, 100, 30);
		passLab.setFont(newFn);
		infoLab.setBounds(250, 250, 240, 50);
		nameText.setBounds(290, 150, 120, 30);
		nameText.setFont(newFn);
		passText.setBounds(290, 200, 120, 30);
		passText.setFont(newFn);
		submit.setBounds(435, 150, 100, 30);
		submit.setFont(newFn);
		reset.setBounds(435, 200, 100, 30);
		reset.setFont(newFn);
		frame.add(nameLab);
		frame.add(passLab);
		frame.add(infoLab);
		frame.add(nameText);
		frame.add(passText);
		frame.add(submit);
		frame.add(reset);
		frame.pack();
		frame.setSize(700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

class LoginCheck {
	private String name;
	private String password;
	
	public LoginCheck(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public boolean validate() throws Exception {
		return "zcwang".equals(name) && "zcwang".equals(password);
	}
	
}