/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.seguridad.action;

/**
 *
 * @author jjacobo
 */

import com.bmp.sigeca.mantenimiento.service.TrabajadorService;
import com.bmp.sigeca.registro.bean.UsuarioBean;
import com.bmp.sigeca.registro.service.FichaService;
import com.bmp.sigeca.resource.Properties;
import com.bmp.sigeca.seguridad.service.UsuarioService;
import com.bmp.sigeca.util.StringUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

public class RegistroUsuarioAction extends DispatchAction{

    protected Logger logg;
    
    public ActionForward irLoguin(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {
        
        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en LoginAction metodo inicio");
                
        return actionMapping.findForward("inicio");
    }
    
     public ActionForward registrarUsuario(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {
              
        String id_trabajador = "";
        String dni = "";
        String clave = "";
        String correo = "";
        String perfil = "";
        String fecha_cese = "";
        String id_est_usu_tra = "";
        String answercaptcha = "";
        String captcha = "";
        int result = 0;
        String destino = "inicio";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            id_trabajador = StringUtil.nullAsEmptyString((String)map.get("id_trabajador")).trim();
            perfil = StringUtil.nullAsEmptyString((String)map.get("id_perfil")).trim();
            fecha_cese = StringUtil.nullAsEmptyString((String)map.get("fecha_cese")).trim();
            id_est_usu_tra = StringUtil.nullAsEmptyString((String)map.get("id_est_usu_tra")).trim();
            answercaptcha = StringUtil.nullAsEmptyString((String)map.get("captcha")).trim();
            captcha = (String)session.getAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
            
            ActionMessages msgs = new ActionMessages();

            if("".equals(id_trabajador)){
                session.removeAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
                msgs.add("ingreso",new ActionMessage("msg.ingreso.trabajadorVacio"));
                destino = "registroUsuario";
            }else if("".equals(perfil)){
                session.removeAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
                msgs.add("ingreso",new ActionMessage("msg.ingreso.perfilVacio"));
                destino = "registroUsuario";
            }else if("".equals(fecha_cese)){
                session.removeAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
                msgs.add("ingreso",new ActionMessage("msg.ingreso.fechaVacio"));
                destino = "registroUsuario";
            }else if("".equals(id_est_usu_tra)){
                session.removeAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
                msgs.add("ingreso",new ActionMessage("msg.ingreso.estadoVacio"));
                destino = "registroUsuario";
            }
            if(msgs.isEmpty() && !captcha.equals(answercaptcha)) {
                session.removeAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
                msgs.add("ingreso",new ActionMessage("msg.ingreso.captcha"));
                destino = "registroUsuario";
            }

            UsuarioService usuarioservice = new UsuarioService();

            if(msgs.isEmpty()){
                HashMap mapa = new HashMap();
                if(usuarioservice.existeUsuarioByTrabajador(mapa)){
                    session.removeAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
                    msgs.add("ingreso",new ActionMessage("msg.ingreso.userExist"));
                    destino = "registroUsuario";
                }else{
                    clave = generarClave();
                    long pk = usuarioservice.getNextPK();
                    map.put("id_usuario", Properties.concatLeftCharacter("0", 3, Long.toString(pk)));
                    map.put("clave", clave);
                    TrabajadorService service = new TrabajadorService();
                    HashMap trabajador = service.obtenerTrabajadorByPK(map);

                    map.put("usuario", (String)trabajador.get("dni"));

                    result = usuarioservice.registrarUsuario(map);

                    if(result>0){
                        msgs.add("ingreso",new ActionMessage("msg.ingreso.saveOk"));
                        correo = (String)trabajador.get("email");
                        dni = (String)trabajador.get("dni");
                        enviarCorreo(dni,clave,correo);
                        destino = "menuAdministrador";
                    }else{
                        msgs.add("ingreso",new ActionMessage("msg.ingreso.error"));
                        destino = "registroUsuario";
                    }
                }
            }
            saveErrors(request,msgs);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        
        return actionMapping.findForward(destino);
    }

    public ActionForward enviarClave(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        String dni = "";
        String answercaptcha = "";
        String captcha = "";
        String destino = "inicio";
        try{
            HttpSession session = request.getSession();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            ActionMessages msgs = new ActionMessages();

            dni = StringUtil.nullAsEmptyString((String)map.get("dni")).trim();
            answercaptcha = StringUtil.nullAsEmptyString((String)map.get("captcha")).trim();
            captcha = (String)session.getAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
            
            if("".equals(dni)){
                session.removeAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
                msgs.add("clave",new ActionMessage("msg.clave.dniVacio"));
                destino = "clave";
            }
            if(msgs.isEmpty() && !captcha.equals(answercaptcha)) {
                session.removeAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
                msgs.add("clave",new ActionMessage("msg.clave.captcha"));
                destino = "clave";
            }

            UsuarioService usuarioservice = new UsuarioService();

            if(msgs.isEmpty()){
                HashMap mapa = new HashMap();
                mapa.put("loguin", dni);
                UsuarioBean usuario = usuarioservice.getUsuarioByPK(mapa);
                if(usuario==null){
                    session.removeAttribute(nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY);
                    msgs.add("clave",new ActionMessage("msg.clave.userNoExist"));
                    destino = "clave";
                }else{
                    enviarCorreo(usuario.getDni(),usuario.getClave(),usuario.getEmail());
                    destino = "inicio";
                }
            }
            saveErrors(request,msgs);

        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        return actionMapping.findForward(destino);
    }
    
    public void enviarCorreo(String user, String clave, String correo){
        
        String mailServer = "";
        String portMail = "";
        String userMail = "";
        String pwdMail = "";
        String useAuth = "";
        String useSSL = "";
        String from = "";
        String asunto = "";
        String body = "";
        try{
            ResourceBundle bundle = ResourceBundle.getBundle(Properties.getMailProperties());
            mailServer = bundle.getString("mailServer");
            portMail = bundle.getString("portMail");
            userMail = bundle.getString("userMail");
            pwdMail = bundle.getString("pwdMail");
            useAuth = bundle.getString("useAuth");
            useSSL = bundle.getString("useSSL");
            from = bundle.getString("from");
            asunto = bundle.getString("subject");
            
            MimeMultipart multipart = new MimeMultipart();
            java.util.Properties props = new java.util.Properties();
            props.setProperty("mail.smtp.host", mailServer);
            props.setProperty("mail.smtp.port", portMail);
            props.setProperty("mail.smtp.starttls.enable", useSSL);
            props.setProperty("mail.smtp.user", userMail);
            props.setProperty("mail.smtp.auth", useAuth);


            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            msg.setSubject(asunto);
            msg.setSentDate(new Date());
            body = "<strong>Su clave de acceso al Sistema de Gestión Catastral (SIGECA), es:</strong><br><br>" +
                    "<strong>Usuario: </strong>"+user+"<br>"+
                    "<strong>Clave: </strong>"+clave+"<br>"+
                    "Atentamente,<br><br>"+
                    "BMP Geomática S.A.";

            // BODY 
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(body, "text/html");

            multipart.addBodyPart(mbp);
            msg.setContent(multipart);

            //Transport.send(msg);
            Transport t = session.getTransport("smtp");
            t.connect(userMail,pwdMail);
            t.sendMessage(msg, msg.getAllRecipients());
            
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    public ActionForward cambiarClave(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        String clave = "";
        String claveActual = "";
        String destino = "";
        try{
            HttpSession session = request.getSession();
            UsuarioBean usuario = session.getAttribute("usuarioBean")!=null?(UsuarioBean)session.getAttribute("usuarioBean"):new UsuarioBean();
            DynaActionForm form = (DynaActionForm)actionForm;
            HashMap map = (HashMap)form.getMap();

            clave = (String)map.get("clave");
            claveActual = (String)map.get("claveActual");

            ActionMessages msgs = new ActionMessages();

            if(msgs.isEmpty()){
                if(claveActual.equals(usuario.getClave())){
                    HashMap mapCambio = new HashMap();
                    mapCambio.put("clave", clave);
                    mapCambio.put("id_usuario", usuario.getCodUsuario());

                    UsuarioService usuarioservice = new UsuarioService();
                    int result = usuarioservice.modificarUsuario(mapCambio);

                    if(result!=0){
                        enviarCorreo(usuario.getDni().trim(),clave,usuario.getEmail().trim());
                        msgs.add("clave",new ActionMessage("msg.cambioClave.CambioClaveOK"));
                        destino = "cambiarClave";
                    }else{
                        msgs.add("clave",new ActionMessage("msg.cambioClave.ErrorModificarUsuario"));
                        destino = "cambiarClave";
                    }
                }else{
                    msgs.add("clave",new ActionMessage("msg.cambioClave.confirmaClaveNuevaIncorrecta"));
                    destino = "cambiarClave";
                }
            }
            saveErrors(request,msgs);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }
    
    public String generarClave(){
        String clave = null;
        int max = 0;
        int min = 0;
        int numeroRandom = 0;
        int asciiRandom = 0;
        String letraRandom = null;
        byte[] bytes = new byte[3];
        try{
            max = 99999;
            min = 10000;
            numeroRandom = (int) Math.floor(Math.random()*(max-min+1)+min);
            
            max = 122;
            min = 97;            
            for(int i=0;i<3;i++){
                asciiRandom = (int) Math.floor(Math.random()*(max-min+1)+min);            
                bytes[i] = (byte)asciiRandom;
            }
            letraRandom = new String(bytes);            
            clave = letraRandom.concat(Integer.toString(numeroRandom));            
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return clave;
    }

    /**
     * Método que deriva a la página de Búsqueda.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irBusqueda(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en RegistroUsuarioAction metodo irBusqueda");

        String destino = "";
        List lista = null;
        try{
            HttpSession session = request.getSession(false);
            UsuarioBean usuario = session.getAttribute("usuarioBean")!=null?(UsuarioBean)session.getAttribute("usuarioBean"):null;

            if(session!=null){
                if(usuario!=null){
                    FichaService fichaService = new FichaService();
                    lista = fichaService.buscarFichas(new HashMap());
                    if(lista!=null && !lista.isEmpty()){
                        for(int i=0;i<lista.size();i++){
                            String num = Properties.concatLeftCharacter("0", 7, (String)((HashMap)lista.get(i)).get("numFicha"));
                            ((HashMap)lista.get(i)).put("numFicha", num);
                        }
                    }
                    session.setAttribute("listaFicha", lista);

                    destino = "busqueda";
                }else{
                    destino = "noSession";
                }
            }else{
                destino = "noSession";
            }

            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }

    /**
     * Método que deriva a la página de Búsqueda.
     * @param mapping The ActionMapping used to select this instance.
     * @param actionForm The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward irInicio(ActionMapping actionMapping,
        ActionForm actionForm,
        HttpServletRequest request,
        HttpServletResponse response) throws java.lang.Exception {

        logg = Logger.getLogger(this.getClass());
        logg.debug(" Log en RegistroUsuarioAction metodo irInicio");

        String destino = "inicio";
        try{
            HttpSession session = request.getSession(false);
            session.removeAttribute("mapFicha");
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return actionMapping.findForward(destino);
    }
}