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
    public class AdicionalesController : ControllerBase
    {
        private readonly Context _context;

        public AdicionalesController(Context context)
        {
            _context = context;
        }

        // GET: api/Adicionales
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Adicionales>>> GetAdicionales()
        {
            return await _context.Adicionales.ToListAsync();
        }

        // GET: api/Adicionales/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Adicionales>> GetAdicionales(int id)
        {
            var adicionales = await _context.Adicionales.FindAsync(id);

            if (adicionales == null)
            {
                return NotFound();
            }

            return adicionales;
        }

        // PUT: api/Adicionales/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAdicionales(int id, Adicionales adicionales)
        {
            if (AdicionalesExists(adicionales.adicionalId))
            {
                _context.Adicionales.Update(adicionales);
            }
            await _context.SaveChangesAsync();
            return Ok(adicionales);
        }

        // POST: api/Adicionales
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Adicionales>> PostAdicionales(Adicionales adicionales)
        {
            _context.Adicionales.Add(adicionales);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAdicionales", new { id = adicionales.adicionalId }, adicionales);
        }

        // DELETE: api/Adicionales/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAdicionales(int id)
        {
            var adicionales = await _context.Adicionales.FindAsync(id);
            if (adicionales == null)
            {
                return NotFound();
            }

            _context.Adicionales.Remove(adicionales);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool AdicionalesExists(int id)
        {
            return _context.Adicionales.Any(e => e.adicionalId == id);
        }
    }
}
