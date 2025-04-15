using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using GeografiaAPI.DAL;
using GeografiaAPI.Models;

namespace GeografiaAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CiudadesController : ControllerBase
    {
        private readonly Context _context;

        public CiudadesController(Context context)
        {
            _context = context;
        }

        // GET: api/Ciudades
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Ciudades>>> GetCiudades()
        {
            return await _context.Ciudades.ToListAsync();
        }

        // GET: api/Ciudades/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Ciudades>> GetCiudades(int id)
        {
            var ciudades = await _context.Ciudades.FindAsync(id);

            if (ciudades == null)
            {
                return NotFound();
            }

            return ciudades;
        }

        // PUT: api/Ciudades/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutCiudades(int id, Ciudades ciudades)
        {
            if (CiudadesExists(ciudades.ciudadId))
            {
                _context.Ciudades.Update(ciudades);
            }
            await _context.SaveChangesAsync();
            return Ok(ciudades);
        }

        // POST: api/Ciudades
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Ciudades>> PostCiudades(Ciudades ciudades)
        {
            _context.Ciudades.Add(ciudades);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetCiudades", new { id = ciudades.ciudadId }, ciudades);
        }

        // POST: api/Ciudades
        // Agregar varios Ciudades
        [HttpPost("bulk")]
        public async Task<ActionResult<IEnumerable<Ciudades>>> PostCiudadesBulk(IEnumerable<Ciudades> ciudadesList)
        {
            if (ciudadesList == null || !ciudadesList.Any())
            {
                return BadRequest("La lista de ciudades no puede estar vacía.");
            }

            _context.Ciudades.AddRange(ciudadesList);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetCiudades", new { id = ciudadesList.First().ciudadId }, ciudadesList);
        }


        // DELETE: api/Ciudades
        // Eliminar todos los Ciudades
        [HttpDelete]
        public async Task<IActionResult> DeleteAllCiudades()
        {
            var ciudades = await _context.Ciudades.ToListAsync();
            if (ciudades == null || !ciudades.Any())
            {
                return NotFound("No se encontraron ciudades para eliminar.");
            }

            _context.Ciudades.RemoveRange(ciudades);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        // DELETE: api/Ciudades/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCiudades(int id)
        {
            var ciudades = await _context.Ciudades.FindAsync(id);
            if (ciudades == null)
            {
                return NotFound();
            }

            _context.Ciudades.Remove(ciudades);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool CiudadesExists(int id)
        {
            return _context.Ciudades.Any(e => e.ciudadId == id);
        }
    }
}
