using DealerposAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace DealerposAPI.DAL
{
    public class Context : DbContext
    {
        public Context(DbContextOptions<Context> options) : base(options) { }
        public DbSet<Productos> Productos { get; set; }
        public DbSet<Sucursales> Sucursales { get; set; }
        public DbSet<DetalleProductoSucursales> DetalleProductoSucursales { get; set; }
        public DbSet<Categorias> Categorias { get; set; }
        public DbSet<Iconos> Iconos { get; set; }
        public DbSet<Caracteristicas> Caracteristicas { get; set; }
        public DbSet<Adicionales> Adicionales { get; set; }
        public DbSet<Ventas> Ventas { get; set; }
        public DbSet<DetalleVentas> DetalleVentas { get; set; }
        public DbSet<DetalleVentaPagos> DetalleVentaPagos { get; set; }
        public DbSet<Usuarios> Usuarios { get; set; }
        public DbSet<Roles> Roles { get; set; }
        public DbSet<Verificaciones> Verificaciones { get; set; }
        public DbSet<Autenticaciones> Autenticaciones { get; set; }
        public DbSet<Favoritos> Favoritos { get; set; }
        public DbSet<Reclamaciones> Reclamaciones { get; set; }
        public DbSet<Tarifas> Tarifas { get; set; }
        public DbSet<Ajustes> Ajustes { get; set; }
    }
}