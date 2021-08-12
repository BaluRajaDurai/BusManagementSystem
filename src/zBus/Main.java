package zBus;

import java.util.Scanner;

class Main
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		BookTicket b = new BookTicket();
		b.setconnection();
		System.out.println("-----Enter Whom Your-----");
		System.out.println("|    |1|CUSTOMER        |");
		System.out.println("|    |2|ADMIN           |");
		System.out.println("-------------------------");
		System.out.print("Enter the your choice: ");
		int user_choice = sc.nextInt();
		int cuschoice;
		if(user_choice == 1)
		{
			do {
			System.out.println("----------USER OPTIONS------------");
			System.out.println("|		|1| ADD CUSTOMER          ");
			System.out.println("|		|2| DELETE CUSTOMER       ");
			System.out.println("|		|3| DISPLAY CUSTOMER      ");
			System.out.println("|		|4| EXIT                  ");
			System.out.println("----------------------------------");
			System.out.println("Enter your choice");
			cuschoice = sc.nextInt();
			
			if( cuschoice ==  1)
				b.booknewticket();
			else if(cuschoice == 2)
				b.deletecustomer();
			else if(cuschoice == 3)
				b.displaycustomer();
			else
				break;
			
			}while(cuschoice != 4);
		}
		else if(user_choice == 2)
		{
			int adminchoice;
			System.out.println("Enter the your Admin_Id : ");
			String ADMIN_ID = "admin";//sc.next();
			System.out.println("Enter the your Admin_Password : ");
			String PASSWORD = "12345";//sc.next();
			if(ADMIN_ID.equalsIgnoreCase("admin") && PASSWORD.equalsIgnoreCase("12345"))
			{
				do
				{
					System.out.println("----------ADMIN------------");
					System.out.println("|	|1| ADD NEW BUS        ");
					System.out.println("|	|2| DELETE BUS         ");
					System.out.println("|	|3| DISPLAY ALL BUS    ");
					System.out.println("|   |4| EDIT BUS DETAILS   ");
					System.out.println("|   |5| VIEW SELLED TICKET ");
					System.out.println("|	|6| EXIT               ");
					System.out.println("---------------------------");
					System.out.println("Enter your choice");
					adminchoice = sc.nextInt();
				
					if(adminchoice == 1)
						b.addnewbus();
					else if(adminchoice == 2)
						b.deletebus();
					else if(adminchoice == 3)
						b.displaybus();
					else if(adminchoice == 4)
						b.editbusdetails();
					else if(adminchoice == 5)
						b.adminview();
					else
						break;
				}while(adminchoice != 6);
			}
			else
			{
				System.out.println("Invaild User!!!!");
			}
		}
		else
		{
			System.out.println("Invalid Option");
		}
	}
}

