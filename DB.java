
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB extends Frame {
	DB() {
		Frame f = new Frame();
		Button b1 = new Button("Create");
		b1.setBounds(50, 150, 50, 30);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextArea a1 = new TextArea();
				a1.setBounds(250, 120, 500, 90);
				Button b2 = new Button("Do");
				b2.setBounds(800, 150, 50, 30);
				f.add(a1);
				f.add(b2);
			}
		});
		Button b2 = new Button("Read");
		b2.setBounds(50, 250, 50, 30);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextArea a1 = new TextArea();
				a1.setBounds(250, 220, 500, 90);
				Button b3 = new Button("Do");
				b3.setBounds(800, 250, 50, 30);
				b3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Class.forName("org.postgresql.Driver");
							Connection con = DriverManager.getConnection(
									"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
							String x = a1.getText();
							String y = "";
							PreparedStatement stmt = con.prepareStatement(x);
							ResultSet rs = stmt.executeQuery();
							while (rs.next()) {
								y += rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDate(3) + " " + rs.getDouble(4)
										+ "\n";
							}
							a1.setText(y);
							con.close();
						} catch (Exception e1) {
							System.out.println(e1);
						}
					}
				});
				f.add(a1);
				f.add(b3);
			}
		});
		Button b3 = new Button("Update");
		b3.setBounds(50, 350, 50, 30);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextArea a1 = new TextArea();
				a1.setBounds(250, 320, 500, 90);
				Button b7 = new Button("Do");
				b7.setBounds(800, 350, 50, 30);
				b7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int c;
							Class.forName("org.postgresql.Driver");
							Connection con = DriverManager.getConnection(
									"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
							String y = a1.getText();
							Statement stmt = con.createStatement();
							c = stmt.executeUpdate(y);
							if (c > 0) {
								System.out.println("No; of rows updated = " + c);
							}
							con.close();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							System.out.println(e2);
						}

					}
				});
				f.add(a1);
				f.add(b7);
			}
		});
		Button b4 = new Button("Delete");
		b4.setBounds(50, 450, 50, 30);
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextArea a1 = new TextArea("Enter Table name, Column name, condition and value with comma seperated..");
				a1.setBounds(250, 420, 500, 90);
				Button b5 = new Button("Do");
				b5.setBounds(800, 450, 50, 30);
				b5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int q;
							Class.forName("org.postgresql.Driver");
							Connection con = DriverManager.getConnection(
									"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
							Statement stmt = con.createStatement();
							String y[] = a1.getText().split(",");
							String p = "delete from " + y[0] + " where " + y[1] + " " + y[2] + " " + y[3];
							System.out.println(p);
							q = stmt.executeUpdate(p);
							if (q > 0) {
								a1.setText("Deleted total of " + q + " rows");
							}
							con.close();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							System.out.println(e2);
						}

					}
				});
				f.add(a1);
				f.add(b5);
			}
		});
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setSize(1200, 1200);
		f.setTitle("TextU");
		f.setLayout(null);
		f.setVisible(true);
		f.setBackground(Color.gray);

	}

	public static void main(String args[]) {
		DB n = new DB();
	}
}
