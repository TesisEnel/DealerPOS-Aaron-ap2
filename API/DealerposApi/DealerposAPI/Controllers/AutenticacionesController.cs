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
    public class AutenticacionesController : ControllerBase
    {
        private readonly Context _context;

        public AutenticacionesController(Context context)
        {
            _context = context;
        }

        // GET: api/Autenticaciones
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Autenticaciones>>> GetAutenticaciones()
        {
            return await _context.Autenticaciones.ToListAsync();
        }

        // GET: api/Autenticaciones/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Autenticaciones>> GetAutenticaciones(int id)
        {
            var autenticaciones = await _context.Autenticaciones.FindAsync(id);

            if (autenticaciones == null)
            {
                return NotFound();
            }

            return autenticaciones;
        }

        // PUT: api/Autenticaciones/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAutenticaciones(int id, Autenticaciones autenticaciones)
        {
            if (AutenticacionesExists(autenticaciones.autenticacionId))
            {
                _context.Autenticaciones.Update(autenticaciones);
            }
            await _context.SaveChangesAsync();
            return Ok(autenticaciones);
        }

        // POST: api/Autenticaciones
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Autenticaciones>> PostAutenticaciones(Autenticaciones autenticaciones)
        {
            _context.Autenticaciones.Add(autenticaciones);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAutenticaciones", new { id = autenticaciones.autenticacionId }, autenticaciones);
        }

        // DELETE: api/Autenticaciones/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAutenticaciones(int id)
        {
            var autenticaciones = await _context.Autenticaciones.FindAsync(id);
            if (autenticaciones == null)
            {
                return NotFound();
            }

            _context.Autenticaciones.Remove(autenticaciones);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool AutenticacionesExists(int id)
        {
            return _context.Autenticaciones.Any(e => e.autenticacionId == id);
        }
    }
}
