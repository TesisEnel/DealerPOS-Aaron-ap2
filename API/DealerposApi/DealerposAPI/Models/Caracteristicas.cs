using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Reflection.PortableExecutable;

namespace DealerposAPI.Models
{
    public class Caracteristicas
    {
        [Key]
        public int caracteristicaId { get; set; }

        [ForeignKey("productoId")]
        public int productoId { get; set; }

        [ForeignKey("iconoId")]
        public int iconoId { get; set; }

        public string? nombre { get; set; }
        public string? descripcion { get; set; }

    }
}
