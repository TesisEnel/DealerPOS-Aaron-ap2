using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Autenticaciones
    {
        [Key]
        public int autenticacionId { get; set; }

        [ForeignKey("usuarioId")]
        public int usuarioId { get; set; }

        public string? codigo { get; set; }
        public string? dispositivo { get; set; }
        public string? fecha { get; set; }
        public string? estado { get; set; }

    }
}
