using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;


namespace TicketLibrary
{
    public class EZTicketManager
    {       

            //Get All Tickets
            public List<Ticket> getAllTickets(){

            List<Ticket> tickets = new List<Ticket>();

            SqlConnection con = new SqlConnection(Connections.ConnectionString());

            SqlCommand cmd = new SqlCommand();

            cmd.Connection = con;

                        //The @ allows for line breaks in a sql statement.
            cmd.CommandText = @"SELECT TicketNumber, Tickets.EmployeeNumber, DateSubmitted, Building, Description, Status, 
                            AssignedTo, FirstName, LastName from Tickets,Employees where Tickets.EmployeeNumber=Employees.EmployeeNumber";

            con.Open();



            SqlDataReader reader = cmd.ExecuteReader();
            
            while (reader.Read())
            {
                Employee e = new Employee(Convert.ToInt32(reader["EmployeeNumber"].ToString()),
                                            reader["FirstName"].ToString(), reader["LastName"].ToString());

                Employee m = getEmployeeByID(Convert.ToInt32(reader["AssignedTo"].ToString()));

                Ticket t = new Ticket(Convert.ToInt32(reader["TicketNumber"].ToString()),
                                                        reader["Building"].ToString(),
                                                        reader["Description"].ToString(),
                                                        reader["Status"].ToString(),e, m);

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


                cmd.Parameters.AddWithValue("EmployeeNumber", id);
                con.Open();

                SqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    m = new Employee(id, reader["FirstName"].ToString(), reader["LastName"].ToString());
                }
                return m;
            }
        
            //Insert a Ticket
            public int insertTicket(Ticket newTicket)
            {         

                SqlConnection con = new SqlConnection(Connections.ConnectionString());
                SqlCommand cmd = new SqlCommand();

                cmd.Connection = con;

                cmd.CommandText = @"INSERT INTO Tickets (TicketNumber, Tickets.EmployeeNumber, Building, Description, 
                                AssignedTo, FirstName, LastName from Tickets,Employees where 
                                Tickets.EmployeeNumber=Employees.EmployeeNumber) 
                                VALUES (@ticketNumber, @employeeNumber, @building, @description, @assignedTo, @firstName, @lastName);SELECT SCOPE_IDENTITY";
                
                cmd.Parameters.AddWithValue("@ticketNumber", newTicket.Id);
                cmd.Parameters.AddWithValue("@employeeNumber", newTicket.SubmittedBy);
                cmd.Parameters.AddWithValue("@building", newTicket.Building);
                cmd.Parameters.AddWithValue("@description", newTicket.Description);
                con.Open();

                //This returns the result of the second, trailing select statement. Finds the "Pin" or TicketNumber
                int key = Convert.ToInt32(cmd.ExecuteScalar());
                con.Close();


                //return a 'pin'....?
                return key;
            }
           
            //Get Tickets By Status
            public List<Ticket> getTicketsByStatus(string status ){
                List<Ticket> tickets = new List<Ticket>();

                SqlConnection con = new SqlConnection(Connections.ConnectionString());

                SqlCommand cmd = new SqlCommand();

                cmd.Connection = con;
                
                //The @ allows for line breaks in a sql statement.
                cmd.CommandText = @"SELECT TicketNumber, Tickets.EmployeeNumber, Building, Description, 
                            AssignedTo, FirstName, LastName from Tickets,Employees WHERE Tickets.Status=@status";

                cmd.Parameters.AddWithValue("status", status);
                con.Open();

                SqlDataReader reader = cmd.ExecuteReader();
                
                while (reader.Read())
                {
                    Employee e = new Employee(Convert.ToInt32(reader["EmployeeNumber"].ToString()),
                                                reader["FirstName"].ToString(), reader["LastName"].ToString());

                    Employee m = getEmployeeByID(Convert.ToInt32(reader["AssignedTo"].ToString()));

                    Ticket t = new Ticket(Convert.ToInt32(reader["TicketNumber"].ToString()),
                                                            reader["Building"].ToString(),
                                                            reader["Description"].ToString(),
                                                            reader["Status"].ToString(), e, m);

                    tickets.Add(t);
                }
                con.Close();
                
                
                //return Tickets
                return tickets;

            }
        
