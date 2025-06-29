﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using GDPTalentoWA.ServicioWeb;

namespace GDPTalentoWA.Paginas
{
    public partial class Inicio : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                using (var client = new InicioWSClient())
                {
                    // TOTALES 
                    var datos = client.devolverTotales();
                    litTotalMiembros.Text = datos.Length > 0 ? datos[0]?.ToString() : "0";
                    litPostulantesActivos.Text = datos.Length > 1 ? datos[1]?.ToString() : "0";
                    litEventosProximos.Text = datos.Length > 2 ? datos[2]?.ToString() : "0";
                    litTareasPendientes.Text = datos.Length > 3 ? datos[3]?.ToString() : "0";

                    // VARIACION MIEMBROS
                    int variacionMiembros = client.devolverVariacionMiembros();
                    litVarMiembros.Text = FormatDiff(variacionMiembros);

                    // PROXIMA ENTREVISTA
                    string prox = client.devolverProximaEntrevista();
                    litProximaEntrevista.Text = string.IsNullOrEmpty(prox) ? "Sin citas programadas": prox;

                    // EVENTOS PRÓXIMOS
                    var evRes = client.devolverEventosResumen();
                    litEventosProximos.Text = evRes.Length > 0 ? evRes[0] : "0";
                    if (evRes.Length > 1 && !string.IsNullOrWhiteSpace(evRes[1]))
                    {
                        litEventosProxDesc.Text = evRes[1];
                        smEventosProxDesc.Attributes["class"] = "text-success";
                    }
                    else
                    {
                        litEventosProxDesc.Text = "Sin eventos próximos";
                        smEventosProxDesc.Attributes["class"] = "text-muted";
                    }

                    // TAREAS PENDIENTES
                    var taRes = client.devolverTareasResumen();
                    litTareasPendientes.Text = taRes.Length > 0 ? taRes[0] : "0";
                    litTareasDesc.Text = taRes.Length > 1 ? taRes[1] : "";
                }

            }
        }

        private string FormatDiff(int diff)
        {
            if (diff > 0) return $"+{diff} desde el mes pasado";
            if (diff < 0) return $"{diff} desde el mes pasado";
            return "0 desde el mes pasado";
        }
    }
}