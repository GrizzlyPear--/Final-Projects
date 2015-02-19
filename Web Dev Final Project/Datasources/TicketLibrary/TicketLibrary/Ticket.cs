using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicketLibrary
{
    public class Ticket
    {
        private int p;

        public int Id { get; set; }
        public string Building { get; set; }
        public string Description { get; set; }
        public string Status { get; set; }


        public Employee SubmittedBy { get; set; }
        public Employee AssignedTo { get; set; }


       

        public Ticket(int id, string building, string description, string status, Employee submittedBy, Employee assignedTo)
        {
            Id = id;
            Building = building;
            Description = description;
            SubmittedBy = submittedBy;
            AssignedTo = assignedTo;

        }

        public Ticket(int Id)
        {
            // TODO: Complete member initialization
            this.Id = Id;
        }

    }

}