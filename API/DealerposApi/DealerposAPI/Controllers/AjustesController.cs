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
    public class AjustesController : ControllerBase
    {
        private readonly Context _context;

        public AjustesController(Context context)
        {
            _context = context;
        }

        // GET: api/Ajustes
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Ajustes>>> GetAjustes()
        {
            return await _context.Ajustes.ToListAsync();
        }

        // GET: api/Ajustes/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Ajustes>> GetAjustes(int id)
        {
            var ajustes = await _context.Ajustes.FindAsync(id);

            if (ajustes == null)
            {
                return NotFound();
            }

            return ajustes;
        }

        // PUT: api/Ajustes/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAjustes(int id, Ajustes ajustes)
        {
            if (AjustesExists(ajustes.ajusteId))
            {
                _context.Ajustes.Update(ajustes);
            }
            await _context.SaveChangesAsync();
            return Ok(ajustes);
        }

        // POST: api/Ajustes
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Ajustes>> PostAjustes(Ajustes ajustes)
        {
            _context.Ajustes.Add(ajustes);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAjustes", new { id = ajustes.ajusteId }, ajustes);
        }

        // DELETE: api/Ajustes/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAjustes(int id)
        {
            var ajustes = await _context.Ajustes.FindAsync(id);
            if (ajustes == null)
            {
                return NotFound();
            }

            _context.Ajustes.Remove(ajustes);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool AjustesExists(int id)
        {
            return _context.Ajustes.Any(e => e.ajusteId == id);
        }
    }
}
