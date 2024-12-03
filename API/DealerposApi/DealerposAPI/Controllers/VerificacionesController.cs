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
    public class VerificacionesController : ControllerBase
    {
        private readonly Context _context;

        public VerificacionesController(Context context)
        {
            _context = context;
        }

        // GET: api/Verificaciones
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Verificaciones>>> GetVerificaciones()
        {
            return await _context.Verificaciones.ToListAsync();
        }

        // GET: api/Verificaciones/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Verificaciones>> GetVerificaciones(int id)
        {
            var verificaciones = await _context.Verificaciones.FindAsync(id);

            if (verificaciones == null)
            {
                return NotFound();
            }

            return verificaciones;
        }

        // PUT: api/Verificaciones/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutVerificaciones(int id, Verificaciones verificaciones)
        {
            if (VerificacionesExists(verificaciones.verificacionId))
            {
                _context.Verificaciones.Update(verificaciones);
            }
            await _context.SaveChangesAsync();
            return Ok(verificaciones);
        }

        // POST: api/Verificaciones
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Verificaciones>> PostVerificaciones(Verificaciones verificaciones)
        {
            _context.Verificaciones.Add(verificaciones);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetVerificaciones", new { id = verificaciones.verificacionId }, verificaciones);
        }

        // DELETE: api/Verificaciones/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteVerificaciones(int id)
        {
            var verificaciones = await _context.Verificaciones.FindAsync(id);
            if (verificaciones == null)
            {
                return NotFound();
            }

            _context.Verificaciones.Remove(verificaciones);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool VerificacionesExists(int id)
        {
            return _context.Verificaciones.Any(e => e.verificacionId == id);
        }
    }
}
