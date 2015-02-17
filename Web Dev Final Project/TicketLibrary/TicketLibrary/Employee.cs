using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicketLibrary
{
    public class Employee
    {
     
        public int EmployeeId { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }

        public Employee() { }

        public Employee(int id, string firstName, string lastName)
        
    {
        EmployeeId = id;
        FirstName = firstName;
        LastName = lastName;
    }
    }
}
