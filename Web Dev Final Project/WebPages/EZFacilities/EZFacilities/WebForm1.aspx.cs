using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TicketLibrary;

namespace EZFacilities
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            EZTicketManager tm = new EZTicketManager();
            GridView1.DataSource = tm.getAllTickets();
            GridView1.DataBind();

        }
    }
}