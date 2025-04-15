using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DealerposAPI.Models
{
    public class Adicionales
    {
        [Key]
        public int adicionalId { get; set; }

        [ForeignKey("productoId")]
        public int productoId { get; set; }

        public string? descripcion {  get; set; }
        public float precio { get; set; }
        public string? imagen { get; set; }
    }
}
