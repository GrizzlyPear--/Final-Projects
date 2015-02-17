using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;


namespace TicketLibrary
{
    class EZTicketManager
    {       

            //Get All Tickets
            public List<Ticket> getAllTickets(){

            List<Ticket> tickets = new List<Ticket>();

            SqlConnection con = new SqlConnection(Connections.ConnectionString());

            SqlCommand cmd = new SqlCommand();

            cmd.Connection = con;

                        //The @ allows for line breaks in a sql statement.
            cmd.CommandText = @"SELECT TicketNumber, Tickets.EmployeeNumber, Building, Description, 
                            AssignedTo, FirstName, LastName from Tickets,Employees where Tickets.EmployeeNumber=Employees.EmployeeNumber";


            SqlDataReader reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                Employee e = new Employee(Convert.ToInt32(reader["EmployeeNumber"].ToString()),
                                            reader["FirstName"].ToString(), reader["LastName"].ToString());

                Employee m = getEmployeeByID(Convert.ToInt32(reader["AssignedTo"].ToString()));

                Ticket t = new Ticket(Convert.ToInt32(reader["TicketNumber"].ToString()),
                                                        reader["Building"].ToString(),
                                                        reader["Description"].ToString(), e, m);

                tickets.Add(t);
            }                            
            
            con.Close();
            return tickets;
        }


            //Get By Employee Number
            public Employee getEmployeeByID(int id)
            {


                Employee m = null;


                SqlConnection con = new SqlConnection(Connections.ConnectionString());

                SqlCommand cmd = new SqlCommand();

                cmd.Connection = con;

                cmd.CommandText = @"SELECT FirstName, LastName from Employees where EmployeeNumber=@EmployeeNumber";

                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    m = new Employee(id, reader["FirstName"].ToString(), reader["LastName"].ToString());
                }
                return m;
            }

           

            //Insert a Ticket
            public void insertTicket( Ticket newTicket)
            {         

                SqlConnection con = new SqlConnection(Connections.ConnectionString());
                SqlCommand cmd = new SqlCommand();

                cmd.Connection = con;

                cmd.CommandText = @"INSERT INTO Tickets (TicketNumber, Tickets.EmployeeNumber, Building, Description, 
                                AssignedTo, FirstName, LastName from Tickets,Employees where 
                                Tickets.EmployeeNumber=Employees.EmployeeNumber) 
                                VALUES (@ticketNumber, @employeeNumber, @building, @description, @assignedTo, @firstName, @lastName)";
                
                cmd.Parameters.AddWithValue("@ticketNumber", newTicket.Id);
                cmd.Parameters.AddWithValue("@employeeNumber", newTicket.SubmittedBy);
                cmd.Parameters.AddWithValue("@building", newTicket.Building);
                cmd.Parameters.AddWithValue("@description", newTicket.Description);
                con.Open();
                cmd.ExecuteNonQuery();
                con.Close();       

            }
           

}
}

