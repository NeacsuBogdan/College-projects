using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using DoodleTech.Models;

namespace AspNetCoreApp1.Configurations
{
    public class CombinationConfiguration : IEntityTypeConfiguration<Combinations>
    {
        public void Configure(EntityTypeBuilder<Combinations> builder)
        {
            builder.HasOne(c => c.Element1)
                .WithMany()
                .HasForeignKey(c => c.Element1Id)
                .OnDelete(DeleteBehavior.NoAction);

            builder.HasOne(c => c.Element2)
                .WithMany()
                .HasForeignKey(c => c.Element2Id)
                .OnDelete(DeleteBehavior.NoAction);

            builder.HasOne(c => c.ResultElement)
                .WithMany()
                .HasForeignKey(c => c.ResultElementId)
                .OnDelete(DeleteBehavior.NoAction);
        }
    }
}
