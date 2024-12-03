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
    public class PaisesController : ControllerBase
    {
        private readonly Context _context;

        public PaisesController(Context context)
        {
            _context = context;
        }

        // GET: api/Paises
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Paises>>> GetPaises()
        {
            return await _context.Paises.ToListAsync();
        }

        // GET: api/Paises/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Paises>> GetPaises(int id)
        {
            var paises = await _context.Paises.FindAsync(id);

            if (paises == null)
            {
                return NotFound();
            }

            return paises;
        }

        // PUT: api/Paises/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutPaises(int id, Paises paises)
        {
            if(PaisesExists(paises.paisId))
    {
                _context.Paises.Update(paises);
            }
            await _context.SaveChangesAsync();
            return Ok(paises);
        }

        // POST: api/Paises
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Paises>> PostPaises(Paises paises)
        {
            _context.Paises.Add(paises);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetPaises", new { id = paises.paisId }, paises);
        }

        // POST: api/Paises
        // Agregar varios Paises
        [HttpPost("bulk")]
        public async Task<ActionResult<IEnumerable<Paises>>> PostPaisesBulk(IEnumerable<Paises> paisesList)
        {
            if (paisesList == null || !paisesList.Any())
            {
                return BadRequest("La lista de paises no puede estar vacía.");
            }

            _context.Paises.AddRange(paisesList);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetPaises", new { id = paisesList.First().paisId }, paisesList);
        }


        // DELETE: api/Paises
        // Eliminar todos los paises
        [HttpDelete]
        public async Task<IActionResult> DeleteAllPaises()
        {
            var paises = await _context.Paises.ToListAsync();
            if (paises == null || !paises.Any())
            {
                return NotFound("No se encontraron paises para eliminar.");
            }

            _context.Paises.RemoveRange(paises);
            await _context.SaveChangesAsync();

            return NoContent();
        }



        // DELETE: api/Paises/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePaises(int id)
        {
            var paises = await _context.Paises.FindAsync(id);
            if (paises == null)
            {
                return NotFound();
            }

            _context.Paises.Remove(paises);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool PaisesExists(int id)
        {
            return _context.Paises.Any(e => e.paisId == id);
        }
    }
}
