package com.finalSW.CRUD.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalSW.CRUD.entidad.Detalles;
import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.CRUD.exceptions.ResourceNotFoundException;
import com.finalSW.CRUD.repository.DetallesInterface;

@Service
public class DetallesService {
    @Autowired
    private DetallesInterface rt;

    public List<Detalles> listarDetalles() {
        return rt.findAll();
    }

    public Detalles buscarDetalle(int id) throws ResourceNotFoundException {
        return rt.findById(id).orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado"));
    }
    
    public Detalles insertarDetalle(Detalles detalle) throws AtributteException {
    	return rt.save(detalle);
    }

    public Detalles actualizarDetalle(int id, Detalles detalle) throws ResourceNotFoundException, AtributteException {
        Detalles existingDetalle = rt.findById(id).orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado"));
        existingDetalle.setIdVenta(detalle.getIdVenta());
        existingDetalle.setProducto(detalle.getProducto());
        existingDetalle.setCantidad(detalle.getCantidad());
        existingDetalle.setSubtotal(detalle.getSubtotal());
        existingDetalle.setEstado(detalle.getEstado());

        return rt.save(existingDetalle);
    }

    public Detalles eliminarDetalle(int id) throws ResourceNotFoundException {
        Detalles detalle = rt.findById(id).orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado"));
        rt.delete(detalle);
        return detalle;
    }
}
