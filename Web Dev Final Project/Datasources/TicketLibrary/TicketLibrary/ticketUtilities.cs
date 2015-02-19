using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicketLibrary
{
    class ticketUtilities
    {

        public void InsertTicket(Ticket newTicket)
        {
            EZTicketManager ez = new EZTicketManager();
            ez.insertTicket(newTicket);
        }

        public Employee getEmployeeByID(int id)
        {
            EZTicketManager ez = new EZTicketManager();
            return ez.getEmployeeByID(id);

        }
    }
}
