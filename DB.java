
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
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
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DB extends Frame {
	private Connection con;
	private Statement stmt;

	DB() {
		Frame f = new Frame();
		Font f1 = new Font("CRUD", 50, 120);
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");

		} catch (Exception e) {
			System.out.println(e);
		}
		Button b1 = new Button("Create / Insert");
		b1.setBounds(50, 200, 100, 30);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextArea a1 = new TextArea("Write a Create table query or Insert query..");
				a1.setBounds(250, 180, 500, 90);
				Button b2 = new Button("Do");
				b2.setBounds(800, 200, 50, 30);
				b2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {

							stmt = con.createStatement();
							String y = a1.getText();
							stmt.executeUpdate(y);
							a1.setText("Table is Created..");

						} catch (Exception e2) {
							System.out.println(e2);
						}
					}
				});
				f.add(a1);
				f.add(b2);
			}
		});
		Button b2 = new Button("Read");
		b2.setBounds(50, 300, 100, 30);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextArea a1 = new TextArea("Write a Select Query..");
				a1.setBounds(250, 280, 500, 90);
				Button b3 = new Button("Do");
				b3.setBounds(800, 300, 50, 30);
				b3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String x = a1.getText();
							String y = "";
							PreparedStatement stmt = con.prepareStatement(x);
							ResultSet rs = stmt.executeQuery();
							ResultSetMetaData rsmd = rs.getMetaData();
							while (rs.next()) {
								for (int i = 1; i < rsmd.getColumnCount(); i++) {
									y += rs.getString(i) + " ";
								}
								y += "\n";
							}
							a1.setText("");
							a1.setText(a1.getText() + y + "\n");

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
		b3.setBounds(50, 400, 100, 30);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextArea a1 = new TextArea("Write a Update Query..");
				a1.setBounds(250, 380, 500, 90);
				Button b7 = new Button("Do");
				b7.setBounds(800, 400, 50, 30);
				b7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int c;

							String y = a1.getText();
							c = stmt.executeUpdate(y);
							if (c >= 0) {
								a1.setText("No; of rows updated = " + c);
							}

						} catch (Exception e2) {
							System.out.println(e2);
						}

					}
				});
				f.add(a1);
				f.add(b7);
			}
		});
		Button b4 = new Button("Delete");
		b4.setBounds(50, 500, 100, 30);
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextArea a1 = new TextArea("Enter Table name, Column name, condition and value with comma seperated..");
				a1.setBounds(250, 480, 500, 90);
				Button b5 = new Button("Do");
				b5.setBounds(800, 500, 50, 30);
				b5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int q;

							stmt = con.createStatement();
							String y[] = a1.getText().split(",");
							String p = "delete from " + y[0] + " where " + y[1] + " " + y[2] + " " + y[3];
							q = stmt.executeUpdate(p);
							if (q >= 0) {
								a1.setText("Deleted total of " + q + " rows");
							}

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
		f.setTitle("CRUD");
		f.setLayout(null);
		f.setVisible(true);
		f.setBackground(Color.gray);

	}

	public static void main(String args[]) {
		DB n = new DB();
	}
}

// Button first = new Button("First");
// first.setBounds(850, 300, 50, 30);
// f.add(first);
// first.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// try {
// rs.first();
// ResultSetMetaData rsmd = rs.getMetaData();
// int c = rsmd.getColumnCount();
// int i = 1;
// while (i != c + 1) {
// a1.setText(a1.getText() + " " + rs.getString(i));
// i++;
// }
// } catch (Exception e1) {
// // TODO Auto-generated catch block
// System.out.println(e1);
// }
// }
// });
