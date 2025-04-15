using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace DealerposAPI.Models
{
    public class Productos
    {
        [Key] 
        public int productoId { get; set; }

        [ForeignKey("categoriaId")]
        public int categoriaId { get; set; }
        
        public string? nombre { get; set; }
        public string? descripcion { get; set; }
        public float precio { get; set; }
        public int cantidad { get; set; }
        public string? codigo { get; set; }
        public float costo {  get; set; }
        public float descuento { get; set; }
        public string? imagen { get; set; }
        public string? diagarantia { get; set; }
        public string? estado { get; set; }
    }
}
