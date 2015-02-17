using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicketLibrary
{
    public class Connections
    {
        private static readonly string connectionString;

        static Connections()
        {
            connectionString = "Data Source=bisiisdev;Initial Catalog=EZTicket13;User ID=bisstudent;Password=bobby2013";
        }

        public static string ConnectionString()
        {
            return connectionString;
        }


    }
}
