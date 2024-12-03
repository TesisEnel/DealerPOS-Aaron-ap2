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
    public class IconosController : ControllerBase
    {
        private readonly Context _context;

        public IconosController(Context context)
        {
            _context = context;
        }

        // GET: api/Iconos
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Iconos>>> GetIconos()
        {
            return await _context.Iconos.ToListAsync();
        }

        // GET: api/Iconos/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Iconos>> GetIconos(int id)
        {
            var iconos = await _context.Iconos.FindAsync(id);

            if (iconos == null)
            {
                return NotFound();
            }

            return iconos;
        }

        // PUT: api/Iconos/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutIconos(int id, Iconos iconos)
        {
            if (IconosExists(iconos.iconoId))
            {
                _context.Iconos.Update(iconos);
            }
            await _context.SaveChangesAsync();
            return Ok(iconos);
        }

        // POST: api/Iconos
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Iconos>> PostIconos(Iconos iconos)
        {
            _context.Iconos.Add(iconos);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetIconos", new { id = iconos.iconoId }, iconos);
        }

        // DELETE: api/Iconos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteIconos(int id)
        {
            var iconos = await _context.Iconos.FindAsync(id);
            if (iconos == null)
            {
                return NotFound();
            }

            _context.Iconos.Remove(iconos);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool IconosExists(int id)
        {
            return _context.Iconos.Any(e => e.iconoId == id);
        }
    }
}
