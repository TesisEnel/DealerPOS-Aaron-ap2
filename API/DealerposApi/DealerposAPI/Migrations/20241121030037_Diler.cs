using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace DealerposAPI.Migrations
{
    /// <inheritdoc />
    public partial class Diler : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Adicionales",
                columns: table => new
                {
                    adicionalId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    productoId = table.Column<int>(type: "INTEGER", nullable: false),
                    descripcion = table.Column<string>(type: "TEXT", nullable: true),
                    precio = table.Column<float>(type: "REAL", nullable: false),
                    imagen = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Adicionales", x => x.adicionalId);
                });

            migrationBuilder.CreateTable(
                name: "Ajustes",
                columns: table => new
                {
                    ajusteId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    nombreEmpresa = table.Column<string>(type: "TEXT", nullable: true),
                    propietario = table.Column<string>(type: "TEXT", nullable: true),
                    direccion = table.Column<string>(type: "TEXT", nullable: true),
                    telefono = table.Column<string>(type: "TEXT", nullable: true),
                    email = table.Column<string>(type: "TEXT", nullable: true),
                    tipoFiscal = table.Column<string>(type: "TEXT", nullable: true),
                    numeroFiscal = table.Column<string>(type: "TEXT", nullable: true),
                    pais = table.Column<string>(type: "TEXT", nullable: true),
                    moneda = table.Column<string>(type: "TEXT", nullable: true),
                    lectora = table.Column<string>(type: "TEXT", nullable: true),
                    estado = table.Column<string>(type: "TEXT", nullable: true),
                    smtpHost = table.Column<string>(type: "TEXT", nullable: true),
                    smtpUsername = table.Column<string>(type: "TEXT", nullable: true),
                    smtpPassword = table.Column<string>(type: "TEXT", nullable: true),
                    smtpPort = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Ajustes", x => x.ajusteId);
                });

            migrationBuilder.CreateTable(
                name: "Autenticaciones",
                columns: table => new
                {
                    autenticacionId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    usuarioId = table.Column<int>(type: "INTEGER", nullable: false),
                    codigo = table.Column<string>(type: "TEXT", nullable: true),
                    dispositivo = table.Column<string>(type: "TEXT", nullable: true),
                    fecha = table.Column<string>(type: "TEXT", nullable: true),
                    estado = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Autenticaciones", x => x.autenticacionId);
                });

            migrationBuilder.CreateTable(
                name: "Caracteristicas",
                columns: table => new
                {
                    caracteristicaId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    productoId = table.Column<int>(type: "INTEGER", nullable: false),
                    iconoId = table.Column<int>(type: "INTEGER", nullable: false),
                    nombre = table.Column<string>(type: "TEXT", nullable: true),
                    descripcion = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Caracteristicas", x => x.caracteristicaId);
                });

            migrationBuilder.CreateTable(
                name: "Categorias",
                columns: table => new
                {
                    categoriaId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    imagen = table.Column<string>(type: "TEXT", nullable: true),
                    descripcion = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Categorias", x => x.categoriaId);
                });

            migrationBuilder.CreateTable(
                name: "DetalleProductoSucursales",
                columns: table => new
                {
                    detalleProductoSucursalId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    productoId = table.Column<int>(type: "INTEGER", nullable: false),
                    sucursalId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_DetalleProductoSucursales", x => x.detalleProductoSucursalId);
                });

            migrationBuilder.CreateTable(
                name: "DetalleVentaPagos",
                columns: table => new
                {
                    detalleVentaPagoId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    ventaId = table.Column<int>(type: "INTEGER", nullable: false),
                    usuarioId = table.Column<int>(type: "INTEGER", nullable: false),
                    metodoPago = table.Column<string>(type: "TEXT", nullable: true),
                    montoPagado = table.Column<float>(type: "REAL", nullable: false),
                    estado = table.Column<string>(type: "TEXT", nullable: true),
                    fecha = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_DetalleVentaPagos", x => x.detalleVentaPagoId);
                });

            migrationBuilder.CreateTable(
                name: "DetalleVentas",
                columns: table => new
                {
                    detalleVentaId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    ventaId = table.Column<int>(type: "INTEGER", nullable: false),
                    productoId = table.Column<int>(type: "INTEGER", nullable: false),
                    descripcionProducto = table.Column<string>(type: "TEXT", nullable: true),
                    cantidadProducto = table.Column<int>(type: "INTEGER", nullable: false),
                    precioProducto = table.Column<float>(type: "REAL", nullable: false),
                    descuentoProducto = table.Column<float>(type: "REAL", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_DetalleVentas", x => x.detalleVentaId);
                });

            migrationBuilder.CreateTable(
                name: "Favoritos",
                columns: table => new
                {
                    favoritoId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    productoId = table.Column<int>(type: "INTEGER", nullable: false),
                    usuarioId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Favoritos", x => x.favoritoId);
                });

            migrationBuilder.CreateTable(
                name: "Iconos",
                columns: table => new
                {
                    iconoId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    titulo = table.Column<string>(type: "TEXT", nullable: true),
                    imagen = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Iconos", x => x.iconoId);
                });

            migrationBuilder.CreateTable(
                name: "Productos",
                columns: table => new
                {
                    productoId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    categoriaId = table.Column<int>(type: "INTEGER", nullable: false),
                    nombre = table.Column<string>(type: "TEXT", nullable: true),
                    descripcion = table.Column<string>(type: "TEXT", nullable: true),
                    precio = table.Column<float>(type: "REAL", nullable: false),
                    cantidad = table.Column<int>(type: "INTEGER", nullable: false),
                    codigo = table.Column<string>(type: "TEXT", nullable: true),
                    costo = table.Column<float>(type: "REAL", nullable: false),
                    descuento = table.Column<float>(type: "REAL", nullable: false),
                    imagen = table.Column<string>(type: "TEXT", nullable: true),
                    diagarantia = table.Column<string>(type: "TEXT", nullable: true),
                    estado = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Productos", x => x.productoId);
                });

            migrationBuilder.CreateTable(
                name: "Reclamaciones",
                columns: table => new
                {
                    reclamacionId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    usuarioId = table.Column<int>(type: "INTEGER", nullable: false),
                    titulo = table.Column<string>(type: "TEXT", nullable: true),
                    descripcion = table.Column<string>(type: "TEXT", nullable: true),
                    fecha = table.Column<string>(type: "TEXT", nullable: true),
                    respuesta = table.Column<string>(type: "TEXT", nullable: true),
                    estado = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Reclamaciones", x => x.reclamacionId);
                });

            migrationBuilder.CreateTable(
                name: "Roles",
                columns: table => new
                {
                    rolId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    nombre = table.Column<string>(type: "TEXT", nullable: true),
                    descripcion = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Roles", x => x.rolId);
                });

            migrationBuilder.CreateTable(
                name: "Sucursales",
                columns: table => new
                {
                    sucursalId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    descripcion = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Sucursales", x => x.sucursalId);
                });

            migrationBuilder.CreateTable(
                name: "Tarifas",
                columns: table => new
                {
                    tarifaId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    sucursalId = table.Column<int>(type: "INTEGER", nullable: false),
                    ciudadId = table.Column<int>(type: "INTEGER", nullable: false),
                    monto = table.Column<float>(type: "REAL", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Tarifas", x => x.tarifaId);
                });

            migrationBuilder.CreateTable(
                name: "Usuarios",
                columns: table => new
                {
                    usuarioId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    rolId = table.Column<int>(type: "INTEGER", nullable: false),
                    paisId = table.Column<int>(type: "INTEGER", nullable: false),
                    estadoId = table.Column<int>(type: "INTEGER", nullable: false),
                    ciudadId = table.Column<int>(type: "INTEGER", nullable: false),
                    nombre = table.Column<string>(type: "TEXT", nullable: true),
                    apellido = table.Column<string>(type: "TEXT", nullable: true),
                    contrasena = table.Column<string>(type: "TEXT", nullable: true),
                    sexo = table.Column<string>(type: "TEXT", nullable: true),
                    telefono = table.Column<string>(type: "TEXT", nullable: true),
                    email = table.Column<string>(type: "TEXT", nullable: true),
                    direccion = table.Column<string>(type: "TEXT", nullable: true),
                    imagen = table.Column<string>(type: "TEXT", nullable: true),
                    verificado = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Usuarios", x => x.usuarioId);
                });

            migrationBuilder.CreateTable(
                name: "Ventas",
                columns: table => new
                {
                    ventaId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    usuarioId = table.Column<int>(type: "INTEGER", nullable: false),
                    paisId = table.Column<int>(type: "INTEGER", nullable: false),
                    provinciaId = table.Column<int>(type: "INTEGER", nullable: false),
                    ciudadId = table.Column<int>(type: "INTEGER", nullable: false),
                    numeroPedido = table.Column<string>(type: "TEXT", nullable: true),
                    tipoVenta = table.Column<string>(type: "TEXT", nullable: true),
                    nombre = table.Column<string>(type: "TEXT", nullable: true),
                    telefono = table.Column<string>(type: "TEXT", nullable: true),
                    email = table.Column<string>(type: "TEXT", nullable: true),
                    descuento = table.Column<float>(type: "REAL", nullable: false),
                    impuesto = table.Column<float>(type: "REAL", nullable: false),
                    envio = table.Column<string>(type: "TEXT", nullable: true),
                    subtotal = table.Column<float>(type: "REAL", nullable: false),
                    metodoEntrega = table.Column<string>(type: "TEXT", nullable: true),
                    direccion = table.Column<string>(type: "TEXT", nullable: true),
                    estadoEntrega = table.Column<string>(type: "TEXT", nullable: true),
                    estadoPagado = table.Column<string>(type: "TEXT", nullable: true),
                    nota = table.Column<string>(type: "TEXT", nullable: true),
                    fecha = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Ventas", x => x.ventaId);
                });

            migrationBuilder.CreateTable(
                name: "Verificaciones",
                columns: table => new
                {
                    verificacionId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    usuarioId = table.Column<int>(type: "INTEGER", nullable: false),
                    codigo = table.Column<string>(type: "TEXT", nullable: true),
                    vencimiento = table.Column<string>(type: "TEXT", nullable: true),
                    estado = table.Column<string>(type: "TEXT", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Verificaciones", x => x.verificacionId);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Adicionales");

            migrationBuilder.DropTable(
                name: "Ajustes");

            migrationBuilder.DropTable(
                name: "Autenticaciones");

            migrationBuilder.DropTable(
                name: "Caracteristicas");

            migrationBuilder.DropTable(
                name: "Categorias");

            migrationBuilder.DropTable(
                name: "DetalleProductoSucursales");

            migrationBuilder.DropTable(
                name: "DetalleVentaPagos");

            migrationBuilder.DropTable(
                name: "DetalleVentas");

            migrationBuilder.DropTable(
                name: "Favoritos");

            migrationBuilder.DropTable(
                name: "Iconos");

            migrationBuilder.DropTable(
                name: "Productos");

            migrationBuilder.DropTable(
                name: "Reclamaciones");

            migrationBuilder.DropTable(
                name: "Roles");

            migrationBuilder.DropTable(
                name: "Sucursales");

            migrationBuilder.DropTable(
                name: "Tarifas");

            migrationBuilder.DropTable(
                name: "Usuarios");

            migrationBuilder.DropTable(
                name: "Ventas");

            migrationBuilder.DropTable(
                name: "Verificaciones");
        }
    }
}
