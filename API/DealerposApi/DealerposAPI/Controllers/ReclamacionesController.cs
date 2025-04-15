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
    public class ReclamacionesController : ControllerBase
    {
        private readonly Context _context;

        public ReclamacionesController(Context context)
        {
            _context = context;
        }

        // GET: api/Reclamaciones
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Reclamaciones>>> GetReclamaciones()
        {
            return await _context.Reclamaciones.ToListAsync();
        }

        // GET: api/Reclamaciones/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Reclamaciones>> GetReclamaciones(int id)
        {
            var reclamaciones = await _context.Reclamaciones.FindAsync(id);

            if (reclamaciones == null)
            {
                return NotFound();
            }

            return reclamaciones;
        }

        // PUT: api/Reclamaciones/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutReclamaciones(int id, Reclamaciones reclamaciones)
        {
            if (ReclamacionesExists(reclamaciones.reclamacionId))
            {
                _context.Reclamaciones.Update(reclamaciones);
            }
            await _context.SaveChangesAsync();
            return Ok(reclamaciones);
        }

        // POST: api/Reclamaciones
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Reclamaciones>> PostReclamaciones(Reclamaciones reclamaciones)
        {
            _context.Reclamaciones.Add(reclamaciones);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetReclamaciones", new { id = reclamaciones.reclamacionId }, reclamaciones);
        }

        // DELETE: api/Reclamaciones/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteReclamaciones(int id)
        {
            var reclamaciones = await _context.Reclamaciones.FindAsync(id);
            if (reclamaciones == null)
            {
                return NotFound();
            }

            _context.Reclamaciones.Remove(reclamaciones);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool ReclamacionesExists(int id)
        {
            return _context.Reclamaciones.Any(e => e.reclamacionId == id);
        }
    }
}
