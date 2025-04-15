using GeografiaAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace GeografiaAPI.DAL
{
    public class Context : DbContext
    {
        public Context(DbContextOptions<Context> options) : base(options) { }
        public DbSet<Paises> Paises { get; set; }
        public DbSet<Estados> Estados { get; set; }
        public DbSet<Ciudades> Ciudades { get; set; }

    }
}
