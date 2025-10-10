package com.agropatriaapp.agropatriaapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.ProductoFiltroDto;
import com.agropatriaapp.agropatriaapi.model.Productos;
import com.agropatriaapp.agropatriaapi.model.Publicacion;
import com.agropatriaapp.agropatriaapi.repositorios.ProductosRepositorio;
import com.agropatriaapp.agropatriaapi.specifications.ProductoSpecifications;
import com.agropatriaapp.agropatriaapi.specifications.PublicacionSpecifications;

@Service
public class ProductosService {
@Autowired
private ProductosRepositorio productosRepositorio;

public List<Productos> buscarProductos(ProductoFiltroDto filtros){
    Integer categoriaId = filtros.getCategoria();
    String nombre = filtros.getNombre();

    Specification<Productos> filtro = Specification.unrestricted();
    if ( categoriaId != null ) filtro = filtro.and(ProductoSpecifications.byCategoria(categoriaId));
    if ( nombre != null ) filtro = filtro.and(ProductoSpecifications.containsName(nombre));

    return productosRepositorio.findAll(filtro);
}

public List<Productos> getProductos(Integer categoriaFiltro, Integer condicionFiltro){
    List<Productos> listaProductos = new ArrayList<>();
    List<Map<String,Object>> lista = productosRepositorio.buscarProductosDePublicaciones(categoriaFiltro, condicionFiltro);
    
    for(Map<String,Object> o : lista){
        Productos productos = new Productos();
        Integer id = (Integer) o.get("id");
        String nombre = (String) o.get("nombre");
        String descripcion = (String) o.get("descripcion");
        String imagen = (String) o.get("imagen");
        Integer precio = (Integer) o.get("precio");
        int condicion = (int) o.get("condicion");
        String clasificacion = (String) o.get("clasificacion");
        String region = (String) o.get("region");
        int categoria = (int) o.get("categoria");
        String moneda = (String) o.get("moneda");
        Long cantidadPublicaciones = (Long) o.get("cantidadPublicaciones");

        productos.setIdProducto(id);
        productos.setNombre(nombre);
        productos.setDescripcion(descripcion);
        productos.setImagen(imagen);
        productos.setPrecio(precio);
        productos.setCondicion(condicion);
        productos.setClasificacion(clasificacion);
        productos.setRegion(region);
        productos.setCategoria(categoria);
        productos.setMoneda(moneda);
        productos.setCantidadPublicaciones(cantidadPublicaciones.intValue());
        listaProductos.add(productos);
    }


    return listaProductos;
}




}
