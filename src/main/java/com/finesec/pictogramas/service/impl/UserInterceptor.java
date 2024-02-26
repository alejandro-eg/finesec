package com.finesec.pictogramas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.finesec.pictogramas.model.Usuarios;
import com.finesec.pictogramas.service.IUsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private IUsuarioService servicioUsuarios;
    private Usuarios nuevoUsuario;
	
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Pre-handle logic if needed
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            // Obtener la autenticación actual
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // Obtener el email del usuario autenticado
            String emailUsuarioActual = authentication.getName();
            // Buscar al usuario por su correo electrónico
            Usuarios usuario = servicioUsuarios.findByEmail(emailUsuarioActual);
            // Agregar el usuario al modelo
            modelAndView.addObject("usuario", usuario);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // After completion logic if needed
    }
}
