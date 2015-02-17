using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicketLibrary
{
    public class Ticket
    {
        public int Id { get; set; }
        public string Building { get; set; }
        public string Description { get; set; }


        public Employee SubmittedBy { get; set; }
        public Employee AssignedTo { get; set; }


       

        public Ticket(int id, string building, string description, Employee submittedBy, Employee assignedTo)
        {
            Id = id;
            Building = building;
            Description = description;
            SubmittedBy = submittedBy;
            AssignedTo = assignedTo;

        }

    }

}