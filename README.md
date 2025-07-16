Proyecto integrador final de talento tech, alumno Gustavo David Fernández año 2025 primer semestre.
Estructura del proyecto creado con IntelliJ IDEA 2025.1.3 (Community Edition).

 src/main/java/gdf/
├── controller/  -> Maneja las solicitudes HTTP y respuestas
│   ├── PedidoController.java
│   └── ProductoController.java
├── dto/         -> Objetos de Transferencia de Datos
│   ├── LineaPedidoCreationDTO.java
│   ├── PedidoCreationDTO.java
│   ├── ProductoCreationDTO.java
│   ├── ProductoDTO.java
│   └── PedidoDTO.java
├── entity/      -> Representa las tablas de la base de datos
│   ├── LineaPedido.java
│   ├── Pedido.java
│   └── Producto.java
├── exception/   -> Clases de excepciones personalizadas (como StockInsuficienteException)
│   ├── ResourceNotFoundException.java (Nueva, general para no encontrados)
│   └── StockInsuficienteException.java
├── repository/  -> Interfaz para la interacción con la base de datos (DAO)
│   ├── LineaPedidoRepository.java
│   ├── PedidoRepository.java
│   └── ProductoRepository.java
└── service/     -> Contiene la lógica de negocio principal
    ├── PedidoService.java
    └── ProductoService.java

Desarrollado con lenguaje JAVA y framework SpringBoot y demás dependencias, las pruebas sobre el api rest full se realizaron con registros cargados a través de sql en el archivo "data.sql" y se adjunta la colección exportada con la aplicación insomnia llamada "Insomnia_gdf" utilizado para realizar las pruebas.

GDF.-
