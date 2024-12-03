using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class DetalleProductoSucursales
    {
        [Key]
        public int detalleProductoSucursalId { get; set; }
        public int productoId { get; set; }
        public int sucursalId { get; set; }
    }
}
