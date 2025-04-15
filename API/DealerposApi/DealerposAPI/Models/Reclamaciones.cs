using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Reclamaciones
    {
        [Key]
        public int reclamacionId { get; set; }

        [ForeignKey("usuarioId")]
        public int usuarioId { get; set; }

        public string? titulo { get; set; }
        public string? descripcion { get; set; }
        public string? fecha { get; set; }
        public string? respuesta { get; set; }
        public string? estado { get; set; }

    }
}
