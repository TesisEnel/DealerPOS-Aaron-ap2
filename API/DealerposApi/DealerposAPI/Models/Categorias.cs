using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Categorias
    {
        [Key]
        public int categoriaId { get; set; }
        public string? imagen {  get; set; }
        public string? descripcion {  get; set; }
    }
}
