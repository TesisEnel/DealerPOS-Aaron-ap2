using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using DealerposAPI.DAL;
using DealerposAPI.Models;

namespace DealerposAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DetalleProductoSucursalesController : ControllerBase
    {
        private readonly Context _context;

        public DetalleProductoSucursalesController(Context context)
        {
            _context = context;
        }

        // GET: api/DetalleProductoSucursales
        [HttpGet]
        public async Task<ActionResult<IEnumerable<DetalleProductoSucursales>>> GetDetalleProductoSucursales()
        {
            return await _context.DetalleProductoSucursales.ToListAsync();
        }

        // GET: api/DetalleProductoSucursales/5
        [HttpGet("{id}")]
        public async Task<ActionResult<DetalleProductoSucursales>> GetDetalleProductoSucursales(int id)
        {
            var detalleProductoSucursales = await _context.DetalleProductoSucursales.FindAsync(id);

            if (detalleProductoSucursales == null)
            {
                return NotFound();
            }

            return detalleProductoSucursales;
        }

        // PUT: api/DetalleProductoSucursales/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutDetalleProductoSucursales(int id, DetalleProductoSucursales detalleProductoSucursales)
        {
            if (DetalleProductoSucursalesExists(detalleProductoSucursales.detalleProductoSucursalId))
            {
                _context.DetalleProductoSucursales.Update(detalleProductoSucursales);
            }
            await _context.SaveChangesAsync();
            return Ok(detalleProductoSucursales);
        }

        // POST: api/DetalleProductoSucursales
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<DetalleProductoSucursales>> PostDetalleProductoSucursales(DetalleProductoSucursales detalleProductoSucursales)
        {
            _context.DetalleProductoSucursales.Add(detalleProductoSucursales);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetDetalleProductoSucursales", new { id = detalleProductoSucursales.detalleProductoSucursalId }, detalleProductoSucursales);
        }

        // DELETE: api/DetalleProductoSucursales/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteDetalleProductoSucursales(int id)
        {
            var detalleProductoSucursales = await _context.DetalleProductoSucursales.FindAsync(id);
            if (detalleProductoSucursales == null)
            {
                return NotFound();
            }

            _context.DetalleProductoSucursales.Remove(detalleProductoSucursales);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool DetalleProductoSucursalesExists(int id)
        {
            return _context.DetalleProductoSucursales.Any(e => e.detalleProductoSucursalId == id);
        }
    }
}
