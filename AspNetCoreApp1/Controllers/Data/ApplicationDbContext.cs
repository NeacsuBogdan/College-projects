using Microsoft.EntityFrameworkCore;
using DoodleTech.Models;
using AspNetCoreApp1.Configurations;  // Make sure this namespace is correct

namespace DoodleTech.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        public DbSet<User> Users { get; set; }
        public DbSet<World> Worlds { get; set; }
        public DbSet<Elements> Elements { get; set; }
        public DbSet<Combinations> Combinations { get; set; }
        public DbSet<UserElements> UserElements { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);  // It's a good practice to call base.OnModelCreating

            modelBuilder.ApplyConfiguration(new CombinationConfiguration());

            // ... any other configurations ...
        }
    }
}
