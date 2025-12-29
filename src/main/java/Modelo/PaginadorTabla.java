/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class PaginadorTabla<T> {

    private List<T> datosCompletos = new ArrayList<>();
    private final JTable tabla;
    private final JLabel lblPagina;
    private final JButton btnAnterior;
    private final JButton btnSiguiente;

    private final int REGISTROS_POR_PAGINA = 7;
    private int paginaActual = 1;
    private int totalPaginas = 0;

    public PaginadorTabla(JTable tabla, JLabel lblPagina, JButton btnAnterior, JButton btnSiguiente) {
        this.tabla = tabla;
        this.lblPagina = lblPagina;
        this.btnAnterior = btnAnterior;
        this.btnSiguiente = btnSiguiente;

        btnAnterior.addActionListener(e -> irPaginaAnterior());
        btnSiguiente.addActionListener(e -> irPaginaSiguiente());

        actualizarEstadoBotones();
    }

    public void cargarDatos(List<T> datos) {
        this.datosCompletos = (datos != null) ? new ArrayList<>(datos) : new ArrayList<>();
        this.paginaActual = 1;
        calcularTotalPaginas();
        mostrarPaginaActual();
        actualizarEstadoBotones();
    }

    public void recargar() {
        calcularTotalPaginas();
        mostrarPaginaActual();
        actualizarEstadoBotones();
    }

    private void calcularTotalPaginas() {
        if (datosCompletos.isEmpty()) {
            totalPaginas = 1;
        } else {
            totalPaginas = (int) Math.ceil((double) datosCompletos.size() / REGISTROS_POR_PAGINA);
        }
    }

    private void mostrarPaginaActual() {
        int inicio = (paginaActual - 1) * REGISTROS_POR_PAGINA;
        int fin = Math.min(inicio + REGISTROS_POR_PAGINA, datosCompletos.size());

        List<T> paginaDatos = datosCompletos.subList(inicio, fin);

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        for (T item : paginaDatos) {
            Object[] fila = obtenerFilaDesdeObjeto(item);
            if (fila != null) {
                modelo.addRow(fila);
            }
        }

        lblPagina.setText("Página " + paginaActual + " de " + totalPaginas);
    }

    private Object[] obtenerFilaDesdeObjeto(T item) {
        if (item instanceof ProductoModelo) {
            ProductoModelo p = (ProductoModelo) item;
            return new Object[]{
                p.getCodigo(),
                p.getNombre(),
                String.format("%.2f", p.getPrecio()),
                p.getCantidadStock()
            };
        }

        if (item instanceof UsuarioModelo) {
            UsuarioModelo u = (UsuarioModelo) item;
            return new Object[]{
                u.getIdUsuario(),
                u.getNombreUsuario(),
                u.getRol(),
                u.isActivo() ? "Activo" : "Inactivo"
            };
        }

        if (item instanceof HistorialVentaModelo) {
            HistorialVentaModelo v = (HistorialVentaModelo) item;
            return new Object[]{
                v.getIdFactura(),
                v.getFechaHora(),
                v.getCajero(),
                v.getCliente(),
                String.format("%.2f", v.getTotal())
            };
        }

        // ===== AGREGADO: PRODUCTOS MÁS VENDIDOS =====
        if (item instanceof ProductoMasVendidoModelo) {
            ProductoMasVendidoModelo p = (ProductoMasVendidoModelo) item;
            return new Object[]{
                p.getNombreProducto(),
                p.getCantidadTotalVendida()
            };
        }

        return null;
    }

    public void irPaginaSiguiente() {
        if (paginaActual < totalPaginas) {
            paginaActual++;
            mostrarPaginaActual();
            actualizarEstadoBotones();
        }
    }

    public void irPaginaAnterior() {
        if (paginaActual > 1) {
            paginaActual--;
            mostrarPaginaActual();
            actualizarEstadoBotones();
        }
    }

    private void actualizarEstadoBotones() {
        btnAnterior.setEnabled(paginaActual > 1);
        btnSiguiente.setEnabled(paginaActual < totalPaginas);
    }

    public int getPaginaActual() { return paginaActual; }
    public int getTotalPaginas() { return totalPaginas; }
    public int getTotalRegistros() { return datosCompletos.size(); }
    public List<T> getDatosCompletos() {
        return new ArrayList<>(datosCompletos);
    }
}