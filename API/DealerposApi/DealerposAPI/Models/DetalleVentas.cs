using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class DetalleVentas
    {
        [Key]
        public int detalleVentaId { get; set; }

        [ForeignKey("ventaId")]
        public int ventaId { get; set; }

        [ForeignKey("productoId")]
        public int productoId { get; set; }

        public string? descripcionProducto { get; set; }
        public int cantidadProducto { get; set; }
        public float precioProducto { get; set; }
        public float descuentoProducto { get; set; }

    }
}
