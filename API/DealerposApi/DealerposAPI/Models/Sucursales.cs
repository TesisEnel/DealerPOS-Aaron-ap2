using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Sucursales
    {
        [Key]
        public int sucursalId { get; set; }
        public string? descripcion { get; set; }
    }
}
