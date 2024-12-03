using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Roles
    {
        [Key]
        public int rolId { get; set; }
        public string? nombre { get; set; }
        public string? descripcion { get; set; }

    }
}
