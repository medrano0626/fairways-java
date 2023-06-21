public void maintenance(int value)
{
try
    {
    String name = txtname.getText();
    String item = txtitem.getText();
            String price = txtprice.getText();
    String id = txtid.getText();
     //.........Connecting to the Data Base........
     Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/dscomputers", "root","");
switch(value)
{
//.......Inserting to the database........
case 1:
pst=con.prepareStatement("INSERT  INTO records(name,item,price) VALUES(?,?,?)");
pst.setString(1,name);
pst.setString(2,item);
pst.setString(3,price);
pst.executeUpdate();
pst.close();
break;
//..........Updateing the database.......
case 2:
pst=con.prepareStatement("UPDATE records SET name=?,item=?,price=? WHERE id=?");
 
pst.setString(1,name);
pst.setString(2,item);
pst.setString(3,price);
pst.setString(4,id);
pst.executeUpdate();
break;
 
//.....Displaying records from the database...
case 3:
pst=con.prepareStatement("SELECT * FROM records WHERE id=?");
pst.setString(1,id);
ResultSet rs=pst.executeQuery();
if(rs.next( )== false)
{
JOptionPane.showMessageDialog(null, "Couldn't Find the ID");
}
else
{
txtname.setText(rs.getString(2));
txtitem.setText(rs.getString(3));
txtprice.setText(rs.getString(4));
}
break;
 
//......Deleteing records from the database.........
case 4:
pst=con.prepareStatement("delete from records WHERE id=?");
pst.setString(1,id);
pst.executeUpdate();
break;
case 5:
txtname.setText("");
txtitem.setText("");
txtprice.setText("");
break;
}
}
//..........Handling Exceptions in the program.......
catch(ClassNotFoundException e)
{
  System.err.println(e);
}
 
   catch(SQLException e)
{
  System.err.println(e);
}
}