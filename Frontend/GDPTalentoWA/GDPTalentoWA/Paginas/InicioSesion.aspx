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
          <a href="javascript:void(0)" class="forgot-link position-absolute end-0 text-decoration-none text-primary">¿Olvidaste tu contraseña?</a>
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
    <!-- Modal: Recuperar Contraseña -->
    <div class="modal fade" id="recuperarModal" tabindex="-1" aria-labelledby="recuperarModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content rounded-4">
          <div class="modal-header border-0">
            <h5 class="modal-title" id="recuperarModalLabel"></h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
          </div>
          <div class="modal-body text-center">
            <div class="mb-3">
              <div class="bg-primary bg-opacity-10 rounded-circle d-inline-flex align-items-center justify-content-center mb-3" 
                 style="width:56px;height:56px;">
                    <i class="fa-regular fa-envelope fs-3 text-primary"></i>
              </div>
            </div>
            <h5 class="fw-bold">Recuperar Contraseña</h5>
            <p class="mb-4 text-muted">Para recuperar tu contraseña, por favor contacta con la administración.</p>
            <div class="d-flex align-items-start rounded-3 p-3 mb-0 bg-primary bg-opacity-10">
              <i class="fa-regular fa-envelope fs-4 text-primary"></i>
              <div class="ms-3 text-start">
                <small class="d-block text-dark fw-bold">Correo de contacto:</small>
                <a href="mailto:gamedevspucp@gmail.com"
                   class="fw-semibold text-primary d-block">
                  gamedevspucp@gmail.com
                </a>
              </div>
            </div>

          </div>
          <div class="modal-footer border-0 justify-content-center">
            <button type="button" class="btn btn-primary px-4" data-bs-dismiss="modal">Entendido</button>
          </div>
        </div>
      </div>
    </div>
    
    <script>
      document.querySelector('.forgot-link').addEventListener('click', function(e){
        e.preventDefault();
        var modalEl = document.getElementById('recuperarModal');
        var modal = new bootstrap.Modal(modalEl);
        modal.show();
      });
    </script>

</body>
</html>
