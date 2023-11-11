using System.Security.Cryptography;
using System.Text;
using AspNetCoreApp1.Models;
using DoodleTech.Data;
using DoodleTech.Models;
using Microsoft.EntityFrameworkCore;

namespace DoodleTech.Services
{
    public class RegisterService
    {
        private readonly ApplicationDbContext _context;
        
        public RegisterService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<bool> RegisterUserAsync(RegisterViewModel model)
        {
            string lowerCaseUsername = model.Username.ToLower();
            string lowerCaseEmail = model.Email.ToLower();

            bool usernameExists = await _context.Users.AnyAsync(u => u.Username.ToLower() == lowerCaseUsername);
            bool emailExists = await _context.Users.AnyAsync(u => u.Email.ToLower() == lowerCaseEmail);
            
            if (usernameExists || emailExists)
            {
                return false;
            }

            var user = new User
            {
                Username = model.Username,
                Email = model.Email,
                Password = ComputeSha256Hash(model.Password)
            };
            _context.Users.Add(user);
            await _context.SaveChangesAsync();
            
            return true;
        }

        private static string ComputeSha256Hash(string rawData)
        {
            if (rawData == null)
            {
                throw new ArgumentNullException(nameof(rawData));
            }

            using var sha256 = SHA256.Create();
            byte[] bytes = sha256.ComputeHash(Encoding.UTF8.GetBytes(rawData));  // Această linie a fost corectată
            var builder = new StringBuilder();
            foreach (var @byte in bytes)
            {
                builder.Append(@byte.ToString("x2"));
            }
            return builder.ToString();
        }
    }
}
