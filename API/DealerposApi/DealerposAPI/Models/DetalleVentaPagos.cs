using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class DetalleVentaPagos
    {
        [Key]
        public int detalleVentaPagoId { get; set; }

        [ForeignKey("ventaId")]
        public int ventaId { get; set; }

        [ForeignKey("usuarioId")]
        public int usuarioId { get; set; }

        public string? metodoPago { get; set; }
        public float montoPagado { get; set; }
        public string? estado { get; set; }
        public string? fecha { get; set; }

    }
}
