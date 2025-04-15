using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Usuarios
    {
        [Key]
        public int usuarioId { get; set; }

        [ForeignKey("rolId")]
        public int rolId { get; set; }

        [ForeignKey("paisId")]
        public int paisId { get; set; }

        [ForeignKey("estadoId")]
        public int estadoId { get; set; }

        [ForeignKey("ciudadId")]
        public int ciudadId { get; set; }

        public string? nombre { get; set; }
        public string? apellido { get; set; }
        public string? contrasena { get; set; }
        public string? sexo { get; set; }
        public string? telefono { get; set; }
        public string? email { get; set; }
        public string? direccion { get; set; }
        public string? imagen { get; set; }
		public string? verificado { get; set; }

	}
}
