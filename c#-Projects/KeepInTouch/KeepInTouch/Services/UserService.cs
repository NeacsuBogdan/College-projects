using KeepInTouch.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Threading.Tasks;

public class UserService
{
    private readonly MyDbContext _dbContext;

    public UserService(MyDbContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<bool> RegisterUserAsync(User user)
    {
        var existingUser = await _dbContext.Users.FirstOrDefaultAsync(u => u.Username == user.Username);
        if (existingUser != null)
        {
            // Utilizatorul există deja în baza de date
            return false;
        }

        var newUser = new User
        {
            Username = user.Username,
            Email = user.Email,
            Password = user.Password
        };


        _dbContext.Users.Add(newUser);
        await _dbContext.SaveChangesAsync();

        return true;
    }

    public async Task<User> AuthenticateUserAsync(string username, string password)
    {
        var user = await _dbContext.Users.FirstOrDefaultAsync(u => u.Username == username && u.Password == password);
        return user;
    }
}
