using System.ComponentModel.DataAnnotations;

namespace DealerposAPI.Models
{
    public class Ajustes
    {
        [Key]
        public int ajusteId { get; set; }
        public string? nombreEmpresa { get; set; }
        public string? propietario { get; set; }
        public string? direccion { get; set; }
        public string? telefono { get; set; }
        public string? email { get; set; }
        public string? tipoFiscal { get; set; }
        public string? numeroFiscal { get; set; }
        public string? pais { get; set; }
        public string? moneda { get; set; }
        public string? lectora { get; set; }
        public string? estado { get; set; }
        public string? smtpHost { get; set; }
        public string? smtpUsername { get; set; }
        public string? smtpPassword { get; set; }
        public int ? smtpPort { get; set; }

    }
}
