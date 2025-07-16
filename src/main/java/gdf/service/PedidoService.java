package gdf.service;

import gdf.dto.LineaPedidoCreationDTO;
import gdf.dto.PedidoCreationDTO;
import gdf.dto.PedidoDTO;
import gdf.dto.LineaPedidoDTO;
import gdf.entity.Pedido;
import gdf.entity.Producto;
import gdf.entity.LineaPedido;
import gdf.exception.ResourceNotFoundException;
import gdf.exception.StockInsuficienteException;
import gdf.repository.PedidoRepository;
import gdf.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public PedidoDTO createPedido(PedidoCreationDTO pedidoDto) {
        Pedido pedido = new Pedido();

        if (pedidoDto.getLineas() == null || pedidoDto.getLineas().isEmpty()) {
            throw new IllegalArgumentException("Un pedido debe tener al menos una línea.");
        }

        for (LineaPedidoCreationDTO lineaDto : pedidoDto.getLineas()) {
            Producto producto = productoRepository.findById(lineaDto.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto", "ID", lineaDto.getProductoId()));

            pedido.agregarLinea(producto, lineaDto.getCantidad());
        }

        Pedido savedPedido = pedidoRepository.save(pedido);

        List<LineaPedidoDTO> lineaPedidoDTOs = savedPedido.getLineasPedido().stream()
                .map(linea -> new LineaPedidoDTO(
                        linea.getProducto().getId(),
                        linea.getNombreProducto(),
                        linea.getPrecioUnitario(),
                        linea.getCantidad(),
                        linea.getSubtotal()))
                .collect(Collectors.toList());

        return new PedidoDTO(savedPedido.getId(), lineaPedidoDTOs, savedPedido.getCostoTotal());
    }

    @Transactional(readOnly = true)
    public PedidoDTO getPedidoById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", "ID", id));

        List<LineaPedidoDTO> lineaPedidoDTOs = pedido.getLineasPedido().stream()
                .map(linea -> new LineaPedidoDTO(
                        linea.getProducto().getId(),
                        linea.getNombreProducto(),
                        linea.getPrecioUnitario(),
                        linea.getCantidad(),
                        linea.getSubtotal()))
                .collect(Collectors.toList());

        return new PedidoDTO(pedido.getId(), lineaPedidoDTOs, pedido.getCostoTotal());
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> getAllPedidos() {
        return pedidoRepository.findAll().stream()
                .map(pedido -> {
                    List<LineaPedidoDTO> lineaPedidoDTOs = pedido.getLineasPedido().stream()
                            .map(linea -> new LineaPedidoDTO(
                                    linea.getProducto().getId(),
                                    linea.getNombreProducto(),
                                    linea.getPrecioUnitario(),
                                    linea.getCantidad(),
                                    linea.getSubtotal()))
                            .collect(Collectors.toList());
                    return new PedidoDTO(pedido.getId(), lineaPedidoDTOs, pedido.getCostoTotal());
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido", "ID", id);
        }
        pedidoRepository.deleteById(id);
    }
}