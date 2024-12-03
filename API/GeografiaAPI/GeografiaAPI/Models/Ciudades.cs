using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GeografiaAPI.Models
{
    public class Ciudades
    {
        [Key]
        public int ciudadId { get; set; }

        [ForeignKey("estadosId")]
        public int estadoId { get; set; }

        public string? descripcion { get; set; }

    }
}
