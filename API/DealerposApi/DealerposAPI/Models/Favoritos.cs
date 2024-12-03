using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Favoritos
    {
        [Key]
        public int favoritoId { get; set; }

        [ForeignKey("productoId")]
        public int productoId { get; set; }

        [ForeignKey("usuarioId")]
        public int usuarioId { get; set; }

    }
}
