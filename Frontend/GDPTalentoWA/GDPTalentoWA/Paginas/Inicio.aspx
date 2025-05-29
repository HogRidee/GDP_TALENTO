<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Inicio.aspx.cs" Inherits="GDPTalentoWA.Inicio" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Inicio</title>
</head>
<body>
    <h1>Página de Inicio</h1>
    <form id="formularioInicio" runat="server">
        <div>
            <asp:Label ID="labelNombre" runat="server" Text="Ingrese el nombre:"></asp:Label>
            <asp:TextBox ID="textoNombre" runat="server"></asp:TextBox>
        </div>
    </form>
</body>
</html>
