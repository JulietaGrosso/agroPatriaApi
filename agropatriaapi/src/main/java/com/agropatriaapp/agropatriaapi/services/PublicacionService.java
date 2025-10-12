package com.agropatriaapp.agropatriaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.agropatriaapp.agropatriaapi.dto.PublicacionDto;
import com.agropatriaapp.agropatriaapi.dto.PublicacionFiltroDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Productos;
import com.agropatriaapp.agropatriaapi.model.Publicacion;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.repositorios.PublicacionRespositorio;
import com.agropatriaapp.agropatriaapi.specifications.PublicacionSpecifications;
import com.agropatriaapp.agropatriaapi.utils.AuthUtil;
import com.agropatriaapp.agropatriaapi.utils.Utils;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRespositorio publicacionRespositorio;

    @Autowired ProductosService productosService;

    public Publicacion getPublicacion(int idPublicacion) throws NotFoundEntityException{
        Optional<Publicacion> publiOp = publicacionRespositorio.findById(idPublicacion);
        if(publiOp.isPresent()){
            return publiOp.get();
        }

        throw new NotFoundEntityException("No se encontró la publicación.");
    }

    public List<Publicacion> getPublicaciones(PublicacionFiltroDto listaFiltros){
        Integer vendedorId = listaFiltros.getVendedor();
        Integer productoId = listaFiltros.getProducto();
        Integer condicion = listaFiltros.getCondicion();
        Integer vendido = listaFiltros.getVendido();
        int page = 0;
        int pageSize = 100000;

        // Por defecto se filtran aquellos no vendidos.
        if ( vendido == null) vendido = 0;

        Specification<Publicacion> filtro = Specification.unrestricted();
        if ( vendedorId != null ) filtro = filtro.and(PublicacionSpecifications.byVendedorId(vendedorId));
        if ( productoId != null ) filtro = filtro.and(PublicacionSpecifications.byProductoId(productoId));
        if ( condicion != null ) filtro = filtro.and(PublicacionSpecifications.byCondicion(condicion));
        if (vendido != null) filtro = filtro.and(PublicacionSpecifications.byVendido(vendido.equals(1)));
        if ( listaFiltros.getPage() != null ) pageSize = listaFiltros.getPage();
        if ( listaFiltros.getElementsPerPage() != null ) page = listaFiltros.getElementsPerPage();
        return publicacionRespositorio.findAll(filtro, PublicacionSpecifications.withPagination(page, pageSize)).getContent();
    }

    public Response postPublicacion(PublicacionDto publicacionDto){
        Publicacion publicacion = new Publicacion();
        Productos producto = productosService.getProductOrSave(publicacionDto);
        int userId = AuthUtil.getAuthenticatedUserId();

        publicacion.setProductosId(producto.getIdProducto());
        publicacion.setVendedorCuentasId(userId);
        publicacion.setVendido(publicacionDto.isVendido());
        publicacion.setNombrePublicacion(publicacionDto.getNombrePublicacion());
        publicacion.setDescripcionPublic(publicacionDto.getDescripcionPublic());
        publicacion.setCondicion(publicacionDto.getCondicion());
        publicacion.setCiudad(publicacionDto.getCiudad());
        publicacion.setPrecio(publicacionDto.getPrecio());
        publicacion.setImagenes(publicacionDto.getImagenes());
        publicacion.setPrecio(publicacionDto.getPrecio());
        publicacion.setModelo(publicacionDto.getModelo());
        publicacionRespositorio.save(publicacion);
        return new Response(true, "Agregado Correctamente");
    }

      public Response deletePublicacion(int id){
        publicacionRespositorio.deleteById(id);
        return new Response(true, "Eliminado Correctamente");
    }


    public Response putPublicacion(int id, PublicacionDto publicacionDto) throws NotFoundEntityException{
        Optional<Publicacion> publicacionOp = publicacionRespositorio.findById(id);
        if(publicacionOp.isPresent()){
            Publicacion publicacion = publicacionOp.get();
            publicacion.setProductosId(publicacionDto.getProductoId());
            publicacion.setVendedorCuentasId(publicacionDto.getVendedorCuentasId());
            publicacion.setVendido(publicacionDto.isVendido());
            publicacion.setNombrePublicacion(publicacionDto.getNombrePublicacion());
            publicacion.setDescripcionPublic(publicacionDto.getDescripcionPublic());
            publicacion.setCondicion(publicacionDto.getCondicion());
            publicacion.setCiudad(publicacionDto.getCiudad());
            publicacion.setPrecio(publicacionDto.getPrecio());
            publicacion.setImagenes(publicacionDto.getImagenes());
            publicacion.setModelo(publicacionDto.getModelo());
            publicacionRespositorio.save(publicacion);
            return new Response(true, "Se actualizó correctamente");
        }
        throw new NotFoundEntityException("No se encontró la publicación");
    }




}
