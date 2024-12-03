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
    public class CaracteristicasController : ControllerBase
    {
        private readonly Context _context;

        public CaracteristicasController(Context context)
        {
            _context = context;
        }

        // GET: api/Caracteristicas
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Caracteristicas>>> GetCaracteristicas()
        {
            return await _context.Caracteristicas.ToListAsync();
        }

        // GET: api/Caracteristicas/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Caracteristicas>> GetCaracteristicas(int id)
        {
            var caracteristicas = await _context.Caracteristicas.FindAsync(id);

            if (caracteristicas == null)
            {
                return NotFound();
            }

            return caracteristicas;
        }

        // PUT: api/Caracteristicas/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutCaracteristicas(int id, Caracteristicas caracteristicas)
        {
            if (CaracteristicasExists(caracteristicas.caracteristicaId))
            {
                _context.Caracteristicas.Update(caracteristicas);
            }
            await _context.SaveChangesAsync();
            return Ok(caracteristicas);
        }

        // POST: api/Caracteristicas
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Caracteristicas>> PostCaracteristicas(Caracteristicas caracteristicas)
        {
            _context.Caracteristicas.Add(caracteristicas);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetCaracteristicas", new { id = caracteristicas.caracteristicaId }, caracteristicas);
        }

        // DELETE: api/Caracteristicas/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCaracteristicas(int id)
        {
            var caracteristicas = await _context.Caracteristicas.FindAsync(id);
            if (caracteristicas == null)
            {
                return NotFound();
            }

            _context.Caracteristicas.Remove(caracteristicas);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool CaracteristicasExists(int id)
        {
            return _context.Caracteristicas.Any(e => e.caracteristicaId == id);
        }
    }
}
