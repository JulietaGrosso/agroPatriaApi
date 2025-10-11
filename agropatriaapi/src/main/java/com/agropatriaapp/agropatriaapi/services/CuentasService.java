package com.agropatriaapp.agropatriaapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.CuentasDto;
import com.agropatriaapp.agropatriaapi.dto.MyInfoDto;
import com.agropatriaapp.agropatriaapi.dto.ResetPasswordDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Cuentas;
import com.agropatriaapp.agropatriaapi.model.Pagos;
import com.agropatriaapp.agropatriaapi.model.Planes;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.Vendedor;
import com.agropatriaapp.agropatriaapi.repositorios.CuentasRepositorio;
import com.agropatriaapp.agropatriaapi.utils.AuthUtil;
import com.agropatriaapp.agropatriaapi.utils.EncryptUtils;

@Service
public class CuentasService implements UserDetailsService {

    @Autowired
    private CuentasRepositorio cuentasRepositorio;

    @Autowired
    private VendedorService vendedorService;

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PlanService planService;

    public List<Cuentas> getCuentas() {
        return cuentasRepositorio.findAll();
    }

    public Response postRegistro(CuentasDto cuentasDto) {
        Optional<Cuentas> cuentasOp = cuentasRepositorio.findByCorreo(cuentasDto.getCorreo());
        if (cuentasOp.isPresent()) {
            return new Response(false, "Cuenta Existente: Inicie sesión");
        } else {
            Cuentas cuentas = new Cuentas();
            String contrasenaEncript = EncryptUtils.encriptarContrasena(cuentasDto.getContrasena());
            cuentas.setCorreo(cuentasDto.getCorreo());
            cuentas.setContrasena(contrasenaEncript);
            cuentas.setAdmin(false);
            cuentas.setActivo(true);
            cuentasRepositorio.save(cuentas);
            return new Response(true, "Agregado Correctamente");
        }
    }

    public Response postLogin(CuentasDto cuentasDto) throws NotFoundEntityException {
        Optional<Cuentas> cuentasOp = cuentasRepositorio.findByCorreo(cuentasDto.getCorreo());
        if (cuentasOp.isPresent()) {
            if ( !cuentasOp.get().isActivo() ) throw new NotFoundEntityException("Su usuario se encuentra desactivado.");


            String contrasena = cuentasDto.getContrasena();
            String contraDB = cuentasOp.get().getContrasena();
            if (EncryptUtils.verificarContrasena(contrasena, contraDB)) {
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

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (usuario.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ADMINISTRADOR"));
        } else {
            authorities.add(new SimpleGrantedAuthority("VENDEDOR"));
        }

        // Devuelve el User de Spring Security
        return org.springframework.security.core.userdetails.User
                .withUsername(String.valueOf(usuario.getId()))
                .password("")
                .authorities(authorities)
                .build();
    }

    public MyInfoDto getMyInformation() {
        int userId = AuthUtil.getAuthenticatedUserId();
        MyInfoDto myInfo = new MyInfoDto();
        Optional<Cuentas> cuentasOp = cuentasRepositorio.findById(userId);

        if (cuentasOp.isPresent()) {
            myInfo.setCuenta(cuentasOp.get());
        }

        try {
            Vendedor vendedor = vendedorService.getVendedor(userId);
            myInfo.setVendedor(vendedor);

            Pagos pago = pagoService.getLastPayment(userId);
            myInfo.setUltimoPago(pago);

            if (pago != null) {
                int planId = pago.getIdPlan();
                Optional<Planes> plan = planService.getPlan(planId);

                if (plan.isPresent()) {
                    myInfo.setPlan(plan.get());
                }
            }


        } catch (NotFoundEntityException e) {
            myInfo.setVendedor(null);
        }

        Map<String, Object> data = vendedorService.getVendedorData(userId);
        myInfo.setUserData(data);

        return myInfo;

    }

    public Response resetPassword(ResetPasswordDto resetDto) {
        if ( !AuthUtil.isAdmin() && resetDto.getId() != AuthUtil.getAuthenticatedUserId() ) {
            throw new Error("Ute no puede ace' eso :P");
        } 
        Optional<Cuentas> cuentasOp = cuentasRepositorio.findById(resetDto.getId());
        if (!cuentasOp.isPresent()) {
            return new Response(false, "Cuenta no encontrada.");
        } else {
            Cuentas cuentas = cuentasOp.get();
            String contrasenaEncript = EncryptUtils.encriptarContrasena(resetDto.getContrasena());
            cuentas.setContrasena(contrasenaEncript);
            cuentasRepositorio.save(cuentas);
            return new Response(true, "Actualizada correctamente");
        }
    }

    public Response updateActividadCuenta(int idCuenta, boolean activo) {
        if ( !AuthUtil.isAdmin() && idCuenta != AuthUtil.getAuthenticatedUserId() ) {
            throw new Error("Ute no puede ace' eso :P");
        } 

        Optional<Cuentas> cuentasOp = cuentasRepositorio.findById(idCuenta);
        if (!cuentasOp.isPresent()) {
            return new Response(false, "Cuenta no encontrada.");
        } else {
            Cuentas cuentas = cuentasOp.get();
            cuentas.setActivo(activo);
            cuentasRepositorio.save(cuentas);
            return new Response(true, "Actualizada correctamente");
        }
    }

}
