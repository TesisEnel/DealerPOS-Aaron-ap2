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
    public class FavoritosController : ControllerBase
    {
        private readonly Context _context;

        public FavoritosController(Context context)
        {
            _context = context;
        }

        // GET: api/Favoritos
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Favoritos>>> GetFavoritos()
        {
            return await _context.Favoritos.ToListAsync();
        }

        // GET: api/Favoritos/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Favoritos>> GetFavoritos(int id)
        {
            var favoritos = await _context.Favoritos.FindAsync(id);

            if (favoritos == null)
            {
                return NotFound();
            }

            return favoritos;
        }

        // PUT: api/Favoritos/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutFavoritos(int id, Favoritos favoritos)
        {
            if (FavoritosExists(favoritos.favoritoId))
            {
                _context.Favoritos.Update(favoritos);
            }
            await _context.SaveChangesAsync();
            return Ok(favoritos);
        }

        // POST: api/Favoritos
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Favoritos>> PostFavoritos(Favoritos favoritos)
        {
            _context.Favoritos.Add(favoritos);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetFavoritos", new { id = favoritos.favoritoId }, favoritos);
        }

        // DELETE: api/Favoritos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteFavoritos(int id)
        {
            var favoritos = await _context.Favoritos.FindAsync(id);
            if (favoritos == null)
            {
                return NotFound();
            }

            _context.Favoritos.Remove(favoritos);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool FavoritosExists(int id)
        {
            return _context.Favoritos.Any(e => e.favoritoId == id);
        }
    }
}
