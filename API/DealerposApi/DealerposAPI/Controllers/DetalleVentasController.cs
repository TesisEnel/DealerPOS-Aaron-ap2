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
    public class DetalleVentasController : ControllerBase
    {
        private readonly Context _context;

        public DetalleVentasController(Context context)
        {
            _context = context;
        }

        // GET: api/DetalleVentas
        [HttpGet]
        public async Task<ActionResult<IEnumerable<DetalleVentas>>> GetDetalleVentas()
        {
            return await _context.DetalleVentas.ToListAsync();
        }

        // GET: api/DetalleVentas/5
        [HttpGet("{id}")]
        public async Task<ActionResult<DetalleVentas>> GetDetalleVentas(int id)
        {
            var detalleVentas = await _context.DetalleVentas.FindAsync(id);

            if (detalleVentas == null)
            {
                return NotFound();
            }

            return detalleVentas;
        }

        // PUT: api/DetalleVentas/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutDetalleVentas(int id, DetalleVentas detalleVentas)
        {
            if (DetalleVentasExists(detalleVentas.detalleVentaId))
            {
                _context.DetalleVentas.Update(detalleVentas);
            }
            await _context.SaveChangesAsync();
            return Ok(detalleVentas);
        }

        // POST: api/DetalleVentas
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<DetalleVentas>> PostDetalleVentas(DetalleVentas detalleVentas)
        {
            _context.DetalleVentas.Add(detalleVentas);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetDetalleVentas", new { id = detalleVentas.detalleVentaId }, detalleVentas);
        }

        // DELETE: api/DetalleVentas/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteDetalleVentas(int id)
        {
            var detalleVentas = await _context.DetalleVentas.FindAsync(id);
            if (detalleVentas == null)
            {
                return NotFound();
            }

            _context.DetalleVentas.Remove(detalleVentas);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool DetalleVentasExists(int id)
        {
            return _context.DetalleVentas.Any(e => e.detalleVentaId == id);
        }
    }
}
