using System.Security.Cryptography;
using System.Text;
using DoodleTech.Data;
using DoodleTech.Models;
using Microsoft.EntityFrameworkCore;

namespace DoodleTech.Services
{
    public class LoginService
    {
        private readonly ApplicationDbContext _context;
        
        public LoginService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<User> AuthenticateUserAsync(LoginViewModel model)
        {
            string hashedPassword = ComputeSha256Hash(model.Password);
            User user = await _context.Users
                .Where(u => (u.Username == model.UsernameOrEmail || u.Email == model.UsernameOrEmail) && u.Password == hashedPassword)
                .FirstOrDefaultAsync();

            return user;
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
