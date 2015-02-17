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
            EZTicketManager rd = new EZTicketManager();
            rd.insertTicket(newTicket);
        }
    }
}