            //Get Tickets By Employee
            public List<Ticket> getTicketsByEmployee(Employee emp){

                List<Ticket> tickets = new List<Ticket>();

                SqlConnection con = new SqlConnection(Connections.ConnectionString());

                SqlCommand cmd = new SqlCommand();

                cmd.Connection = con;

                //The @ allows for line breaks in a sql statement.
                cmd.CommandText = @"SELECT TicketNumber, Tickets.EmployeeNumber, Building, Description, 
                                    AssignedTo, FirstName, LastName from Tickets,Employees WHERE 
                                            Tickets.AssignedTo=@emp";

                cmd.Parameters.AddWithValue("emp", emp);

                con.Open();
                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Employee e = new Employee(Convert.ToInt32(reader["EmployeeNumber"].ToString()),
                                                reader["FirstName"].ToString(), reader["LastName"].ToString());

                    Employee m = getEmployeeByID(Convert.ToInt32(reader["AssignedTo"].ToString()));

                    Ticket t = new Ticket(Convert.ToInt32(reader["TicketNumber"].ToString()),
                                                            reader["Building"].ToString(),
                                                            reader["Description"].ToString(),
                                                            reader["Status"].ToString(), e, m);

                    tickets.Add(t);
                }
                con.Close();


                //return Tickets
                return tickets;

            }

            //Get Ticket's By PIN
            public List<Ticket> getTicketsByPin(string pin)
            {
                List<Ticket> tickets = new List<Ticket>();

                SqlConnection con = new SqlConnection(Connections.ConnectionString());

                SqlCommand cmd = new SqlCommand();

                cmd.Connection = con;

                //The @ allows for line breaks in a sql statement.
                cmd.CommandText = @"SELECT TicketNumber, Tickets.EmployeeNumber, Building, Description, 
                            AssignedTo, FirstName, LastName from Tickets,Employees WHERE TicketNumber=@ticketNumber";


                cmd.Parameters.AddWithValue("ticketNumber", pin);

                con.Open();

                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Employee e = new Employee(Convert.ToInt32(reader["EmployeeNumber"].ToString()),
                                                reader["FirstName"].ToString(), reader["LastName"].ToString());

                    Employee m = getEmployeeByID(Convert.ToInt32(reader["AssignedTo"].ToString()));

                    Ticket t = new Ticket(Convert.ToInt32(reader["TicketNumber"].ToString()),
                                                            reader["Building"].ToString(),
                                                            reader["Description"].ToString(),
                                                            reader["Status"].ToString(), e, m);

                    tickets.Add(t);
                }
                con.Close();


                //return Tickets
                return tickets;

            }
            
            //Get PIN's by Email Address
            public List<Ticket> SelectAllPinByEmail(string email)
         {
            List<Ticket> ticket = new List<Ticket>();

             string sql = @"SELECT ticket.TicketNumber From Tickets ticket, Employees emp Where 
                ticket.EmployeeNumber=emp.EmployeeNumber AND emp.Email=@Email ";

             SqlConnection con = new SqlConnection(Connections.ConnectionString());
             SqlCommand cmd = new SqlCommand(sql, con);
             //cmd.Connection = con;
             cmd.Parameters.AddWithValue("@Email", email);

             con.Open();
             SqlDataReader reader = cmd.ExecuteReader();
             while (reader.Read())
             {
                 Ticket t = new Ticket(Convert.ToInt32(reader["TicketNumber"]));

                 ticket.Add(t);
                 
             }
             con.Close();

             return ticket;

         }

           

}
}

