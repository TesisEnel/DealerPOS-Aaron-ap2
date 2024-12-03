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
    public class EstadosController : ControllerBase
    {
        private readonly Context _context;

        public EstadosController(Context context)
        {
            _context = context;
        }

        // GET: api/Estados
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Estados>>> GetEstados()
        {
            return await _context.Estados.ToListAsync();
        }

        // GET: api/Estados/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Estados>> GetEstados(int id)
        {
            var estados = await _context.Estados.FindAsync(id);

            if (estados == null)
            {
                return NotFound();
            }

            return estados;
        }

        // PUT: api/Estados/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutEstados(int id, Estados estados)
        {
            if (EstadosExists(estados.estadoId))
            {
                _context.Estados.Update(estados);
            }
            await _context.SaveChangesAsync();
            return Ok(estados);
        }

        // POST: api/Estados
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Estados>> PostEstados(Estados estados)
        {
            _context.Estados.Add(estados);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetEstados", new { id = estados.estadoId }, estados);
        }

        // POST: api/Estados
        // Agregar varios estados
        [HttpPost("bulk")]
        public async Task<ActionResult<IEnumerable<Estados>>> PostEstadosBulk(IEnumerable<Estados> estadosList)
        {
            if (estadosList == null || !estadosList.Any())
            {
                return BadRequest("La lista de estados no puede estar vacía.");
            }

            _context.Estados.AddRange(estadosList);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetEstados", new { id = estadosList.First().estadoId }, estadosList);
        }


        // DELETE: api/Estados
        // Eliminar todos los estados
        [HttpDelete]
        public async Task<IActionResult> DeleteAllEstados()
        {
            var estados = await _context.Estados.ToListAsync();
            if (estados == null || !estados.Any())
            {
                return NotFound("No se encontraron estados para eliminar.");
            }

            _context.Estados.RemoveRange(estados);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        // DELETE: api/Estados/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteEstados(int id)
        {
            var estados = await _context.Estados.FindAsync(id);
            if (estados == null)
            {
                return NotFound();
            }

            _context.Estados.Remove(estados);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool EstadosExists(int id)
        {
            return _context.Estados.Any(e => e.estadoId == id);
        }
    }
}
