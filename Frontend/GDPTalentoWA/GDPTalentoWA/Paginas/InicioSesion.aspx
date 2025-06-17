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
<body class="login-page">
  <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card p-4 login-card" style="width:100%; max-width:450px;">
      
      <!-- Logo -->
      <div class="login-avatar mb-3"></div>
      
      <!-- Títulos -->
      <h2 class="text-center fw-bold mb-1">GDP Talento</h2>
      <p class="subtitle text-center mb-4">Portal de Gestión Integral de Miembros</p>
      
      <!-- Formulario -->
      <form id="formLogin" runat="server">
        <div class="mb-3">
          <label for="txtCodigo" class="form-label">Correo electrónico</label>
          <asp:TextBox 
            ID="txtCodigo" runat="server" 
            CssClass="form-control" 
            placeholder="correo@gdp.com" />
        </div>
        
        <div class="mb-3 position-relative">
          <label for="txtPassword" class="form-label">Contraseña</label>
          <a href="#" class="forgot-link position-absolute end-0">¿Olvidaste tu contraseña?</a>
          <asp:TextBox 
            ID="txtPassword" runat="server" 
            TextMode="Password" 
            CssClass="form-control" 
            placeholder="" />
        </div>
        
        <div class="mb-2">
          <asp:Label 
            ID="lblMensaje" runat="server" 
            CssClass="text-danger text-center d-block" 
            EnableViewState="false" />
        </div>
        
        <div class="d-grid mt-3">
          <asp:Button 
            ID="btnLogin" runat="server" 
            CssClass="btn btn-primary" 
            Text="Iniciar sesión" 
            OnClick="btnLogin_Click"/>
        </div>
      </form>
      
      <!-- Nota prototipo -->
      <div class="login-note mt-4">
        Para el prototipo, puedes usar:<br>
        1<br>
        Contraseña: <strong>hash12345</strong>
      </div>
    </div>
  </div>
</body>
</html>
