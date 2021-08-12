package zBus;
import java.util.*;
import java.sql.*;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class BookTicket
{
	Scanner sc = new Scanner(System.in);
	Connection conn;
	Statement stmt;
	public void setconnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ticket_booking_system","root", "");
			stmt=conn.createStatement();
			System.out.println("Database is connected !");
		}
		catch(Exception e)
		{
			System.out.print("Do not connect to DB - Error:"+e);
		}
	}
	
	public void addnewbus()
	{
		System.out.print("Enter the Bus_Name            :");
		String bus_name = sc.nextLine();
		System.out.print("Enter the Source Point        :");
		String source = sc.next();
		System.out.print("Enter the Destination Point   :");
		String depart = sc.next();
		System.out.print("Enter the Arrival Time        :");
		String arrive_t = sc.next();
		System.out.print("Enter the Departure Time      :");
		String depart_t = sc.next();
		System.out.print("Enter the Travel Duration     :");
		String dur = sc.next();
		System.out.print("Enter the Total_Seat Available:");
		int seat = sc.nextInt();
		System.out.print("Enter the Cost of Per Ticket  :");
		int price = sc.nextInt();
		try
		{
			 String sql = "INSERT INTO bus_details VALUES('"+bus_name+"','"+source+"','"+depart+"','"+arrive_t+"','"+depart_t+"','"+dur+"','"+seat+"','"+price+"')";
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into the table...");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deletebus()
	{
		System.out.println("-----------------Delete Page-----------------");
		System.out.print("Enter the bus name that you need to delete  :");
		String delbusname = sc.nextLine().trim();
		try
		{
			stmt.executeUpdate("delete from bus_details where BUS_NAME = '"+ delbusname+"'");
			System.out.println("Deleted records from the table...");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displaybus()
	{
		System.out.println("-----------------Display Page-----------------");
		try
		{
			ResultSet rs = stmt.executeQuery("select * from bus_details");
			while(rs.next())
			{
				String s1 = rs.getString("BUS_NAME");
				String s2 = rs.getString("BUS_SOURCE");
				String s3 = rs.getString("BUS_DESTINATION");
				String s4 = rs.getString("ARRIVE_TIME");
				String s5 = rs.getString("TRAVEL_DURATION");
				int s6 = rs.getInt("SEAT_AVAILABLE");
				int s7 = rs.getInt("PER_TICKET_PRICE");
				System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void editbusdetails()
	{
		System.out.println("-----WHICH YOU WANT TO EDIT-----");
		System.out.println("|     |1| BUS NAME             |");
		System.out.println("|     |2| SOURCE POINT         |");
		System.out.println("|     |3| DESTINATION POINT    |");
		System.out.println("|     |4| ARRIVAL TIME         |");
		System.out.println("|     |5| TRAVEL DURATION      |");
		System.out.println("|     |6| SEAT AVAILABLE       |");
		System.out.println("|     |7| PER TICKET COST      |");
		System.out.println("--------------------------------");
		System.out.print("Enter the your choice : ");
		int editchoice = sc.nextInt();
		sc.nextLine();
		if(editchoice == 1)
		{
			System.out.print("Enter the old bus Name: ");
			String oldbusname = sc.nextLine();
			try
			{
				ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME='"+oldbusname+"'");
				while(rs.next())
				{
					String s1 = rs.getString("BUS_NAME");
					String s2 = rs.getString("BUS_SOURCE");
					String s3 = rs.getString("BUS_DESTINATION");
					String s4 = rs.getString("ARRIVE_TIME");
					String s5 = rs.getString("TRAVEL_DURATION");
					int s6 = rs.getInt("SEAT_AVAILABLE");
					int s7 = rs.getInt("PER_TICKET_PRICE");
					System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.print("Enter the New Bus Name: ");
			String newbusname = sc.nextLine();
			try
			{
				String sql = "UPDATE bus_details " +
		                   "SET BUS_NAME = '"+newbusname+"' "
		                   + "WHERE BUS_NAME = '"+ oldbusname+"'";
		      stmt.executeUpdate(sql);
		      System.out.println("BUS_NAME is update successfully");
		      try
				{
					ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME = '"+newbusname+"'");
					while(rs.next())
					{
						String s1 = rs.getString("BUS_NAME");
						String s2 = rs.getString("BUS_SOURCE");
						String s3 = rs.getString("BUS_DESTINATION");
						String s4 = rs.getString("ARRIVE_TIME");
						String s5 = rs.getString("TRAVEL_DURATION");
						int s6 = rs.getInt("SEAT_AVAILABLE");
						int s7 = rs.getInt("PER_TICKET_PRICE");
						System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(editchoice == 2)
		{
			System.out.print("Enter the bus Name in which you want to edit source: ");
			String busname = sc.nextLine();
			try
			{
				ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME='"+busname+"'");
				while(rs.next())
				{
					String s1 = rs.getString("BUS_NAME");
					String s2 = rs.getString("BUS_SOURCE");
					String s3 = rs.getString("BUS_DESTINATION");
					String s4 = rs.getString("ARRIVE_TIME");
					String s5 = rs.getString("TRAVEL_DURATION");
					int s6 = rs.getInt("SEAT_AVAILABLE");
					int s7 = rs.getInt("PER_TICKET_PRICE");
					System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.print("Enter the New Source Point: ");
			String newsource = sc.nextLine();
			try
			{
				String sql = "UPDATE bus_details " +
		                   "SET BUS_SOURCE = '"+newsource+"' "
		                   + "WHERE BUS_NAME = '"+busname+"'";
		      stmt.executeUpdate(sql);
		      System.out.println("Source is update successfully");
		      try
				{
					ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME = '"+busname+"'");
					while(rs.next())
					{
						String s1 = rs.getString("BUS_NAME");
						String s2 = rs.getString("BUS_SOURCE");
						String s3 = rs.getString("BUS_DESTINATION");
						String s4 = rs.getString("ARRIVE_TIME");
						String s5 = rs.getString("TRAVEL_DURATION");
						int s6 = rs.getInt("SEAT_AVAILABLE");
						int s7 = rs.getInt("PER_TICKET_PRICE");
						System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(editchoice == 3)
		{
			System.out.print("Enter the bus Name in which you want to edit destination: ");
			String busname = sc.nextLine();
			try
			{
				ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME='"+busname+"'");
				while(rs.next())
				{
					String s1 = rs.getString("BUS_NAME");
					String s2 = rs.getString("BUS_SOURCE");
					String s3 = rs.getString("BUS_DESTINATION");
					String s4 = rs.getString("ARRIVE_TIME");
					String s5 = rs.getString("TRAVEL_DURATION");
					int s6 = rs.getInt("SEAT_AVAILABLE");
					int s7 = rs.getInt("PER_TICKET_PRICE");
					System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.print("Enter the New DESTINATION Point: ");
			String newdestination = sc.nextLine();
			try
			{
				String sql = "UPDATE bus_details " +
		                   "SET BUS_DESTINATION = '"+newdestination+"' "
		                   + "WHERE BUS_NAME = '"+busname+"'";
		      stmt.executeUpdate(sql);
		      System.out.println("Destination is update successfully");
		      try
				{
					ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME = '"+busname+"'");
					while(rs.next())
					{
						String s1 = rs.getString("BUS_NAME");
						String s2 = rs.getString("BUS_SOURCE");
						String s3 = rs.getString("BUS_DESTINATION");
						String s4 = rs.getString("ARRIVE_TIME");
						String s5 = rs.getString("TRAVEL_DURATION");
						int s6 = rs.getInt("SEAT_AVAILABLE");
						int s7 = rs.getInt("PER_TICKET_PRICE");
						System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(editchoice == 4)
		{
			System.out.print("Enter the bus Name in which you want to edit arrival time: ");
			String busname = sc.nextLine();
			try
			{
				ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME='"+busname+"'");
				while(rs.next())
				{
					String s1 = rs.getString("BUS_NAME");
					String s2 = rs.getString("BUS_SOURCE");
					String s3 = rs.getString("BUS_DESTINATION");
					String s4 = rs.getString("ARRIVE_TIME");
					String s5 = rs.getString("TRAVEL_DURATION");
					int s6 = rs.getInt("SEAT_AVAILABLE");
					int s7 = rs.getInt("PER_TICKET_PRICE");
					System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.print("Enter the New Arrival Time: ");
			String newtime = sc.nextLine();
			try
			{
				String sql = "UPDATE bus_details " +
		                   "SET ARRIVE_TIME = '"+newtime+"' "
		                   + "WHERE BUS_NAME = '"+busname+"'";
		      stmt.executeUpdate(sql);
		      System.out.println("New Arrival Time is update successfully");
		      try
				{
					ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME = '"+busname+"'");
					while(rs.next())
					{
						String s1 = rs.getString("BUS_NAME");
						String s2 = rs.getString("BUS_SOURCE");
						String s3 = rs.getString("BUS_DESTINATION");
						String s4 = rs.getString("ARRIVE_TIME");
						String s5 = rs.getString("TRAVEL_DURATION");
						int s6 = rs.getInt("SEAT_AVAILABLE");
						int s7 = rs.getInt("PER_TICKET_PRICE");
						System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(editchoice == 5)
		{
			System.out.print("Enter the bus Name in which you want to travel duration: ");
			String busname = sc.nextLine();
			try
			{
				ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME='"+busname+"'");
				while(rs.next())
				{
					String s1 = rs.getString("BUS_NAME");
					String s2 = rs.getString("BUS_SOURCE");
					String s3 = rs.getString("BUS_DESTINATION");
					String s4 = rs.getString("ARRIVE_TIME");
					String s5 = rs.getString("TRAVEL_DURATION");
					int s6 = rs.getInt("SEAT_AVAILABLE");
					int s7 = rs.getInt("PER_TICKET_PRICE");
					System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.print("Enter the New Travel Duration: ");
			String dur = sc.nextLine();
			try
			{
				String sql = "UPDATE bus_details " +
		                   "SET TRAVEL_DURATION = '"+dur+"' "
		                   + "WHERE BUS_NAME = '"+busname+"'";
		      stmt.executeUpdate(sql);
		      System.out.println("Travel Duration is update successfully");
		      try
				{
					ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME = '"+busname+"'");
					while(rs.next())
					{
						String s1 = rs.getString("BUS_NAME");
						String s2 = rs.getString("BUS_SOURCE");
						String s3 = rs.getString("BUS_DESTINATION");
						String s4 = rs.getString("ARRIVE_TIME");
						String s5 = rs.getString("TRAVEL_DURATION");
						int s6 = rs.getInt("SEAT_AVAILABLE");
						int s7 = rs.getInt("PER_TICKET_PRICE");
						System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(editchoice == 6)
		{
			System.out.print("Enter the bus Name in which you want to edit source: ");
			String busname = sc.nextLine();
			try
			{
				ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME='"+busname+"'");
				while(rs.next())
				{
					String s1 = rs.getString("BUS_NAME");
					String s2 = rs.getString("BUS_SOURCE");
					String s3 = rs.getString("BUS_DESTINATION");
					String s4 = rs.getString("ARRIVE_TIME");
					String s5 = rs.getString("TRAVEL_DURATION");
					int s6 = rs.getInt("SEAT_AVAILABLE");
					int s7 = rs.getInt("PER_TICKET_PRICE");
					System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.print("Enter the New SEAT AVAILABLE: ");
			int newseatcount = sc.nextInt();
			try
			{
				String sql = "UPDATE bus_details " +
		                   "SET SEAT_AVAILABLE = '"+newseatcount+"' "
		                   + "WHERE BUS_NAME = '"+busname+"'";
		      stmt.executeUpdate(sql);
		      System.out.println("Seat Available is update successfully");
		      try
				{
					ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME = '"+busname+"'");
					while(rs.next())
					{
						String s1 = rs.getString("BUS_NAME");
						String s2 = rs.getString("BUS_SOURCE");
						String s3 = rs.getString("BUS_DESTINATION");
						String s4 = rs.getString("ARRIVE_TIME");
						String s5 = rs.getString("TRAVEL_DURATION");
						int s6 = rs.getInt("SEAT_AVAILABLE");
						int s7 = rs.getInt("PER_TICKET_PRICE");
						System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(editchoice == 7)
		{
			System.out.print("Enter the bus Name in which you want to edit ticket price: ");
			String busname = sc.nextLine();
			try
			{
				ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME='"+busname+"'");
				while(rs.next())
				{
					String s1 = rs.getString("BUS_NAME");
					String s2 = rs.getString("BUS_SOURCE");
					String s3 = rs.getString("BUS_DESTINATION");
					String s4 = rs.getString("ARRIVE_TIME");
					String s5 = rs.getString("TRAVEL_DURATION");
					int s6 = rs.getInt("SEAT_AVAILABLE");
					int s7 = rs.getInt("PER_TICKET_PRICE");
					System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.print("Enter the New Ticket Price: ");
			int newprice = sc.nextInt();
			try
			{
				String sql = "UPDATE bus_details " +
		                   "SET PER_TICKET_PRICE = '"+newprice+"' "
		                   + "WHERE BUS_NAME = '"+busname+"'";
		      stmt.executeUpdate(sql);
		      System.out.println("New Ticket Price is update successfully");
		      try
				{
					ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_NAME = '"+busname+"'");
					while(rs.next())
					{
						String s1 = rs.getString("BUS_NAME");
						String s2 = rs.getString("BUS_SOURCE");
						String s3 = rs.getString("BUS_DESTINATION");
						String s4 = rs.getString("ARRIVE_TIME");
						String s5 = rs.getString("TRAVEL_DURATION");
						int s6 = rs.getInt("SEAT_AVAILABLE");
						int s7 = rs.getInt("PER_TICKET_PRICE");
						System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Invalid Option");
		}
	}
	public void booknewticket()
	{
		System.out.println("-----NEW TICKET-----");
		System.out.print("Enter the CUSTOMER NAME        :");
		String cusname = sc.nextLine().trim();
		System.out.print("Enter your phone_number        :");
		String phone = sc.next();
		System.out.print("Enter your Email_Address       :");
		String mail = sc.next();
		System.out.print("Enter your source point        :");
		String cus_source = sc.next();
		System.out.print("Enter your destination point   :");
		String cus_destination = sc.next();
		try
		{
				int count=0,price=0;
				ResultSet sr = stmt.executeQuery("select BUS_NAME from bus_details where BUS_SOURCE='"+cus_source+"' AND BUS_DESTINATION='"+cus_destination+"'");
				String buschoosen="";int noofseat=0;
				while(sr.next())
				{
					count++;
				}
				if(count != 0)
				{
					try {
						ResultSet rs = stmt.executeQuery("select * from bus_details where BUS_SOURCE='"+cus_source+"' AND BUS_DESTINATION='"+cus_destination+"'");
						while(rs.next())
						{
							String s1 = rs.getString("BUS_NAME");
							String s2 = rs.getString("BUS_SOURCE");
							String s3 = rs.getString("BUS_DESTINATION");
							String s4 = rs.getString("ARRIVE_TIME");
							String s5 = rs.getString("TRAVEL_DURATION");
							int s6 = rs.getInt("SEAT_AVAILABLE");
							int s7 = rs.getInt("PER_TICKET_PRICE");
							System.out.println("BUS_NAME:"+s1+"---BUS_SOURCE:"+s2+"---BUS_DESTINATION:"+s3+"---ARRIVE_TIME:"+s4+"---TRAVEL_DUR:"+s5+"---SEAT AVAILABLE:"+s6+"---PER_TICKET_PRICE:"+s7);
							sc.nextLine();
							System.out.println("Enter your choosed bus name  :");
							buschoosen = sc.nextLine();
							System.out.println("Enter no of seats you required :");
							noofseat = sc.nextInt();
						}
					} 
					catch(Exception e) {
						e.printStackTrace();
					}	
				}
				else
				{
					System.out.println("BUS NOT AVAILABLE NOW!!!");
				}
				try
				{
					String sql = "UPDATE bus_details " +
					"SET SEAT_AVAILABLE = SEAT_AVAILABLE - '"+noofseat+"' "
					+ "WHERE BUS_NAME = '"+buschoosen+"'";
					stmt.executeUpdate(sql);
				} 
				catch(Exception e)
				{
					e.printStackTrace();
				}
				System.out.println("*****Payment Page*****");
				System.out.print("Enter your CARD_NO                   :");
				String cardno = sc.next(); 
				System.out.print("Enter your CARD_CCV                  :");
				String cardccv = sc.next();
				System.out.print("Enter your CARD_EXP date (DD-MM-YYYY):");
				String carddate = sc.next();
				try
				{
					 String sql = "INSERT INTO user_details VALUES('"+cusname+"','"+phone+"','"+mail+"','"+cus_source+"','"+cus_destination+"','"+noofseat+"','"+price+"','"+buschoosen+"','"+cardno+"','"+cardccv+"','"+carddate+"')";
					stmt.executeUpdate(sql);
					System.out.println("Customer records inserted into the table...");
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception e) {
				e.printStackTrace();
		}
	}
	public void deletecustomer()
	{
		System.out.println("-----------------Customer Delete Page-----------------");
		System.out.print("Enter the customer name that you need to delete  :");
		String cus_name = sc.nextLine().trim();
		try
		{
			stmt.executeUpdate("delete from user_details where CUSTOMER_NAME = '"+ cus_name+"'");
			System.out.println("CUSTOMER Deleted from the table...");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void displaycustomer()
	{
		System.out.println("-----------------Display Page-----------------");
		try
		{
			System.out.println("Enter the Customer name: ");
			String customername=sc.nextLine().trim();
			int count=0;
			ResultSet r = stmt.executeQuery("select CUSTOMER_NAME from user_details where CUSTOMER_NAME ='"+customername+"'");
			while(r.next())
			{
				count++;
			}
			if(count != 0)
			{
				ResultSet rs = stmt.executeQuery("select * from user_details where CUSTOMER_NAME ='"+customername+"'");
				while(rs.next())
				{
					String s1 = rs.getString("CUSTOMER_NAME");
					String s2 = rs.getString("PHONE_NUMBER");
					String s3 = rs.getString("EMAIL_ID");
					String s4 = rs.getString("CUS_SOURCE");
					String s5 = rs.getString("CUS_DESTINATION");
					int s6 = rs.getInt("SEAT_REQUIRED -");
					int s7 = rs.getInt("PRICE");
					String s8 = rs.getString("BUS_NAME");
					String s9 = rs.getString("CARD_NO");
					String s10 = rs.getString("CARD_CCV");
					String s11 = rs.getString("CARD_EXP_DATE");
					System.out.println("-----CUSTOMER DETAILS-----");
					System.out.println("|CUSTOMER NAME        : "+s1);
					System.out.println("|PHONE NUMBER         : "+s2);
					System.out.println("|EMAIL ADDRESS 		  : "+s3);
					System.out.println("|CUSTOMER SOURCE      : "+s4);
					System.out.println("|CUSTOMER DESTINATION : "+s5);
					System.out.println("|NO OF SEAT BOOKED    : "+s6);
					System.out.println("|TOTAL PRICE          : "+s7);
					System.out.println("|BUS NAME             : "+s8);
					System.out.println("|CARD NO              : "+s9);
					System.out.println("|CARD CVV             : "+s10);
					System.out.println("|CARD DATE            : "+s11);
				}
			}
			else
			{
				System.out.println("CUSTOMER NAME IS INVALID!!!");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void adminview()
	{
		System.out.println("-----------------ADMIN VIEW PAGE-----------------");
		try
		{
			int count=0;
			ResultSet r = stmt.executeQuery("select CUSTOMER_NAME from user_details");
			while(r.next())
			{
				count++;
			}
			if(count != 0)
			{
				ResultSet rs = stmt.executeQuery("select * from user_details");
				while(rs.next())
				{
					String s1 = rs.getString("CUSTOMER_NAME");
					String s2 = rs.getString("PHONE_NUMBER");
					String s3 = rs.getString("EMAIL_ID");
					String s4 = rs.getString("CUS_SOURCE");
					String s5 = rs.getString("CUS_DESTINATION");
					int s6 = rs.getInt("SEAT_REQUIRED -");
					int s7 = rs.getInt("PRICE");
					String s8 = rs.getString("BUS_NAME");
					System.out.println("-----CUSTOMER DETAILS-----");
					System.out.println("|CUSTOMER NAME        : "+s1);
					System.out.println("|PHONE NUMBER         : "+s2);
					System.out.println("|EMAIL ADDRESS 		  : "+s3);
					System.out.println("|CUSTOMER SOURCE      : "+s4);
					System.out.println("|CUSTOMER DESTINATION : "+s5);
					System.out.println("|NO OF SEAT BOOKED    : "+s6);
					System.out.println("|TOTAL PRICE          : "+s7);
					System.out.println("|BUS NAME             : "+s8);
				}
			}
			else
			{
				System.out.println("CUSTOMER NAME IS INVALID!!!");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}