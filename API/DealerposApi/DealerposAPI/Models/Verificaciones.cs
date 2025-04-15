using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Verificaciones
    {
        [Key]
        public int verificacionId { get; set; }

        [ForeignKey("usuarioId")]
        public int usuarioId { get; set; }

        public string? codigo { get; set; }
        public string? vencimiento { get; set; }
        public string? estado { get; set; }

    }
}
