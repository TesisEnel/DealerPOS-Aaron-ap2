using Microsoft.Extensions.Logging;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DealerposAPI.Models
{
    public class Ventas
    {
        [Key]
        public int ventaId { get; set; }

        [ForeignKey("usuarioId")]
        public int usuarioId { get; set; }

        [ForeignKey("paisId")]
        public int paisId { get; set; }

        [ForeignKey("provinciaId")]
        public int provinciaId { get; set; }

        [ForeignKey("ciudadId")]
        public int ciudadId { get; set; }
        
        public string? numeroPedido { get; set; }
        public string? tipoVenta { get; set; }
        public string? nombre {  get; set; }
        public string? telefono { get; set; }
        public string? email { get; set; }
        public float descuento { get; set; }
        public float impuesto { get; set; }
        public string? envio { get; set; }
        public float subtotal { get; set; }
        public string? metodoEntrega { get; set; }
        public string? direccion { get; set; }
        public string? estadoEntrega { get; set; }
        public string? estadoPagado { get; set; }
        public string? nota { get; set; }
        public string? fecha { get; set; }

    }
}
