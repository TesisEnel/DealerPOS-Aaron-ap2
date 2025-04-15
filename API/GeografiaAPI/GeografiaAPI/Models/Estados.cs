using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace GeografiaAPI.Models
{
    public class Estados
    {
        [Key]
        public int estadoId { get; set; }

        [ForeignKey("paisId")]
        public int paisId { get; set; }
        
        public string? descripcion { get; set; }

    }
}
