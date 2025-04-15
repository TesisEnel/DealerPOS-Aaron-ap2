using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Iconos
    {
        [Key]
        public int iconoId { get; set; }
        public string? titulo { get; set; }
        public string? imagen { get; set; }
    }
}
