﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;


namespace GDPTalentoWA.Paginas
{
    public partial class InicioSesion : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            try {
            lblMensaje.Text = "";
            int codigo = Convert.ToInt32(txtCodigo.Text);
            String contrasenha = Convert.ToString(txtPassword.Text);
            ServicioWeb.UsuarioWSClient objservicio = new ServicioWeb.UsuarioWSClient();
            
            int tipo = objservicio.verificar(codigo, contrasenha);
            if (tipo!=0)
            {
                FormsAuthenticationTicket tkt;
                String cookiestr;
                HttpCookie ck;
                Session["id"]=codigo;
                tkt = new FormsAuthenticationTicket(1, Convert.ToString(codigo), DateTime.Now, DateTime.Now.AddMinutes(45), true, "datos adicionales");
                cookiestr = FormsAuthentication.Encrypt(tkt);
                ck = new HttpCookie(FormsAuthentication.FormsCookieName, cookiestr);
                ck.Expires = tkt.Expiration;
                ck.Path = FormsAuthentication.FormsCookiePath;
                Response.Cookies.Add(ck);
                string strRedirect;
                strRedirect = Request["ReturnUrl"];
                if (strRedirect == null)
                    Response.Redirect("Inicio.aspx");
                Response.Redirect(strRedirect, true);
            }
            else
            {
                lblMensaje.Text = "El codigo o contraseña que ingresó con incorrectos <br />Vuelva a intentarlo o comuniquese con la administración";
            }
            }
            catch(Exception ex)
            {
                lblMensaje.Text = "Error: " + ex.Message;
            }
        }
    }
}