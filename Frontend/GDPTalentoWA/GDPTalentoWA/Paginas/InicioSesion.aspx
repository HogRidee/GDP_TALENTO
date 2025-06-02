<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="InicioSesion.aspx.cs" Inherits="GDPTalentoWA.Paginas.InicioSesion" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link href="../Content/bootstrap.css" rel="stylesheet" />
    <link href="../Content/Fonts/css/all.css" rel="stylesheet" />
    <link href="../Content/site.css" rel="stylesheet" />

    <script src="../Scripts/bootstrap.js"></script>
    <script src="../Scripts/jquery-3.7.1.js"></script>
    <script src="../Scripts/bootstrap.bundle.js"></script>



    <title> Inicio de sesion</title>
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="card p-4" style="width: 100%; max-width: 400px;">
            <h3 class="card-title text-center mb-4">Iniciar Sesion<br>GDP - RRHH</h3>
            <img src="../Imagenes/LOGO_GDP.png" alt="Logo GDP" width="150" height="auto"  class="d-block mx-auto mb-3">
            <form id="formLogin" runat="server">
                <div class="form-group">
                    <label for="txtUsername">Usuario</label>
                    <asp:TextBox ID="txtCodigo" runat="server" CssClass="form-control" placeholder="Codigo PUCP"></asp:TextBox>
                </div>
                <div class="form-group mt-3">
                    <label for="txtPassword">Contraseña</label>
                    <asp:TextBox ID="txtPassword" runat="server" TextMode="Password" CssClass="form-control" placeholder="Contraseña"></asp:TextBox>
                </div>
                <div class="form-group mt-2">
                    <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger text-center d-block" EnableViewState="false"></asp:Label>
                </div>
                <div class="text-center mt-3">
                    <asp:Button ID="btnLogin" runat="server" Text="Ingresar" CssClass="btn btn-primary w-100" OnClick="btnLogin_Click"/>
                </div>
            </form>
            <div class="text-center mt-3">
                <small>¿Olvidaste tu contraseña? <a href="#">Recuperar</a></small>
            </div>
        </div>
    </div>
</body>
</html>
