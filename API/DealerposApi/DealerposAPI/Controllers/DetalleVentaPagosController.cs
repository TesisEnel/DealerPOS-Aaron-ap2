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
    public class DetalleVentaPagosController : ControllerBase
    {
        private readonly Context _context;

        public DetalleVentaPagosController(Context context)
        {
            _context = context;
        }

        // GET: api/DetalleVentaPagos
        [HttpGet]
        public async Task<ActionResult<IEnumerable<DetalleVentaPagos>>> GetDetalleVentaPagos()
        {
            return await _context.DetalleVentaPagos.ToListAsync();
        }

        // GET: api/DetalleVentaPagos/5
        [HttpGet("{id}")]
        public async Task<ActionResult<DetalleVentaPagos>> GetDetalleVentaPagos(int id)
        {
            var detalleVentaPagos = await _context.DetalleVentaPagos.FindAsync(id);

            if (detalleVentaPagos == null)
            {
                return NotFound();
            }

            return detalleVentaPagos;
        }

        // PUT: api/DetalleVentaPagos/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutDetalleVentaPagos(int id, DetalleVentaPagos detalleVentaPagos)
        {
            if (DetalleVentaPagosExists(detalleVentaPagos.detalleVentaPagoId))
            {
                _context.DetalleVentaPagos.Update(detalleVentaPagos);
            }
            await _context.SaveChangesAsync();
            return Ok(detalleVentaPagos);
        }

        // POST: api/DetalleVentaPagos
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<DetalleVentaPagos>> PostDetalleVentaPagos(DetalleVentaPagos detalleVentaPagos)
        {
            _context.DetalleVentaPagos.Add(detalleVentaPagos);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetDetalleVentaPagos", new { id = detalleVentaPagos.detalleVentaPagoId }, detalleVentaPagos);
        }

        // DELETE: api/DetalleVentaPagos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteDetalleVentaPagos(int id)
        {
            var detalleVentaPagos = await _context.DetalleVentaPagos.FindAsync(id);
            if (detalleVentaPagos == null)
            {
                return NotFound();
            }

            _context.DetalleVentaPagos.Remove(detalleVentaPagos);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool DetalleVentaPagosExists(int id)
        {
            return _context.DetalleVentaPagos.Any(e => e.detalleVentaPagoId == id);
        }
    }
}
