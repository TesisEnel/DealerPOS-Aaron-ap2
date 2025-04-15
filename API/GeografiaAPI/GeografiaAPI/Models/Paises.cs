using System.ComponentModel.DataAnnotations;

namespace GeografiaAPI.Models
{
    public class Paises
    {
        [Key]
        public int paisId { get; set; }
        public string? descripcion { get; set; }
    }
}
