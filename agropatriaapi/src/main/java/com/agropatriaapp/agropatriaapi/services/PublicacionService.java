package com.agropatriaapp.agropatriaapi.services;

import java.util.ArrayList;
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
        Specification<Publicacion> filtro = Specification.unrestricted();

        Integer vendedorId = listaFiltros.getVendedor();
        Integer productoId = listaFiltros.getProducto();
        Integer condicion = listaFiltros.getCondicion();
        Integer vendido = listaFiltros.getVendido();
        String categoria = listaFiltros.getCategoria();
        int page = 0;
        int pageSize = 100000;

        // Por defecto se filtran aquellos no vendidos.
        if ( vendido == null) vendido = 0;

        // combinamos el OR con el filtro inicial
        Specification<Publicacion> orCategorias = this.getCategoriaFilter(categoria);
        if (orCategorias != null) {
            filtro = filtro.and(orCategorias);
        }


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
        publicacion.setCategoria(publicacionDto.getCategoria());
        publicacion.setMoneda(publicacionDto.getMoneda());
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
            publicacion.setMoneda(publicacionDto.getMoneda());
            publicacion.setCategoria(publicacionDto.getCategoria());
            publicacionRespositorio.save(publicacion);
            return new Response(true, "Se actualizó correctamente");
        }
        throw new NotFoundEntityException("No se encontró la publicación");
    }


    private Specification<Publicacion> getCategoriaFilter(String categoria){

        // Obtenemos categorias
        String[] categorias = {categoria};
        if (categoria.contains(",")) categorias = categoria.split(",");

        Specification<Publicacion> orSpec = null;

        // Recorremos cada categoría para el filtro.
        for(String cat : categorias) {
            try {
                int catNumber = Integer.parseInt(cat);
                Specification<Publicacion> catSpec = PublicacionSpecifications.byCategoriaId(catNumber);

                if (orSpec == null) {
                    orSpec = catSpec; // la primera
                } else {
                    orSpec = orSpec.or(catSpec); // agregamos OR
                }
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir " + cat + " en numero.");
            }
        }

        return orSpec;
    }


}
