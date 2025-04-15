using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Tarifas
    {
        [Key]
        public int tarifaId { get; set; }

        [ForeignKey("sucursalId")]
        public int sucursalId { get; set; }

        [ForeignKey("ciudadId")]
        public int ciudadId { get; set; }

        public float monto { get; set; }

    }
}
