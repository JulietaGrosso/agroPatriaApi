package com.agropatriaapp.agropatriaapi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.CuentasDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Cuentas;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.repositorios.CuentasRepositorio;
import com.agropatriaapp.agropatriaapi.utils.EncryptUtils;


@Service
public class CuentasService implements UserDetailsService {

    @Autowired
    private CuentasRepositorio cuentasRepositorio;

     public List<Cuentas> getCuentas(){
        return cuentasRepositorio.findAll();
    }

     public Response postRegistro(CuentasDto cuentasDto){
        Optional<Cuentas> cuentasOp = cuentasRepositorio.findByCorreo(cuentasDto.getCorreo());
        if(cuentasOp.isPresent()){
            return new Response(false, "Cuenta Existente: Inicie sesión");
        }else{
            Cuentas cuentas = new Cuentas();
            String contrasenaEncript = EncryptUtils.encriptarContrasena(cuentasDto.getContrasena());
            cuentas.setCorreo(cuentasDto.getCorreo());
            cuentas.setContrasena(contrasenaEncript);
            cuentasRepositorio.save(cuentas);
            return new Response(true, "Agregado Correctamente");
        }
     }

     public Response postLogin(CuentasDto cuentasDto) throws NotFoundEntityException{
        Optional<Cuentas> cuentasOp = cuentasRepositorio.findByCorreo(cuentasDto.getCorreo());
        if(cuentasOp.isPresent()){
            String contrasena = cuentasDto.getContrasena();
            String contraDB = cuentasOp.get().getContrasena();
            if(EncryptUtils.verificarContrasena(contrasena, contraDB)){
                String token = JwtService.generateToken(cuentasOp.get().getId());
                return new Response(true, token);
            }
            throw new NotFoundEntityException("Contraseña incorrecta");
        }
        throw new NotFoundEntityException("Usuario no encontrado");
     }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cuentas usuario = cuentasRepositorio.findById(Integer.valueOf(username))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Devuelve el User de Spring Security
        return org.springframework.security.core.userdetails.User
                .withUsername(String.valueOf(usuario.getId()))
                .password("")
                .authorities(Collections.emptyList())
                .build();
    }




}
